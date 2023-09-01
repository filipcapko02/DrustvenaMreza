package rs.ac.uns.ftn.svtvezbe07.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Korisnik;
import rs.ac.uns.ftn.svtvezbe07.service.KorisnikService;

import javax.servlet.http.HttpServletRequest;

//** Komponenta koja moze da obavlja dodatnu proveru zahteva pre nego sto dospe na endpoint.
// Moguce je pristupiti @PathVariable podacima sa URL-a zahteva na endpoint, poput {id}.
// https://docs.spring.io/spring-security/site/docs/5.2.11.RELEASE/reference/html/authorization.html
@Component
public class WebSecurity {

    @Autowired
    private KorisnikService userService;

    public boolean checkClubId(Authentication authentication, HttpServletRequest request, int id) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Korisnik korisnik = userService.findByUsername(userDetails.getUsername());
            if(id == korisnik.getKorisnik_id()) {
                return true;
            }
            return false;
        }
}
