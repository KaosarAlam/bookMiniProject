package com.miniProject.miniProjectTask.model;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

public class BookDto {
    private Long id;

    private String name;

    private Boolean status;

    private LocalDate dueDate;

    private Long nextUser;

}
