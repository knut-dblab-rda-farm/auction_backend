package org.dblab.auction_backend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import org.dblab.auction_backend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider { // JWT 토큰을 생성 및 검증 모듈

    private String secretKey = "asdagsd^&445asasdfasdsdf&(@#@DSFS!DSFSD#%@%@$!@#$#!@)";

    private long tokenValidMillisecond = 1000L * 60 * 60 * 3; // 3시간 토큰 유효

    // private final UserDetailsService userDetailsService;
    private final CustomUserDetailsService customUserDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // Jwt 토큰 생성
    public String createToken(String email, String checkUser) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("checkUser", checkUser);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 데이터
                .setIssuedAt(now) // 토큰 발행일자
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond)) // 토큰 유효시간 설정
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, 암호키
                .compact();
    }

    // Jwt 토큰으로 인증 정보 조회
    public Authentication getAuthentication(String token) {

        UserDetails userDetails;

        String emailCheckUser = this.getUserInfo(token);
        String[] split = emailCheckUser.split(":");
        
        if (split.length < 2)
            throw new UsernameNotFoundException("User not authorized.");
        
        // split[0]은 eamil, split[1]는 checkUser입니다.
        if (split[1].equals("consumer")){
            userDetails = customUserDetailsService.loadConsumerByEmail(split[0], token);
            System.out.println(userDetails.toString());
        } else {
            userDetails = customUserDetailsService.loadFarmByEmail(split[0], token);
            System.out.println(userDetails.toString());
        }

        // UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserInfo(token));

        System.out.println("getAuthentication : " + userDetails.toString());
        System.out.println("getAuthorities : " + userDetails.getAuthorities());
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        System.out.println(auth.toString());
        return auth;
    }

    // Jwt 토큰에서 회원 구별 정보 추출
    public String getUserInfo(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        System.out.println("getUserInfo1 : " + claims.getSubject());
        System.out.println("getUserInfo2 : " + claims.toString());
        System.out.println("getUserInfo3 : " + claims.get("checkUser"));
        
        // ex. test@naver.com:소비자
        return claims.getSubject() + ":" + claims.get("checkUser");
    }

    // Request의 Header에서 token 파싱 : "TOKEN: jwt토큰"
    public String resolveToken(HttpServletRequest req) {
        System.out.println(req.toString());
        System.out.println("resolveToken, TOKEN : " + req.getHeader("TOKEN"));
        return req.getHeader("TOKEN");
    }

    // Jwt 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            System.out.println("validateToken : " + !claims.getBody().getExpiration().before(new Date()));
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            System.out.println("validateToken : " + false);
            return false;
        }
    }
}