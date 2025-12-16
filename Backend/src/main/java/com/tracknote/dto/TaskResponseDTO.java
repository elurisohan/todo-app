package com.tracknote.dto;

import com.tracknote.model.Priority;
import com.tracknote.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Response DTO for Task entity
 * Used when returning task data to clients
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {
    private int id;
    private String name;
    private String description;
    private Status status;
    private Priority priority;
    private Date dueDate;
    private int projectId;      // Reference to parent project
    private String projectName;  // Helpful for UI display
}
