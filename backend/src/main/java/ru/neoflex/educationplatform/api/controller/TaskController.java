package ru.neoflex.educationplatform.api.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.TasksApi;
import org.openapitools.model.AnswerAllInfo;
import org.openapitools.model.TaskCreateRequestDto;
import org.openapitools.model.TaskUpdateRequestDto;
import org.openapitools.model.TasksAllInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.educationplatform.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController implements TasksApi {

    private final TaskService taskService;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
    public ResponseEntity<Void> deleteTask(Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<AnswerAllInfo>> getAnswersByTaskId(Long id) {
        return ResponseEntity.ok(taskService.getAnswersByTaskId(id));
    }

    @Override
    public ResponseEntity<TasksAllInfo> getTaskById(Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
    public ResponseEntity<Void> updateTask(TaskUpdateRequestDto taskUpdateRequestDto) {
        taskService.updateTask(taskUpdateRequestDto);
        return ResponseEntity.ok().build();
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
    public ResponseEntity<Void> createTask(TaskCreateRequestDto taskCreateRequestDto) {
        taskService.createTask(taskCreateRequestDto);
        return ResponseEntity.ok().build();
    }
}
