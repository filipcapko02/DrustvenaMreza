package rs.ac.uns.ftn.svtvezbe07.model.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "slike")
public class Slika {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String putanja;

    @ManyToOne
    private Objava objava;

    @ManyToOne
    private Korisnik koisnik;
}
