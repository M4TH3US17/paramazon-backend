package br.com.paramazon.demo.application.services.show.band;

import br.com.paramazon.demo.domain.repository.show.band.BandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BandService {

    private final BandRepository repository;
}
