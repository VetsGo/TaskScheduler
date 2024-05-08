package com.example.taskscheduler.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PROJECT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectId")
    private Integer projectId;
    @Column(name = "projectName", nullable = false, length = 100)
    private String projectName;
    @Column(name = "description", nullable = false, length = 100)
    private String description;
    @Column(name = "startDate", length = 10)
    private Date startDate;
    @Column(name = "endDate", length = 10)
    private Date endDate;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "PROJECT_USER",
            joinColumns = @JoinColumn(name = "projectId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private List<User> userId;
}