package ru.neoflex.educationplatform.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.CourseAllInfoResponseDto;
import org.openapitools.model.CourseCreateRequestDto;
import org.openapitools.model.CourseUpdateRequestDto;
import org.openapitools.model.LessonAllInfo;
import org.springframework.stereotype.Service;
import ru.neoflex.educationplatform.mapper.CourseMapper;
import ru.neoflex.educationplatform.mapper.LessonMapper;
import ru.neoflex.educationplatform.model.Course;
import ru.neoflex.educationplatform.model.InterestTag;
import ru.neoflex.educationplatform.model.User;
import ru.neoflex.educationplatform.repository.CourseRepository;
import ru.neoflex.educationplatform.repository.InterestTagRepository;
import ru.neoflex.educationplatform.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final InterestTagRepository tagRepository;
    private final UserRepository userRepository;
    private final LessonMapper lessonMapper;

    @Override
    public void deleteCourse(Long id) {
        log.info("deleteCourse(), id = {}", id);
        courseRepository.deleteById(id);
    }

    @Override
    public List<CourseAllInfoResponseDto> getAllPublicCourses() {
        log.info("getAllPublicCourses()");
        return courseRepository.findAllByIsPrivateAndStatus(false, "AVAILABLE")
                .stream().map(courseMapper::mapEntityToCourseAllInfoResponseDto).collect(Collectors.toList());
    }

    @Override
    public CourseAllInfoResponseDto getCourse(Long id) {
        log.info("getCourse(), id = {}", id);
        return courseMapper.mapEntityToCourseAllInfoResponseDto(courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("В базе нет курса с id " + id)));
    }

    @Override
    public CourseAllInfoResponseDto createCourse(CourseCreateRequestDto courseCreateRequestDto) {
        log.info("createCourse(), courseCreateRequestDto = {}", courseCreateRequestDto);
        List<InterestTag> allTagsById = tagRepository.findAllByIdIn(courseCreateRequestDto.getInterestTags());
        User author = userRepository.findById(courseCreateRequestDto.getAuthor())
                .orElseThrow(() -> new EntityNotFoundException("В базе нет пользователя с id " + courseCreateRequestDto.getAuthor()));
        Course save = courseRepository.save(courseMapper.mapCourseCreateRequestDtoToEntity(courseCreateRequestDto, allTagsById, author));
        return courseMapper.mapEntityToCourseAllInfoResponseDto(save);
    }

    @Override
    public CourseAllInfoResponseDto updateCourse(CourseUpdateRequestDto courseRequestDto) {
        log.info("getAllPublicCourses(), courseRequestDto = {}", courseRequestDto);
        List<InterestTag> allTagsById = tagRepository.findAllByIdIn(courseRequestDto.getInterestTags());
        User author = userRepository.findById(courseRequestDto.getAuthor())
                .orElseThrow(() -> new EntityNotFoundException("В базе нет пользователя с id " + courseRequestDto.getAuthor()));

        Course course = courseRepository.findById(courseRequestDto.getId())
                .map(c -> courseMapper.updateEntityFromCourseRequestDto(c, courseRequestDto, allTagsById, author))
                .orElseThrow(() -> new EntityNotFoundException("В базе нет курса с id " + courseRequestDto.getId()));
        course = courseRepository.save(course);
        return courseMapper.mapEntityToCourseAllInfoResponseDto(course);
    }

    @Override
    public List<LessonAllInfo> getLessonsByCourseId(Long id) {
        log.info("getLessonsByCourseId(), id = {}", id);
        Course course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("В базе нет курса с id " + id));
        return course.getLessons().stream().map(lessonMapper::mapEntityToLessonAllInfo).collect(Collectors.toList());
    }
}
