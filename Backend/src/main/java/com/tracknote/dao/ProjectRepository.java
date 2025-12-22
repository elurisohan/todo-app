package com.tracknote.dao;


import com.tracknote.model.Project;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {


     List<Project> findAllByOwnerUsername(String username);

    @EntityGraph(attributePaths = {"tasks"})
     List<Project> findBySharedUsersUsername(String username);
     void deleteById(int id);

}
