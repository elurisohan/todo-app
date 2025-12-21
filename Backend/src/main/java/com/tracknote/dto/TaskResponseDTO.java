package com.tracknote.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.tracknote.model.Priority;
import com.tracknote.model.Project;
import com.tracknote.model.Status;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TaskResponseDTO {


    private int id;

    private String projectName;

    private int projectId;

    private String name;

    private String description;

    private Status status;
    
    private Priority priority;

    private Date dueDate;
}
