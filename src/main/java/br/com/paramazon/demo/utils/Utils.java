package br.com.paramazon.demo.utils;

import br.com.paramazon.demo.application.dto.ValidationEmailDTO;
import br.com.paramazon.demo.domain.model.user.validations.ValidationEmail;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {


    public static ValidationEmailDTO convertValidationEmailToDTO(ValidationEmail data) {
        return ValidationEmailDTO.builder()
                .idEmail(data.getIdValidationEmail())
                .email(data.getEmail())
                .isConfirmed(data.getIsConfirmed())
                .build();
    }
}
