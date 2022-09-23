package org.dblab.auction_backend.service;

import java.util.HashMap;
import java.util.List;

import org.dblab.auction_backend.domain.ConsumerMemberDTO;
import org.dblab.auction_backend.domain.FarmMemberDTO;
import org.dblab.auction_backend.domain.MemberProfileDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberService {

    // #################################################### 소비자, 농가 공통 기능 ####################################################

    public int updateMemberPassword(String checkUser, int id, String passwd);

    public int updateMemberPhoneNumber(String checkUser, int id, String phonenum);

    public int updateMemberName(String checkUser, int id, String name);

    public int updateMemberAddress(String checkUser, int id, String zipcode, String location);

    public int updateMemberProfileImage(MemberProfileDTO userProfileDTO);

    public int deleteMember(String checkUser, int id);

    // #################################################### 소비자 C ####################################################

    public HashMap<String, Object> signupConsumer(ConsumerMemberDTO consumerMember);

    // #################################################### 농가 CRUD ####################################################

    public HashMap<String, Object> signupFarmMember(FarmMemberDTO farmMemberDTO);

    public FarmMemberDTO getFarmMember(int farm_id);

    public int updateFarmMemberBank(FarmMemberDTO farmMemberDTO);

    public int updateFarmImages(FarmMemberDTO farmMemberDTO);

    public int updateFarmMemberNumber(int farm_id, String f_num);

    public int updateFarmMemberFarmName(int farm_id, String f_farm_name);

    public int updateFarmMemberExplanation(int farm_id, String f_explanation);

    public int updateFarmMemberMajorCrop(int farm_id, String f_major_crop);
    
    public int updateFarmMemberFarmImage(int farm_id, String f_img);

    // #################################################### 농가, 소비자 아이디 비번 찾기 ####################################################
    
    public int findFarmId(int f_name, int f_phonenum);


    // #################################################### 로그인, 로그아웃, 이메일 중복 확인, 휴대폰 인증번호 확인 ####################################################

    public UserDetails login(String checkUser ,String email, String password);

    public int logout(String checkUser ,String email);

    public int existEmail(String email);

    // 실제로 사용할 때 주석 풀기!!
    /*
    public String checkPhoneNumber(String phoneNumber);
    */
}
