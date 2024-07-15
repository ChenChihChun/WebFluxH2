package com.demo.model;

import org.springframework.data.annotation.Id;

/***
 * 商品基本資訊
 * @author USER
 *
 */
public class ProductItem {

	@Id
	private int id;

	private String productId;

	private String productName;

	private int productPrice;

	public ProductItem() {

	}
	
	public ProductItem(String productId,String productName, int productPrice) {
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
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
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productPrice
	 */
	public int getProductPrice() {
		return productPrice;
	}

	/**
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
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

	@Override
	public String toString() {
		return "ProductItem [id=" + id + ", productId=" + productId + ", productName=" + productName + ", productPrice="
				+ productPrice + "]";
	}

	
}
