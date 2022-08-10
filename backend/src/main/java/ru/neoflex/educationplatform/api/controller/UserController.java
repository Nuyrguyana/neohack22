package ru.neoflex.educationplatform.api.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.UsersApi;
import org.openapitools.model.TokenDto;
import org.openapitools.model.UserAllInfoResponseDto;
import org.openapitools.model.UserLoginDto;
import org.openapitools.model.UserRegistrationDto;
import org.openapitools.model.UserRequestDto;
import org.openapitools.model.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.educationplatform.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final UserService userService;

    @Override
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<UserAllInfoResponseDto> getUser(Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @Override
    public ResponseEntity<UserAllInfoResponseDto> updateUser(UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.updateUser(userRequestDto));
    }

    @Override
    public ResponseEntity<TokenDto> login(UserLoginDto userLoginDto) {
        return ResponseEntity.ok(userService.login(userLoginDto));
    }

    @Override
    public ResponseEntity<Void> registration(UserRegistrationDto userRegistrationDto) {
        userService.registration(userRegistrationDto);
        return ResponseEntity.ok().build();
    }
}
