package ru.neoflex.educationplatform.service;

import org.openapitools.model.AnswerAllInfo;
import org.openapitools.model.TaskCreateRequestDto;
import org.openapitools.model.TaskUpdateRequestDto;
import org.openapitools.model.TasksAllInfo;

import java.util.List;

public interface TaskService {

    void deleteTaskById(Long id);

    List<AnswerAllInfo> getAnswersByTaskId(Long id);

    TasksAllInfo getTaskById(Long id);

    void updateTask(TaskUpdateRequestDto taskRequestDto);

    void createTask(TaskCreateRequestDto taskCreateRequestDto);
}
