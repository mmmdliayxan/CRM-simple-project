package org.example.crmbackinterntask.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.crmbackinterntask.entity.Authority;
import org.example.crmbackinterntask.entity.User;
import org.example.crmbackinterntask.jwt.JwtService;
import org.example.crmbackinterntask.mapper.UserMapper;
import org.example.crmbackinterntask.model.consts.Const;
import org.example.crmbackinterntask.model.request.LoginRequest;
import org.example.crmbackinterntask.model.request.UserRequest;
import org.example.crmbackinterntask.model.response.LoginResponse;
import org.example.crmbackinterntask.repository.AuthorityRepository;
import org.example.crmbackinterntask.repository.UserRepository;
import org.example.crmbackinterntask.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        try {
            log.info("authenticate method started by: {}", loginRequest.getUsername());

            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));
            log.info("authentication details: {}", authentication);

            String username = authentication.getName();

            User user = userRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new NoSuchElementException("Credentials are wrong"));

            String token = jwtService.issueToken(user);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, Const.BEARER + token);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUsername(username);
            loginResponse.setToken(token);

            log.info("user: {} logged in", user.getUsername());
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(loginResponse);
        }catch (BadCredentialsException e){
            log.error("Error due to {} ", e.getMessage());
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void register(UserRequest userRequestDto) {
        log.info("user registration is started");

        User user = userMapper.toUser(userRequestDto);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        Authority authority = authorityRepository.findByAuthority("USER");
        user.setAuthority(authority);
        userRepository.save(user);
        log.info("user is successfully registered");
    }
}
