package com.example.BaitapJava.Bai2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.BaitapJava.Bai2.model.Book;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    public BookService() {
        addBook(new Book(null, "Lập trình Java Cơ Bản", "Nguyễn Văn A"));
        addBook(new Book(null, "Spring Boot cho người mới", "Trần Thị B"));
        addBook(new Book(null, "Cấu trúc dữ liệu và Giải thuật", "Phạm Văn C"));
        addBook(new Book(null, "Thiết kế hệ thống Microservices", "Lê Văn D"));
    }

    public List<Book> getAllBooks() {
        return books;
    }
    public Optional<Book> getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    public void addBook(Book book) {
        book.setId(nextId++); 
        books.add(book);
    }

    public void updateBook(Book updatedBook) {
        books.stream()
                .filter(book -> book.getId().equals(updatedBook.getId()))
                .findFirst()
                .ifPresent(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                });
    }

    public void deleteBook(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}