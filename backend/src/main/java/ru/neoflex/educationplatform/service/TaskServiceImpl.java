package ru.neoflex.educationplatform.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.AnswerAllInfo;
import org.openapitools.model.TaskCreateRequestDto;
import org.openapitools.model.TaskUpdateRequestDto;
import org.openapitools.model.TasksAllInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.neoflex.educationplatform.mapper.AnswerMapper;
import ru.neoflex.educationplatform.mapper.TaskMapper;
import ru.neoflex.educationplatform.model.Answer;
import ru.neoflex.educationplatform.model.Lesson;
import ru.neoflex.educationplatform.model.Task;
import ru.neoflex.educationplatform.repository.AnswerRepository;
import ru.neoflex.educationplatform.repository.LessonRepository;
import ru.neoflex.educationplatform.repository.TaskRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final AnswerRepository answerRepository;
    private final TaskMapper taskMapper;
    private final AnswerMapper answerMapper;
    private final LessonRepository lessonRepository;

    @Override
    public void deleteTaskById(Long id) {
        log.info("deleteTaskById(), id = {}", id);
        taskRepository.deleteById(id);
    }

    @Override
    public List<AnswerAllInfo> getAnswersByTaskId(Long id) {
        log.info("getAnswersByTaskId(), id = {}", id);
        if (id != null) {
            Optional<Task> task = taskRepository.findById(id);
            if (task.isPresent()) {
                return task.get().getAnswers().stream()
                        .map(answerMapper::mapEntityToSimpleResponseDto)
                        .collect(Collectors.toList());
            }
        }
        return null;
    }

    @Override
    public TasksAllInfo getTaskById(Long id) {
        log.info("getTaskById(), id = {}", id);
        return taskRepository.findById(id)
                .map(taskMapper::mapEntityToTasksAllInfo)
                .orElseThrow(() -> new EntityNotFoundException("В базе нет задачи с id " + id));
    }

    @Override
    public void updateTask(TaskUpdateRequestDto taskUpdateRequestDto) {
        log.info("updateTask(), taskUpdateRequestDto = {}", taskUpdateRequestDto);
        Lesson lesson = lessonRepository.findById(taskUpdateRequestDto.getLesson())
                .orElseThrow(() -> new EntityNotFoundException("в базе нет урока с id " + taskUpdateRequestDto.getLesson()));

        Task taskFromDb = taskRepository.findById(taskUpdateRequestDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("в базе нет задачи с id " + taskUpdateRequestDto.getId()));
        Set<Answer> answers = taskFromDb.getAnswers();
        Task updatedTask = taskMapper.updateTaskFromTaskUpdateRequestDto(taskFromDb, taskUpdateRequestDto, lesson);
        updatedTask.setAnswers(answers);
        taskRepository.save(updatedTask);
    }

    @Override
    public void createTask(TaskCreateRequestDto taskCreateRequestDto) {
        log.info("createTask(), taskCreateRequestDto = {}", taskCreateRequestDto);
        Lesson lesson = lessonRepository.findById(taskCreateRequestDto.getLesson())
                .orElseThrow(() -> new EntityNotFoundException("в базе нет урока с id " + taskCreateRequestDto.getLesson()));
        Set<Answer> answers = taskCreateRequestDto.getAnswers().stream()
                .map(answerMapper::mapEntityFromDto)
                .collect(Collectors.toSet());
        Task taskToSave = taskMapper.mapEntityFromTaskCreateRequestDto(taskCreateRequestDto, lesson);

        taskToSave.setAnswers(answers);
        taskToSave.setLesson(lesson);
        taskRepository.save(taskToSave);
        answers.forEach(answer -> answer.setTask(taskToSave));
        answerRepository.saveAll(answers);
    }
}
