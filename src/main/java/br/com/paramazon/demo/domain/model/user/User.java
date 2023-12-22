package br.com.paramazon.demo.domain.model.user;


import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.band.Band;
import br.com.paramazon.demo.domain.model.media.Media;
import br.com.paramazon.demo.domain.model.user.role.Role;
import br.com.paramazon.demo.domain.model.user.role.UserRole;
import br.com.paramazon.demo.domain.model.user.validations.ValidationEmail;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Getter @Setter
@ToString @NoArgsConstructor
@Builder @Entity
@Table(name = "user") @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id")
    private Media photograph;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_id")
    private ValidationEmail email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_preference",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "band_id"))
    private Set<Band> preferences;

    @PrePersist
    private void prePersist() {
        this.createDate = LocalDate.now();
    }

}
