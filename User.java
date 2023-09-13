package com.miniProject.miniProjectTask.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "first_name", nullable = false)
        private String firstName;
        @Column(name = "last_name", nullable = false)
        private String lastName;
        @Column(nullable = false, unique = true)
        private String email;
        @Column(nullable = false)
        private String password;
        @Column(nullable = false)
        private String address;
        @OneToOne(mappedBy = "user")
        private History history;
}
