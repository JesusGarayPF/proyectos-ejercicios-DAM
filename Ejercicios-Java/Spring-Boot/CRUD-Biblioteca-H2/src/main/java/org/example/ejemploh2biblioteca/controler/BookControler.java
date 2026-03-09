package org.example.ejemploh2biblioteca.controler;

import org.example.ejemploh2biblioteca.entities.Book;
import org.example.ejemploh2biblioteca.repositories.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookControler {

    private final BookRepository bookRepository;

    public BookControler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/hello-world")
    public String metodo(){
        return "Hello world";
    }
    @RequestMapping(value="/random-book", method = RequestMethod.GET)
    public Book getRandomBookGet(){
        return new Book(8989898L, "El Quijote", "Un señor loco flipa mucho");
    }
    @RequestMapping(value="/random-book", method = RequestMethod.POST)
    public Book getRandomBookPost(){
        return new Book(8989898L, "El Quijote", "Un señor loco flipa mucho");
    }
    @GetMapping(value="/random-book2")
    public ResponseEntity<Book> getRandomBookGetOtravez(){
        //Book book =  new Book(8989898L, "El Quijote", "Un señor loco flipa mucho");
        List<Book> allBooks = bookRepository.findAll();
        int randomIndex = (int) (Math.random() * allBooks.size());
        return ResponseEntity.ok(allBooks.get(randomIndex));
    }
}
