package ru.neoflex.educationplatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.openapitools.model.TaskCreateRequestDto;
import org.openapitools.model.TaskUpdateRequestDto;
import org.openapitools.model.TasksAllInfo;
import ru.neoflex.educationplatform.model.Lesson;
import ru.neoflex.educationplatform.model.Task;

@Mapper(componentModel = "spring", uses = AnswerMapper.class)
public interface TaskMapper {

    @Mapping(target = "lesson", source = "lesson")
    @Mapping(target = "id", ignore = true)
    Task updateTaskFromTaskUpdateRequestDto(@MappingTarget Task task, TaskUpdateRequestDto taskRequestDto, Lesson lesson);

    @Mapping(target = "lessonId", source = "lesson.id")
    @Mapping(target = "answers", source = "answers")
    TasksAllInfo mapEntityToTasksAllInfo(Task task);

    @Mapping(target = "lesson", source = "lesson")
    @Mapping(target = "id", ignore = true)
    Task mapEntityFromTaskCreateRequestDto(TaskCreateRequestDto taskCreateRequestDto, Lesson lesson);
}
