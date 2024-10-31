package org.example.crmbackinterntask.controller;

import lombok.RequiredArgsConstructor;
import org.example.crmbackinterntask.model.request.LoginRequest;
import org.example.crmbackinterntask.model.request.UserRequest;
import org.example.crmbackinterntask.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserRequest userRequestDto){
        authService.register(userRequestDto);
    }


}
