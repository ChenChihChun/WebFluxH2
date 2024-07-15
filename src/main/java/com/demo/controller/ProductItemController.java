package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.demo.model.MemberData;
import com.demo.model.ProductItem;
import com.demo.service.ProductItemService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/product")
public class ProductItemController {

	@Autowired
	ProductItemService productItemService;

	@GetMapping("/listAllProducts")
	@ResponseStatus(HttpStatus.OK)
	public Flux<ProductItem> getListAllMembers() {
		return productItemService.findAll();
	}

	@GetMapping("/getProductItemById/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<ProductItem> getProductItemById(@PathVariable("id") int id) {
		return productItemService.findById(id);
	}

	@PostMapping("/createProductItem")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<ProductItem> createProductItem(@RequestBody ProductItem productItem) {
		return productItemService.save(productItem);
	}

	@PutMapping("/updateProductItem/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<ProductItem> updateProductItem(@PathVariable("id") int id, @RequestBody ProductItem productItem) {
		return productItemService.update(id, productItem);
	}

	@DeleteMapping("/deleteProductItem/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteProductItem(@PathVariable("id") int id) {
		return productItemService.deleteById(id);
	}

	@DeleteMapping("/deleteAllProductItems")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteAllProductItems() {
		return productItemService.deleteAll();
	}

}
