package com.programacion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    public static final Long serialVersionUID = 1L;
    private long id;
    private Author author;
    private String isbn;
    private String title;
    private double price;
}
