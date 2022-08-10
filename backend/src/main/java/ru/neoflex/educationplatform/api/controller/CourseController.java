package ru.neoflex.educationplatform.api.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.CoursesApi;
import org.openapitools.model.CourseAllInfoResponseDto;
import org.openapitools.model.CourseCreateRequestDto;
import org.openapitools.model.CourseUpdateRequestDto;
import org.openapitools.model.LessonAllInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.educationplatform.service.CourseService;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class CourseController implements CoursesApi {

    private final CourseService courseService;

    @Override
    public ResponseEntity<Void> deleteCourse(Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CourseAllInfoResponseDto>> getAllPublicCourses() {
        return ResponseEntity.ok(courseService.getAllPublicCourses());
    }

    @Override
    public ResponseEntity<CourseAllInfoResponseDto> getCourse(Long id) {
        return ResponseEntity.ok(courseService.getCourse(id));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
    public ResponseEntity<CourseAllInfoResponseDto> updateCourse(CourseUpdateRequestDto courseRequestDto) {
        return ResponseEntity.ok(courseService.updateCourse(courseRequestDto));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
    public ResponseEntity<CourseAllInfoResponseDto> createCourse(CourseCreateRequestDto courseCreateRequestDto) {
        return ResponseEntity.ok(courseService.createCourse(courseCreateRequestDto));
    }

    @Override
    public ResponseEntity<List<LessonAllInfo>> getLessonsByCourseId(Long id) {
        return ResponseEntity.ok(courseService.getLessonsByCourseId(id));
    }
}
