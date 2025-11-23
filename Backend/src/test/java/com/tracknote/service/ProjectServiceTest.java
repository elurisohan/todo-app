package com.tracknote.service;

import com.tracknote.dto.ProjectResponseDTO;
import com.tracknote.model.Project;
import com.tracknote.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProjectServiceTest {
    @Test
    List<ProjectResponseDTO> getProjectBySharedUsersTest(String username){
        User usr=getUserByUsername(username);
        return projectRepository.findBySharedUsersUsername(username);
    }
}
