package org.dblab.auction_backend.controller;


import java.sql.Date;

import org.dblab.auction_backend.config.BidDeadlineTimer;
import org.dblab.auction_backend.mapper.TestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private TestMapper testMapper;

    private Logger log = LoggerFactory.getLogger(TestController.class);
    
    @GetMapping("/test")
    public String test() {
        System.out.println("test#################");
        return "test!!";
    }

    @GetMapping("/testTime")
    public String testTime() {
        System.out.println("testTime");
        return testMapper.getTime();
    }

    	// ---------------------------------  TEST  --------------------------------------

	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {

		log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
		return "안녕하세요";
	}

	@GetMapping(value = "/getText2", produces = "text/plain; charset=UTF-8")
	public String getText2(@RequestParam("abc")String sdf) {

		log.info("getText2" + sdf);
		return "안녕하세요 " + sdf;
	}

	@GetMapping(value = "/getText3/{user}", produces = "text/plain; charset=UTF-8")
	public String getText3(@PathVariable("user") String user) {

		log.info("getText2" + user);
		return "안녕하세요 " + user;
	}

	// 이미지 가져오기 TEST
    @PostMapping(value = "/productImage", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public String productImage(@RequestParam("productImage") MultipartFile img) {
        System.out.println(img.getOriginalFilename());
        System.out.println(img);
        return img.getOriginalFilename();
    }

	// // 이미지 내보내기 TEST
	// @GetMapping(value = "/testImage", produces = { MediaType. })
	// public String testImage(@RequestParam("productImage") MultipartFile img) {
	// 	System.out.println(img.getOriginalFilename());
	// 	System.out.println(img);
	
	// 	return img.getOriginalFilename();
	// }
	
}
