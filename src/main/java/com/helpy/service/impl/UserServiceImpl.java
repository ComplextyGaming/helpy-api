package com.helpy.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.helpy.model.User;
import com.helpy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User usuario = repo.findOneByEmail(email);

        if(usuario == null) {
            throw new UsernameNotFoundException(String.format("Usuario no existe", email));
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        usuario.getRoles().forEach(rol -> {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        });

        return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(), roles);
    }

}
