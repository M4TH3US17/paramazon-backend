package br.com.paramazon.demo.domain.model.media;

import br.com.paramazon.demo.domain.enums.MediaType;

import java.io.Serializable;
import java.time.LocalDate;

public class Media implements Serializable {

    private Long idMedia;
    private String s3Key;
    private MediaType type;
    private LocalDate createDate;
}
