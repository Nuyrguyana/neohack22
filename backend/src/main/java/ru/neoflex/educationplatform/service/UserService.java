package ru.neoflex.educationplatform.service;

import org.openapitools.model.TokenDto;
import org.openapitools.model.UserAllInfoResponseDto;
import org.openapitools.model.UserLoginDto;
import org.openapitools.model.UserRegistrationDto;
import org.openapitools.model.UserRequestDto;
import org.openapitools.model.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();

    UserAllInfoResponseDto getUser(Long id);

    UserAllInfoResponseDto updateUser(UserRequestDto userRequestDto);

    TokenDto login(UserLoginDto userLoginDto);

    void registration(UserRegistrationDto userRegistrationDto);
}
