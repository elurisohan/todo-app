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

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
 

    private Task getTask(final int id){
        return taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));
    }

    private Project getProject(final int id){
        return projectRepository.findById(id).orElseThrow(()->new ProjectNotFoundException(id));
    }



}
