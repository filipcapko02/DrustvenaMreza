package rs.ac.uns.ftn.svtvezbe07.service;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Korisnik;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Primary
public class KorisnikDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private KorisnikService korisnikService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Korisnik korisnik = korisnikService.findByUsername(username);

        if(korisnik == null){
            throw new UsernameNotFoundException("Ne postoji korisnik sa korisnicik imenom " + username);
        }else{
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            String uloga = "ULOGA_" + korisnik.getUloga().toString();
            grantedAuthorities.add(new SimpleGrantedAuthority(uloga));

            return new org.springframework.security.core.userdetails.User(
                    korisnik.getUsername().trim(),
                    korisnik.getLozinka().trim(),
                    grantedAuthorities);
        }
    }
}
