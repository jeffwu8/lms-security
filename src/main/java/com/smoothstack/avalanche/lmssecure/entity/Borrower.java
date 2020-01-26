package com.smoothstack.avalanche.lmssecure.entity;

import java.util.List;

public class Borrower {

	private Long cardNo;
	private String name;
	private String address;
	private String phone;
	private List<BookLoans> loanBooks;
	
	public Long getCardNo() {
		return cardNo;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public List<BookLoans> getLoanBooks() {
		return loanBooks;
	}
	
	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setLoanBooks(List<BookLoans> loanBooks) {
		this.loanBooks = loanBooks;
	}
	
}
