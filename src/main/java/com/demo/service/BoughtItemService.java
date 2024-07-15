package com.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.model.BoughtItem;
import com.demo.repository.BoughtItemRepository;
import com.demo.repository.impl.RepositoryCustom;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BoughtItemService {

	@Autowired
	BoughtItemRepository boughtItemRepository;
	
	@Autowired
	RepositoryCustom boughtItemRepositoryCustom;

	public Flux<BoughtItem> findAll() {
		return boughtItemRepository.findAll();
	}

	public Mono<BoughtItem> findById(int id) {
		return boughtItemRepository.findById(id);
	}

	public Mono<BoughtItem> save(BoughtItem item) {
		return boughtItemRepository.save(item);
	}
	
	public Flux<BoughtItem> listOrderItem(Pageable pageable,BoughtItem item){
		return boughtItemRepositoryCustom.listOrderItem(pageable,item);
	}

	public Mono<BoughtItem> update(int id, BoughtItem item) {
		return boughtItemRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
				.flatMap(optional -> {
					if (optional.isPresent()) {
						item.setId(id);
						return boughtItemRepository.save(item);
					}
					return Mono.empty();
				});
	}

	public Mono<Void> deleteById(int id) {
		return boughtItemRepository.deleteById(id);
	}

	public Mono<Void> deleteAll() {
		return boughtItemRepository.deleteAll();
	}
}
