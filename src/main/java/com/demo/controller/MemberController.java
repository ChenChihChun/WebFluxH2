package com.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.demo.model.MemberData;
import com.demo.service.MemberDataService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/***
 * 
 */
@RestController
@RequestMapping("/api/member")
public class MemberController {
	@Autowired
	MemberDataService memberDataService;

	private static final String USER_ACCOUNT_ALREADY_EXISTS = "User account already exists!";
	private static final String USER_ACCOUNT_AND_USER_NAME_CANNOT_BE_EMPTY = "User account and user name cannot be empty!";

	@GetMapping("/listAllMembers")
	@ResponseStatus(HttpStatus.OK)
	public Flux<MemberData> getListAllMembers() {
		return memberDataService.findAll();
	}
	
	@GetMapping("/listMembersWithPageable")
	@ResponseStatus(HttpStatus.OK)
	public Flux<MemberData> getMembersWithPageable(Pageable pageable) {
        return memberDataService.getMembersWithPageable(pageable);
    }

	@GetMapping("/getMemberDataById/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<MemberData> getMemberDataById(@PathVariable("id") int id) {
		return memberDataService.findById(id);
	}

	@PostMapping("/createMemberData")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<MemberData> createMemberData(@RequestBody MemberData memberData) {

		return Mono.just(memberData).flatMap(it -> {
			if (StringUtils.isEmpty(it.getUserAccount()) || StringUtils.isEmpty(it.getUserName())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_ACCOUNT_AND_USER_NAME_CANNOT_BE_EMPTY);
			}
			return userCreateCheck(it);
		});

	}

	private Mono<MemberData> userCreateCheck(MemberData it) {
		return memberDataService.isUserExists(it.getUserAccount()).flatMap(exists -> {
			if (!exists) {
				return memberDataService.save(it);
			} else {
				return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_ACCOUNT_ALREADY_EXISTS));
			}
		});
	}

	@PutMapping("/updateMemberData/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<MemberData> updateMemberData(@PathVariable("id") int id, @RequestBody MemberData memberData) {
		return memberDataService.update(id, memberData);
	}

	@DeleteMapping("/deleteMemberData/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteMemberData(@PathVariable("id") int id) {
		return memberDataService.deleteById(id);
	}

	@DeleteMapping("/deleteAllMemberDatas")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteAllMemberDatas() {
		return memberDataService.deleteAll();
	}

}
