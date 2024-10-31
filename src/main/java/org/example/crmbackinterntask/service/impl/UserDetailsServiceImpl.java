package org.example.crmbackinterntask.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.crmbackinterntask.entity.User;
import org.example.crmbackinterntask.model.enums.UserStatus;
import org.example.crmbackinterntask.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new NoSuchElementException("User is not found"));
        if(!isActive(user)){
            throw new NoSuchElementException("User is not active");
        }
        return user;
    }

    private boolean isActive(User user){
        return user.getStatus()== UserStatus.ACTIVE;
    }
}
