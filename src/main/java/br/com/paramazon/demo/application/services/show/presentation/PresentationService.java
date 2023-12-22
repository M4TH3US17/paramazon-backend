package br.com.paramazon.demo.application.services.show.presentation;

import br.com.paramazon.demo.domain.repository.show.presentation.PresentationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PresentationService {

    private final PresentationRepository repository;
}
