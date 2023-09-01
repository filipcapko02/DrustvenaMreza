package rs.ac.uns.ftn.svtvezbe07.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.konstruktori.KorisnikKonstruktor;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Euloga;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Korisnik;
import rs.ac.uns.ftn.svtvezbe07.repository.KorisnikRepository;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
public class KorisnikService implements KorisnikSevice {

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Korisnik geOne(Long korisnik_id) { return korisnikRepository.getOne(korisnik_id);};
    @Override
    public Korisnik findByUsername(String username) {
        Optional<Korisnik> korisnik = korisnikRepository.findByUsername(username);
        if (!korisnik.isEmpty()) {
            return korisnik.get();
        }
        return null;
    }
    @Transactional
    public Korisnik save(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

    @Override
    public Korisnik createUser (KorisnikKonstruktor korisnik1) {

        Optional<Korisnik> korisnik = korisnikRepository.findByUsername(korisnik1.getKor_ime());

        if(korisnik.isPresent()){
            return null;
        }

        Korisnik nov_korisnik = new Korisnik();
        nov_korisnik.setKor_ime(korisnik1.getKor_ime());
        nov_korisnik.setLozinka(passwordEncoder.encode(korisnik1.getLozinka()));
        nov_korisnik.setUloga(Euloga.KORISNIK);
        nov_korisnik.setIme(korisnik1.getIme());
        nov_korisnik.setPrezime(korisnik1.getPrezime());
        nov_korisnik.setEmail(korisnik1.getEmail());
        nov_korisnik = korisnikRepository.save(nov_korisnik);

        return nov_korisnik;
    }
}
