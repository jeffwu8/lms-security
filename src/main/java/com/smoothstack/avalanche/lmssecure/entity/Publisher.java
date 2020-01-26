package com.smoothstack.avalanche.lmssecure.entity;

import java.util.Collection;

public class Publisher {
	private Long id;
	private String name;
	private String address;
	private String phone;
	private Collection<Book> publisherBooks;
	
	public Long getId() {
		return id;
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
	
	public void setId(Long id) {
		this.id = id;
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
	
}
