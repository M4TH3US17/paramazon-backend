package br.com.paramazon.demo.domain.model.show;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.presentation.Presentation;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@Builder @Entity
@Table(name = "show") @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Show implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShow;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private Double budget;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "show_presentation",
            joinColumns = @JoinColumn(name = "show_id"),
            inverseJoinColumns = @JoinColumn(name = "presentation_id"))
    private List<Presentation> presentations;

}
