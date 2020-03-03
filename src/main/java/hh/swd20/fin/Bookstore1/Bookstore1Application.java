package hh.swd20.fin.Bookstore1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hh.swd20.fin.Bookstore1.domain.Book;
import hh.swd20.fin.Bookstore1.domain.BookRepository;
import hh.swd20.fin.Bookstore1.domain.Category; 
import hh.swd20.fin.Bookstore1.domain.CategoryRepository;

@SpringBootApplication
public class Bookstore1Application {

	private static final Logger log = LoggerFactory.getLogger(Bookstore1Application.class); // Uusi loggeriattribuutti 
	
	public static void main(String[] args) {
		SpringApplication.run(Bookstore1Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			
			log.info("Save a few categories");
			categoryRepository.save(new Category("Kauhu"));
			categoryRepository.save(new Category("Oppaat")); 
			categoryRepository.save(new Category("Kaunokirjallisuus")); 
			categoryRepository.save(new Category("Oppimismateriaali")); 
			categoryRepository.save(new Category("Mysteerikirjat")); 
			categoryRepository.save(new Category("Realistinen fiktio")); 

		
			log.info("Save a few books"); 
			bookRepository.save(new Book("Vegaanin opas", "Vegaanimies123", 1994, "11111-1", 52, categoryRepository.findByName("Oppaat").get(0)));
			bookRepository.save(new Book("Urheilijan venyttelyopas", "Matti Meikäläinen", 2020, "22222-1", 190.20, categoryRepository.findByName("Oppaat").get(0)));
			bookRepository.save(new Book("How to make mon€y", "Mukhtar Mahmud", 2001, "10101010-1", 24.95, categoryRepository.findByName("Oppaat").get(0)));
			bookRepository.save(new Book("Vigilante", "Kady Cross", 2017, "13: 9781489220820", 24.95, categoryRepository.findByName("Realistinen fiktio").get(0)));

			
			
			

			
			log.info("All books:"); // Toistorakenne alla fetchaamiseen liittyen 
			for (Book book : bookRepository.findAll()) { 
				log.info(book.toString());
			}

	//		Näyttää tältä:
	//
	//				2020-02-19 18:29:03.603  INFO 12072 --- [  restartedMain] h.s.f.Bookstore1.Bookstore1Application   : Book [id=1, title=Vegaanin opas, author=Vegaanimies123, year=1994, isbn=11111-1, price=52.0]
	//				2020-02-19 18:29:03.604  INFO 12072 --- [  restartedMain] h.s.f.Bookstore1.Bookstore1Application   : Book [id=2, title=Urheilijan venyttelyopas, author=Matti Meikäläinen, year=2020, isbn=22222-1, price=190.2]
	//				2020-02-19 18:29:03.604  INFO 12072 --- [  restartedMain] h.s.f.Bookstore1.Bookstore1Application   : Book [id=3, title=How to make mon€y, author=Mukhtar Mahmud, year=2001, isbn=10101010-1, price=24.95]
			
			
			
			
			
			
			
			/* Alla eräs tapa tehdä tiedon tallennus H2:een 
			 * 
			 * 
			Book b1 = new Book("Vegaanin opas", "Vegaanimies123", 1994, "11111-1", 52);
			Book b2 = new Book("Urheilijan venyttelyopas", "Matti Meikäläinen", 2020, "22222-1", 190.20);
			Book b3 = new Book("How to make mon€y", "Mukhtar Mahmud", 2001, "10101010-1", 24.95);
			
			
			repository.save(b1);
			repository.save(b2);
			repository.save(b3);
			*/ 
			
		};
		
		
	}
	
	
}
