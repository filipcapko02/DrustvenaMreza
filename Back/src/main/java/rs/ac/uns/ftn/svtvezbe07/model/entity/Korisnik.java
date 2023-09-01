package rs.ac.uns.ftn.svtvezbe07.model.entity;

//import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "korisnici")
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long korisnik_id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String lozinka;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private LocalDateTime d_v_prijave;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Euloga uloga;

    @OneToMany(mappedBy = "korisnik")
    private Set<Admin_grupe> admin_grupe;

    @Column
    private boolean isDeleted;
}
