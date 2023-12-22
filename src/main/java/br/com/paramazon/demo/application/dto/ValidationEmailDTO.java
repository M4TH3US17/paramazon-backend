package br.com.paramazon.demo.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.*;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Email Validation", value = "Email Validation", description = "Situacao do email no sistema.")
public class ValidationEmailDTO implements Serializable {

    private Long idEmail;
    private String email;
    private Boolean isConfirmed;

}
