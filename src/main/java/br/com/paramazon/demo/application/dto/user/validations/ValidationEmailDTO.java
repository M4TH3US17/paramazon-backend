package br.com.paramazon.demo.application.dto.user.validations;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.*;
import lombok.*;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Email Validation", value = "Email Validation", description = "Situacao do email no sistema.")
public record ValidationEmailDTO(
        Long idEmail,
        String email,
        Boolean isConfirmed
) implements Serializable {

}