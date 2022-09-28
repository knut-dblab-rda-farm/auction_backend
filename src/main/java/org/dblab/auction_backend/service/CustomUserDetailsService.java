package org.dblab.auction_backend.service;

import org.dblab.auction_backend.domain.ConsumerMemberDTO;
import org.dblab.auction_backend.domain.FarmMemberDTO;
import org.dblab.auction_backend.mapper.MemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    private Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    public ConsumerMemberDTO loadConsumerByEmail(String email, String token) throws UsernameNotFoundException {

        ConsumerMemberDTO consumerMemberDTO = null;
        consumerMemberDTO = memberMapper.getConsumerMember(email);
        System.out.println(token);
        System.out.println(consumerMemberDTO.getToken());
        System.out.println(consumerMemberDTO.toString());
        if (consumerMemberDTO == null || !token.equals(consumerMemberDTO.getToken())){
            System.out.println("토큰이 같지 않음!");
            throw new UsernameNotFoundException("User not authorized.");
        }
        System.out.println("consumerMemberDTO.getToken(): " + consumerMemberDTO.getToken());

        return consumerMemberDTO;
    }

    public FarmMemberDTO loadFarmByEmail(String email, String token) throws UsernameNotFoundException {

        FarmMemberDTO farmMemberDTO = null;
        farmMemberDTO = memberMapper.getFarmMemberAuth(email);
        
        if (farmMemberDTO == null || !token.equals(farmMemberDTO.getToken())){
            System.out.println("토큰이 같지 않음!");
            throw new UsernameNotFoundException("User not authorized.");
        }

        return farmMemberDTO;
    }
   
    @Override
    public UserDetails loadUserByUsername(String emailCheckUser) throws UsernameNotFoundException {
        log.info("######################"+ emailCheckUser);

        String[] split = emailCheckUser.split(":");
        
        if (split.length < 2)
            throw new UsernameNotFoundException("User not authorized.");
        
        String email = split[0];
        String checkUser = split[1];

        UserDetails user = null;

        if (checkUser.equals("consumer")){
            user = memberMapper.getConsumerMember(email);
            System.out.println(user.toString());
        } else {
            user = memberMapper.getFarmMemberAuth(email);
            System.out.println(user.toString());
        }
        if (user == null)
            throw new UsernameNotFoundException("User not authorized.");
        
        return user;
    }
    
}
