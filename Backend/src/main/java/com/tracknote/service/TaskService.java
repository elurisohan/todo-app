

package com.tracknote.service;

import com.tracknote.dao.ProjectRepository;
import com.tracknote.dao.TaskRepository;
import com.tracknote.dto.TaskDTO;
import com.tracknote.dto.TaskResponseDTO;
import com.tracknote.exception.ProjectNotFoundException;
import com.tracknote.exception.TaskNotFoundException;
import com.tracknote.exception.UnauthorizedException;
import com.tracknote.model.Project;
import com.tracknote.model.Status;
import com.tracknote.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final DTOMapper dtoMapper;
    private final ProjectService projectService;

    @Transactional
    public TaskResponseDTO createTask(int projectId, TaskDTO taskDTO, String username) {
        Project project = projectService.getProject(projectId);

        if (!project.getOwner().getUsername().equals(username)) {
            throw new UnauthorizedException("User " + username + " not authorized to add tasks to this project.");
        }

        Task task = Task.builder()
                .name(taskDTO.getName())
                .description(taskDTO.getDescription())
                .status(taskDTO.getStatus() != null ? taskDTO.getStatus() : Status.NEW)
                .priority(taskDTO.getPriority())
                .dueDate(taskDTO.getDueDate())
                .project(project)
                .build();

        Task savedTask = taskRepository.save(task);
        return dtoMapper.toTaskResponse(savedTask);
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getTasksByProject(int projectId, String username) {
        Project project = projectService.getProject(projectId);

        if (!project.getOwner().getUsername().equals(username)) {
            throw new UnauthorizedException("User " + username + " not authorized to view tasks for this project.");
        }

        return taskRepository.findByProjectId(projectId).stream()
                .map(dtoMapper::toTaskResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TaskResponseDTO getTask(int taskId, String username) {
        Task task = findTaskById(taskId);

        if (!task.getProject().getOwner().getUsername().equals(username)) {
            throw new UnauthorizedException("User " + username + " not authorized to view this task.");
        }

        return dtoMapper.toTaskResponse(task);
    }

    @Transactional
    public TaskResponseDTO updateTask(int taskId, TaskDTO taskDTO, String username) {
        Task task = findTaskById(taskId);

        if (!task.getProject().getOwner().getUsername().equals(username)) {
            throw new UnauthorizedException("User " + username + " not authorized to update this task.");
        }

        if (taskDTO.getName() != null && !taskDTO.getName().trim().isEmpty()) {
            task.setName(taskDTO.getName());
        }

        if (taskDTO.getDescription() != null) {
            task.setDescription(taskDTO.getDescription());
        }

        if (taskDTO.getStatus() != null) {
            task.setStatus(taskDTO.getStatus());
        }

        if (taskDTO.getPriority() != null) {
            task.setPriority(taskDTO.getPriority());
        }

        if (taskDTO.getDueDate() != null) {
            task.setDueDate(taskDTO.getDueDate());
        }

        Task updatedTask = taskRepository.save(task);
        return dtoMapper.toTaskResponse(updatedTask);
    }

    @Transactional
    public void deleteTask(int taskId, String username) {
        Task task = findTaskById(taskId);

        if (!task.getProject().getOwner().getUsername().equals(username)) {
            throw new UnauthorizedException("User " + username + " not authorized to delete this task.");
        }

        taskRepository.delete(task);
    }

    private Task findTaskById(int taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    private Task getTask(final int id){
        return taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));
    }

    private Project getProject(final int id){
        return projectRepository.findById(id).orElseThrow(()->new ProjectNotFoundException(id));
    }
}
