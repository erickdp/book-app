package com.programacion.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author implements Serializable {
    public static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
}
