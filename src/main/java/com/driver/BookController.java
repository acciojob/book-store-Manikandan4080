package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    BookService bookService;

    // One example controller, make the rest by yourself
    @PostMapping("/create-book")
    public ResponseEntity createBook(@RequestBody Book book){
        Book newbook = bookService.createBook(book);
        return new ResponseEntity<>(newbook, HttpStatus.CREATED);
    }
    @GetMapping("/get-book-by-id/{id}")
    public Book findBookById(@PathVariable("id")String id){
        return bookService.findBookById(id);
    }

    @DeleteMapping("/delete-book-by-id/{id}")
    public String deleteBookById(@PathVariable("id") String id){
        return bookService.deleteBookById(id);
    }

    @GetMapping("/get-all-books")
    public List<Book> getAllBooks(){
        return bookService.findAllBooks();

    }

    @GetMapping("/get-books-by-author/{name}")
    public List<Book> getBookByAuthor(@PathVariable("name") String authorName){
        return bookService.findBooksByAuthor(authorName);

    }
    @GetMapping("get-books-by-genre/{genre}")
    public List<Book> getBookByGenre(@PathVariable("genre") String genreName){
        return bookService.findBooksByGenre(genreName);
    }
    @DeleteMapping("delete-all-books")
    public String deleteAllBooks(){
        return bookService.deleteAllBooks();
    }
}