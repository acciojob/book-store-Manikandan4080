package com.driver;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    public BookRepository(){

    }
    HashMap<Integer,Book> bookDb = new HashMap<>();

    public Book save(Book book){

        bookDb.put(book.getId(), book);
        return book;
    }

    public Book findBookById(int id){

        return bookDb.get(id);

    }

    public List<Book> findAll(){
        List<Book> booklist = new ArrayList<>();
        for(Integer id: bookDb.keySet()) {
            booklist.add(bookDb.get(id));
        }

        return booklist;
    }

    public String deleteBookById(int id){

        if(bookDb.containsKey(id)){
            bookDb.remove(id);
        }
        return "book is deleted";
    }

    public String deleteAll(){
        bookDb.clear();
        return "all books are deleted";
    }

    public List<Book> findBooksByAuthor(String author){
        List<Book> bookAuthorList = new ArrayList<>();

        for(Integer id: bookDb.keySet()){
            if(bookDb.get(id).getAuthor().equals(author)){
                bookAuthorList.add(bookDb.get(id));
            }
        }
        return bookAuthorList;
    }

    public List<Book> findBooksByGenre(String genre){
        List<Book> genreBookList = new ArrayList<>();

        for(Integer id: bookDb.keySet()){
            if(bookDb.get(id).getGenre().equals(genre)){
                genreBookList.add(bookDb.get(id));
            }
        }
        return genreBookList;
    }
}