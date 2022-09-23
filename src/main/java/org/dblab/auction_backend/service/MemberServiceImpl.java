package org.dblab.auction_backend.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
    String MEMBER_PROFILE_IMAGES_FOLDER_PATH= "../../../../resources/static/member_profile_images/";
    String BANK_IMAGES_FOLDER_PATH= "../../../../resources/static/bank_images/";
    String FARM_IMAGES_FOLDER_PATH= "../../../../resources/static/farm_images/";
    
    private Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    // #################################################### 소비자, 농가 공통 기능 ####################################################

    public int updateMemberPassword(String checkUser, int id, String passwd) {
        log.info("updateMemberPassword..........");

        String newPassword = passwordEncoder.encode(passwd);

        if (checkUser.equals("consumer")) {
            return memberMapper.updateConsumerMemberPassword(id, newPassword);
        } else {
            return memberMapper.updateFarmMemberPassword(id, newPassword);
        }
    }

    public int updateMemberPhoneNumber(String checkUser, int id, String phonenum) {
        log.info("updateMemberPhoneNumber..........");

        if (checkUser.equals("consumer")) {
            return memberMapper.updateConsumerMemberPhoneNumber(id, phonenum);
        } else {
            return memberMapper.updateFarmMemberPhoneNumber(id, phonenum);
        }
    }

    public int updateMemberName(String checkUser, int id, String name) {
        log.info("updateMemberName..........");

        if (checkUser.equals("consumer")) {
            return memberMapper.updateConsumerMemberName(id, name);
        } else {
            return memberMapper.updateFarmMemberName(id, name);
        }
    }

    public int updateMemberAddress(String checkUser, int id, String zipcode, String location) {
        log.info("updateMemberZipcode..........");

        if (checkUser.equals("consumer")) {
            return memberMapper.updateConsumerMemberAddress(id, zipcode, location);
        } else {
            return memberMapper.updateFarmMemberAddress(id, zipcode, location);
        }
    }

    public int updateMemberProfileImage(MemberProfileDTO memberProfileDTO) {
        log.info("updateMemberPassword..........");
        
        // 이전 이미지 삭제
        File profileImageFile = new File(MEMBER_PROFILE_IMAGES_FOLDER_PATH + memberProfileDTO.getProfile_img());

        if (profileImageFile.exists()){
            if (profileImageFile.delete()){
                System.out.println(memberProfileDTO.getProfile_img() + " 프로필 이미지 삭제 성공");
            } else {
                System.out.println(memberProfileDTO.getProfile_img() + "프로필 이미지 삭제 실패...");
            }
        } else{
    		System.out.println("회원 프로필 파일이 존재하지 않습니다.");
    	}

        // 새로운 프로필 이미지 생성
        String profile_img = memberProfileDTO.getCheckUser() + "_" +memberProfileDTO.getId() + ".png";

        try {
            memberProfileDTO.getNew_profile_img().transferTo(new File(MEMBER_PROFILE_IMAGES_FOLDER_PATH + profile_img));
            System.out.println(profile_img + "새로운 프로필 이미지 저장 완료");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 이미지 이름 DB에 저장
        if (memberProfileDTO.getCheckUser().equals("consumer")) {
            return memberMapper.updateConsumerMemberProfileImage(memberProfileDTO.getId(), profile_img);
        } else {
            return memberMapper.updateFarmMemberProfileImage(memberProfileDTO.getId(), profile_img);
        }
    }

    public int deleteMember(String checkUser, int id){
        // 관련 경매 및 프로필 삭제 코드
        return checkUser.equals("consumer") ?  memberMapper.deleteConsumerMember(id) : memberMapper.deleteFarmMember(id);
    }

    // #################################################### 소비자 C ####################################################

    public HashMap<String, Object> signupConsumer(ConsumerMemberDTO consumerMember) {
        log.info("signupConsumer..........");

        HashMap<String, Object> token = null;

        consumerMember.setC_passwd(passwordEncoder.encode(consumerMember.getC_passwd()));
        log.info(consumerMember.getC_passwd());

        if (memberMapper.signupConsumer(consumerMember) == 1){
            token = new HashMap<String,Object>();
            String tokenString = jwtTokenProvider.createToken(consumerMember.getC_email(), "consumer");

            System.out.println(tokenString);
            token.put("token", tokenString);
        }
        return token;
    }

    // #################################################### 농가 CRUD ####################################################

    public HashMap<String, Object> signupFarmMember(FarmMemberDTO farmMemberDTO) {
        log.info("signupFarmMember..........");

        HashMap<String, Object> token = null;

        farmMemberDTO.setF_passwd(passwordEncoder.encode(farmMemberDTO.getF_passwd()));

        if (memberMapper.signupFarmMember(farmMemberDTO) == 1){
            token = new HashMap<String,Object>();

            // String tokenString = jwtManager.generateToken(farmMemberDTO.getF_email());
            String tokenString = jwtTokenProvider.createToken(farmMemberDTO.getF_email(), "farm");
            System.out.println(tokenString);
            token.put("token", tokenString);
        }
        return token;
    }

    public FarmMemberDTO getFarmMember(int farm_id){
        return memberMapper.getFarmMember(farm_id);
    }
    
    public int updateFarmMemberBank(FarmMemberDTO farmMemberDTO) {
        log.info("updateFarmMemberBank..........");

        // 이전 통장사본 이미지 삭제
        File farmBankImageFile = new File(BANK_IMAGES_FOLDER_PATH + farmMemberDTO.getF_bank_img());

        if (farmBankImageFile.exists()){
            if (farmBankImageFile.delete()){
                System.out.println(farmMemberDTO.getF_bank_img() + " 통장사본 이미지 삭제 성공");
            } else {
                System.out.println(farmMemberDTO.getF_bank_img() + " 통장사본 이미지 삭제 실패...");
            }
        } else{
    		System.out.println("회원 프로필 파일이 존재하지 않습니다.");
    	}
        
        // 새로운 통장사본 이미지 생성
        farmMemberDTO.setF_bank_img(farmMemberDTO.getFarm_id() + "_" + farmMemberDTO.getF_bank_name() + ".png");
        try {
            farmMemberDTO.getNew_bank_img().transferTo(new File(BANK_IMAGES_FOLDER_PATH + farmMemberDTO.getF_bank_img()));
            System.out.println(farmMemberDTO.getF_bank_img() + " 새로운 통장사본 이미지 저장 완료");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 이미지 이름 DB에 저장
        return memberMapper.updateFarmImages(farmMemberDTO);
    }

    public int updateFarmImages(FarmMemberDTO farmMemberDTO) {
        log.info("updateFarmImages..........");

        // 이전 농가업체 이미지 삭제
        File farmImageFile = new File(FARM_IMAGES_FOLDER_PATH + farmMemberDTO.getF_img());

        if (farmImageFile.exists()){
            if (farmImageFile.delete()){
                System.out.println(farmMemberDTO.getF_img() + " 농가업체 이미지 삭제 성공");
            } else {
                System.out.println(farmMemberDTO.getF_img() + " 농가업체 이미지 삭제 실패...");
            }
        } else{
    		System.out.println("농가업체 이미지 파일이 존재하지 않습니다.");
    	}
        
        // 새로운 농가업체 이미지 생성
        farmMemberDTO.setF_img(farmMemberDTO.getFarm_id() + "_" + farmMemberDTO.getF_img() + ".png");
        try {
            farmMemberDTO.getNew_farm_img().transferTo(new File(FARM_IMAGES_FOLDER_PATH + farmMemberDTO.getF_bank_img()));
            System.out.println(farmMemberDTO.getF_img() + " 새로운 농가업체 이미지 저장 완료");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 이미지 이름 DB에 저장
        return memberMapper.updateFarmImages(farmMemberDTO);
    }

    public int updateFarmMemberNumber(int farm_id, String f_num) {
        log.info("updateFarmMemberNum..........");

        return memberMapper.updateFarmMemberNumber(farm_id, f_num);
    }

    public int updateFarmMemberFarmName(int farm_id, String f_farm_name) {
        log.info("updateFarmMemberFarmName..........");

        return memberMapper.updateFarmMemberFarmName(farm_id, f_farm_name);
    }

    public int updateFarmMemberExplanation(int farm_id, String f_explanation) {
        log.info("updateFarmMemberExplanation..........");

        return memberMapper.updateFarmMemberExplanation(farm_id, f_explanation);
    }

    public int updateFarmMemberMajorCrop(int farm_id, String f_major_crop) {
        log.info("updateFarmMemberMajorCrop..........");

        return memberMapper.updateFarmMemberMajorCrop(farm_id, f_major_crop);
    }

    public int updateFarmMemberFarmImage(int farm_id, String f_img) {
        log.info("updateFarmMemberFarmImage..........");
        // 이미지 처리 코드 추가!!
        return memberMapper.updateFarmMemberFarmImage(farm_id, f_img);
    }

    // #################################################### 농가, 소비자 아이디 비번 찾기 ####################################################
    
    public int findFarmId(int farm_name, int f_phonenum){
        log.info("findFarmid................");
        return memberMapper.findFarmId(farm_name, f_phonenum);
    }

    // #################################################### 로그인, 로그아웃, 이메일 중복 확인 ####################################################

    public UserDetails login(String checkUser ,String email, String password) {
        log.info("login..........");

        // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        ConsumerMemberDTO consumerMember = null;
        FarmMemberDTO farmMember = null;
        String token = null;

        System.out.println("checkUser: " + checkUser);
        
        if (checkUser.equals("consumer")){
            consumerMember = memberMapper.getConsumerMember(email);
            if(consumerMember == null || !passwordEncoder.matches(password, consumerMember.getC_passwd())) {
                System.out.println("비밀번호가 틀렸습니다.");
                System.out.println("널입니다!");
                return null;
            } else if (consumerMember.getToken() != null){

                System.out.println("토큰 만료");
                memberMapper.setNullConsumerToken(consumerMember.getC_email());

                ConsumerMemberDTO duplicateLoginObject = new ConsumerMemberDTO();
                duplicateLoginObject.setAuthority("ROLE_USER");
                return duplicateLoginObject;
            }
            System.out.println(consumerMember.toString());

            // 생성된 토큰 넣어주기
            token = jwtTokenProvider.createToken(email, checkUser);
            memberMapper.setConsumerToken(email, token);

            consumerMember.setToken(token);
            consumerMember.setC_passwd("");

            System.out.println(token);
            return consumerMember;
        }

        farmMember = memberMapper.getFarmMemberAuth(email);

        if(farmMember == null && !passwordEncoder.matches(password, farmMember.getF_passwd())) {
            System.out.println("널입니다!");
            return null;
        } else if (farmMember.getToken() != null){
            System.out.println("토큰 만료");
            memberMapper.setNullFarmToken(farmMember.getF_email());

            ConsumerMemberDTO duplicateLoginObject = new ConsumerMemberDTO();
            duplicateLoginObject.setAuthority("ROLE_USER");
            return duplicateLoginObject;
        }
        System.out.println(farmMember.toString());

        // 생성된 토큰 넣어주기
        token = jwtTokenProvider.createToken(email, checkUser);
        memberMapper.setFarmToken(email, token);
        farmMember.setToken(token);
        farmMember.setF_passwd("");

        System.out.println(token);

        return farmMember;
    }

    @Override
    public int logout(String checkUser, String email) {

        int result;
        if (checkUser.equals("consumer")) {
            result = memberMapper.setNullConsumerToken(email);
        } else {
            result = memberMapper.setNullFarmToken(email);
        }
        return result;
    }

    public int existEmail(String email) {
        int result = memberMapper.existEmail(email);
        System.out.println("result: "+ result);
        return result;
    }

    // 실제로 사용할 때 주석 풀기!!
    /* 
    public String checkPhoneNumber(String phoneNumber) {

        String api_key = "NCS0EL4LZSW359YQ";
        String api_secret = "G7DMS4WVT3BP7MP9XXSAEYACYGP0NUQM";
        String sentPhoneNumber = "01098474711";
        Message coolsms = new Message(api_key, api_secret);
        HashMap<String, String> params = new HashMap<String, String>();
        Random rand  = new Random();
        String certificationNumber = "";

        for(int i=0; i<4; i++) certificationNumber += Integer.toString(rand.nextInt(10));

        params.put("to", phoneNumber);          // 수신전화번호
        params.put("from", sentPhoneNumber);    // 발신전화번호
        params.put("type", "SMS");
        params.put("text", "Pachi 못난이 농산물 경매 플랫폼 휴대폰 인증 메시지 : 인증번호는" + "["+certificationNumber+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
            System.out.println("인증번호: " + certificationNumber);
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

        return certificationNumber;
    }
    */
    
}
