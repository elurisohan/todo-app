package com.tracknote.dto;

import com.tracknote.model.Task;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ProjectResponseDTO {
    private int projectId;

    private String name;

    private String desc;

    private Date createdAt;

}
