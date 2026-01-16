package com.gladkiei.tasktracker.services;

import com.gladkiei.tasktracker.jwt.auth.JwtAuthResponse;
import com.gladkiei.tasktracker.dtos.AuthRequestDto;
import com.gladkiei.tasktracker.models.User;
import com.gladkiei.tasktracker.security.UserDetailsImpl;
import com.gladkiei.tasktracker.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthResponse signUp(AuthRequestDto request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.ROLE_USER)
                .build();

        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .user(user)
//                .role(Role.ROLE_USER)
                .build();

        userService.save(user);
        var jwt = jwtService.generateToken(userDetails);
        return new JwtAuthResponse(jwt);
    }

    public JwtAuthResponse signIn(AuthRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        var jwt = jwtService.generateToken(userDetails);
        return new JwtAuthResponse(jwt);
    }
}
