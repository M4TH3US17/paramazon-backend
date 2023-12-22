package br.com.paramazon.demo.application.services.music;

import br.com.paramazon.demo.domain.repository.music.MusicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository repository;

}
