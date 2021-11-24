package com.helpy.repository;

import com.helpy.model.Game;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends GenericRepository<Game, Long> {
    List<Game> findByNameContainingIgnoreCase(String like);
}
