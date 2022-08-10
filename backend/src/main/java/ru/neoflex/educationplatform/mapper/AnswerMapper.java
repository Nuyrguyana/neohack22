package ru.neoflex.educationplatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.AnswerAllInfo;
import org.openapitools.model.AnswerRequestDto;
import ru.neoflex.educationplatform.model.Answer;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    Answer mapEntityFromDto(AnswerRequestDto answerRequestDto);

    @Mapping(target = "taskId", source = "task.id")
    AnswerAllInfo mapEntityToSimpleResponseDto(Answer answer);


}
