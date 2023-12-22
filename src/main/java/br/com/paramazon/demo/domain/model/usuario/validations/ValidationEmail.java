package br.com.paramazon.demo.domain.model.usuario.validations;

import java.io.Serializable;

public class ValidationEmail implements Serializable {

    private Long idValidationEmail;
    private String email;
    private Integer verificationCode;
    private Boolean isConfirmed;
}
