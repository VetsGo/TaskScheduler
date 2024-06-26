package com.example.taskscheduler.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TASK")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskId")
    private Integer taskId;
    @Column(name = "taskName", nullable = true, length = 100)
    private String taskName;
    @Column(name = "description", nullable = true, length = 100)
    private String description;
    @Column(name = "status", nullable = true, length = 50)
    private String status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId", nullable = true)
    private Project projectId;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TASK_USER",
            joinColumns = @JoinColumn(name = "taskId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private Set<User> users = new HashSet<>();
}