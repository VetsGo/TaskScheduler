package com.example.taskscheduler.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MyUser")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "name", nullable = true, length = 100)
    private String name;
    @Column(name = "email", nullable = true, length = 100)
    private String email;
    @Column(name = "password", nullable = true, length = 60)
    @JsonIgnore
    private byte[] password;
}