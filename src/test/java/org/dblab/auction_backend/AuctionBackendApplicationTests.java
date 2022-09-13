package org.dblab.auction_backend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuctionBackendApplicationTests {

	@Test
	void contextLoads() {

		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		
		System.out.println(LocalDate.now());
		System.out.println(LocalTime.now());

		System.out.println(LocalDateTime.now());
		System.out.println(LocalDateTime.now().toString().substring(0, 19));
	}

}
