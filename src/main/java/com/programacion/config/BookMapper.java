package com.programacion.config;

import com.programacion.dto.Book;
import io.helidon.dbclient.DbMapper;
import io.helidon.dbclient.DbRow;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class BookMapper implements DbMapper<Book> {
    @Override
    public Book read(DbRow row) {
        var id = row.column("id");
        var authorId = row.column("author_id");
        var isbn = row.column("isbn");
        var title = row.column("title");
        var price = row.column("price");

        return new Book(
                id.as(Integer.class),
                authorId.as(Integer.class),
                isbn.as(String.class),
                title.as(String.class),
                price.as(Double.class)
        );
    }

    @Override
    public Map<String, ?> toNamedParameters(Book value) {
        var map = new HashMap<String, Object>();
        map.put("id", value.getId());
        map.put("authorId", value.getAuthorId());
        map.put("isbn", value.getIsbn());
        map.put("title", value.getTitle());
        map.put("price", value.getPrice());
        return map;
    }

    @Override
    public List<?> toIndexedParameters(Book value) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(value.getId());
        list.add(value.getAuthorId());
        list.add(value.getIsbn());
        list.add(value.getTitle());
        list.add(value.getPrice());
        return list;
    }
}
