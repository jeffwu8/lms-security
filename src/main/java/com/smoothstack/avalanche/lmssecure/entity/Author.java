package com.smoothstack.avalanche.lmssecure.entity;

import java.util.Set;

public class Author 
{
	private Long id;
	private String name;
	private Set<Book> books;
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
