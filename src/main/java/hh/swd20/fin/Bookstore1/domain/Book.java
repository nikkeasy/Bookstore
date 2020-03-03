package hh.swd20.fin.Bookstore1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity 	
public class Book {
	@Id		// ATTRIBUUTIT 
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private Long id; // new attribute id 
	private String name; 
	private String author; 
	private Integer year; 
	private String isbn; 
	private double price; 
	
	// PERUS attribuutit 
	
	@ManyToOne	
	@JoinColumn(name = "categoryId")
	private Category category; 
	
	public Book () {} 
	
	public Book(String name, String author, Integer year, String isbn, double price, Category category) {
		super();
		this.name = name;
		this.author = author;
		this.year = year;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}



	
	// GETTERIT 
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
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
	
	// SETTERIT 
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
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

	
// toString
	
	@Override
	public String toString() {
		if (this.category != null)
			return "Book [id=" + id + ", name=" + name + ", author=" + author + ", year=" + year + ", isbn=" + isbn
					+ ", price=" + "price =" + ", category =" + this.getCategory() + "]";  
		else	
			return "Book [id=" + id + ", name=" + name + ", author=" + author + ", year=" + year + ", isbn=" + isbn
				+ ", price=" + price +  "]";
	}
}	



	

