package br.com.paramazon.demo.application.services.user;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.user.User;
import br.com.paramazon.demo.domain.repository.user.UserRepository;
import br.com.paramazon.demo.infrastructure.response.users.UserResponse;
import br.com.paramazon.demo.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserResponse getAllUsers() {
        log.info("UserService :: Obtendo todos os usuarios cadastrados no sistema...");
        List<User> users = repository.findAllByStatus(Status.ACTIVE);

        if(users.isEmpty()) {
            log.info("UserService :: Nenhum usuario encontrado!");
            return UserResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message("Não foi encontrado nenhum usuario na base")
                    .build();
        }

        return UserResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Segue a lista com todos os Usuarios encontrados")
                .data(UserUtils.buildBaseUserList(users))
                .build();
    }
}
