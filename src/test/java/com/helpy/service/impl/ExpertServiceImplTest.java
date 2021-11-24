//package com.helpy.service.impl;
//
//import com.helpy.exception.ResourceNotFoundException;
//import com.helpy.model.Expert;
//import com.helpy.repository.ExpertRepository;
//import com.helpy.service.ExpertService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.web.client.ResourceAccessException;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.catchThrowable;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//class ExpertServiceImplTest {
//
//    @MockBean
//    private ExpertRepository expertRepository;
//    @Autowired
//    private ExpertService expertService;
//
//    @TestConfiguration
//    static  class ExpertServiceImplTestConfiguration{
//        @Bean
//        public  ExpertService expertService(){
//            return new ExpertServiceImpl();
//        }
//    }
//
//    @Test
//    @DisplayName("When getExpertById With Valid Id Then Return Expert")
//    public void whenGetExpertByIdWithValidIdThenReturnExpert() throws Exception {
//        //Arrange
//        Long id = 1L;
//        Expert expert = new Expert();
//        expert.setId(id);
//        when(expertRepository.findById(id))
//                .thenReturn(Optional.of(expert));
//        //Act
//        Expert foundExpert = expertService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Expert", "Id", id));
//        //Assert
//        assertThat(foundExpert.getId()).isEqualTo(id);
//    }
//
//    @Test
//    @DisplayName("When getExpertById With Invalid Id Then Returns Resource Not Found Exception")
//    public void whenGetExpertByIdWithInvalidIdThenReturnsResourceNotFoundException(){
//        //Arrange
//        Long id = 1L;
//        String template = "Resource %s not found for %s with value %s";
//        when(expertRepository.findById(id))
//                .thenReturn(Optional.empty());
//        String expectedMessage = String.format(template,"Expert", "Id", id);
//        //Act
//        Throwable exception = catchThrowable(() -> {
//            Expert foundExpert = expertService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Expert", "Id", id));
//        });
//        //Assert
//        assertThat(exception)
//                .isInstanceOf(ResourceNotFoundException.class)
//                .hasMessage(expectedMessage);
//    }
//}