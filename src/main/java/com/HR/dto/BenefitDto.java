package com.HR.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import java.io.Serializable;

/**
 * DTO for {@link com.HR.persistence.entities.Benefit}
 */
@Data
public class BenefitDto implements Serializable {
    Integer id;
    @Size(max = 255)
    String benefitName;
    String description;
}