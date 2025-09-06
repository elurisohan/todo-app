package com.tracknote.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data //getter setter methods, toString and equal to methods
@Entity // to tell JPA that this class should be mapped to a table in DB . By default the class name is asusmed to be the tablen name
@Table(name="project", uniqueConstraints = @UniqueConstraint(columnNames = {"name"})) // Only if we want to change schema , unique ocnstraints, and table name change
@Builder
public class Project {

    @GeneratedValue
    @Id
    private int id;

    @Column( nullable = false, unique = true)
    private String name;

    private String description;
    private Date createdAt=new Date();

    //In JPA/Hibernate, relationships between entities are represented by object references, not by primitive types like int.
    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;

    @ManyToMany
    @JoinTable(name="project_shared_users",joinColumns =@JoinColumn(name="project_id"),inverseJoinColumns = @JoinColumn(name="user_id"))
    private Set<User> sharedUsers=new HashSet<>();


    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Task> tasks=new ArrayList<>();
}
