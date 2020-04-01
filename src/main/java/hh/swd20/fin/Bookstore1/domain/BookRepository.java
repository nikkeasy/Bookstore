package hh.swd20.fin.Bookstore1.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends CrudRepository<Book, Long> {

	
	
    List<Book> findByName(String name); 
    List<Book> findByYear(int year); 
    List<Book> findByIsbn(String isbn);
    List<Book> findByAuthor(String author);
   
}
