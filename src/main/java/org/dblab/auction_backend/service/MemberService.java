package org.dblab.auction_backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dblab.auction_backend.domain.ConsumerMemberDTO;
import org.dblab.auction_backend.domain.FarmMemberDTO;
import org.dblab.auction_backend.domain.MemberProfileDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberService {

    // #################################################### 소비자, 농가 공통 기능 ####################################################

    public Integer updateMemberPassword(String checkUser, Integer id, String passwd);

    public Integer updateMemberPhoneNumber(String checkUser, Integer id, String phonenum);

    public Integer updateMemberName(String checkUser, Integer id, String name);

    public Integer updateMemberAddress(String checkUser, Integer id, String zipcode, String location, String c_detail_location);

    public String updateMemberProfileImage(MemberProfileDTO userProfileDTO);

    public Integer deleteMember(String checkUser, Integer id, String email, String password);

    // #################################################### 소비자 C ####################################################

    public UserDetails signupConsumer(ConsumerMemberDTO consumerMemberDTO);

    // #################################################### 농가 CRUD ####################################################

    public UserDetails signupFarmMember(FarmMemberDTO farmMemberDTO);

    public Map<String, Object> getFarmMember(Integer farm_id);

    public String updateFarmMemberBank(FarmMemberDTO farmMemberDTO);

    public String updateFarmImages(FarmMemberDTO farmMemberDTO);

    public Integer updateFarmMemberNumber(Integer farm_id, String f_num);

    public Integer updateFarmMemberFarmName(Integer farm_id, String f_farm_name);

    public Integer updateFarmMemberExplanation(Integer farm_id, String f_explanation);

    public Integer updateFarmMemberMajorCrop(Integer farm_id, String f_major_crop);

    // #################################################### 농가, 소비자 아이디 비번 찾기 ####################################################
    
    public String findEmail(String checkUser, String name, String phonenum); //아이디가 이메일이라서 이메일 반환해주는

    public Map<String, Object> findPassword(String checkUser ,String name, String email, String phonenum);  //비밀번호 찾기 전 아이디 검증 


    // #################################################### 로그인, 로그아웃, 이메일 중복 확인, 휴대폰 인증번호 확인 ####################################################

    public UserDetails login(String checkUser ,String email, String password);

    public Integer logout(String checkUser ,String email);

    public Integer existEmail(String email);

    // 실제로 사용할 때 주석 풀기!!
    /*
    public String checkPhoneNumber(String phoneNumber);
    */
}
