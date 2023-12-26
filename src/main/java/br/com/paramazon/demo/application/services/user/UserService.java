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
            return returnsError404NotFoundResponse("Não foi encontrado nenhum usuario na base", null);
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

        return returnsError404NotFoundResponse(String.format("Usuario de ID %d não foi encontrado na base.", idUser), null);
    }

    public UserResponse disableUser(Long idUser) {
        log.info("UserService :: Iniciando etapa de desativação do usuario");
        Optional<User> user = repository.findByIdUserAndStatus(idUser, Status.ACTIVE);
        try {
            if (user.isEmpty())
                return returnsError404NotFoundResponse(String.format("Usuario de ID %d não foi encontrado na base.", idUser), null);

            log.info("UserService :: Usuario encontrado!");
            User userToBeDeleted = user.get();
            log.info("UsuarioService :: Desativando usuario...");
            userToBeDeleted.setStatus(Status.INACTIVE);
            repository.save(userToBeDeleted);
            userToBeDeleted.setPassword(/*bCryptPasswordEncoder.encode(*/"usu@r10D3s@t1v@do"/*)*/);
            log.info("UserService :: Usuario desativado com sucesso!");
            return new UserResponse(HttpStatus.NO_CONTENT.value(), "Usuario desativado com sucesso!", null);
        } catch (Exception e) {
            return returnsError500InternalServerErrorResponse(e);
        }
    }

    /* METODOS PRIVADOS PARA AUXILIAR A CLASSE DE SERVICO */
    private UserResponse returnsError404NotFoundResponse(String message, Object aFalse) {
        log.info("Não foi possivel encontrar o usuario!");
        return new UserResponse(
                HttpStatus.NOT_FOUND.value(),
                message,
                Objects.nonNull(aFalse) ? aFalse : null);
    }

    private UserResponse returnsError500InternalServerErrorResponse(Exception error) {
        log.error(error.getLocalizedMessage());
        return new UserResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro desconhecido!",
                error);
    }

    private UserResponse returnsError400BadRequestResponse(String message) {
        log.info(message);
        return new UserResponse(
                HttpStatus.BAD_REQUEST.value(),
                message,
                null);
    }
}
