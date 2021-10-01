package com.helpy.service.impl;

import com.helpy.exception.ResourceNotFoundException;
import com.helpy.model.Material;
import com.helpy.repository.ExpertRepository;
import com.helpy.repository.MaterialRepository;
import com.helpy.repository.PlayerRepository;
import com.helpy.service.MaterialService;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MaterialServiceImplTest {
    @MockBean
    private MaterialRepository materialRepository;

    @MockBean
    private ExpertRepository expertRepository;

    @MockBean
    private PlayerRepository playerRepository;

    @Autowired
    private MaterialService materialService;

    @TestConfiguration
    static  class TrainingMaterialServiceImplTestConfiguration{
        @Bean
        public  MaterialService trainingMaterialService(){
            return new MaterialServiceImpl();
        }
    }
    @Test
    @DisplayName("When getMaterialById With Valid Id Then Return Material")
    public void whenGetTMaterialByIdWithValidIdThenReturnMaterial() throws Exception {
        //Arrange
        Long id = 1L;
        Material trainingMaterial = new Material();
        trainingMaterial.setId(id);
        when(materialRepository.findById(id))
                .thenReturn(Optional.of(trainingMaterial));
        //Act
        Material foundMaterial = materialService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Material", "Id", id));
        //Assert
        assertThat(foundMaterial.getId()).isEqualTo(id);
    }

}