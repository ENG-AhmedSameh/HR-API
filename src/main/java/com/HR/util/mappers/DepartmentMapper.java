package com.HR.util.mappers;

import com.HR.dto.DepartmentDto;
import com.HR.persistence.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper extends GenericMapper<DepartmentDto, Department>{
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
    @Mapping(source = "managerID",target = "manager.id")
    Department toEntity(DepartmentDto departmentDto);

    @Mapping(source = "manager.id",target = "managerID")
    DepartmentDto toDto(Department department);
//    List<DepartmentDto> toDtoList(List<Department> departments);
//    List<Department> toEntityList(List<DepartmentDto> departmentDtos);
//
//    Set<DepartmentDto> toDtoSet(Set<Department> departments);
//    Set<Department> toEntitySet(Set<DepartmentDto> departmentDtos);
}
