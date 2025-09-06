package com.tracknote.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="users" )
@Builder
public class User {

    @GeneratedValue
    @Id
    private int id;

    @Column(nullable = false,unique = true)
    private String username;

    private String fullname;

    private String email;

    private String password;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Project> ownedProject= new ArrayList<>();

    @ManyToMany(mappedBy = "sharedUsers")
    private Set<Project> sharedProjects=new HashSet<>();
}
