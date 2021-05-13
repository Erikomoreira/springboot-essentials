package br.com.erik.springboot.service;

import br.com.erik.springboot.domain.Anime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {

    //private AnimeRepository animeRepository;

    public List<Anime> listAll(){
        return List.of(new Anime(1L, "Boku No Hero"), new Anime(2L,"Berserk"));
    }
}
