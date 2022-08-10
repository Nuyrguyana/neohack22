package ru.neoflex.educationplatform.service;

import org.openapitools.model.CourseAllInfoResponseDto;
import org.openapitools.model.CourseCreateRequestDto;
import org.openapitools.model.CourseUpdateRequestDto;
import org.openapitools.model.LessonAllInfo;

import java.util.List;

public interface CourseService {
    void deleteCourse(Long id);

    List<CourseAllInfoResponseDto> getAllPublicCourses();

    CourseAllInfoResponseDto getCourse(Long id);

    CourseAllInfoResponseDto updateCourse(CourseUpdateRequestDto courseRequestDto);

    List<LessonAllInfo> getLessonsByCourseId(Long id);

    CourseAllInfoResponseDto createCourse(CourseCreateRequestDto courseCreateRequestDto);
}
