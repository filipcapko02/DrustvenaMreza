package rs.ac.uns.ftn.svtvezbe07.konstruktori;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Korisnik;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KorisnikKonstruktor {
    private Long korisnik_id;

    private String kor_ime;

    private String lozinka;

    private String ime;

    private String prezime;

    private String email;


    public KorisnikKonstruktor(Korisnik nov_korisnik) {
        this.korisnik_id = nov_korisnik.getKorisnik_id();
        this.kor_ime = nov_korisnik.getKor_ime();
        this.email = nov_korisnik.getEmail();
        this.ime = nov_korisnik.getIme();
        this.prezime = nov_korisnik.getPrezime();
    }
}
