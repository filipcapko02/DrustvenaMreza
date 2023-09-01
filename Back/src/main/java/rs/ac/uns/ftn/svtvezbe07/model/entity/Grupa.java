package rs.ac.uns.ftn.svtvezbe07.model.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

import java.time.LocalDate;
import java.util.List;
@Data
@Entity
@Getter
@Setter
@Table(name = "grupe")
public class Grupa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ime;

    @Column
    private String opis;

    @Column(nullable = false)
    private LocalDate d_v_kreiranja;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Objava> objave;

    @ManyToOne
    @JoinColumn(name = "korisnik_id", nullable = false)
    private Korisnik korisnik;

    @Column(nullable = false)
    private boolean je_suspendovan;


    public Grupa() {
        this.d_v_kreiranja = LocalDate.now();
        this.je_suspendovan = false;
    }
}
