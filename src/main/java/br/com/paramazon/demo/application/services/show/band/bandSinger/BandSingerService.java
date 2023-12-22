package br.com.paramazon.demo.application.services.show.band.bandSinger;

import br.com.paramazon.demo.domain.repository.show.band.BandSingerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BandSingerService {

    private final BandSingerRepository repository;

}
