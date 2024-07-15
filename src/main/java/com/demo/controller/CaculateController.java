package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.MemberData;
import com.demo.service.MemberDataService;

import reactor.core.publisher.Flux;

/***
 * 訂單統計api，統計訂單數大於n的會員資料
 */
@RestController
@RequestMapping("/api/caculate")
public class CaculateController {

	@Autowired
	MemberDataService memberDataService;
	
	@GetMapping("v1")
	@ResponseStatus(HttpStatus.OK)
	public Flux<MemberData> findUserByOrderCountOver(int count) {
		return memberDataService.findUserByOrderCountOver(count);
	}
}
