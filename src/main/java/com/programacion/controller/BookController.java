package com.programacion.controller;

import com.programacion.dto.Book;
import com.programacion.service.BookService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Path("/book")
public class BookController {
    @Inject
    private BookService bookService;

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Book findOneById(@PathParam("id") int id) throws ExecutionException, InterruptedException {
        return this.bookService.findOne(id);
    }

    @GET
    @Produces("application/json")
    public List<?> findAll() throws ExecutionException, InterruptedException {
        return this.bookService.findAll();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Map<String, Long> update(Book book) {
        return Map.of("rowsChanged", this.bookService.update(book));
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Map<String, Long> save(Book book) {
        return Map.of("rowsChanged", this.bookService.save(book));
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Map<String, Long> delete(@PathParam("id") int id) {
        return Map.of("rowsChanged", this.bookService.delete(id));
    }
}
