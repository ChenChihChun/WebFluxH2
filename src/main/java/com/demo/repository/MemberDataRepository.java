package com.demo.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.MemberData;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MemberDataRepository extends R2dbcRepository<MemberData, Integer>{
  
	@Query("select * from member_data where user_account = :userAccount")
	public Mono<MemberData> findByUserAccount(String userAccount);
	
	@Query("select * from member_data where user_account = (select user_account from BOUGHT_ITEM  group by user_account having count(*) > :orderCount)")
	public Flux<MemberData> findUserByOrderCountOver(int orderCount);
}
