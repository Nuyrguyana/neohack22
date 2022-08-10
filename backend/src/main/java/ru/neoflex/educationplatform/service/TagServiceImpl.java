package ru.neoflex.educationplatform.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.SimpleResponseDto;
import org.springframework.stereotype.Service;
import ru.neoflex.educationplatform.mapper.InterestTagMapper;
import ru.neoflex.educationplatform.repository.InterestTagRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{
    private final InterestTagRepository tagRepository;
    private final InterestTagMapper tagMapper;

    @Override
    public List<SimpleResponseDto> getAllTags() {
        log.info("getAllTags()");
        return tagRepository.findAll().stream().map(tagMapper::mapEntityToDto).collect(Collectors.toList());
    }
}
