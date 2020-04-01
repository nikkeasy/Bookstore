package hh.swd20.fin.Bookstore1;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.fin.Bookstore1.domain.Book;
import hh.swd20.fin.Bookstore1.domain.BookRepository;
import hh.swd20.fin.Bookstore1.domain.Category;
import hh.swd20.fin.Bookstore1.web.BookController;
import hh.swd20.fin.Bookstore1.web.UserDetailServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

	@Test
	void contextLoadsOther() {
	}
	
	// Smoke test to see if any of the controllers below 
	// actually contain anything


    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookController bookController; 
    
    @Autowired
    private UserDetailServiceImpl repository; 

   
    @Test
    public void contexLoads0() throws Exception {
    	assertThat(bookRepository).isNotNull();
    }
    
    @Test
    public void contexLoads1() throws Exception {
    	assertThat(bookController).isNotNull();
    }
    
    @Test
    public void contexLoads2() throws Exception {
    	assertThat(repository).isNotNull();
    }
 
}