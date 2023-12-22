package br.com.paramazon.demo.domain.model.music;

import br.com.paramazon.demo.domain.enums.MusicalStyle;
import br.com.paramazon.demo.domain.enums.Status;

import java.io.Serializable;

public class Music implements Serializable {

    private Long idMusic;
    private String name;
    private Status status;
    private MusicalStyle style;

}
