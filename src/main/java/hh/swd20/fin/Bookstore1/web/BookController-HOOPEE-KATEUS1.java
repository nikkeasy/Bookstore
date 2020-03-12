package hh.swd20.fin.Bookstore1.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.fin.Bookstore1.domain.Book;
import hh.swd20.fin.Bookstore1.domain.BookRepository;
import hh.swd20.fin.Bookstore1.domain.Category;
import hh.swd20.fin.Bookstore1.domain.CategoryRepository;

@Controller
public class BookController {
	
	


		// Spring-alusta luo sovelluksen käynnistyessä BookRepository-rajapintaa toteuttavan luokan olion
		// ja kytkee olion BookController-luokasta luodun olion attribuuttiolioksi 

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired 
	private CategoryRepository categoryRepository;
	
	
	@RequestMapping(value="/allbooks", method = RequestMethod.GET)
	public String getAllBooks(Model model) { 
		List<Book> books = (List<Book>) bookRepository.findAll();
		model.addAttribute("books", books);

		// Haetaan muistipohjaisesta tietokannasta kirjat listaan 
	//model.addAttribute("books", bookRepository.findAll());							// Palautetaan sopivan käyttöliittymätemplaten (html) nimi
	return "booklist";
	}
	
	@RequestMapping(value = "/allcats", method = RequestMethod.GET)
	public String getAllCategories(Model model) {
		//Haetaan tietokannasta kirjat listaan
		List<Category> categories = (List<Category>) categoryRepository.findAll();
		//Laitetaan mddel-mappiin kategorialista templatea varten
		model.addAttribute("catslist", categories);
		//Palautetaan sopivan templaten nimi.
		return "categorieslist";
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
	
	@RequestMapping(value = "/newcat", method = RequestMethod.GET)
	public String getNewCatForm(Model model) {
		model.addAttribute("category", new Category());
		return "newcategoryform";
	}
	
	// Kirjalomakkeella syötettyjen tietojen vastaanotto ja TALLENNUS (POST)!!!
	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute @Valid Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("catslist", categoryRepository.findAll());
			return "addbook";
			}
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






//RESTful service to get all books.
@RequestMapping(value="/books", method = RequestMethod.GET)
public @ResponseBody List<Book> bookListRest() {
	return (List<Book>) bookRepository.findAll();
}

//RESTful service to get one book by id.
@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
public @ResponseBody Optional <Book> findBookRest(@PathVariable Long id) { // Täytyy olla id, ei toimi bookId tässä!
	return bookRepository.findById(id); // Laitetaan util-importti optional, jotta voidaan käyttää PathVariablea myös tässä.

}

   // RESTful service to save new car 
@RequestMapping(value="/books", method = RequestMethod.POST) // POST kertoo, että tallennetaan tietoa.
public @ResponseBody Book saveBookRest(@RequestBody Book book) {	
	return bookRepository.save(book);
}

// Home page of REST services
@RequestMapping(value="/resthome", method = RequestMethod.GET)
public String getRestHome() {	
	return "resthomepage"; // resthomepage.html, ei oma tiedosto vaan tulee importtien kautta.
}
}
/* logger.debug("editBookForm() : {}", bookId);

bookRepository.findById(bookId); 
model.addAttribute("book", new Book()); 
public String editBookForm(@PathVariable("id") Long bookId, Model model)
*/ 