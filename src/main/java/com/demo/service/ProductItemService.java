package com.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.ProductItem;
import com.demo.repository.ProductItemRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductItemService {

	@Autowired
	ProductItemRepository productItemRepository;

	public Flux<ProductItem> findAll() {
		return productItemRepository.findAll();
	}

	public Mono<ProductItem> findById(int id) {
		return productItemRepository.findById(id);
	}

	public Mono<ProductItem> save(ProductItem item) {
		return productItemRepository.save(item);
	}

	public Mono<ProductItem> update(int id, ProductItem item) {
		return productItemRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
				.flatMap(optional -> {
					if (optional.isPresent()) {
						item.setId(id);
						return productItemRepository.save(item);
					}
					return Mono.empty();
				});
	}

	public Mono<Void> deleteById(int id) {
		return productItemRepository.deleteById(id);
	}

	public Mono<Void> deleteAll() {
		return productItemRepository.deleteAll();
	}
}
