package com.HR.util.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Set;

public interface GenericMapper<DTO, Entity> {
    DTO toDto(Entity entity);

    Entity toEntity(DTO dto);

    List<DTO> toDtoList(List<Entity> entityList);
    List<Entity> toEntityList(List<DTO> dtoList);

    Set<DTO> toDtoSet(Set<Entity> entitySet);
    Set<Entity> toEntitySet(Set<DTO> dtoSet);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Entity updateEntity(DTO dto, @MappingTarget Entity entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DTO updateDto(Entity entity, @MappingTarget DTO dto);
}
