package br.com.paramazon.demo.domain.model.singer;

import br.com.paramazon.demo.domain.model.band.Band;
import br.com.paramazon.demo.domain.model.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "singer")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Singer implements Serializable {

    @Id
    @Column(nullable = false, name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSinger;

    @Column
    @Builder.Default
    private boolean enabled = false;

    @Column
    private LocalDate registrationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario singer;

}
