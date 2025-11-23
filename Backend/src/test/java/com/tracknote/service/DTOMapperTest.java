package com.tracknote.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.tracknote.model.Project;
import com.tracknote.service.DTOMapperTest;
import com.tracknote.dto.ProjectResponseDTO;


     public class DTOMapperTest {
        @Test
        //default here is package private and Junit methods cannot be null. So Junit will handle object creation for this.
        void testProjectWithNullTasksReturnsEmptyList() {
            // Arrange
            Project project = new Project();
            project.setId(1);
            project.setName("Demo");
            project.setDescription("Testing null tasks");
            project.setTasks(null); // explicitly null
            DTOMapper mapper = new DTOMapper();

            // Act
            ProjectResponseDTO dto = mapper.toProjectResponse(project);

            // Assert
            assertNotNull(dto.getTasks()); // Should not be null, should be empty
            assertEquals(0, dto.getTasks().size());
        }
    }