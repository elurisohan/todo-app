package com.tracknote.exception;

public class ProjectDeletionException extends RuntimeException{
    public ProjectDeletionException(String message,Throwable cause){
        super(message,cause);
    }
}
