/**
 * 
 */
package com.smoothstack.avalanche.lmssecure.entity;

import java.util.List;
import java.util.Set;

public class Book 
{
	private Long id;
	private String title;
    private Set<Author> authors;
	private Publisher publisher;
	private List<BookLoans> loanBooks;
	
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public Set<Author> getAuthors() {
		return authors;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
}
