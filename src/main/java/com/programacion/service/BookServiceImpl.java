package com.programacion.service;

import com.programacion.config.BookMapper;
import com.programacion.dto.Book;
import io.helidon.dbclient.DbClient;
import io.helidon.dbclient.DbRow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class BookServiceImpl implements BookService {

    @Inject
    private DbClient dbClient;
    @Inject
    private BookMapper bookMapper;

    @Override
    public List<?> findAll() {
//        Single<Optional<DbRow>> execute = this.dbClient
//                .execute(exe -> exe.namedGet("find-all"))
//                .thenAccept(opt -> opt.ifPresent());
        return null;
    }

    @Override
    public Book findOne(int id) throws ExecutionException, InterruptedException {
        Optional<DbRow> dbRow = this.dbClient
                .execute(exe -> exe.createGet("SELECT * FROM books WHERE id = :id").addParam("id", 1).execute()).get();

        return dbRow.isPresent() ? this.bookMapper.read(dbRow.get()) : new Book();
    }

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public Book update(Book book) {
        return null;
    }

    @Override
    public Book delete(int id) {
        return null;
    }
}
