package br.com.erik.springboot.service;

import br.com.erik.springboot.domain.Anime;
import br.com.erik.springboot.exception.BadRequestException;
import br.com.erik.springboot.mapper.AnimeMapper;
import br.com.erik.springboot.repository.AnimeRepository;
import br.com.erik.springboot.requests.AnimePostRequestBody;
import br.com.erik.springboot.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeMapper mapper;

    private final AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public List<Anime> listAllNonPageable() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findByIdOrThrowBadRequestException(Long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not Found"));
    }

    @Transactional // da o rollback, caso exista uma checked exception
    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(mapper.toAnime(animePostRequestBody));
    }

    public void delete(Long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        var savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        var anime = mapper.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }

}
