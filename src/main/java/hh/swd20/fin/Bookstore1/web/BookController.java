package hh.swd20.fin.Bookstore1.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
	// Show all books (login required) 
	
	@RequestMapping(value="/login")
	public String login () {
		return "login";
	}
	
	@RequestMapping(value="/booklist")
	public String getAllBooks(Model model) { 		// Haetaan muistipohjaisesta tietokannasta kirjat listaan 
	model.addAttribute("books", bookRepository.findAll());							// Palautetaan sopivan käyttöliittymätemplaten (html) nimi
	return "booklist";
	}
	

// RESTful service to get all books
	
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> studentListRest() {	
        return (List<Book>) bookRepository.findAll();
    }    
	 
	
    // RESTful service to get a book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findStudentRest(@PathVariable Long BookId) {	
    	return bookRepository.findById(BookId);
    }       
	
    

	
	// tyhjän kirjalomakkeen muodostaminen 
	@RequestMapping(value= "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book()); 
		model.addAttribute("categories", categoryRepository.findAll());
		// Tyhjä kirja-olio
		return "addbook";
	}
	
	// Kirjalomakkeella syötettyjen tietojen vastaanotto ja TALLENNUS (POST)!!!
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book) { 
		bookRepository.save(book);  	// Talletetaan YHDEN kirjan tiedot h2-tietokantaan 
		return "redirect:/booklist"; // booklist-endpointin kutsu 
	}
	
	// KIRJAN POISTO 
	@RequestMapping(value ="/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	// KIRJAN MUOKKAUS 
		@RequestMapping(value="/edit/{id}")
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
			return "redirect:../booklist";
		}
	
}
/* logger.debug("editBookForm() : {}", bookId);

bookRepository.findById(bookId); 
model.addAttribute("book", new Book()); 
public String editBookForm(@PathVariable("id") Long bookId, Model model)
*/ 