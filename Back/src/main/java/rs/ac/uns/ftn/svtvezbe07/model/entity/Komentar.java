package rs.ac.uns.ftn.svtvezbe07.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "komentar")
public class Komentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sadzraj;

    @Column
    private Long korisnik_Id;

    @Column
    private LocalDateTime d_v_objave;

    @ManyToOne
    @JoinColumn(name = "objava_id")
    @JsonIgnore
    private Objava objava;

    @Column
    private boolean isDeleted;

    public Komentar() {
        this.d_v_objave = LocalDateTime.now();
    }
}
