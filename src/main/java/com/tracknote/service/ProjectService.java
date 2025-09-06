package com.tracknote.service;

import com.tracknote.dao.ProjectRepository;
import com.tracknote.dao.TaskRepository;
import com.tracknote.dao.UserRepository;
import com.tracknote.dto.ProjectDTO;
import com.tracknote.exception.ProjectNotFoundException;
import com.tracknote.exception.UserNotFoundException;
import com.tracknote.model.Project;
import com.tracknote.model.Task;
import com.tracknote.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    TaskRepository taskRepository;
    UserRepository userRepository;
    ProjectRepository projectRepository;

    public Project createProject(String username, ProjectDTO project){
        User usr=getUsername(username);
        Project project1=Project.builder().name(project.getName()).description(project.getDescription()).owner(usr).build();
        return projectRepository.save(project1);
    }

    public List<Project> getProjectByUser(String username){
        //User username=getUsername(username);
        return projectRepository.findAllByOwnerUsername(username);
    }

    public List<Project> getProjectBySharedUsers(String username){
        User usr=getUsername(username);
        return projectRepository.findBySharedUsersUsername(username);
    }

    public Project updateProject(int id, ProjectDTO updatedProject ){
        Project proj= getProject(id);
        proj.setName(updatedProject.getName());
        proj.setDescription(updatedProject.getDescription());
        return projectRepository.save(proj);
    }




    public User getUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
    }

    public Project getProject(int id){
        return projectRepository.findById(id).orElseThrow(()->new ProjectNotFoundException(id));

    }
}
