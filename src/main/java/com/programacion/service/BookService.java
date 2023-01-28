package com.programacion.service;

import com.programacion.dto.Book;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BookService {
    List<?> findAll();
    Book findOne(int id) throws ExecutionException, InterruptedException;
    Book save(Book book);
    Book update(Book book);
    Book delete(int id);
}
