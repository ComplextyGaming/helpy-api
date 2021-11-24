package com.helpy.service.impl;

import com.helpy.model.Player;
import com.helpy.model.Rol;
import com.helpy.repository.GenericRepository;
import com.helpy.repository.PlayerRepository;
import com.helpy.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PlayerServiceImpl extends CrudServiceImpl<Player, Long> implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Override
    protected GenericRepository<Player, Long> getRepository() {
        return playerRepository;
    }
    @Autowired
    private BCryptPasswordEncoder bcrypt;
    @Override
    public Player create(Player player) throws Exception {
        player.setPassword(bcrypt.encode(player.getPassword()));
        var role = new Rol();
        role.setIdRol(1);
        player.getRoles().add(role);
        return super.create(player);
    }
}
