package com.tracknote.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "project")
@Table(name="tasks")
public class Task {

    @GeneratedValue
    @Id
    private int id;
//Owning side
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

    /*
    It is not required to have both @ManyToOne and @OneToMany.
You can have only @ManyToOne on Task → unidirectional from Task to Project (this is often enough and simpler).

Or you can have @ManyToOne on Task and @OneToMany(mappedBy = "project") on Project → bidirectional; this lets you navigate both task.getProject() and project.getTasks().
    The side without mappedBy (here @ManyToOne Task.project) is the owning side and controls the foreign key. The side with mappedBy (here @OneToMany(mappedBy = "project") Project.tasks) is the inverse side; it exists for convenience in Java, not for controlling the database column.

So:
If you only need task.getProject(), use only @ManyToOne. If you also want project.getTasks(), add @OneToMany(mappedBy = "project") on Project, but still remember to set the owning side: always do both project.getTasks().add(task); and task.setProject(project);
     */

}
