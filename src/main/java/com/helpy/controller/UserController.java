package com.helpy.controller;

import com.helpy.dto.UserResponse;
import com.helpy.model.User;
import com.helpy.repository.UserRepository;
import com.helpy.util.UserConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor @Slf4j
public class UserController {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        var users = userRepository.findAll();
        return ResponseEntity.ok().body(userConverter.converToResponse(users));
    }
}
