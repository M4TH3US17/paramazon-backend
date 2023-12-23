package br.com.paramazon.demo.application.dto;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.*;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Media", value = "Media", description = "Arquivo do sistema para uso.")
public record MediaDTO(
        Long idMedia,
        String s3Key
) implements Serializable {

}