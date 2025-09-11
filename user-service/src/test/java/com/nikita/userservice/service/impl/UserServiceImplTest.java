package com.nikita.userservice.service.impl;

import com.nikita.userservice.mapper.UserMapper;
import com.nikita.userservice.model.dto.UserCreateRequestDto;
import com.nikita.userservice.model.dto.UserDto;
import com.nikita.userservice.model.entity.UserEntity;
import com.nikita.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    // TODO надо было сделать Mappers не static а компонентами... ну вот сейчас мучаться надо с mockitoStatic

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;
    @Test
    void register_success() {
        UserCreateRequestDto request = new UserCreateRequestDto();
        request.setPhone("123456");
        UserEntity userEntity = new UserEntity();
        UserDto userDto = new UserDto();

        when(userRepository.existsByPhone(request.getPhone())).thenReturn(false);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        try (MockedStatic<UserMapper> mapper = mockStatic(UserMapper.class)) {
            mapper.when(() -> UserMapper.toUserEntity(request)).thenReturn(userEntity);
            mapper.when(() -> UserMapper.toUserResponseDto(userEntity)).thenReturn(userDto);

            var response = userService.register(request);

            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertEquals(userDto, response.getBody());
        }
    }

    @Test
    void register_phoneExists_throwsException() {
        UserCreateRequestDto request = new UserCreateRequestDto();
        request.setPhone("123456");

        when(userRepository.existsByPhone(request.getPhone())).thenReturn(true);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> userService.register(request));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void getUserById_found() {
        UUID id = UUID.randomUUID();
        UserEntity userEntity = new UserEntity();
        UserDto userDto = new UserDto();

        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));
        try (MockedStatic<UserMapper> mapper = mockStatic(UserMapper.class)) {
            mapper.when(() -> UserMapper.toUserResponseDto(userEntity)).thenReturn(userDto);

            var response = userService.getUserById(id);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(userDto, response.getBody());
        }
    }

    @Test
    void getUserById_notFound() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> userService.getUserById(id));
    }

    @Test
    void deleteUser_success() {
        UUID id = UUID.randomUUID();
        UserEntity userEntity = new UserEntity();
        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));

        var response = userService.deleteUser(id);

        verify(userRepository).delete(userEntity);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteUser_notFound() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> userService.deleteUser(id));
    }
}