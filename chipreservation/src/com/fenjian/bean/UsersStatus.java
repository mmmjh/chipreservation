package com.fenjian.bean;

public class UsersStatus {
	private String username;
	private String type; //оƬ����
	private int reservedamount; //ԤԼ����
	private int needreturn;  //��黹����
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getReservedamount() {
		return reservedamount;
	}
	public void setReservedamount(int reservedamount) {
		this.reservedamount = reservedamount;
	}
	public int getNeedreturn() {
		return needreturn;
	}
	public void setNeedreturn(int needreturn) {
		this.needreturn = needreturn;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
