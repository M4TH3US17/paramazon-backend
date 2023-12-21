package br.com.paramazon.demo.utils.usuarios;

import br.com.paramazon.demo.application.dto.usuario.UsuarioLogadoDTO;
import br.com.paramazon.demo.application.enums.RoleEnumDTO;
import br.com.paramazon.demo.domain.model.usuario.Usuario;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioUtils {

    public static Object retornaListaDeUsuariosDaBase(List<Usuario> users) {
        List<UsuarioLogadoDTO> list = new ArrayList<>();
        users.forEach(usuario -> list.add(retornaUsuarioLogadoDTO(usuario)));
        return list;
    }

    private static UsuarioLogadoDTO retornaUsuarioLogadoDTO(Usuario usuario) {
        return UsuarioLogadoDTO.builder()
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .urlImage(usuario.getUrlImage())
                .roles(null)
                .build();
    }

 /*   private static Set<RoleEnumDTO> retornaRoleDTO(Set<Role> usuarioRoles) {
        Set<RoleEnumDTO> dtoSet = new HashSet<>();

        usuarioRoles.forEach(usuarioRole -> {
            if (usuarioRole.equals(Role.ROLE_ADMIN))
                dtoSet.add(RoleEnumDTO.Administrador);
            else
                dtoSet.add(RoleEnumDTO.Usuario);
        });

        return dtoSet;
    }*/
}
