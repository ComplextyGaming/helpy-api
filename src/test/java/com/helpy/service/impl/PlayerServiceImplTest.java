package com.helpy.service.impl;

import com.helpy.exception.ResourceNotFoundException;
import com.helpy.model.Player;
import com.helpy.repository.PlayerRepository;
import com.helpy.service.PlayerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PlayerServiceImplTest {

    @MockBean
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

    @TestConfiguration
    static  class PlayerServiceImplTestConfiguration{
        @Bean
        public  PlayerService playerService(){
            return new PlayerServiceImpl();
        }
    }

    @Test
    @DisplayName("When getPlayerById With Valid Id Then Return Player")
    public void whenGetPlayerByIdWithValidIdThenReturnPlayer() throws Exception {
        //Arrange
        Long id = 1L;
        Player player = new Player();
        player.setId(id);
        when(playerRepository.findById(id))
                .thenReturn(Optional.of(player));
        //Act
        Player foundPlayer = playerService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Player", "Id", id));
        //Assert
        assertThat(foundPlayer.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("When getPlayerById With Invalid Id Then Returns Resource Not Found Exception")
    public void whenGetPlayerByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(playerRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template,"Player", "Id", id);
        //Act
        Throwable exception = catchThrowable(() -> {
            Player foundPlayer = playerService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Player", "Id", id));
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}