package br.com.paramazon.demo.utils.user.role;

import br.com.paramazon.demo.application.dto.user.role.RoleDTO;
import br.com.paramazon.demo.domain.model.user.role.Role;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleUtils {

    /**
     * Converte uma lista de entidades role em uma lista de DTO.
     *
     * @param roles Um conjunto de entidades Role.
     * @return Um conjunto de RoleDTO.
     */
    public static Set<RoleDTO> buildBaseRoleList(Set<Role> roles) {
        return roles.stream()
                .map(role -> convertToDTO(role))
                .collect(Collectors.toSet());
    }

    /**
     * Converte uma unica role em RoleDTO.
     *
     * @param data Trata-se de uma entidade Role.
     * @return Uma RoleDTO.
     */
    public static RoleDTO convertToDTO(Role data) {
        return new RoleDTO(data.getName());
    }

}
