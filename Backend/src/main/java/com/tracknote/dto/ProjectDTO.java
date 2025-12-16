package com.tracknote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tracknote.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectDTO {

    @NotBlank(message = "Please enter your project name")
    private String name;

    @NotBlank(message = "Please enter your project description")
    private String description;

        // Tasks are NOT managed through project updates
    // Use separate TaskController endpoints: POST /projects/{id}/tasks, PATCH /tasks/{id}
    // This follows Single Responsibility Principle and RESTful API design

}
