package com.smoothstack.avalanche.lmssecure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import com.smoothstack.avalanche.lmssecure.db.UserRepository;
import com.smoothstack.avalanche.lmssecure.entity.Author;
import com.smoothstack.avalanche.lmssecure.entity.Book;
import com.smoothstack.avalanche.lmssecure.entity.BookCopies;
import com.smoothstack.avalanche.lmssecure.entity.BookLoans;
import com.smoothstack.avalanche.lmssecure.entity.Borrower;
import com.smoothstack.avalanche.lmssecure.entity.Branch;
import com.smoothstack.avalanche.lmssecure.entity.Publisher;
import com.smoothstack.avalanche.lmssecure.entity.User;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
@RequestMapping("lms")
@CrossOrigin
public class LmsOrchsController {
	private UserRepository userRepository;

	@Autowired
	RestTemplate restTemplate;

	public LmsOrchsController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/***********
	 * Admin Functions
	 ***********/
	@GetMapping("admin/users")
	public List<User> users() {
		return this.userRepository.findAll();
	}
	
	//Admin Books
	@RequestMapping(path = "/admin/books", method = RequestMethod.GET)
	public ResponseEntity<Book[]> readBooksAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<Book> request = new HttpEntity<Book>(headers);
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/books", HttpMethod.GET, request, Book[].class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<Book[]>(e.getStatusCode());
		}
	}

	@RequestMapping(path = "/admin/book", method = RequestMethod.POST)
	public ResponseEntity<Book> createBookAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @RequestBody Book book) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<Book> request = new HttpEntity<Book>(book, headers);
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/book", HttpMethod.POST, request, Book.class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<Book>(e.getStatusCode());
		}
	}

	@RequestMapping(path = "/admin/books/{bookId}", method = RequestMethod.PUT)
	public ResponseEntity<Book> updateBookAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @RequestBody Book book, @PathVariable Long bookId) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<Book> request = new HttpEntity<Book>(book, headers);
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/books/" + bookId, HttpMethod.PUT, request, Book.class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<Book>(e.getStatusCode());
		}
	}

	@RequestMapping(path = "/admin/books/{bookId}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteBookAdmin(@PathVariable("bookId") Long bookId) {
		RequestEntity<HttpStatus> request = new RequestEntity<>(HttpMethod.DELETE, URI.create("http://localhost:8081/lms/admin/books/" + bookId));	
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/books/" + bookId, HttpMethod.DELETE, request, HttpStatus.class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<HttpStatus>(e.getStatusCode());
		}
	}

	//Admin Authors
	@RequestMapping(path = "/admin/authors", method = RequestMethod.GET)
	public ResponseEntity<Author[]> readAuthorsAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<Author> request = new HttpEntity<Author>(headers);
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/authors", HttpMethod.GET, request, Author[].class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<Author[]>(e.getStatusCode());
		}
	}

	@RequestMapping(path = "/admin/author", method = RequestMethod.POST)
	public ResponseEntity<Author> createAuthorAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @RequestBody Author author) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<Author> request = new HttpEntity<Author>(author, headers);
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/author", HttpMethod.POST, request, Author.class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<Author>(e.getStatusCode());
		}
	}

	@RequestMapping(path = "/admin/authors/{authorId}", method = RequestMethod.PUT)
	public ResponseEntity<Author> updateAuthorAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @RequestBody Author author, @PathVariable Long authorId) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<Author> request = new HttpEntity<Author>(author, headers);
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/authors/" + authorId, HttpMethod.PUT, request, Author.class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<Author>(e.getStatusCode());
		}
	}

	@RequestMapping(path = "/admin/authors/{authorId}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteAuthorAdmin(@PathVariable("authorId") Long authorId) {
		RequestEntity<HttpStatus> request = new RequestEntity<>(HttpMethod.DELETE, URI.create("http://localhost:8081/lms/admin/authors/" + authorId));	
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/authors/" + authorId, HttpMethod.DELETE, request, HttpStatus.class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<HttpStatus>(e.getStatusCode());
		}
	}

	//Admin Publishers
	@RequestMapping(path = "/admin/publishers", method = RequestMethod.GET)
	public ResponseEntity<Publisher[]> readPublishersAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<Publisher> request = new HttpEntity<Publisher>(headers);
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/publishers", HttpMethod.GET, request, Publisher[].class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<Publisher[]>(e.getStatusCode());
		}
	}

	@RequestMapping(path = "/admin/publisher", method = RequestMethod.POST)
	public ResponseEntity<Publisher> createPublisherAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @RequestBody Publisher publisher) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<Publisher> request = new HttpEntity<Publisher>(publisher, headers);
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/publisher", HttpMethod.POST, request, Publisher.class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<Publisher>(e.getStatusCode());
		}
	}

	@RequestMapping(path = "/admin/publishers/{publisherId}", method = RequestMethod.PUT)
	public ResponseEntity<Publisher> updatePublisherAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @RequestBody Publisher publisher, @PathVariable Long publisherId) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<Publisher> request = new HttpEntity<Publisher>(publisher, headers);
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/publishers/" + publisherId, HttpMethod.PUT, request, Publisher.class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<Publisher>(e.getStatusCode());
		}
	}

	@RequestMapping(path = "/admin/publishers/{publisherId}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deletePublisherAdmin(@PathVariable("publisherId") Long publisherId) {
		RequestEntity<HttpStatus> request = new RequestEntity<>(HttpMethod.DELETE, URI.create("http://localhost:8081/lms/admin/publishers/" + publisherId));	
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/publishers/" + publisherId, HttpMethod.DELETE, request, HttpStatus.class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<HttpStatus>(e.getStatusCode());
		}
	}

	//Admin Branches
	@RequestMapping(path = "/admin/branches", method = RequestMethod.GET)
	public ResponseEntity<Branch[]> readBranchsAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<Branch> request = new HttpEntity<Branch>(headers);
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/branches", HttpMethod.GET, request, Branch[].class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<Branch[]>(e.getStatusCode());
		}
	}

	@RequestMapping(path = "/admin/branch", method = RequestMethod.POST)
	public ResponseEntity<Branch> createBranchAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @RequestBody Branch branch) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<Branch> request = new HttpEntity<Branch>(branch, headers);
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/branch", HttpMethod.POST, request, Branch.class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<Branch>(e.getStatusCode());
		}
	}

	@RequestMapping(path = "/admin/branches/{branchId}", method = RequestMethod.PUT)
	public ResponseEntity<Branch> updateBranchAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @RequestBody Branch branch, @PathVariable Long branchId) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<Branch> request = new HttpEntity<Branch>(branch, headers);
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/branches/" + branchId, HttpMethod.PUT, request, Branch.class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<Branch>(e.getStatusCode());
		}
	}

	@RequestMapping(path = "/admin/branches/{branchId}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteBranchAdmin(@PathVariable("branchId") Long branchId) {
		RequestEntity<HttpStatus> request = new RequestEntity<>(HttpMethod.DELETE, URI.create("http://localhost:8081/lms/admin/branches/" + branchId));	
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/branches/" + branchId, HttpMethod.DELETE, request, HttpStatus.class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<HttpStatus>(e.getStatusCode());
		}
	}

	//Admin Borrowers
	@RequestMapping(path = "/admin/borrowers", method = RequestMethod.GET)
	public ResponseEntity<Borrower[]> readBorrowersAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<Borrower> request = new HttpEntity<Borrower>(headers);
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/borrowers", HttpMethod.GET, request, Borrower[].class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<Borrower[]>(e.getStatusCode());
		}
	}

	@RequestMapping(path = "/admin/borrower", method = RequestMethod.POST)
	public ResponseEntity<Borrower> createBorrowerAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @RequestBody Borrower borrower) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<Borrower> request = new HttpEntity<Borrower>(borrower, headers);
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/borrower", HttpMethod.POST, request, Borrower.class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<Borrower>(e.getStatusCode());
		}
	}

	@RequestMapping(path = "/admin/borrowers/{cardNo}", method = RequestMethod.PUT)
	public ResponseEntity<Borrower> updateBorrowerAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @RequestBody Borrower borrower, @PathVariable Long cardNo) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<Borrower> request = new HttpEntity<Borrower>(borrower, headers);
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/borrowers/" + cardNo, HttpMethod.PUT, request, Borrower.class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<Borrower>(e.getStatusCode());
		}
	}

	@RequestMapping(path = "/admin/borrowers/{cardNo}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteBorrowerAdmin(@PathVariable("cardNo") Long cardNo) {
		RequestEntity<HttpStatus> request = new RequestEntity<>(HttpMethod.DELETE, URI.create("http://localhost:8081/lms/admin/borrowers/" + cardNo));	
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/borrowers/" + cardNo, HttpMethod.DELETE, request, HttpStatus.class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<HttpStatus>(e.getStatusCode());
		}
	}

	//Admin Loans
	@RequestMapping(path = "/admin/loans", method = RequestMethod.GET)
	public ResponseEntity<BookLoans[]> readBookLoansAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<BookLoans> request = new HttpEntity<BookLoans>(headers);
		try {
			return restTemplate.exchange("http://localhost:8081/lms/admin/loans", HttpMethod.GET, request, BookLoans[].class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<BookLoans[]>(e.getStatusCode());
		}
	}

	@RequestMapping(path = "/admin/loans:renew", method = RequestMethod.PUT)
	public ResponseEntity<BookLoans> updateBookLoansAdmin(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @RequestBody BookLoans loan) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		HttpEntity<BookLoans> request = new HttpEntity<BookLoans>(loan, headers);
		try {
			return restTemplate.exchange("/admin/loans:renew", HttpMethod.PUT, request, BookLoans.class);
		} catch (HttpStatusCodeException e) {
			return new ResponseEntity<BookLoans>(e.getStatusCode());
		}
	}

	/********************************************************
	 * >>Librarian Functions
	 ********************************************************/

	@RequestMapping(path = "/librarian/branches", method = RequestMethod.GET)
	public Branch[] readBranchesLibrarian() {
		ResponseEntity<Branch[]> response = restTemplate.getForEntity("http://localhost:8082/lms/librarian/branches",
				Branch[].class);
		return response.getBody();
	}

	@RequestMapping(path = "/librarian/bookcopies/{branchId}", method = RequestMethod.GET)
	public BookCopies[] readBookCopiesByBranchLibrarian(@PathVariable("branchId") Long branchId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("branchId", branchId);
		ResponseEntity<BookCopies[]> response = restTemplate
				.getForEntity("http://localhost:8082/lms/librarian/bookcopies/{branchId}", BookCopies[].class, params);
		return response.getBody();
	}

	@RequestMapping(path = "/librarian/branches", method = RequestMethod.PUT)
	public String updateBranchLibrarian(@RequestBody Branch branch) {
		restTemplate.put("http://localhost:8081/lms/librarian/branches", branch);
		return "Update Success!";
	}

	@RequestMapping(path = "/librarian/bookcopies", method = RequestMethod.PUT)
	public String updateBookCopiesLibrarian(@RequestBody BookCopies bc) {
		restTemplate.put("http://localhost:8081/lms/librarian/bookCopies", bc);
		return "Update Success!";
	}

	/*********
	 * BORROWER FUNCTIONS
	 */
	@RequestMapping(path = "/borrower/bookloans/{cardNo}", method = RequestMethod.GET)
	public BookLoans[] readLoansByCardNoBorrower(@PathVariable("cardNo") Long cardNo) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("cardNo", cardNo);
		ResponseEntity<BookLoans[]> response = restTemplate
				.getForEntity("http://localhost:8083/lms/borrower/bookloans/{cardNo}", BookLoans[].class, params);
		return response.getBody();
	}

	@RequestMapping(path = "/borrower/bookloans", method = RequestMethod.POST)
	public String createLoanBorrower(@RequestBody BookLoans loans) {
		restTemplate.postForEntity("http://localhost:8083/lms/borrower/bookloans", loans, BookLoans.class);
		return "Create Successful!";
	}

	@RequestMapping(path = "/borrower/bookloans", method = RequestMethod.PUT)
	public String updateLoanBorrower(@RequestBody BookLoans loans) {
		restTemplate.put("http://localhost:8083/lms/borrower/bookloans", loans);
		return "Update Successful!";
	}

	@RequestMapping(path = "/borrower/branches", method = RequestMethod.GET)
	public Branch[] readBranchesBorrower() {
		ResponseEntity<Branch[]> response = restTemplate.getForEntity("http://localhost:8083/lms/borrower/branches",
				Branch[].class);
		return response.getBody();
	}

	@RequestMapping(path = "/borrower/bookcopies/{branchId}", method = RequestMethod.GET)
	public BookCopies[] readBookCopiesByBranchBorrower(@PathVariable("branchId") Long branchId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("branchId", branchId);
		ResponseEntity<BookCopies[]> response = restTemplate
				.getForEntity("http://localhost:8083/lms/borrower/bookcopies/{branchId}", BookCopies[].class, params);
		return response.getBody();
	}

	@RequestMapping(path = "/borrower/bookcopies", method = RequestMethod.PUT)
	public String updateBookCopiesBorrower(@RequestBody BookCopies bc) {
		restTemplate.put("http://localhost:8081/lms/librarian/bookCopies", bc);
		return "Update Success!";
	}
}
