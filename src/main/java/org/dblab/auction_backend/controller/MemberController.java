package org.dblab.auction_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dblab.auction_backend.domain.ConsumerMemberDTO;
import org.dblab.auction_backend.domain.FarmMemberDTO;
import org.dblab.auction_backend.domain.LoginObj;
import org.dblab.auction_backend.domain.MemberProfileDTO;
import org.dblab.auction_backend.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private Logger log = LoggerFactory.getLogger(MemberController.class);


	// #################################################### 소비자 CRUD ####################################################

	@PostMapping(value = "/signupConsumer", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public HashMap<String, Object> signupConsumer(@RequestBody ConsumerMemberDTO consumerMemberDTO) {
		System.out.println(consumerMemberDTO.toString());
		return memberService.signupConsumer(consumerMemberDTO);
	}

	@GetMapping(value = "/getConsumer", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ConsumerMemberDTO getConsumer(@RequestParam(value = "email") String email) {
		System.out.println(email);
		return memberService.getConsumerMember(email);
	}

	@PatchMapping(value = "/updateConsumerMember/passwd")
	public int updateConsumerMemberPassword(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateMemberPassword("consumer", Integer.parseInt(map.get("consumer_id")), map.get("c_passwd"));
	}

	@PatchMapping(value = "/updateConsumerMember/phonenum")
	public int updateConsumerMemberPhoneNumber(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateMemberPhoneNumber("consumer", Integer.parseInt(map.get("consumer_id")), map.get("c_phonenum"));
	}

	@PatchMapping(value = "/updateConsumerMember/name")
	public int updateConsumerMemberName(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateMemberName("consumer", Integer.parseInt(map.get("consumer_id")), map.get("c_name"));
	}

	@PatchMapping(value = "/updateConsumerMember/address")
	public int updateConsumerMemberZipcode(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateMemberAddress("consumer", Integer.parseInt(map.get("consumer_id")), map.get("c_zipcode"), map.get("c_location"));
	}

	@PatchMapping(value = "/updateConsumerMember/profileImg")
	public int updateConsumerMemberProfileImg(@ModelAttribute MemberProfileDTO memberProfileDTO) {
		System.out.println(memberProfileDTO.toString());
		return memberService.updateMemberProfileImage("consumer", memberProfileDTO);
	}

	// ----------

	@DeleteMapping(value = "/deleteConsumerMember/{consumer_id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public int deleteConsumerMember(@PathVariable("consumer_id") int consumer_id) {
		System.out.println("deleteConsumerMember: " + consumer_id);
		return memberService.deleteConsumerMember(consumer_id);
	}


	// #################################################### 농가 CRUD ####################################################

	@PostMapping(value = "/signupFarmMember", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public HashMap<String, Object> signupFarmMember(@RequestBody FarmMemberDTO farmMemberDTO) {
		System.out.println(farmMemberDTO.toString());
		return memberService.signupFarmMember(farmMemberDTO);
	}

	@PatchMapping(value = "/updateFarmMember/passwd")
	public int updateFarmMemberPassword(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateMemberPassword("farm", Integer.parseInt(map.get("farm_id")), map.get("f_passwd"));
	}

	@PatchMapping(value = "/updateFarmMember/phonenum")
	public int updateFarmMemberPhoneNumber(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateMemberPhoneNumber("farm", Integer.parseInt(map.get("farm_id")), map.get("f_phonenum"));
	}

	@PatchMapping(value = "/updateFarmMember/name")
	public int updateFarmMemberName(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateMemberName("farm", Integer.parseInt(map.get("farm_id")), map.get("f_name"));
	}

	@PatchMapping(value = "/updateFarmMember/zipcode")
	public int updateFarmMemberZipcode(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateMemberAddress("farm", Integer.parseInt(map.get("farm_id")), map.get("f_zipcode"), map.get("f_location"));
	}

	@PatchMapping(value = "/updateFarmMember/profileImg")
	public int updateFarmMemberProfileImg(@ModelAttribute MemberProfileDTO memberProfileDTO) {
		System.out.println(memberProfileDTO.toString());
		return memberService.updateMemberProfileImage("farm", memberProfileDTO);
	}

	@PatchMapping(value = "/updateFarmMember/bank")
	public int updateFarmMemberBank(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateFarmMemberBank(Integer.parseInt(map.get("farm_id")), map.get("f_bank"), map.get("f_bank_name"), Integer.parseInt(map.get("f_bank_num")));
	}

	@PatchMapping(value = "/updateFarmMember/num")
	public int updateFarmMemberNum(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateFarmMemberNum(Integer.parseInt(map.get("farm_id")), map.get("f_num"));
	}
	
	@PatchMapping(value = "/updateFarmMember/farmName")
	public int updateFarmMemberFarmName(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateFarmMemberFarmName(Integer.parseInt(map.get("farm_id")), map.get("f_farm_name"));
	}

	@PatchMapping(value = "/updateFarmMember/explanation")
	public int updateFarmMemberExplanation(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateFarmMemberExplanation(Integer.parseInt(map.get("farm_id")), map.get("f_explanation"));
	}

	@PatchMapping(value = "/updateFarmMember/majorCrop")
	public int updateFarmMemberMajorCrop(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateFarmMemberMajorCrop(Integer.parseInt(map.get("farm_id")), map.get("f_major_crop"));
	}
	
	@PatchMapping(value = "/updateFarmMember/img")
	public int updateFarmMemberFarmImage(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateFarmMemberFarmImage(Integer.parseInt(map.get("farm_id")), map.get("img"));
	}

	// ----------

	@DeleteMapping(value = "/deleteFarmMember/{farm_id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public int deleteFarmMember(@PathVariable("farm_id") int farm_id) {
		System.out.println("deleteFarmMember: " + farm_id);
		return memberService.deleteFarmMember(farm_id);
	}


	// #################################################### 로그인, 로그아웃, 이메일 중복 확인, 휴대폰 인증번호 확인 ####################################################

	@PostMapping(value = "/login", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public UserDetails login(@RequestBody LoginObj loginObj) {
		System.out.println(loginObj);
		return memberService.login(loginObj.getCheckUser(), loginObj.getEmail(), loginObj.getPassword());
	}

	@GetMapping(value = "/logout/{checkUser}/{email}")
	public int login(@PathVariable("checkUser") String checkUser, @PathVariable("email") String email) {
		System.out.println(checkUser + "  /  " + email);

		return memberService.logout(checkUser, email);
	}

	@GetMapping(value = "/existEmail")
	public int existEmail(@RequestParam(value = "email") String email) {
		System.out.println(email);

		return memberService.existEmail(email);
	}

	@PostMapping(value = "/checkPhoneNumber")
	public String checkPhoneNumber(@RequestBody Map<String, String> phoneNumber) {
		System.out.println(phoneNumber.toString());

		return memberService.checkPhoneNumber(phoneNumber.get("phoneNumber"));
	}

}