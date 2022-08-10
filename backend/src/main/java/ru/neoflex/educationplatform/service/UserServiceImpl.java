package ru.neoflex.educationplatform.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.TokenDto;
import org.openapitools.model.UserAllInfoResponseDto;
import org.openapitools.model.UserLoginDto;
import org.openapitools.model.UserRegistrationDto;
import org.openapitools.model.UserRequestDto;
import org.openapitools.model.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.neoflex.educationplatform.exception.CustomException;
import ru.neoflex.educationplatform.mapper.UserMapper;
import ru.neoflex.educationplatform.model.Role;
import ru.neoflex.educationplatform.model.User;
import ru.neoflex.educationplatform.repository.RoleRepository;
import ru.neoflex.educationplatform.repository.UserRepository;
import ru.neoflex.educationplatform.security.JwtTokenProvider;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDto> getAllUsers() {
        log.info("getAllUsers()");
        return userRepository.findAll().stream()
                .map(userMapper::mapEntityToUserResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserAllInfoResponseDto getUser(Long id) {
        log.info("getUser(), id = {}", id);
        return userMapper.mapEntityToUserAllInfoResponseDto(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("В базе нет пользователя с id " + id)));
    }

    @Override
    public UserAllInfoResponseDto updateUser(UserRequestDto userRequestDto) {
        log.info("updateUser(), userRequestDto = {}", userRequestDto);
        if (userRequestDto.getId() != null){
            User user = userRepository.findById(userRequestDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("В базе нет пользователя с id " + userRequestDto.getId()));
            User user1 = userMapper.updateEntityFromUserRequestDto(user, userRequestDto);
            user = userRepository.save(user1);
            return userMapper.mapEntityToUserAllInfoResponseDto(user);
        } else {
            User save = userRepository.save(userMapper.mapEntityFromUserRequestDto(userRequestDto));
            return userMapper.mapEntityToUserAllInfoResponseDto(save);
        }
    }

    @Override
    public TokenDto login(UserLoginDto userLoginDto) {
        log.info("login()");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));
            return TokenDto.builder()
                    .token(jwtTokenProvider.createToken(userDetailsService.loadUserByUsername(userLoginDto.getEmail())))
                    .build();
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void registration(UserRegistrationDto userRegistrationDto) {
        log.info("registration()");
        if (userRepository.existsByEmail(userRegistrationDto.getEmail())) {
            throw new CustomException("Пользователь с таким email уже существует", HttpStatus.BAD_REQUEST);
        } else {
            Role role = roleRepository.findByName(userRegistrationDto.getRole().getValue().toUpperCase(Locale.ROOT));
            User user = userMapper.mapUserRegistrationDtoToEntity(userRegistrationDto, role, passwordEncoder.encode(userRegistrationDto.getPassword()));
            user.setRegistrationDate(LocalDate.now());
            user.setActive(true);
            userRepository.save(user);
        }
    }
}
