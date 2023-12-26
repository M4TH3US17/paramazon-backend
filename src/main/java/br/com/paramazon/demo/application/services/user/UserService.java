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

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserResponse getAllUsers() {
        log.info("UserService :: Obtendo todos os usuarios cadastrados no sistema...");
        List<User> activeUsers = repository.findAllByStatus(Status.ACTIVE);

        if (activeUsers.isEmpty()) {
            log.info("UserService :: Nenhum usuario encontrado!");
            return new UserResponse(
                    HttpStatus.NOT_FOUND.value(),
                    "Não foi encontrado nenhum usuario na base",
                    new ArrayList<>());
        }

        return new UserResponse(
                HttpStatus.OK.value(),
                "Segue a lista com todos os Usuarios encontrados",
                UserUtils.buildBaseUserList(activeUsers));
    }

    public UserResponse getUserById(Long idUser) {
        log.info("UserService :: Iniciando a busca de usuario...");
        Optional<User> user = repository.findByIdUserAndStatus(idUser, Status.ACTIVE);

        if(user.isPresent()) {
            log.info("UserService :: Usuario encontrado!");
            return new UserResponse(
                    HttpStatus.OK.value(),
                    String.format("Segue os dados dados do usuário de id %d", idUser),
                    UserUtils.convertUserToDTO(user.get()));
        }

        return new UserResponse(
                HttpStatus.NOT_FOUND.value(),
                String.format("Usuario cujo id é %d não existe", idUser),
                null);
    }

    public UserResponse disableUser(Long idUser) {
        log.info("UserService :: Iniciando etapa de desativação do usuario");
        Optional<User> user = repository.findByIdUserAndStatus(idUser, Status.ACTIVE);
        try {
            if (user.isEmpty())
                return new UserResponse(HttpStatus.NOT_FOUND.value(), "Usuario nao encontrado!", null);

            log.info("UserService :: Usuario encontrado!");
            User user2 = user.get();
            log.info("UsuarioService :: Desativando usuario...");
            user2.setStatus(Status.INACTIVE);
            repository.save(user2);
            user2.setPassword(/*bCryptPasswordEncoder.encode(*/"usu@r10D3s@t1v@do"/*)*/);
            log.info("UserService :: Usuario desativado com sucesso!");
            return new UserResponse(HttpStatus.NO_CONTENT.value(), "Usuario desativado com sucesso!", "");
        } catch (Exception e) {
            return /*setaInternalServerErroResponse(e)*/ null;
        }
    }
}
