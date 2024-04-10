package com.HR.util.mappers;

import com.HR.dto.BenefitDto;
import com.HR.persistence.entities.Benefit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BenefitMapper extends GenericMapper<BenefitDto, Benefit>{
    BenefitMapper INSTANCE = Mappers.getMapper(BenefitMapper.class);
//    Benefit toEntity(BenefitDto benefitDto);
//    BenefitDto toDto(Benefit benefit);
//    List<BenefitDto> toDtoList(List<Benefit> benefits);
//    List<Benefit> toEntityList(List<BenefitDto> benefitDtos);
//
//    Set<BenefitDto> toDtoSet(Set<Benefit> benefits);
//    Set<Benefit> toEntitySet(Set<BenefitDto> benefitDtos);
}
