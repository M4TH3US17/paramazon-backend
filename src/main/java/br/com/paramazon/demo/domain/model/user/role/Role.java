package br.com.paramazon.demo.domain.model.user.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@Entity
@Table(name = "role") @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    @Column(name = "name", nullable = false)
    private String name;

    @PrePersist
    private void prePersist() {
        if (this.name == null)
            this.name = UserRole.ROLE_USER.toString();
    }
}
