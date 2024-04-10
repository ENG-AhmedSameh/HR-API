package com.HR.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.HR.persistence.entities.Department}
 */
@Data
public class DepartmentDto implements Serializable {
    Integer id;
    @Size(max = 255)
    String departmentName;
    Integer managerID;
}