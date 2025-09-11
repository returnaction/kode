package com.nikita.userservice.controller;

import com.nikita.userservice.model.dto.UserCreateRequestDto;
import com.nikita.userservice.model.dto.UserDto;
import com.nikita.userservice.model.dto.UserWithAddressesDto;
import com.nikita.userservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @Test
    void registerUser_success() {
        UserCreateRequestDto request = new UserCreateRequestDto();
        UserDto userDto = new UserDto();

        when(userService.register(request)).thenReturn(new ResponseEntity<>(userDto, HttpStatus.CREATED));

        var response = userController.registerUser(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDto, response.getBody());
        verify(userService).register(request);
    }

    @Test
    void getUserById_success() {
        UUID id = UUID.randomUUID();
        UserDto userDto = new UserDto();

        when(userService.getUserById(id)).thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));

        var response = userController.getUserById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
        verify(userService).getUserById(id);
    }

    @Test
    void getUserByIdWithAddresses_success() {
        UUID id = UUID.randomUUID();
        UserWithAddressesDto dto = new UserWithAddressesDto();

        when(userService.getUserByIdWithAddresses(id)).thenReturn(new ResponseEntity<>(dto, HttpStatus.OK));

        var response = userController.getUserByIdWithAddresses(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        verify(userService).getUserByIdWithAddresses(id);
    }

    @Test
    void getUserByPhoneNumber_success() {
        String phone = "12345";
        UserDto userDto = new UserDto();

        when(userService.getUserByPhone(phone)).thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));

        var response = userController.getUserByPhoneNumber(phone);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
        verify(userService).getUserByPhone(phone);
    }

    @Test
    void deleteUser_success() {
        UUID id = UUID.randomUUID();

        when(userService.deleteUser(id)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        var response = userController.deleteUser(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService).deleteUser(id);
    }

    @Test
    void updateUser_success() {
        UUID id = UUID.randomUUID();
        UserDto request = new UserDto();
        UserDto userDto = new UserDto();

        when(userService.updateUser(id, request)).thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));

        var response = userController.updateUser(id, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
        verify(userService).updateUser(id, request);
    }
}