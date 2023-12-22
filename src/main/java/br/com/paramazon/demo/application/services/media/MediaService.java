package br.com.paramazon.demo.application.services.media;

import br.com.paramazon.demo.domain.repository.media.MediaRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository repository;

}
