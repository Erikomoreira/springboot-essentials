package br.com.erik.springboot.repository;

import br.com.erik.springboot.domain.Anime;

import java.util.List;

public interface AnimeRepository {
    List<Anime> listAll();
}
