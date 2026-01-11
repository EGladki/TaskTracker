package com.gladkiei.tasktracker.services;

import com.gladkiei.tasktracker.dtos.AuthRequestDto;
import com.gladkiei.tasktracker.dtos.UserResponseDto;
import com.gladkiei.tasktracker.exceptions.AlreadyExistException;
import com.gladkiei.tasktracker.exceptions.NotFoundException;
import com.gladkiei.tasktracker.mapper.UserMapper;
import com.gladkiei.tasktracker.models.User;
import com.gladkiei.tasktracker.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new AlreadyExistException("Such email already exists!");
        }

//        User user = UserMapper.INSTANCE.AuthRequestDtoToUser(requestDto);
        return userRepository.save(user);
//        return UserMapper.INSTANCE.userToUserResponseDto(saved);
    }

    public User getByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotFoundException("User not found!");
        }
    }

    public void deleteUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new NotFoundException("User not found!");
        }
    }

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotFoundException("User not found!");
        }
    }

}
