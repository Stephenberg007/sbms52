package com.jtc.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class AccountPK implements Serializable {
	private Integer accId;
	private String acctype;
	private Long accnum;
	public AccountPK() {
		// TODO Auto-generated constructor stub
	}
	
	public AccountPK(Integer accId, String acctype, Long accnum) {
		super();
		this.accId = accId;
		this.acctype = acctype;
		this.accnum = accnum;
	}
	public Integer getAccId() {
		return accId;
	}
	public void setAccId(Integer accId) {
		this.accId = accId;
	}
	public String getAcctype() {
		return acctype;
	}
	public void setAcctype(String acctype) {
		this.acctype = acctype;
	}
	public Long getAccnum() {
		return accnum;
	}
	public void setAccnum(Long accnum) {
		this.accnum = accnum;
	}
	@Override
	public String toString() {
		return "AccountPK [accId=" + accId + ", acctype=" + acctype + ", accnum=" + accnum + "]";
	}
	
	
}
