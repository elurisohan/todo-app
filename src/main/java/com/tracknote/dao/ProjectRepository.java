package com.tracknote.dao;


import com.tracknote.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {

    public List<Project> findAllByOwnerUsername(String username);
    public List<Project> findBySharedUsersUsername(String username);
    public void deleteById(int id);

}
