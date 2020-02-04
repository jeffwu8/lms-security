/**
 * 
 */
package com.smoothstack.avalanche.lmssecure.entity;

import java.util.List;

public class Branch 
{
	
	private Long id;
	private String name;
	private String address;
	private List<BookCopies> bookCopies;
	private List<BookLoans> bookLoans;
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public List<BookCopies> getBookCopies() {
		return bookCopies;
	}
	public List<BookLoans> getBookLoans() {
		return bookLoans;
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
	public void setBookCopies(List<BookCopies> bookCopies) {
		this.bookCopies = bookCopies;
	}
	public void setBookLoans(List<BookLoans> bookLoans) {
		this.bookLoans = bookLoans;
	}
	
	
}
