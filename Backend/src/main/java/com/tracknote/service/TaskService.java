package com.tracknote.service;

import com.tracknote.dao.ProjectRepository;
import com.tracknote.dao.TaskRepository;
import com.tracknote.dao.UserRepository;
import com.tracknote.dto.TaskDTO;
import com.tracknote.exception.ProjectNotFoundException;
import com.tracknote.exception.TaskNotFoundException;
import com.tracknote.model.Project;
import com.tracknote.model.Status;
import com.tracknote.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    //create

    public Task createTask(int projectId, TaskDTO dto){
        Project project=getProject(projectId);
        Task task= Task.builder().name(dto.getName()).description(dto.getDescription()).status(dto.getStatus()).priority(dto.getPriority()).dueDate(dto.getDueDate()).project(project).build();
        return taskRepository.save(task);
    }

    public List<Task> getTasksByProject(int projectId){
        Project project=getProject(projectId);
        return taskRepository.findByProjectId(projectId);
    }


    public Task updateTask(int id,TaskDTO updatedTask){
        //Considering frontend shows only the tasks which are available. I haven't checked if the task exists.
        Task task=getTask(id);
        Optional.ofNullable(updatedTask.getName()).ifPresent(task::setName);
        Optional.ofNullable(updatedTask.getDescription()).ifPresent(task::setDescription);
        Optional.ofNullable(updatedTask.getStatus()).ifPresent(task::setStatus);
        Optional.ofNullable(updatedTask.getPriority()).ifPresent(task::setPriority);
        Optional.ofNullable(updatedTask.getDueDate()).ifPresent(task::setDueDate);
        return taskRepository.save(task);
       }

    public void deleteTask(int id){
        taskRepository.deleteById(id);
    }

    //pagination
    public Page<Task> getTasks(Status status, Integer projectId, Pageable pageable) {
        if (status != null && projectId != null) {
            return taskRepository.findByStatusAndProjectId(status, projectId,pageable);
        } else if (status != null && projectId == null) {
            return taskRepository.findByStatus(status,pageable);
        } else if (status == null && projectId != null) {
            return taskRepository.findByProjectId(projectId,pageable);
        } else {
            return taskRepository.findAll(pageable);
        }
    }

    private Task getTask(final int id){
        return taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));
    }

    private Project getProject(final int id){
        return projectRepository.findById(id).orElseThrow(()->new ProjectNotFoundException(id));
    }



}
