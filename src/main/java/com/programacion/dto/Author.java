package com.programacion.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Author implements Serializable {
    public static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
}
