package rs.ac.uns.ftn.svtvezbe07.service;


import rs.ac.uns.ftn.svtvezbe07.konstruktori.KorisnikKonstruktor;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;

public interface KorisnikSevice {

    User geOne(Long userId);

    User findByUsername(String username);


    User createUser(KorisnikKonstruktor korisnikDTO);
}
