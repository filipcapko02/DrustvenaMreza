package rs.ac.uns.ftn.svtvezbe07.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;

import java.util.ArrayList;
import java.util.List;

@Primary
public class KorisnikDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private KorisnikService korisnikService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = korisnikService.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("Ne postoji korisnik sa korisnicik imenom " + username);
        }else{
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            String role = "ROLE_" + user.getRole().toString();
            grantedAuthorities.add(new SimpleGrantedAuthority(role));

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername().trim(),
                    user.getPassword().trim(),
                    grantedAuthorities);
        }
    }
}
