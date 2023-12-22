package br.com.paramazon.demo.domain.model.music;

import br.com.paramazon.demo.domain.enums.MusicalStyle;
import br.com.paramazon.demo.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter @Setter
@ToString
@NoArgsConstructor @Builder
@Entity @Table(name = "music")
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Music implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMusic;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "style", nullable = false)
    @Enumerated(EnumType.STRING)
    private MusicalStyle style;

}
