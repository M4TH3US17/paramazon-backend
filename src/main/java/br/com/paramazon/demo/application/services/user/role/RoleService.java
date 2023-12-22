package br.com.paramazon.demo.application.services.user.role;

import br.com.paramazon.demo.domain.repository.user.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;


}
