package ru.neoflex.educationplatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.openapitools.model.CourseAllInfoResponseDto;
import org.openapitools.model.CourseCreateRequestDto;
import org.openapitools.model.CourseUpdateRequestDto;
import ru.neoflex.educationplatform.model.Course;
import ru.neoflex.educationplatform.model.InterestTag;
import ru.neoflex.educationplatform.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "status", target = "status")
    CourseAllInfoResponseDto mapEntityToCourseAllInfoResponseDto(Course course);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "interestTags", source = "allTagsById")
    @Mapping(target = "author", source = "author")
    Course updateEntityFromCourseRequestDto(@MappingTarget Course course, CourseUpdateRequestDto courseRequestDto, List<InterestTag> allTagsById, User author);

    @Mapping(target = "interestTags", source = "allTagsById")
    @Mapping(target = "author", source = "author")
    Course mapCourseCreateRequestDtoToEntity(CourseCreateRequestDto courseCreateRequestDto, List<InterestTag> allTagsById, User author);
}
