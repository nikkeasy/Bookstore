package hh.swd20.fin.Bookstore1.domain;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

	
	
	public Book findByName (String name);
}
