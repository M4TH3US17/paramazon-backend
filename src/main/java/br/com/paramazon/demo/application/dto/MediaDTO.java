package br.com.paramazon.demo.application.dto;

import br.com.paramazon.demo.domain.enums.MediaType;
import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Media", value = "Media", description = "Arquivo do sistema para uso.")
public class MediaDTO implements Serializable {

    private Long idMedia;
    private String s3Key;
    private MediaType type;
    private Date createDate;
}
