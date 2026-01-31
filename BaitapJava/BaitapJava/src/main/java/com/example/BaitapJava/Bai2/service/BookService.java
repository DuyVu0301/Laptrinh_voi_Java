package com.example.BaitapJava.Bai2.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.BaitapJava.Bai2.model.Book;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();
    public BookService() {
        books.add(new Book(1, "Lập trình Java Cơ Bản", "Nguyễn Văn A"));
        books.add(new Book(2, "Spring Boot cho người mới", "Trần Thị B"));
        books.add(new Book(3, "Cấu trúc dữ liệu và Giải thuật", "Phạm Văn C"));
        books.add(new Book(4, "Thiết kế hệ thống Microservices", "Lê Văn D"));
    }
    public  List<Book> getAllBooks(){
        return books;
    }
    public Book getBookById(int id){
        return books.stream().filter(book -> book.getId()==id).findFirst().orElse(null);
    }
    public void addBook(Book book){
        books.add(book);
    }
    public void updateBook(int id, Book updatedBook){
        books.stream()
                .filter(book -> book.getId()==id)
                .findFirst()
                .ifPresent(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                });
            }
            public void deleteBook(int id){
                books.removeIf(book -> book.getId()==id);
            }
    }
    

