package ru.neoflex.educationplatform.mapper;

import org.mapstruct.Mapper;
import org.openapitools.model.SimpleResponseDto;
import ru.neoflex.educationplatform.model.InterestTag;

@Mapper(componentModel = "spring")
public interface InterestTagMapper {

    SimpleResponseDto mapEntityToDto(InterestTag tag);
}
