package br.com.paramazon.demo.domain.model.user.validations;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter @Setter
@ToString @NoArgsConstructor
@Builder @Entity
@Table(name = "validation_email") @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationEmail implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idValidationEmail;

    @Column(nullable = false)
    private String email;

    @Column(name = "verification_code", nullable = false)
    private Integer verificationCode;

    @Builder.Default
    @Column(name = "is_confirmed ", nullable = false)
    private Boolean isConfirmed = false;
}
