package org.dblab.auction_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
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

	// #################################################### 소비자, 농가 공통 Updatem, Delete 기능 ####################################################
	@PatchMapping(value = "/memberPassword")
	public int updateMemberPassword(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateMemberPassword(map.get("checkUser"), Integer.parseInt(map.get("id")), map.get("passwd"));
	}

	@PatchMapping(value = "/memberPhoneNumber")
	public int updateMemberPhoneNumber(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateMemberPhoneNumber(map.get("checkUser"), Integer.parseInt(map.get("id")), map.get("phonenum"));
	}

	@PatchMapping(value = "/memberName")
	public int updateMemberName(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateMemberName(map.get("checkUser"), Integer.parseInt(map.get("id")), map.get("name"));
	}

	@PatchMapping(value = "/memberAddress")
	public int updateMemberAddress(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateMemberAddress(map.get("checkUser"), Integer.parseInt(map.get("id")), map.get("zipcode"), map.get("location"));
	}

	@PatchMapping(value = "/memberProfileImage")
	public int updateMemberProfileImage(@ModelAttribute MemberProfileDTO memberProfileDTO) {
		System.out.println(memberProfileDTO.toString());
		return memberService.updateMemberProfileImage(memberProfileDTO);
	}

	@DeleteMapping(value = "/member", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public int deleteConsumerMember(@RequestBody Map<String, String> map) {
		System.out.println("deleteMember: " + map.get("consumer_id"));
		return memberService.deleteMember(map.get("checkUser"), Integer.parseInt(map.get("consumer_id")));
	}

	// #################################################### 소비자 C ####################################################

	@PostMapping(value = "/signupConsumer", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public HashMap<String, Object> signupConsumer(@RequestBody ConsumerMemberDTO consumerMemberDTO) {
		System.out.println(consumerMemberDTO.toString());
		return memberService.signupConsumer(consumerMemberDTO);
	}

	// #################################################### 농가 CUD ####################################################

	@PostMapping(value = "/signupFarmMember", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public HashMap<String, Object> signupFarmMember(@RequestBody FarmMemberDTO farmMemberDTO) {
		System.out.println(farmMemberDTO.toString());
		return memberService.signupFarmMember(farmMemberDTO);
	}

	@GetMapping(value = "/farmMember/{farm_id}")
	public FarmMemberDTO getFarmMember(@PathVariable("farm_id") int farm_id) {
		System.out.println(farm_id);
		return memberService.getFarmMember(farm_id);
	}
	
	@PatchMapping(value = "/farmMemberBank")
	public int updateFarmMemberBank(@ModelAttribute FarmMemberDTO farmMemberDTO) {
		System.out.println(farmMemberDTO.toString());
		return memberService.updateFarmMemberBank(farmMemberDTO);
	}

	@PatchMapping(value = "/farmMemberNumber")
	public int updateFarmMemberNumber(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateFarmMemberNumber(Integer.parseInt(map.get("farm_id")), map.get("f_num"));
	}
	
	@PatchMapping(value = "/farmMemberFarmName")
	public int updateFarmMemberFarmName(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateFarmMemberFarmName(Integer.parseInt(map.get("farm_id")), map.get("f_farm_name"));
	}

	@PatchMapping(value = "/farmMemberExplanation")
	public int updateFarmMemberExplanation(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateFarmMemberExplanation(Integer.parseInt(map.get("farm_id")), map.get("f_explanation"));
	}

	@PatchMapping(value = "/farmMemberMajorCrop")
	public int updateFarmMemberMajorCrop(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateFarmMemberMajorCrop(Integer.parseInt(map.get("farm_id")), map.get("f_major_crop"));
	}
	
	@PatchMapping(value = "/farmMemberFarmImage")
	public int updateFarmMemberFarmImage(@RequestBody Map<String, String> map) {
		System.out.println(map.toString());
		return memberService.updateFarmMemberFarmImage(Integer.parseInt(map.get("farm_id")), map.get("img"));
	}
	// #################################################### 소비자, 농가 아이디, 비번 찾기 ####################################################

	@GetMapping(value = "findFarmId")
	public int findFarmId(@PathVariable("f_name") int f_name, @PathVariable("f_phonenum") int f_phonenum){
		return memberService.findFarmId(f_name, f_phonenum);
	}

	// @PatchMapping(value = "changeFarmPw")
	// public int changePw(@PathVariable("f_email") String f_email){
		//return memberService.changeFarmPw(f_email);

	// }




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

	// 실제로 사용할 때 주석 풀기!!
	// @PostMapping(value = "/checkPhoneNumber")
	// public String checkPhoneNumber(@RequestBody Map<String, String> phoneNumber) {
	// 	System.out.println(phoneNumber.toString());

	// 	return memberService.checkPhoneNumber(phoneNumber.get("phoneNumber"));
	// }

}