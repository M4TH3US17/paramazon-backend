package br.com.paramazon.demo.utils;

import br.com.paramazon.demo.application.dto.user.validations.ValidationEmailDTO;
import br.com.paramazon.demo.domain.model.user.validations.ValidationEmail;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {


    public static ValidationEmailDTO convertValidationEmailToDTO(ValidationEmail data) {
        return new ValidationEmailDTO(
                data.getIdValidationEmail(),
                data.getEmail(),
                data.getIsConfirmed()
        );
    }
}
