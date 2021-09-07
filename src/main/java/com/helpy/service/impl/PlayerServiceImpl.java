package com.helpy.service.impl;

import com.helpy.model.Player;
import com.helpy.repository.GenericRepository;
import com.helpy.repository.PlayerRepository;
import com.helpy.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl extends CrudServiceImpl<Player, Long> implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    protected GenericRepository<Player, Long> getRepository() {
        return playerRepository;
    }
}
