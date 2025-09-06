package com.tracknote.controller;

import com.tracknote.Jwtutil;
import com.tracknote.dto.ProjectDTO;
import com.tracknote.model.User;
import com.tracknote.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final Jwtutil jwtutil;

    //create
    @PostMapping("/")
    public ResponseEntity<?> createProject(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody ProjectDTO dto){
        String username=userDetails.getUsername();
        return ResponseEntity.ok(projectService.createProject(username,dto));
    }

    @GetMapping("/username")
    public ResponseEntity<?> getProjectByUser(@AuthenticationPrincipal UserDetails userDetails){
        String username=userDetails.getUsername();
        return ResponseEntity.ok(projectService.getProjectByUser(username));
    }

    /*@PostMapping("/{id}")
    public ResponseEntity<?> updateProject(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int id,@Valid @RequestBody ProjectDTO projectDTO){
        try{
            boolean ifexists=projectService.existsByIdAndOwner(id,userDetails.getUsername());
            projectService.getProject(id);
            return ResponseEntity.ok(projectService.updateProject(id, projectDTO));
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable int id){
        return projectService.deleteProject(id);
    }*/
}
