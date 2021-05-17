package br.com.erik.springboot.controller;

import br.com.erik.springboot.domain.Anime;
import br.com.erik.springboot.service.AnimeService;
import br.com.erik.springboot.util.AnimeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
class AnimeControllerTest {

    @InjectMocks //classe que será testada
    private AnimeController animeController;

    @Mock //mock para as classes que estão sendo utilizadas dentro da classe testada
    private AnimeService animeService;

    @BeforeEach
    void setup(){
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeService.listAll(ArgumentMatchers.any()))
                .thenReturn(animePage);
    }

    @Test
    @DisplayName(("List returns list of anime inside page object when successful"))
    void list_ReturnsListOfAnimesInsidePagesObject_WhenSuccessful() {
        String expectedName = AnimeCreator.createAnimeToBeSaved().getName();
        Page<Anime> animePage = animeController.list(null).getBody();

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);

    }
}