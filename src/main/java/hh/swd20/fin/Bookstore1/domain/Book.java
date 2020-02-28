package hh.swd20.fin.Bookstore1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity 	

public class Book {
	// ATTRIBUUTIT 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private Long id; // new attribute id 
	private String title; 
	private String author; 
	private Integer year; 
	private String isbn; 
	private double price; 
	
	@ManyToOne	
	@JoinColumn(name ="kategoriaid")
	private Category kategoria; 
	

	
	// Konstruktorit 
	public Book() {
		super();
		this.id = null; 
		this.title = null;
		this.author = null; 
		this.year = 0; 
		this.isbn = null; 
		this.price = 0;
		// TODO Auto-generated constructor stub
	}
	public Book(String title, String author, Integer year, String isbn, double price) {
		super();
		
		this.title = title;
		this.author = author;
		this.year = year;
		this.isbn = isbn;
		this.price = price;
	}
	
	// SETTERIT 
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	// GETTERIT 
	
	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public double getPrice() {
		return price;
	}
	
// toString
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", year=" + year + ", isbn=" + isbn
				+ ", price=" + price + "]";
	}
	



	
}
