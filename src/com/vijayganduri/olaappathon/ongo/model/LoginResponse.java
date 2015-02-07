package com.vijayganduri.olaappathon.ongo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3944725908331591482L;

	private String status;

	@JsonProperty("request_type")
	private String requestType;

	@JsonProperty("user_id")
	private String userId;

	@JsonProperty("referral_code")
	private String referralCode;

	private boolean activated;

	@JsonProperty("state_id")
	private int stateId;

	private String name;
	private String phone;

	@JsonProperty("ola_money_balance")
	private int olaMoneyBalance;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getOlaMoneyBalance() {
		return olaMoneyBalance;
	}

	public void setOlaMoneyBalance(int olaMoneyBalance) {
		this.olaMoneyBalance = olaMoneyBalance;
	}

	@Override
	public String toString() {
		return "LoginResponse [status=" + status + ", requestType="
				+ requestType + ", userId=" + userId + ", referralCode="
				+ referralCode + ", activated=" + activated + ", stateId="
				+ stateId + ", name=" + name + ", phone=" + phone
				+ ", olaMoneyBalance=" + olaMoneyBalance + "]";
	}

}
