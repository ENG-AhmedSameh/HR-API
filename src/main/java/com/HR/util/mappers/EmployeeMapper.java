package com.HR.util.mappers;

import com.HR.dto.EmployeeBenefitDto;
import com.HR.dto.EmployeeDto;
import com.HR.persistence.entities.Employee;
import com.HR.persistence.entities.EmployeeBenefit;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface EmployeeMapper extends GenericMapper<EmployeeDto, Employee>{
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    @Mappings({
            @Mapping(source = "directManagerID", target = "directManager.id"),
            @Mapping(source = "teamID", target = "team.id"),
    })
    Employee toEntity(EmployeeDto employeeDto);



    @Mappings({
            @Mapping(source = "directManager.id", target = "directManagerID"),
            @Mapping(source = "team.id", target = "teamID")
    })
    EmployeeDto toDto(Employee employee);

    @AfterMapping
    default void setDirectManagerName(@MappingTarget EmployeeDto employeeDto, Employee employee) {
        if (employee.getDirectManager() != null && employee.getDirectManager().getFirstName()!=null){
            String directManagerName = employee.getDirectManager().getFirstName() + " " + employee.getDirectManager().getLastName();
            employeeDto.setDirectManagerName(directManagerName);
        }
        if (employee.getTeam() != null) {
            employeeDto.setTeamName(employee.getTeam().getTeamName());
        }
    }
//    List<EmployeeDto> toDtoList(List<Employee> employees);
//    List<Employee> toEntityList(List<EmployeeDto> employeeDtos);
//
//    Set<Employee> toEntitySet(Set<EmployeeDto> employeeDtos);
//    Set<EmployeeDto> toDtoSet(Set<Employee> employees);

    Set<EmployeeBenefitDto> toEmployeeBenefitDtoSet(Set<EmployeeBenefit> employeeBenefits);
    Set<EmployeeBenefit> toEmployeeBenefitSet(Set<EmployeeBenefitDto> employeeBenefitDtos);
}
