package org.dblab.auction_backend.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.dblab.auction_backend.domain.ConsumerMemberDTO;
import org.dblab.auction_backend.domain.FarmMemberDTO;

public interface MemberMapper {

    // #################################################### 소비자 CRUD ####################################################

    public ConsumerMemberDTO signupConsumer(ConsumerMemberDTO consumerMember);

    public ConsumerMemberDTO getConsumerMember(@Param("c_email") String c_email);

    public Integer updateConsumerMemberPassword(@Param("consumer_id") Integer consumer_id, @Param("c_passwd") String c_passwd);

    public Integer updateConsumerMemberPhoneNumber(@Param("consumer_id") Integer consumer_id, @Param("c_phonenum") String c_phonenum);

    public Integer updateConsumerMemberName(@Param("consumer_id") Integer consumer_id, @Param("c_name") String c_name);

    public Integer updateConsumerMemberAddress(@Param("consumer_id") Integer consumer_id, @Param("c_zipcode") String c_zipcode, @Param("c_location") String c_location, @Param("c_detail_location") String c_detail_location);

    public Integer updateConsumerMemberProfileImage(@Param("consumer_id") Integer consumer_id, @Param("c_profile_img") String c_profile_img);

    public Integer setConsumerToken(@Param("c_email") String c_email, @Param("token") String token);

    public Integer setNullConsumerToken(@Param("c_email") String c_email);

    public Integer deleteConsumerMemberWish(@Param("consumer_id") Integer consumer_id);

    public Integer deleteConsumerMember(@Param("consumer_id") Integer consumer_id, @Param("withdrawal_member") String withdrawal_member);
    
    // #################################################### 농가 CRUD ####################################################
    
    public FarmMemberDTO signupFarmMember(FarmMemberDTO farmMemberDTO);

    public Map<String, Object> getFarmMember(@Param("farm_id") Integer farm_id);

    public FarmMemberDTO getFarmMemberAuth(@Param("f_email") String f_email);

    public Integer updateFarmMemberPassword(@Param("farm_id") Integer farm_id, @Param("f_passwd") String f_passwd);

    public Integer updateFarmMemberPhoneNumber(@Param("farm_id") Integer farm_id, @Param("f_phonenum") String f_phonenum);

    public Integer updateFarmMemberName(@Param("farm_id") Integer farm_id, @Param("f_name") String f_name);

    public Integer updateFarmMemberAddress(@Param("farm_id") Integer farm_id, @Param("f_zipcode") String f_zipcode, @Param("f_location") String f_location);

    public Integer updateFarmMemberProfileImage(@Param("farm_id") Integer farm_id, @Param("f_profile_img") String f_profile_img);

    public Integer updateFarmMemberBank(FarmMemberDTO farmMemberDTO);

    public Integer updateFarmImages(FarmMemberDTO farmMemberDTO);

    public Integer updateFarmMemberNumber(@Param("farm_id") Integer farm_id, @Param("f_num") String f_num);

    public Integer updateFarmMemberFarmName(@Param("farm_id") Integer farm_id, @Param("f_farm_name") String f_farm_name);

    public Integer updateFarmMemberExplanation(@Param("farm_id") Integer farm_id, @Param("f_explanation") String f_explanation);

    public Integer updateFarmMemberMajorCrop(@Param("farm_id") Integer farm_id, @Param("f_major_crop") String f_major_crop);

    // ------------------------

    public Integer deleteFarmMember(@Param("farm_id") Integer farm_id, @Param("withdrawal_member") String withdrawal_member);

    public Integer setFarmToken(@Param("f_email") String f_email, @Param("token") String token);

    public Integer setNullFarmToken(@Param("f_email") String f_email);

    public String findFarmEmail(@Param("f_name") String f_name, @Param("f_phonenum") String f_phonenum);

    public String findConsumerEmail(@Param("c_name") String c_name, @Param("c_phonenum") String c_phonenum);

    public Integer findFarmId(@Param("f_name") String f_name,  @Param("f_email") String f_email, @Param("f_phonenum") String f_phonenum);

    public Integer findConsumerId(@Param("c_name") String c_name,  @Param("c_email") String c_email, @Param("c_phonenum") String c_phonenum);

    // #################################################### 로그인, 이메일 중복 확인, 현재 경매 참여 중 여부 #################################################### 

    public ConsumerMemberDTO loginConsumerMember(@Param("email") String email, @Param("password") String password);

    public FarmMemberDTO loginFarmMember(@Param("email") String email, @Param("password") String password);

    public Integer existEmail(@Param("email") String email);

    public Integer existAuctionBiddingUser(@Param("consumer_id") Integer consumer_id, @Param("farm_id") Integer farm_id);
}