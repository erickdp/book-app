package com.programacion.controller;

import com.programacion.dto.Book;
import com.programacion.service.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import static jakarta.ws.rs.core.Response.Status.ACCEPTED;

@ApplicationScoped
@Path("api/book")
public class BookController {
    @Inject
    private BookService bookService;

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Book findOneById(@PathParam("id") long id) throws ExecutionException, InterruptedException {
        return this.bookService.findOne(id);
    }

    @GET
    @Produces("application/json")
    public Response findAll() throws ExecutionException, InterruptedException {
        return Response.status(ACCEPTED).entity(bookService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Map<String, Long> update(@PathParam("id") long id, Book book) {
        return Map.of("rowsChanged", this.bookService.update(id, book));
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
    public Map<String, Long> delete(@PathParam("id") long id) {
        return Map.of("rowsChanged", this.bookService.delete(id));
    }
}
