package com.miniProject.miniProjectTask.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "dueDate")
    private LocalDate dueDate;
    @Column(name="nextUser")
    private Long nextUser;
    @OneToOne(mappedBy = "book")
    private History history;
}
