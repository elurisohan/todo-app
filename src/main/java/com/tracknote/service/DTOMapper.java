package com.tracknote.service;

import com.tracknote.dto.ProjectResponseDTO;
import com.tracknote.dto.TaskDTO;
import com.tracknote.model.Project;
import com.tracknote.model.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DTOMapper {


    public ProjectResponseDTO toProjectResponse(Project project){
        //Lombok generates an internal class to make the builder work
        return ProjectResponseDTO.builder()
                .projectId(project.getId())
                .name(project.getName())
                .desc(project.getDescription())
                .createdAt(project.getCreatedAt())
                //Task entity shouldn't be returned directly, as it could expose the id and the relationship bw Task and Project could recursively return infinite loop
                .tasks(project.getTasks()
                        .stream()
                        .map(this::toTaskDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public TaskDTO toTaskDTO(Task tasks){
        return TaskDTO.builder()
                .name(tasks.getName())
                .description(tasks.getDescription())
                .status(tasks.getStatus())
                .priority(tasks.getPriority())
                .dueDate(tasks.getDueDate())
                .build();

    }

}
