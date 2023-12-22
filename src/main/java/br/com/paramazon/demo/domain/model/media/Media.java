package br.com.paramazon.demo.domain.model.media;

import br.com.paramazon.demo.domain.enums.MediaType;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter @Setter
@ToString
@NoArgsConstructor @Builder
@Entity @Table(name = "media")
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Media implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedia;

    @Column(name = "s3_key", nullable = false)
    private String s3Key;

    @Column(name = "upload_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MediaType type;

    @PrePersist
    private void prePersist() {
        this.createDate = LocalDate.now();
    }

}
