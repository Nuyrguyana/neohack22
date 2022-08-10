package ru.neoflex.educationplatform.api.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.LessonsApi;
import org.openapitools.model.LessonAllInfo;
import org.openapitools.model.LessonCreateRequestDto;
import org.openapitools.model.LessonUpdateRequestDto;
import org.openapitools.model.TasksAllInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.educationplatform.service.LessonService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LessonController implements LessonsApi {

    private final LessonService lessonService;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
    public ResponseEntity<Void> deleteLesson(Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<LessonAllInfo> getLesson(Long id) {
        return ResponseEntity.ok(lessonService.getLesson(id));
    }

    @Override
    public ResponseEntity<List<TasksAllInfo>> getTasksByLessonId(Long id) {
        return ResponseEntity.ok(lessonService.getTasksByLessonId(id));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
    public ResponseEntity<LessonAllInfo> updateLesson(LessonUpdateRequestDto lessonsRequestDto) {
        return ResponseEntity.ok(lessonService.updateLesson(lessonsRequestDto));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
    public ResponseEntity<LessonAllInfo> createLesson(LessonCreateRequestDto lessonCreateRequestDto) {
        return ResponseEntity.ok(lessonService.createLesson(lessonCreateRequestDto));
    }
}
