package com.HR.util.mappers;

import com.HR.dto.VacationDto;
import com.HR.persistence.entities.Vacation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface VacationMapper extends GenericMapper<VacationDto, Vacation>{
    VacationMapper INSTANCE = Mappers.getMapper(VacationMapper.class);


    @Mapping(source = "employee.id", target = "employeeID")
    VacationDto toDto(Vacation vacation);

    @Mapping(source = "employeeID", target = "employee.id")
    Vacation toEntity(VacationDto vacationDto);
//    Set<Vacation> toEntitySet(Set<VacationDto> vacationDtos);
//    Set<VacationDto> toDtoSet(Set<Vacation> vacations);

}
