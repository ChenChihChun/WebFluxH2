package com.demo.controller;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Pageable;

import com.demo.model.BoughtItem;
import com.demo.service.BoughtItemService;
import com.demo.service.MemberDataService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/***
 * 訂單查詢api，會員可以根據訂單編號或產品名稱或購買日期做分頁查詢。
 */

@Tag(name="訂單查詢API", description="會員可以根據訂單編號或產品名稱或購買日期做分頁查詢")
@RestController
@RequestMapping("/api/boughtItem")
public class BoughtItemController {
	
	private static final String USER_ACCOUNT_CANNOT_BE_EMPTY = "User account cannot be empty!";
	private static final String USER_ACCOUNT_DOES_NOT_EXIST = "User account does not exist!";

	@Autowired
	BoughtItemService boughtItemService;
	
	@Autowired
	MemberDataService memberDataService;
	
	//List all orders
	@Operation(summary = "列出所有訂單", description = "無差別列出所有訂單")
	@GetMapping("/listAllOrders")
	@ResponseStatus(HttpStatus.OK)
	public Flux<BoughtItem> getListAllOrders() {
		return boughtItemService.findAll();
	}
	
	
	//Get order by id
	@Operation(summary = "根據訂單編號查詢訂單", description = "根據訂單編號查詢訂單")
	@GetMapping("/getOrderById/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<BoughtItem> getOrderById(@PathVariable("id") int id) {
		return boughtItemService.findById(id);
	}

	@Operation(summary = "新增訂單", description = "新增訂單，一個會員可以有個筆訂單，訂單資料包含訂單編號、會員帳號、購買日期、產品代碼")
	@PostMapping("/createOrder")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<BoughtItem> createProductItem(@RequestBody BoughtItem item) {
		 return Mono.just(item).flatMap(it -> {
		        if (StringUtils.isEmpty(it.getUserAccount())) {
		            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_ACCOUNT_CANNOT_BE_EMPTY);
		        }
		        return 	checkUserExistAndCreateOrder(it);
		        		
		    }).onErrorMap(ex -> {
		        if (ex instanceof ResponseStatusException) {
		            return ex; 
		        }
		        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		    });
	}

	private Mono<BoughtItem> checkUserExistAndCreateOrder(BoughtItem it) {
		return memberDataService.isUserExists(it.getUserAccount())
		 .flatMap(exists -> {
		     if (!exists) {
		         return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_ACCOUNT_DOES_NOT_EXIST));
		     }
		     return boughtItemService.save(it);
		 });
	}
	
	@Operation(summary = "列出所有訂單，可以分頁", description = "列出所有訂單，可以分頁")
	@GetMapping("/listOrdersWithFilter")
	@ResponseStatus(HttpStatus.OK)
    public Flux<BoughtItem> listOrdersWithFilter(Pageable pageable,BoughtItem item) {
        return boughtItemService.listOrderItem(pageable,item);
    }

	@Operation(summary = "更新訂單資料", description = "更新訂單資料")
	@PutMapping("/updateOrderData/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<BoughtItem> updateOrderData(@PathVariable("id") int id, @RequestBody BoughtItem item) {
		return boughtItemService.update(id, item);
	}

	@Operation(summary = "刪除訂單", description = "刪除訂單(指定UID)")
	@DeleteMapping("/deleteOrderById/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteOrderById(@PathVariable("id") int id) {
		return boughtItemService.deleteById(id);
	}

	@Operation(summary = "刪除所有訂單", description = "刪除所有訂單")
	@DeleteMapping("/deleteAllOrderItems")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteAllOrderItems() {
		return boughtItemService.deleteAll();
	}
}
