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
public class Author implements Serializable {
    public static final Long serialVersionUID = 1L;
    private long id;
    private String firstName;
    private String lastName;
}
