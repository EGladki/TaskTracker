package com.gladkiei.tasktracker.services;

import com.gladkiei.tasktracker.dtos.event.RegistrationEventDto;
import com.gladkiei.tasktracker.dtos.user.UserResponseDto;
import com.gladkiei.tasktracker.exceptions.EmailAlreadyExistException;
import com.gladkiei.tasktracker.exceptions.NotFoundException;
import com.gladkiei.tasktracker.mapper.UserMapper;
import com.gladkiei.tasktracker.models.User;
import com.gladkiei.tasktracker.repositories.UserRepository;
import com.gladkiei.tasktracker.services.event.publisher.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EventPublisher eventPublisher;

    @Transactional
    public UserResponseDto save(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistException("This email is already taken");
        }

        User saved = userRepository.save(user);

        RegistrationEventDto registrationEventDto = userMapper.userToRegistrationEventDto(saved);
        eventPublisher.sendRegistrationEvent(registrationEventDto);
        return userMapper.userToUserResponseDto(saved);
    }

    public User getByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotFoundException("User not found!");
        }
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new NotFoundException("User not found!");
        }
    }

    public UserResponseDto getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return userMapper.userToUserResponseDto(user.get());
        } else {
            throw new NotFoundException("User not found!");
        }
    }

}
