package com.programacion.controller;

import com.programacion.dto.Book;
import com.programacion.service.BookService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.concurrent.ExecutionException;

@Path("/book")
public class BookController {
    @Inject
    private BookService bookService;

    @GET
    @Path("/{id}")
    public Book getOneBook(@PathParam("id") int id) throws ExecutionException, InterruptedException {
        return this.bookService.findOne(id);
    }
}
