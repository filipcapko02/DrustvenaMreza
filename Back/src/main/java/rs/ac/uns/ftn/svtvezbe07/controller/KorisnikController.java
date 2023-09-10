package rs.ac.uns.ftn.svtvezbe07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Euloga;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;
import rs.ac.uns.ftn.svtvezbe07.service.KorisnikService;

import javax.transaction.Transactional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/user")
public class KorisnikController {
    @Autowired
    KorisnikService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @PutMapping("/updatepassword/{old}")
    public ResponseEntity<User> create(@RequestBody User user, @PathVariable("old") String oldPassword) {

        String encodedPassword = service.geOne(user.getUserId()).getPassword();

        boolean match = passwordEncoder.matches(oldPassword, encodedPassword);
        System.out.println(oldPassword);
        System.out.println(service.geOne(user.getUserId()).getPassword());
        if(match){
            user.setUloga(Euloga.USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User nov = service.save(user);
            return new ResponseEntity<>(nov, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(user, HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
