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

    public int updateMemberProfileImage(String checkUser, MemberProfileDTO userProfileDTO);

    
    // #################################################### 소비자 CRUD ####################################################

    public HashMap<String, Object> signupConsumer(ConsumerMemberDTO consumerMember);

	public ConsumerMemberDTO getConsumerMember(String c_email);

    public int updateConsumerMember(ConsumerMemberDTO consumerMember);

    public int deleteConsumerMember(int consumer_id);


    // #################################################### 농가 CRUD ####################################################

    public HashMap<String, Object> signupFarmMember(FarmMemberDTO farmMemberDTO);

    public int updateFarmMember(FarmMemberDTO farmMemberDTO);

    public int updateFarmMemberBank(int farm_id, String f_bank, String f_bank_name, int f_bank_num);

    public int updateFarmMemberNum(int farm_id, String f_num);

    public int updateFarmMemberFarmName(int farm_id, String f_farm_name);

    public int updateFarmMemberExplanation(int farm_id, String f_explanation);

    public int updateFarmMemberMajorCrop(int farm_id, String f_major_crop);
    
    public int updateFarmMemberFarmImage(int farm_id, String f_img);
    
    public int deleteFarmMember(int farm_id);

    public List<ConsumerMemberDTO> getAllConsumerMember();

    // #################################################### 로그인, 로그아웃, 이메일 중복 확인, 휴대폰 인증번호 확인 ####################################################

    public UserDetails login(String checkUser ,String email, String password);

    public int logout(String checkUser ,String email);

    public int existEmail(String email);

    public String checkPhoneNumber(String phoneNumber);

}
