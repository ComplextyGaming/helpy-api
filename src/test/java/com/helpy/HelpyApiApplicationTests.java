package com.helpy;

import com.helpy.model.Player;
import com.helpy.model.User;
import com.helpy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
class HelpyApiApplicationTests {

    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Test
    void verficarClave() {
        Player us = new Player();
        us.setUsername("user@gmail.com");
        us.setPassword(bcrypt.encode("1234"));
        us.setFirstName("user");
        us.setLastName("userlast");
        us.setEmail("user@gmail.com");
        us.setPhone("961460601");
        Player retorno = repo.save(us);
        assertTrue(retorno.getPassword().equals(us.getPassword()));
    }
}
