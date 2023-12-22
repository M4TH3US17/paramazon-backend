package br.com.paramazon.demo.application.services.user.validations;

import br.com.paramazon.demo.domain.repository.user.validations.ValidationEmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationEmailService {

    private final ValidationEmailRepository repository;
}
