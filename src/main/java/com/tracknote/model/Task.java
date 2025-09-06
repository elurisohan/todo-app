package com.tracknote.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Builder
public class Task {

    @GeneratedValue
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name="project_id")
    @JsonProperty("project_id")
    private Project project;

    @Column(nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private Date createdAt=new Date();

    private Date dueDate;

}
