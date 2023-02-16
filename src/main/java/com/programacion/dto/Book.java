package com.programacion.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    public static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @ManyToOne
    private Author author;
    private String isbn;
    private String title;
    private double price;
}
