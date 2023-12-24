package br.com.paramazon.demo.application.dto.user.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "User Roles", value = "User Roles", description = "Role Usuario")
public record RoleDTO(String name) {
}
