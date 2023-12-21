package br.com.paramazon.demo.application.services;

import br.com.paramazon.demo.domain.model.usuario.Usuario;
import br.com.paramazon.demo.domain.repository.UsuarioRepository;
import br.com.paramazon.demo.infrastructure.response.usuario.UsuarioResponse;
import br.com.paramazon.demo.utils.usuarios.UsuarioUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j @Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioResponse getAllUsers() {
        log.info("UsuarioService :: Obtendo todos os usuarios cadastrados no sistema...");

        List<Usuario> users = repository.findAllByEnabledTrue();
        if (users.isEmpty()){
            log.info("UsuarioService :: Nenhum usuario cadastrado no sistema");
            return UsuarioResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message("Não foi encontrado nenhum usuario na base")
                    .build();
        } else {
            return UsuarioResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("Segue a lista com todos os Usuarios encontrados")
                    .data(UsuarioUtils.retornaListaDeUsuariosDaBase(users))
                    .build();
        }
    }
}
