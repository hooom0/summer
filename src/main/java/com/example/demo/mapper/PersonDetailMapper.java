package com.example.demo.mapper;

import com.example.demo.dto.PersonDetailResponseDTO;
import com.example.demo.entity.PersonDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonDetailMapper {
    PersonDetailMapper INSTANCE = Mappers.getMapper(PersonDetailMapper.class);

    @Mapping(source = "person.id", target = "personId")
    PersonDetailResponseDTO toResponseDTO(PersonDetail personDetail);

    @Mapping(target = "person", ignore = true)
    @Mapping(target = "registerTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    PersonDetail toEntity(PersonDetailRequestDTO requestDTO);
}