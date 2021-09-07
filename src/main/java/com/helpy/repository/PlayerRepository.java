package com.helpy.repository;

import com.helpy.model.Player;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends GenericRepository<Player, Long>{
}
