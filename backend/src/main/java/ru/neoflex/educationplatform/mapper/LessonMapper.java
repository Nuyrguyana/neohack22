package ru.neoflex.educationplatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.openapitools.model.LessonAllInfo;
import org.openapitools.model.LessonCreateRequestDto;
import org.openapitools.model.LessonUpdateRequestDto;
import ru.neoflex.educationplatform.model.Course;
import ru.neoflex.educationplatform.model.Lesson;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(target = "courseId", source = "course.id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "teacher", source = "teacher.id")
    LessonAllInfo mapEntityToLessonAllInfo(Lesson lesson);

    @Mapping(target = "course", source = "course")
    @Mapping(target = "name", source = "lessonsRequestDto.name")
    @Mapping(target = "status", source = "lessonsRequestDto.status")
    @Mapping(target = "id", ignore = true)
    Lesson updateLessonFromLessonUpdateRequestDto(@MappingTarget Lesson lesson,
                                                  LessonUpdateRequestDto lessonsRequestDto,
                                                  Course course);

    @Mapping(target = "course", source = "course")
    @Mapping(target = "status", source = "lessonCreateRequestDto.status")
    @Mapping(target = "name", source = "lessonCreateRequestDto.name")
    Lesson mapLessonFromLessonsCreateRequestDto(LessonCreateRequestDto lessonCreateRequestDto, Course course);
}
