package com.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.model.MemberData;
import com.demo.repository.MemberDataRepository;
import com.demo.repository.impl.RepositoryCustom;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MemberDataService {

  @Autowired
  MemberDataRepository memberDataRepository;
  
  @Autowired
  RepositoryCustom repositoryCustom;

  public Flux<MemberData> findAll() {
    return memberDataRepository.findAll();
  }

  public Mono<MemberData> findById(int id) {
    return memberDataRepository.findById(id);
  }

  public Mono<MemberData> save(MemberData memberData) {
    return memberDataRepository.save(memberData);
  }
  
  public Mono<Boolean> isUserExists(String userAccount){
	  return memberDataRepository.findByUserAccount(userAccount).map(user->true).defaultIfEmpty(false);
  }

  public Mono<MemberData> update(int id, MemberData memberData) {
    return memberDataRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
        .flatMap(optionalMemberData -> {
          if (optionalMemberData.isPresent()) {
            memberData.setId(id);
            return memberDataRepository.save(memberData);
          }

          return Mono.empty();
        });
  }

  public Mono<Void> deleteById(int id) {
    return memberDataRepository.deleteById(id);
  }

  public Mono<Void> deleteAll() {
    return memberDataRepository.deleteAll();
  }

  public Flux<MemberData> findUserByOrderCountOver(int orderCount){
	  return this.memberDataRepository.findUserByOrderCountOver(orderCount);
  }
  
  public Flux<MemberData> getMembersWithPageable(Pageable pageable) {
	  return repositoryCustom.getMembersWithPageable(pageable);
  }
}
