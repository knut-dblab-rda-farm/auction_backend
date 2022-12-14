package org.dblab.auction_backend.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ProcessHandle.Info;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.dblab.auction_backend.config.JwtTokenProvider;
import org.dblab.auction_backend.domain.ConsumerMemberDTO;
import org.dblab.auction_backend.domain.FarmMemberDTO;
import org.dblab.auction_backend.domain.MemberProfileDTO;
import org.dblab.auction_backend.mapper.MemberMapper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final MemberMapper memberMapper;
    private final JwtTokenProvider jwtTokenProvider;
    String MEMBER_PROFILE_IMAGES_FOLDER_PATH= "/home/webapp_farm_auction/rda_farm/auction_backend/src/main/resources/static/member_profile_images/";
    String BANK_IMAGES_FOLDER_PATH= "/home/webapp_farm_auction/rda_farm/auction_backend/src/main/resources/static/bank_images/";
    String FARM_IMAGES_FOLDER_PATH= "/home/webapp_farm_auction/rda_farm/auction_backend/src/main/resources/static/farm_images/";
    private Integer PARTICIPATING_IN_THE_AUCTION = -1;
    private Integer ID_PASSWORD_FAILED = -2;
    private Integer SUCCESSFUL_WITHDRAWA = 1;
    private WithdrawalMember withdrawalMember = new WithdrawalMember();
    
    private Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    // #################################################### ?????????, ?????? ?????? ?????? ####################################################

    public Integer updateMemberPassword(String checkUser, Integer id, String passwd) {
        log.info("updateMemberPassword..........");

        String newPassword = passwordEncoder.encode(passwd);

        if (checkUser.equals("consumer")) {
            return memberMapper.updateConsumerMemberPassword(id, newPassword);
        } else {
            return memberMapper.updateFarmMemberPassword(id, newPassword);
        }
    }

    public Integer updateMemberPhoneNumber(String checkUser, Integer id, String phonenum) {
        log.info("updateMemberPhoneNumber..........");

        if (checkUser.equals("consumer")) {
            return memberMapper.updateConsumerMemberPhoneNumber(id, phonenum);
        } else {
            return memberMapper.updateFarmMemberPhoneNumber(id, phonenum);
        }
    }

    public Integer updateMemberName(String checkUser, Integer id, String name) {
        log.info("updateMemberName..........");

        if (checkUser.equals("consumer")) {
            return memberMapper.updateConsumerMemberName(id, name);
        } else {
            return memberMapper.updateFarmMemberName(id, name);
        }
    }

    public Integer updateMemberAddress(String checkUser, Integer id, String zipcode, String location, String c_detail_location) {
        log.info("updateMemberZipcode..........");

        if (checkUser.equals("consumer")) {
            return memberMapper.updateConsumerMemberAddress(id, zipcode, location, c_detail_location);
        } else {
            return memberMapper.updateFarmMemberAddress(id, zipcode, location);
        }
    }

    public void deleteMemberProfileImage(String memberProfile_img_name){
        File profileImageFile = new File(MEMBER_PROFILE_IMAGES_FOLDER_PATH + memberProfile_img_name + ".png");
        if (profileImageFile.exists()){
            if (profileImageFile.delete()){
                log.info(memberProfile_img_name + " ????????? ????????? ?????? ??????");
            } else {
                log.info(memberProfile_img_name + "????????? ????????? ?????? ??????...");
            }
        } else{
    		log.info("?????? ????????? ????????? ???????????? ????????????.");
    	}

    }

    public String updateMemberProfileImage(MemberProfileDTO memberProfileDTO) {
        log.info("updateMemberProfileImage..........");
        deleteMemberProfileImage(memberProfileDTO.getProfile_img());

        String profile_img = null;
        if(memberProfileDTO.getNew_profile_img() != null){
            // ????????? ????????? ????????? ??????
            profile_img = memberProfileDTO.getCheckUser() + "_" +memberProfileDTO.getId();

            try {
                memberProfileDTO.getNew_profile_img().transferTo(new File(MEMBER_PROFILE_IMAGES_FOLDER_PATH + profile_img + ".png"));
                log.info(profile_img + "????????? ????????? ????????? ?????? ??????");
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // ????????? ?????? DB??? ??????
        if (memberProfileDTO.getCheckUser().equals("consumer")) {
            memberMapper.updateConsumerMemberProfileImage(memberProfileDTO.getId(), profile_img);
        } else {
            memberMapper.updateFarmMemberProfileImage(memberProfileDTO.getId(), profile_img);
        }
        return profile_img;
    }

    public Integer deleteMember(String checkUser, Integer id, String email, String password){
        log.info("deleteMember....." + checkUser + "    " + id);
        log.info(withdrawalMember.getWithdrawalMember());
        // ?????? ?????? ?????? ?????? ?????????
        Integer consumer_id = null;
        Integer farm_id = null;
        if(checkUser.equals("consumer_id")){
            consumer_id = id;
        } else{
            farm_id = id;
        }
        if(memberMapper.existAuctionBiddingUser(consumer_id, farm_id) == 1) return PARTICIPATING_IN_THE_AUCTION;

        // ????????? ??????
        ConsumerMemberDTO consumerMemberDTO = null;
        FarmMemberDTO farmMemberDTO = null;
        if (checkUser.equals("consumer")){
            consumerMemberDTO = memberMapper.getConsumerMember(email);
            if(consumerMemberDTO == null || !passwordEncoder.matches(password, consumerMemberDTO.getC_passwd())) {
                log.info("????????? ?????????, ??????????????? ???????????????.");
                return ID_PASSWORD_FAILED;
            }
            deleteMemberProfileImage(consumerMemberDTO.getC_profile_img());
            memberMapper.deleteConsumerMemberWish(consumerMemberDTO.getConsumer_id());
            memberMapper.deleteConsumerMember(consumerMemberDTO.getConsumer_id(), withdrawalMember.getWithdrawalMember());
        } else {
            farmMemberDTO = memberMapper.getFarmMemberAuth(email);
            if(farmMemberDTO == null || !passwordEncoder.matches(password, farmMemberDTO.getF_passwd())) {
                log.info("?????? ?????????, ??????????????? ???????????????.");
                return ID_PASSWORD_FAILED;
            }
            deleteMemberProfileImage(farmMemberDTO.getF_profile_img());
            deleteFarmImages(farmMemberDTO.getF_img());
            deleteFarmBankImage(farmMemberDTO.getF_bank_img());
            memberMapper.deleteFarmMember(farmMemberDTO.getFarm_id(), withdrawalMember.getWithdrawalMember());
        }
        withdrawalMember.setWithdrawalMember();
        log.info(withdrawalMember.getWithdrawalMember());
        return SUCCESSFUL_WITHDRAWA;
    }

    // #################################################### ????????? C ####################################################

    public UserDetails signupConsumer(ConsumerMemberDTO consumerMemberDTO) {
        log.info("signupConsumer..........");
        consumerMemberDTO.setC_passwd(passwordEncoder.encode(consumerMemberDTO.getC_passwd()));
        consumerMemberDTO.setToken(jwtTokenProvider.createToken(consumerMemberDTO.getC_email(), "consumer"));
        consumerMemberDTO = memberMapper.signupConsumer(consumerMemberDTO);
        log.info(consumerMemberDTO.toString());
        return consumerMemberDTO;
    }

    // #################################################### ?????? CRUD ####################################################

    public UserDetails signupFarmMember(FarmMemberDTO farmMemberDTO) {
        log.info("signupFarmMember..........");
        farmMemberDTO.setF_passwd(passwordEncoder.encode(farmMemberDTO.getF_passwd()));
        farmMemberDTO.setToken(jwtTokenProvider.createToken(farmMemberDTO.getF_email(), "farm"));
        farmMemberDTO = memberMapper.signupFarmMember(farmMemberDTO);
        log.info(farmMemberDTO.toString());
        return farmMemberDTO;
    }

    public Map<String, Object> getFarmMember(Integer farm_id){
        return memberMapper.getFarmMember(farm_id);
    }
    
    public void deleteFarmBankImage(String f_bank_img_name){
        // ?????? ???????????? ????????? ??????
        File farmBankImageFile = new File(BANK_IMAGES_FOLDER_PATH + f_bank_img_name+ ".png");

        if (farmBankImageFile.exists()){
            if (farmBankImageFile.delete()){
                log.info(f_bank_img_name + " ???????????? ????????? ?????? ??????");
            } else {
                log.info(f_bank_img_name + " ???????????? ????????? ?????? ??????...");
            }
        } else{
    		log.info("???????????? ????????? ????????? ???????????? ????????????.");
    	}
    }

    public String updateFarmMemberBank(FarmMemberDTO farmMemberDTO) {
        log.info("updateFarmMemberBank..........");

        deleteFarmBankImage(farmMemberDTO.getF_bank_img());
        
        // ????????? ???????????? ????????? ??????
        farmMemberDTO.setF_bank_img(farmMemberDTO.getFarm_id() + "_" + farmMemberDTO.getF_bank());
        try {
            farmMemberDTO.getNew_bank_img().transferTo(new File(BANK_IMAGES_FOLDER_PATH + farmMemberDTO.getF_bank_img() + ".png"));
            log.info(farmMemberDTO.getF_bank_img() + " ????????? ???????????? ????????? ?????? ??????");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ????????? ?????? DB??? ??????
        memberMapper.updateFarmMemberBank(farmMemberDTO);
        return farmMemberDTO.getF_bank_img();
    }

    public void deleteFarmImages(String f_img){
        // ?????? ?????? ????????? ??????
        if(f_img != null){
            try {
                Integer f_img_length = Integer.parseInt(f_img.substring(f_img.indexOf(")")+1));
                f_img = f_img.substring(0, f_img.indexOf("("));

                for(Integer i=0; i < f_img_length; i++){
                    File farmImageFile = new File(FARM_IMAGES_FOLDER_PATH + f_img + "("+i+")" +f_img_length+ ".png");// ?????? ??????
                    log.info(farmImageFile.toString());
                    if (farmImageFile.exists()){
                        if (farmImageFile.delete()){
                            log.info(f_img + " ?????? ????????? ?????? ??????");
                        } else {
                            log.info(f_img + " ?????? ????????? ?????? ??????...");
                        }
                    }else{
                        log.info("?????? ????????? ????????? ???????????? ????????????.");
                    }
                } 
            } catch (Exception e) {
                log.info(e.toString());
                log.info("????????? ????????? ???????????????!");
            }
        }

    }

    public String updateFarmImages(FarmMemberDTO farmMemberDTO) {
        log.info("updateFarmImages..........");

        deleteFarmImages(farmMemberDTO.getF_img());
        
        
        //?????? ?????????????????? ???????????? ??????
        int numberOfFarmImg = farmMemberDTO.getFarm_img_files().size();
        String f_img_name = farmMemberDTO.getFarm_id() + "," + LocalDateTime.now().toString().substring(0, 19);
        
        // ????????? ???????????? ????????? ?????? farm_id_??????_(0)??????
        log.info(FARM_IMAGES_FOLDER_PATH + farmMemberDTO.getF_img());
        try {
            for(int i=0; i< numberOfFarmImg;i++){
                farmMemberDTO.getFarm_img_files().get(i).transferTo(new File(FARM_IMAGES_FOLDER_PATH + f_img_name + "("+i+")"+ numberOfFarmImg+ ".png"));
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        farmMemberDTO.setF_img(f_img_name + "(0)" + numberOfFarmImg);
        log.info(farmMemberDTO.getF_img() + " ????????? ?????? ????????? ?????? ??????");
        

        // ????????? ?????? DB??? ??????
        memberMapper.updateFarmImages(farmMemberDTO);
        return farmMemberDTO.getF_img();
    }

    public Integer updateFarmMemberNumber(Integer farm_id, String f_num) {
        log.info("updateFarmMemberNum..........");

        return memberMapper.updateFarmMemberNumber(farm_id, f_num);
    }

    public Integer updateFarmMemberFarmName(Integer farm_id, String f_farm_name) {
        log.info("updateFarmMemberFarmName..........");

        return memberMapper.updateFarmMemberFarmName(farm_id, f_farm_name);
    }

    public Integer updateFarmMemberExplanation(Integer farm_id, String f_explanation) {
        log.info("updateFarmMemberExplanation..........");

        return memberMapper.updateFarmMemberExplanation(farm_id, f_explanation);
    }

    public Integer updateFarmMemberMajorCrop(Integer farm_id, String f_major_crop) {
        log.info("updateFarmMemberMajorCrop..........");

        return memberMapper.updateFarmMemberMajorCrop(farm_id, f_major_crop);
    }

    // #################################################### ??????, ????????? ????????? ?????? ?????? ####################################################
    
    public String findEmail(String checkUser, String name, String phonenum){
        log.info("findEmail................" + checkUser + "_" + name + "_" + phonenum);
        return checkUser.equals("consumer") ?  memberMapper.findConsumerEmail(name, phonenum) : memberMapper.findFarmEmail(name, phonenum);
    }

    public Map<String, Object> findPassword(String checkUser, String name, String email, String phonenum){
        log.info("findId................");
        // String phoneAuthNumber =  checkPhoneNumber(phonenum);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("phoneAuthNumber", checkPhoneNumber(phonenum));
        if(!checkUser.equals("signup")){
            resultMap.put("id", checkUser.equals("consumer") ?  memberMapper.findConsumerId(name, email, phonenum) : memberMapper.findFarmId(name, email, phonenum));
        }
        log.info("findId................" + resultMap.toString());
        return resultMap;
    }


    // #################################################### ?????????, ????????????, ????????? ?????? ?????? ####################################################

    public UserDetails login(String checkUser ,String email, String password) {
        log.info("login..........");
        ConsumerMemberDTO consumerMember = null;
        FarmMemberDTO farmMember = null;
        String token = null;

        log.info("checkUser: " + checkUser);
        
        if (checkUser.equals("consumer")){
            consumerMember = memberMapper.getConsumerMember(email);
            if(consumerMember == null || !passwordEncoder.matches(password, consumerMember.getC_passwd())) {
                log.info("??????????????? ???????????????.");
                log.info("????????????!");
                return null;
            } else if (consumerMember.getToken() != null){

                log.info("?????? ??????");
                memberMapper.setNullConsumerToken(consumerMember.getC_email());

                ConsumerMemberDTO duplicateLoginObject = new ConsumerMemberDTO();
                duplicateLoginObject.setAuthority("ROLE_USER");
                return duplicateLoginObject;
            }
            log.info(consumerMember.toString());

            // ????????? ?????? ????????????
            token = jwtTokenProvider.createToken(email, checkUser);
            memberMapper.setConsumerToken(email, token);

            consumerMember.setToken(token);
            consumerMember.setC_passwd("");

            log.info(token);
            return consumerMember;
        }

        farmMember = memberMapper.getFarmMemberAuth(email);
        if(farmMember == null || !passwordEncoder.matches(password, farmMember.getF_passwd())) {
            log.info("????????????!");
            return null;
        } else if (farmMember.getToken() != null){
            log.info("?????? ??????");
            memberMapper.setNullFarmToken(farmMember.getF_email());

            ConsumerMemberDTO duplicateLoginObject = new ConsumerMemberDTO();
            duplicateLoginObject.setAuthority("ROLE_USER");
            return duplicateLoginObject;
        }
        log.info(farmMember.toString());

        // ????????? ?????? ????????????
        token = jwtTokenProvider.createToken(email, checkUser);
        memberMapper.setFarmToken(email, token);
        farmMember.setToken(token);
        farmMember.setF_passwd("");

        log.info(token);

        return farmMember;
    }

    @Override
    public Integer logout(String checkUser, String email) {
        log.info("logout.........." + checkUser + "   " + email);
        Integer result;
        if (checkUser.equals("consumer")) {
            result = memberMapper.setNullConsumerToken(email);
        } else {
            result = memberMapper.setNullFarmToken(email);
        }
        return result;
    }

    public Integer existEmail(String email) {
        Integer result = memberMapper.existEmail(email);
        log.info("result: "+ result);
        return result;
    }

    // ????????? ????????? ??? ?????? ??????!!
    
    public String checkPhoneNumber(String phoneNumber) {

        Random rand  = new Random();
        String certificationNumber = "";

        for(int i=0; i<4; i++) certificationNumber += Integer.toString(rand.nextInt(10));
        log.info("checkPhoneNumber.........." + certificationNumber);

        return certificationNumber;
    }

    
}
