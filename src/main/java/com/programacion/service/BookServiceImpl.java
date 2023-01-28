package com.programacion.service;

import com.programacion.config.BookMapper;
import com.programacion.dto.Book;
import io.helidon.common.reactive.Multi;
import io.helidon.dbclient.DbClient;
import io.helidon.dbclient.DbRow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookServiceImpl implements BookService {

    @Inject
    private DbClient dbClient;
    @Inject
    private BookMapper bookMapper;

    @Override
    public List<Book> findAll() throws ExecutionException, InterruptedException {
        Multi<DbRow> execute = this.dbClient
                .execute(exe -> exe.createQuery("SELECT a.id as author_id, a.first_name, a.last_name, b.id, b.title, b.isbn, b.price FROM books b JOIN authors a ON b.author_id = a.id").execute());
        return execute.collectList().get().stream().map(this.bookMapper::read).collect(Collectors.toList());
    }

    @Override
    public Book findOne(int id) throws ExecutionException, InterruptedException {
        Optional<DbRow> dbRow = this.dbClient
                .execute(exe -> exe.createGet("SELECT a.id as author_id, a.first_name, a.last_name, b.id, b.title, b.isbn, b.price FROM books b JOIN authors a ON b.id = a.id WHERE b.id = :id").addParam("id", id).execute()).get();

        return dbRow.isPresent() ? this.bookMapper.read(dbRow.get()) : new Book();
    }

    @Override
    public long save(Book book) {
        var rowsChanged = 0L;
        try {
            rowsChanged = this.dbClient
                    .execute(exec -> exec
                            .insert("INSERT INTO books (author_id, isbn, title, price) VALUES(?, ?, ?, ?)",
                                    book.getAuthor().getId(), book.getIsbn(), book.getTitle(), book.getPrice()
                            )).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsChanged;
    }

    @Override
    public long update(Book book) {
        var rowsChanged = 0L;
        try {
            rowsChanged = this.dbClient
                    .execute(exec -> exec
                            .update("UPDATE books SET author_id = ?, isbn = ?, title = ?, price = ? WHERE id = ?",
                                    book.getAuthor().getId(), book.getIsbn(), book.getTitle(), book.getPrice(), book.getId()
                            )).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsChanged;
    }

    @Override
    public long delete(int id) {
        var rowsChanged = 0L;
        try {
            rowsChanged = this.dbClient
                    .execute(exec -> exec
                            .delete("DELETE FROM books WHERE id = ?",
                                    id
                            )).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsChanged;
    }
}
