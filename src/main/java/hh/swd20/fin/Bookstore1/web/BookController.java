package hh.swd20.fin.Bookstore1.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.fin.Bookstore1.domain.Book;
import hh.swd20.fin.Bookstore1.domain.BookRepository;
import hh.swd20.fin.Bookstore1.domain.CategoryRepository;

@Controller
public class BookController {
	
	


		// Spring-alusta luo sovelluksen käynnistyessä BookRepository-rajapintaa toteuttavan luokan olion
		// ja kytkee olion BookController-luokasta luodun olion attribuuttiolioksi 

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired 
	private CategoryRepository categoryRepository;
	
	
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public String getAllBooks(Model model) { 		// Haetaan muistipohjaisesta tietokannasta kirjat listaan 
	model.addAttribute("books", bookRepository.findAll());							// Palautetaan sopivan käyttöliittymätemplaten (html) nimi
	return "booklist";
	}
	
	
	//List<Book> books = (List <Book>) bookRepository.findAll();
	// Laitetaan model-mappiin autolista thymeleaf-templatea varten 
	
	// tyhjän kirjalomakkeen muodostaminen 
	
	
	@RequestMapping(value= "/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book()); 
		model.addAttribute("categories", categoryRepository.findAll());
		// Tyhjä kirja-olio
		return "addbook";
	}
	
	// Kirjalomakkeella syötettyjen tietojen vastaanotto ja TALLENNUS (POST)!!!
	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book) { 
		bookRepository.save(book);  	// Talletetaan YHDEN kirjan tiedot h2-tietokantaan 
		return "redirect:/books"; // books-endpointin kutsu 
	}
	
	// KIRJAN POISTO 
	@RequestMapping(value ="/deletebook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		return "redirect:../books";
	}
	
	// KIRJAN MUOKKAUS 
		@RequestMapping(value="/editbook/{id}")
		public String editBook(@PathVariable("id") Long bookId, Model model) {
		    model.addAttribute("book", bookRepository.findById(bookId).get()); // Miksi eri metodi kuin departmentsissä? 
		    model.addAttribute("categories", categoryRepository.findAll()); // Miksi tässä findAll 
		 
		return "editbook";
	}
		//päivitys
		@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
		public String saveEdit(@PathVariable("id")Long id, Book book, Model model) {
			book.setId(id);
			bookRepository.save(book);
			return "redirect:../books";
		}
	
}
/* logger.debug("editBookForm() : {}", bookId);

bookRepository.findById(bookId); 
model.addAttribute("book", new Book()); 
public String editBookForm(@PathVariable("id") Long bookId, Model model)
*/ 