package com.miniProject.miniProjectTask.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "borrowed_book_id", nullable = false)
    private Long borrowedBookId;
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
