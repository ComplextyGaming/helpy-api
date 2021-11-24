package com.helpy;

import com.helpy.dto.GameRequest;
import com.helpy.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

@SpringBootApplication @Slf4j
public class HelpyApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelpyApiApplication.class, args);
    }

    /*
    @Bean
    CommandLineRunner run(GameService gameService){
        return args -> {
            if (gameService.getAll().isEmpty()){
                log.info("Games is empty");
                gameService.create(new GameRequest(1372L, "Counter-Strike: Global Offensive"));
                gameService.create(new GameRequest(2963L, "Dota 2"));
                gameService.create(new GameRequest(114795L, "Apex Legends"));
                gameService.create(new GameRequest(131800L, "Call of Duty: Warzone"));

            }
        };
    }
    */
}
