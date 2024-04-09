package com.HR.util.mappers;

import com.HR.dto.EmployeeBenefitDto;
import com.HR.dto.EmployeeDto;
import com.HR.persistence.entities.Employee;
import com.HR.persistence.entities.EmployeeBenefit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    @Mappings({
            @Mapping(source = "directManagerID", target = "directManager.id"),
            @Mapping(source = "departmentID", target = "department.id"),
            @Mapping(source = "teamID", target = "team.id")
    })
    Employee toEntity(EmployeeDto employeeDto);

    @Mappings({
            @Mapping(source = "directManager.id", target = "directManagerID"),
            @Mapping(source = "department.id", target = "departmentID"),
            @Mapping(source = "team.id", target = "teamID")
    })
    EmployeeDto toDto(Employee employee);

    List<EmployeeDto> toDtoList(List<Employee> employees);
    List<Employee> toEntityList(List<EmployeeDto> employeeDtos);

    Set<Employee> toEntitySet(Set<EmployeeDto> employeeDtos);
    Set<EmployeeDto> toDtoSet(Set<Employee> employees);

    Set<EmployeeBenefitDto> toEmployeeBenefitDtoSet(Set<EmployeeBenefit> employeeBenefits);
    Set<EmployeeBenefit> toEmployeeBenefitSet(Set<EmployeeBenefitDto> employeeBenefitDtos);
}
