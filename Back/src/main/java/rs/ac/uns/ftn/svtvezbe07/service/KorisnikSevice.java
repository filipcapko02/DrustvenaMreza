package rs.ac.uns.ftn.svtvezbe07.service;


import rs.ac.uns.ftn.svtvezbe07.konstruktori.KorisnikKonstruktor;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Korisnik;

public interface KorisnikSevice {

    Korisnik geOne(Long userId);

    Korisnik findByUsername(String username);


    Korisnik createUser(KorisnikKonstruktor korisnikDTO);
}
