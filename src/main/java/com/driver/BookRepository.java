package com.driver;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    Map<Integer, Book> bookMap;
    Map<String, List<Book>> authorMap;
    Map<String, List<Book>> genreMap;
    public BookRepository(){
        this.bookMap = new HashMap<>();
        this.authorMap = new HashMap<>();
        this.genreMap = new HashMap<>();
    }

    public Book save(Book book){
        bookMap.put(book.getId(), book);

        String author = book.getAuthor();
        String genre = book.getGenre();

        List<Book> authorBooks = authorMap.getOrDefault(author, new ArrayList<>());
        authorBooks.add(book);
        authorMap.put(author, authorBooks);

        List<Book> genreBooks = genreMap.getOrDefault(genre, new ArrayList<>());
        genreBooks.add(book);
        genreMap.put(genre, genreBooks);

        return book;
    }

    public Book findBookById(int id){
        if(!bookMap.containsKey(id)) return null;
        return bookMap.get(id);
    }

    public List<Book> findAll(){
        List<Book> books = new ArrayList<>();
        for(int id : bookMap.keySet())
            books.add(bookMap.get(id));

        return books;
    }

    public void deleteBookById(int id){
        if(bookMap.containsKey(id)){
            Book book = bookMap.get(id);
            bookMap.remove(id);

            List<Book> authorBooks = authorMap.get(book.getAuthor());
            authorBooks.remove(book);
            List<Book> genreBooks = genreMap.get(book.getGenre());
            genreBooks.remove(book);

            authorMap.put(book.getAuthor(), authorBooks);
            genreMap.put(book.getGenre(), genreBooks);
        }
        else{
            throw new RuntimeException("Book Not Exist");
        }

        return;
    }

    public void deleteAll(){
        bookMap.clear();
        authorMap.clear();
        genreMap.clear();
    }

    public List<Book> findBooksByAuthor(String author){
        if(!authorMap.containsKey(author)) return null;
        return authorMap.get(author);
    }

    public List<Book> findBooksByGenre(String genre){
        if(!genreMap.containsKey(genre)) return null;
        return genreMap.get(genre);
    }
}
