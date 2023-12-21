package br.com.paramazon.demo.domain.model.usuario;

import br.com.paramazon.demo.domain.model.usuario.role.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.*;
import java.util.HashSet;
import java.util.Set;

@Data @Builder
@Entity @Table(name = "usuario")
@NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usuario implements Serializable {

    @Id
    @Column(nullable = false, name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String email;

    @Column
    @Builder.Default
    private boolean enabled = false;

    @Column
    private String urlImage;

    @Column
    private LocalDate RegistrationDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Role> roles = new HashSet<>();

}
