package rs.ac.uns.ftn.svtvezbe07.model.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "admini_grupe")
public class Admin_grupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "korisnik_id", nullable = false)
    private Korisnik korisnik;
}
