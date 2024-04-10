package com.HR.util.mappers;

import com.HR.dto.TeamDto;
import com.HR.persistence.entities.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface TeamMapper extends GenericMapper<TeamDto, Team>{
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    @Mappings({
            @Mapping(source = "departmentID", target = "department.id"),
            @Mapping(source = "teamLeaderID", target = "teamLeader.id")
    })
    Team toEntity(TeamDto teamDto);

    @Mappings({
            @Mapping(source = "department.id", target = "departmentID"),
            @Mapping(source = "teamLeader.id", target = "teamLeaderID")
    })
    TeamDto toDto(Team team);

    //    Set<Team> toEntitySet(Set<TeamDto> teamDtos);
//    Set<TeamDto> toDtoSet(Set<Team> teams);
}
