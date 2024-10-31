package org.example.crmbackinterntask.service;

import org.example.crmbackinterntask.model.request.LoginRequest;
import org.example.crmbackinterntask.model.request.UserRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> login(LoginRequest loginRequest);
    void register(UserRequest userRequest);
}
