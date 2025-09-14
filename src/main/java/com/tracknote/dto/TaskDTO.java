package com.tracknote.dto;

import com.tracknote.model.Priority;
import com.tracknote.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TaskDTO {

    @NotBlank
    private String name;
    private String description;
    private Status status;
    private Priority priority;
    private Date dueDate;
}
