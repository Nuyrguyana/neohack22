package ru.neoflex.educationplatform.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.LessonAllInfo;
import org.openapitools.model.LessonCreateRequestDto;
import org.openapitools.model.LessonUpdateRequestDto;
import org.openapitools.model.TasksAllInfo;
import org.springframework.stereotype.Service;
import ru.neoflex.educationplatform.mapper.LessonMapper;
import ru.neoflex.educationplatform.mapper.TaskMapper;
import ru.neoflex.educationplatform.model.Course;
import ru.neoflex.educationplatform.model.Lesson;
import ru.neoflex.educationplatform.repository.CourseRepository;
import ru.neoflex.educationplatform.repository.LessonRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService{

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final TaskMapper taskMapper;
    private final CourseRepository courseRepository;

    @Override
    public void deleteLesson(Long id) {
        log.info("deleteLesson(), id = {}", id);
        lessonRepository.deleteById(id);
    }

    @Override
    public LessonAllInfo getLesson(Long id) {
        log.info("getLesson(), id = {}", id);
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("в базе нет урока с id " + id));
        return lessonMapper.mapEntityToLessonAllInfo(lesson);
    }

    @Override
    public List<TasksAllInfo> getTasksByLessonId(Long id) {
        log.info("getTasksByLessonId(), id = {}", id);
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("в базе нет урока с id " + id));
        return lesson.getTasks().stream().map(taskMapper::mapEntityToTasksAllInfo).collect(Collectors.toList());
    }

    @Override
    public LessonAllInfo updateLesson(LessonUpdateRequestDto lessonsRequestDto) {
        log.info("updateLesson(), lessonsRequestDto = {}", lessonsRequestDto);
        Course course = courseRepository.findById(lessonsRequestDto.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("в базе нет курса с id " + lessonsRequestDto.getCourseId()));
        Lesson lesson = lessonRepository.findById(lessonsRequestDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("в базе нет урока с id " + lessonsRequestDto.getId()));
        lesson = lessonMapper.updateLessonFromLessonUpdateRequestDto(lesson, lessonsRequestDto, course);
        return lessonMapper.mapEntityToLessonAllInfo(lessonRepository.save(lesson));
    }

    @Override
    public LessonAllInfo createLesson(LessonCreateRequestDto lessonCreateRequestDto) {
        log.info("createLesson(), lessonCreateRequestDto = {}", lessonCreateRequestDto);
        Course course = courseRepository.findById(lessonCreateRequestDto.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("в базе нет курса с id " + lessonCreateRequestDto.getCourseId()));

        Lesson lesson = lessonMapper.mapLessonFromLessonsCreateRequestDto(lessonCreateRequestDto, course);
        lesson = lessonRepository.save(lesson);
        return lessonMapper.mapEntityToLessonAllInfo(lesson);
    }
}
