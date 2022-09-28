package org.dblab.auction_backend.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {

    private JwtTokenProvider jwtTokenProvider; // JWT 토큰을 생성 및 검증 모듈 클래스

    // Jwt Provider 주입
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Request로 들어오는 Jwt Token의 유효성을 검증 (jwtTokenProvider.validateToken)하는
    // filter를 filterChain에 등록한다.
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        HttpServletResponse httpSeveletResponse = (HttpServletResponse)response;

        System.out.println("token : " + token);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {   // validateToken : Jwt 토큰의 유효성 + 만료일자 확인
                Authentication auth = jwtTokenProvider.getAuthentication(token);   // getAuthentication : Jwt 토큰으로 인증 정보 조회
                SecurityContextHolder.getContext().setAuthentication(auth);
                httpSeveletResponse.addHeader("TOKEN", "token");
            }
        } catch(UsernameNotFoundException e){
            System.out.println("UsernameNotFoundException 발생");
            httpSeveletResponse.addHeader("TOKEN", "not exist token");
            filterChain.doFilter(request, httpSeveletResponse);
        } catch (Exception e) {
            System.out.println("Exception 발생");
        }
        filterChain.doFilter(request, httpSeveletResponse);
        // if (token == null) {
        //     System.out.println("toekn nulladadssda");
        //     // HttpServletResponse redirectResponse = (HttpServletResponse)response;
        //     // redirectResponse.sendRedirect("http://localhost:8082/login");
        //     // redirectResponse.sendRedirect("/");
        //     filterChain.doFilter(request, redirectResponse);
        // } 
        // else {
        //     filterChain.doFilter(request, response);
        // }
        
    }
}