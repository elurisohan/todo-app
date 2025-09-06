package com.tracknote.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(int projectId) {

        super(String.format("Project with id '%s' not found",projectId));
    }
}
