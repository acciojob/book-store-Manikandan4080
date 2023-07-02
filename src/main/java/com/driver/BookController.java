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
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book newbook = bookService.createBook(book);
        return new ResponseEntity<>(newbook, HttpStatus.CREATED);
    }

    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> getBokById(@PathVariable("id") String bookId){
        Book book = bookService.findBookById(bookId);
        if(book == null) throw new RuntimeException("Book Not Found");
        return new ResponseEntity<>(book, HttpStatus.FOUND);
    }

    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.findAllBooks();
        return new ResponseEntity<>(books, HttpStatus.FOUND);
    }

    @GetMapping("/books/get-books-by-author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam("author") String author){
        List<Book> authorBooks = bookService.findBooksByAuthor(author);
        if(authorBooks == null) throw new RuntimeException("Author Not Found");

        return new ResponseEntity<>(authorBooks, HttpStatus.FOUND);
    }

    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam("genre") String genre){
        List<Book> books = bookService.findBooksByGenre(genre);
        if(books == null) throw new RuntimeException("Genre Not Found");

        return new ResponseEntity<>(books, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") String id){
        bookService.deleteBookById(id);
        return new ResponseEntity<>("Book deleted", HttpStatus.OK);
    }

    @DeleteMapping("/delete-all-books")
    public ResponseEntity<String> deleteAllBooks(){
        bookService.deleteAllBooks();
        return new ResponseEntity<>("All Books are deleted", HttpStatus.OK);
    }
}
