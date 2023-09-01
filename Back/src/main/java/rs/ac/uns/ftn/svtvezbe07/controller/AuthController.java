package rs.ac.uns.ftn.svtvezbe07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.svtvezbe07.konstruktori.LoginRequest;
import rs.ac.uns.ftn.svtvezbe07.konstruktori.RegisterRequest;
import rs.ac.uns.ftn.svtvezbe07.konstruktori.UserTokenState;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Korisnik;
import rs.ac.uns.ftn.svtvezbe07.service.AuthService;
import rs.ac.uns.ftn.svtvezbe07.service.KorisnikService;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    KorisnikService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Validated RegisterRequest registerRequest){
        authService.singup(registerRequest);
        return new ResponseEntity<>("Register successful", HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> login(@RequestBody LoginRequest loginrequest){
        return authService.login(loginrequest);

    }
    @GetMapping("/profil")
    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    public Korisnik korisnik(Principal korisnik) {
        return this.userService.findByUsername(korisnik.getName());
    }
}

