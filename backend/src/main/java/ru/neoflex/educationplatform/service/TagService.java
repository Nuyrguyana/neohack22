package ru.neoflex.educationplatform.service;

import org.openapitools.model.SimpleResponseDto;

import java.util.List;

public interface TagService {
    List<SimpleResponseDto> getAllTags();
}
