package ru.neoflex.educationplatform.api.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.TagsApi;
import org.openapitools.model.SimpleResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.educationplatform.service.TagService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagConstructor implements TagsApi {

    private final TagService tagService;

    @Override
    public ResponseEntity<List<SimpleResponseDto>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }
}
