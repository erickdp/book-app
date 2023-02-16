package com.programacion.service;

import com.programacion.dto.Book;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BookService {
    List<Book> findAll() throws ExecutionException, InterruptedException;
    Book findOne(long id) throws ExecutionException, InterruptedException;
    void save(Book book);
    void update(long id, Book book);
    void delete(long id);
}