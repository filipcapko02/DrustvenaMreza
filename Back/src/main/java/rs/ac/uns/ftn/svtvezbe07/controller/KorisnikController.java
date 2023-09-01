package rs.ac.uns.ftn.svtvezbe07.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Euloga;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Korisnik;
import rs.ac.uns.ftn.svtvezbe07.service.KorisnikService;

import javax.transaction.Transactional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/korisnik")
public class KorisnikController {
    @Autowired
    KorisnikService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @PutMapping("/updatelozinka/{stara}")
    public ResponseEntity<Korisnik> create(@RequestBody Korisnik korisnik, @PathVariable("stara") String stara_lozinka) {

        String encodedPassword = service.geOne(korisnik.getKorisnik_id()).getLozinka();

        boolean match = passwordEncoder.matches(stara_lozinka, encodedPassword);
        System.out.println(stara_lozinka);
        System.out.println(service.geOne(korisnik.getKorisnik_id()).getLozinka());
        if(match){
            korisnik.setUloga(Euloga.KORISNIK);
            korisnik.setLozinka(passwordEncoder.encode(korisnik.getLozinka()));
            Korisnik nov = service.save(korisnik);
            return new ResponseEntity<>(nov, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(korisnik, HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
