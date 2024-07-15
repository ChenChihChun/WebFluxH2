package com.demo.model;

import org.springframework.data.annotation.Id;

public class MemberData {

	@Id
	private int id;

	private String userName;

	private String birthDay;

	private String userAccount;

	public MemberData() {

	}

	public MemberData(String userName, String birthDay, String userAccount) {
		this.userAccount = userAccount;
		this.birthDay = birthDay;
		this.userName = userName;

	}

	public void setId(int id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the birthDay
	 */
	public String getBirthDay() {
		return birthDay;
	}

	/**
	 * @param birthDay the birthDay to set
	 */
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
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

	@Override
	public String toString() {
		return "MemberData [id=" + id + ", userName=" + userName + ", birthDay=" + birthDay + ", userAccount="
				+ userAccount + "]";
	}

}
