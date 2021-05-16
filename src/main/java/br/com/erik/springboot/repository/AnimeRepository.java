package br.com.erik.springboot.repository;

import br.com.erik.springboot.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
}
