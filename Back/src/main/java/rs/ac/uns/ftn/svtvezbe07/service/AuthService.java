package rs.ac.uns.ftn.svtvezbe07.service;


import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.konstruktori.LoginRequest;
import rs.ac.uns.ftn.svtvezbe07.konstruktori.RegisterRequest;
import rs.ac.uns.ftn.svtvezbe07.konstruktori.UserTokenState;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Euloga;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Korisnik;
import rs.ac.uns.ftn.svtvezbe07.repository.KorisnikRepository;
import rs.ac.uns.ftn.svtvezbe07.security.TokenUtils;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private KorisnikRepository korisnikRepository;


    @Autowired
    TokenUtils tokenUtils;

    private final AuthenticationManager authenticationManager;
    @Transactional
    public void singup(RegisterRequest request){
        Korisnik korisnik = new Korisnik();
        korisnik.setIme(request.getIme());
        korisnik.setPrezime(request.getPrezime());
        korisnik.setUsername(request.getUsername());
        korisnik.setUloga(Euloga.KORISNIK);
        korisnik.setLozinka(passwordEncoder.encode(request.getLozinka()));
        korisnik.setEmail(request.getEmail());
        korisnik.setDeleted(false);
        korisnikRepository.save(korisnik);
    }
    public ResponseEntity<UserTokenState> login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getLozinka()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user);
        int expiresIn = tokenUtils.getExpiredIn();
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }


}

