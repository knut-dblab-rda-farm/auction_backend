package org.dblab.auction_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.dblab.auction_backend.domain.ConsumerMemberDTO;
import org.dblab.auction_backend.domain.FarmMemberDTO;

public interface MemberMapper {

    // #################################################### 소비자 CRUD ####################################################

    public int signupConsumer(ConsumerMemberDTO consumerMember);

    public ConsumerMemberDTO getConsumerMember(@Param("c_email") String c_email);

    public int updateConsumerMemberPassword(@Param("consumer_id") int consumer_id, @Param("c_passwd") String c_passwd);

    public int updateConsumerMemberPhoneNumber(@Param("consumer_id") int consumer_id, @Param("c_phonenum") String c_phonenum);

    public int updateConsumerMemberName(@Param("consumer_id") int consumer_id, @Param("c_name") String c_name);

    public int updateConsumerMemberAddress(@Param("consumer_id") int consumer_id, @Param("c_zipcode") String c_zipcode, @Param("c_location") String c_location);

    public int updateConsumerMemberProfileImage(@Param("consumer_id") int consumer_id, @Param("c_profile_img") String c_profile_img);

    public int deleteConsumerMember(@Param("consumer_id") int consumer_id);

    public int setConsumerToken(@Param("c_email") String c_email, @Param("token") String token);

    public int setNullConsumerToken(@Param("c_email") String c_email);


    // #################################################### 농가 CRUD ####################################################
    
    public int signupFarmMember(FarmMemberDTO farmMemberDTO);

    public FarmMemberDTO getFarmMember(@Param("f_email") String f_email);

    public int updateFarmMemberPassword(@Param("farm_id") int farm_id, @Param("f_passwd") String f_passwd);

    public int updateFarmMemberPhoneNumber(@Param("farm_id") int farm_id, @Param("f_phonenum") String f_phonenum);

    public int updateFarmMemberName(@Param("farm_id") int farm_id, @Param("f_name") String f_name);

    public int updateFarmMemberAddress(@Param("farm_id") int farm_id, @Param("f_zipcode") String f_zipcode, @Param("f_location") String f_location);

    public int updateFarmMemberProfileImage(@Param("farm_id") int farm_id, @Param("f_profile_img") String f_profile_img);

    public int updateFarmMemberBank(@Param("farm_id") int farm_id, @Param("f_bank") String f_bank, @Param("f_bank_name") String f_bank_name, @Param("f_bank_num") int f_bank_num);

    public int updateFarmMemberNumber(@Param("farm_id") int farm_id, @Param("f_num") String f_num);

    public int updateFarmMemberFarmName(@Param("farm_id") int farm_id, @Param("f_farm_name") String f_farm_name);

    public int updateFarmMemberExplanation(@Param("farm_id") int farm_id, @Param("f_explanation") String f_explanation);

    public int updateFarmMemberMajorCrop(@Param("farm_id") int farm_id, @Param("f_major_crop") String f_major_crop);
    
    public int updateFarmMemberFarmImage(@Param("farm_id") int farm_id, @Param("f_img") String f_img);

    // -------------------

    public int deleteFarmMember(@Param("farm_id") int farm_id);

    public int setFarmToken(@Param("f_email") String f_email, @Param("token") String token);

    public int setNullFarmToken(@Param("f_email") String f_email);


    // #################################################### 로그인, 이메일 중복 확인 #################################################### 

    public ConsumerMemberDTO loginConsumerMember(@Param("email") String email, @Param("password") String password);

    public FarmMemberDTO loginFarmMember(@Param("email") String email, @Param("password") String password);

    public int existEmail(@Param("email") String email);
}