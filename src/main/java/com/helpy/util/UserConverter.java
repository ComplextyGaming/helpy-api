package com.helpy.util;

import com.helpy.dto.UserResponse;
import com.helpy.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserConverter {
    private final ModelMapper mapper;

    public UserResponse converToResponse(User user){
        return mapper.map(user, UserResponse.class);

    }
    public List<UserResponse> converToResponse(List<User> users){
        return users.stream().map(this::converToResponse).collect(Collectors.toList());

    }
}
