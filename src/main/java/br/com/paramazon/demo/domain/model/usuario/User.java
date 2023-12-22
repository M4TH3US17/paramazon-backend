package br.com.paramazon.demo.domain.model.usuario;


import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.band.Band;
import br.com.paramazon.demo.domain.model.media.Media;
import br.com.paramazon.demo.domain.model.usuario.role.UserRole;
import br.com.paramazon.demo.domain.model.usuario.validations.ValidationEmail;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class User implements Serializable {

    private Long idUser;
    private String username;
    private String password;
    private Status status;
    private Media photograph;
    private LocalDate createDate;
    private ValidationEmail email;
    private Set<UserRole> role = new HashSet<>();
    private Set<Band> preferences = new HashSet<>();

}
