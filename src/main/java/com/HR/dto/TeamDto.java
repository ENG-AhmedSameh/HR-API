package com.HR.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.HR.persistence.entities.Team}
 */
@Data
public class TeamDto implements Serializable {
    Integer id;
    @Size(max = 255)
    String teamName;
    Integer departmentID;
    Integer teamLeaderID;
}