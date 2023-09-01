package rs.ac.uns.ftn.svtvezbe07.model.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name = "objave")
public class Objava {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long objava_id;

    @Column
    private String objava_ime;

    @Column
    private String sadzaj;

    @Column
    private String url;

    @Column
    private LocalDateTime creationDate;

    @ManyToOne
    private Korisnik korisnik;

    @OneToMany(mappedBy = "objava", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Komentar> comments = new ArrayList<>();

    @OneToMany(mappedBy = "objava", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    public Objava() {
        this.creationDate = LocalDateTime.now();
    }
}
