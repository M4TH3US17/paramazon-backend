package br.com.paramazon.demo.application.services.show;

import br.com.paramazon.demo.domain.repository.show.ShowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository repository;
}
