package com.HR.util.mappers;

import com.HR.dto.ContractDto;
import com.HR.persistence.entities.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface ContractMapper {
    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    @Mapping(source = "employeeID", target = "employee.id")
    Contract toEntity(ContractDto contractDto);
    @Mapping(source = "employee.id", target = "employeeID")
    ContractDto toDto(Contract contract);

    Set<Contract> toEntitySet(Set<ContractDto> contractDtos);
    Set<ContractDto> toDtoSet(Set<Contract> contracts);
}
