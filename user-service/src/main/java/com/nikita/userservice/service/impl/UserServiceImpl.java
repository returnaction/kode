package com.nikita.userservice.service.impl;

import com.nikita.userservice.mapper.UserMapper;
import com.nikita.userservice.model.dto.UserCreateRequestDto;
import com.nikita.userservice.model.dto.UserDto;
import com.nikita.userservice.model.dto.UserWithAddressesDto;
import com.nikita.userservice.model.entity.UserEntity;
import com.nikita.userservice.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    //TODO не забудь сделать интерфейс для сервиса и в контролер пихать уже интерфейс
    private final UserRepository userRepository;

    public ResponseEntity<UserDto> register(@Valid UserCreateRequestDto request) {
        if(userRepository.existsByPhone(request.getPhone())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number already exists");
        }

        // TODO может лучше не делать статический метод его потом будет тяжело тестировать???
        UserEntity newUser = UserMapper.toUserEntity(request);
        // TODO encrypt Password
        // TODO вынести создание User в helper

        UserEntity savedUser = userRepository.save(newUser);
        UserDto userDto = UserMapper.toUserResponseDto(savedUser);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);

    }

    @Transactional(readOnly = true)
    public ResponseEntity<UserDto> getUserById(UUID id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Пользователь не найден"));
        UserDto userDto = UserMapper.toUserResponseDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }

    @Transactional(readOnly = true)
    public ResponseEntity<UserWithAddressesDto> getUserByIdWithAddresses(UUID id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));
        UserWithAddressesDto userWithAddressesDto = UserMapper.toUserWithAddressesResponseDto(user);
        return new ResponseEntity<>(userWithAddressesDto, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<UserDto> getUserByPhone(String phone) {
        UserEntity user = userRepository.findByPhone(phone).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));
        UserDto userDto = UserMapper.toUserResponseDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


    public ResponseEntity<Void> deleteUser(UUID id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<UserDto> updateUser(UUID id, UserDto request) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());

        UserEntity save = userRepository.save(user);
        UserDto userDto = UserMapper.toUserResponseDto(save);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
