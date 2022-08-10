package ru.neoflex.educationplatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.openapitools.model.UserAllInfoResponseDto;
import org.openapitools.model.UserRegistrationDto;
import org.openapitools.model.UserRequestDto;
import org.openapitools.model.UserResponseDto;
import ru.neoflex.educationplatform.model.Role;
import ru.neoflex.educationplatform.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto mapEntityToUserResponseDto(User user);

    UserAllInfoResponseDto mapEntityToUserAllInfoResponseDto(User user);

    User mapEntityFromUserRequestDto(UserRequestDto userRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "active", ignore = true)
    User updateEntityFromUserRequestDto(@MappingTarget User user, UserRequestDto userRequestDto);

    @Mapping(target = "role", source = "role")
    @Mapping(target = "password", source = "password")
    User mapUserRegistrationDtoToEntity(UserRegistrationDto userRegistrationDto, Role role, String password);
}
