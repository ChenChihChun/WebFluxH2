package com.demo.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

/***
 * 購買明細
 * @author USER
 *
 */
public class BoughtItem {

	@Id
	private int id;
	
	private String orderId;
	
	private String productId;
	
	private String userAccount;
	
	private LocalDate buyDate;
	
	public BoughtItem() {
		
	}
	
	public BoughtItem(String orderId, String productId, String userAccount, LocalDate buyDate) {
		this.orderId = orderId;
		this.productId = productId;
		this.userAccount = userAccount;
		this.buyDate = buyDate;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}

	/**
	 * @param userAccount the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * @return the buyDate
	 */
	public LocalDate getBuyDate() {
		return buyDate;
	}

	/**
	 * @param buyDate the buyDate to set
	 */
	public void setBuyDate(LocalDate buyDate) {
		this.buyDate = buyDate;
	}

	@Override
	public String toString() {
		return "BoughtItem [id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", userAccount="
				+ userAccount + ", buyDate=" + buyDate + "]";
	}
	
	
	
	
}
