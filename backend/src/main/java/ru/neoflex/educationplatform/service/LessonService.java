package ru.neoflex.educationplatform.service;

import org.openapitools.model.LessonAllInfo;
import org.openapitools.model.LessonCreateRequestDto;
import org.openapitools.model.LessonUpdateRequestDto;
import org.openapitools.model.TasksAllInfo;

import java.util.List;

public interface LessonService {
    void deleteLesson(Long id);

    LessonAllInfo getLesson(Long id);

    List<TasksAllInfo> getTasksByLessonId(Long id);

    LessonAllInfo updateLesson(LessonUpdateRequestDto lessonsRequestDto);

    LessonAllInfo createLesson(LessonCreateRequestDto lessonCreateRequestDto);
}
