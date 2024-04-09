package com.HR.util.mappers;

import com.HR.dto.BenefitDto;
import com.HR.persistence.entities.Benefit;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-09T03:26:24+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class BenefitMapperImpl implements BenefitMapper {

    @Override
    public Benefit toEntity(BenefitDto benefitDto) {
        if ( benefitDto == null ) {
            return null;
        }

        Benefit benefit = new Benefit();

        benefit.setId( benefitDto.getId() );
        benefit.setBenefitName( benefitDto.getBenefitName() );
        benefit.setDescription( benefitDto.getDescription() );

        return benefit;
    }

    @Override
    public BenefitDto toDto(Benefit benefit) {
        if ( benefit == null ) {
            return null;
        }

        Integer id = null;
        String benefitName = null;
        String description = null;

        id = benefit.getId();
        benefitName = benefit.getBenefitName();
        description = benefit.getDescription();

        BenefitDto benefitDto = new BenefitDto( id, benefitName, description );

        return benefitDto;
    }

    @Override
    public List<BenefitDto> toDtoList(List<Benefit> benefits) {
        if ( benefits == null ) {
            return null;
        }

        List<BenefitDto> list = new ArrayList<BenefitDto>( benefits.size() );
        for ( Benefit benefit : benefits ) {
            list.add( toDto( benefit ) );
        }

        return list;
    }

    @Override
    public List<Benefit> toEntityList(List<BenefitDto> benefitDtos) {
        if ( benefitDtos == null ) {
            return null;
        }

        List<Benefit> list = new ArrayList<Benefit>( benefitDtos.size() );
        for ( BenefitDto benefitDto : benefitDtos ) {
            list.add( toEntity( benefitDto ) );
        }

        return list;
    }
}
