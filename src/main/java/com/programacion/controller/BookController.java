package com.programacion.controller;

import com.programacion.dto.Book;
import com.programacion.service.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.concurrent.ExecutionException;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.ACCEPTED;

@ApplicationScoped
@Path("api/book")
public class BookController {
    @Inject
    private BookService bookService;

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Book findOneById(@PathParam("id") long id) throws ExecutionException, InterruptedException {
        return this.bookService.findOne(id);
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response findAll() throws ExecutionException, InterruptedException {
        return Response.status(ACCEPTED).entity(bookService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response update(@PathParam("id") long id, Book book) {
//        return Map.of("rowsChanged", this.bookService.update(id, book));
        this.bookService.update(id, book);
        return Response.accepted().build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response save(Book book) {
//        return Map.of("rowsChanged", this.bookService.save(book));
        this.bookService.save(book);
        return Response.accepted().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
//        return Map.of("rowsChanged", this.bookService.delete(id));
        this.bookService.delete(id);
        return Response.accepted().build();
    }
}
