package hh.swd20.fin.Bookstore1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.fin.Bookstore1.domain.Book;
import hh.swd20.fin.Bookstore1.domain.BookRepository;
import hh.swd20.fin.Bookstore1.domain.Category;
import hh.swd20.fin.Bookstore1.domain.CategoryRepository;
import hh.swd20.fin.Bookstore1.domain.User;
import hh.swd20.fin.Bookstore1.domain.UserRepository; 


@RunWith(SpringRunner.class)
@DataJpaTest

class Bookstore1ApplicationTests {

	@Test
	void contextLoads() {
	}
	
	
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private CategoryRepository categoryRepository; 
    
    @Autowired
    private UserRepository userRepository; 
	
	   
    // Test if new book and category can be created 
    @Test
    public void createNewBook() {
    	Book book = new Book("Mikki hiiren seikkailut", "Teemu Kosonen", 1999, "123456-111", 50.20, new Category("Horror"));
    	bookRepository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    

    @Test
    public void createNewUser() {
    	User user = new User("monkey666", "gngj84jg983", "ADMIN", "Monkeyboy@mail.com");
    	userRepository.save(user);
    	assertThat(user.getId()).isNotNull();
    }
    
    @Test 
    public void findByNameTest () { 
    	List<Book> books = bookRepository.findByName("Vegaanin opas");
    	assertThat(books).hasSize(1); 
    	assertThat(books.get(0).getName()).isEqualTo("Vegaanin opas"); 
    }
    
    @Test
    public void deleteBooks () { 
    	Book book = new Book("Koleraepidemia", "Teemu Kolera Khelester", 2080, "111111-69", 39.01, categoryRepository.findByName("Kauhu").get(0));
    	bookRepository.save(book); 
    	bookRepository.deleteAll(); 
    	assertThat(bookRepository.count()).isEqualTo(0); 
    	
    }
    

}
