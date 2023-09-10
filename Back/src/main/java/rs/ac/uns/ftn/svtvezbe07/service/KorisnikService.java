package rs.ac.uns.ftn.svtvezbe07.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.konstruktori.KorisnikKonstruktor;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Euloga;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;
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
    public User geOne(Long userId) { return korisnikRepository.getOne(userId);};
    @Override
    public User findByUsername(String username) {
        Optional<User> user = korisnikRepository.findByUsername(username);
        if (!user.isEmpty()) {
            return user.get();
        }
        return null;
    }
    @Transactional
    public User save(User user) {
        return korisnikRepository.save(user);
    }

    @Override
    public User createUser (KorisnikKonstruktor korisnik1) {

        Optional<User> user = korisnikRepository.findByUsername(korisnik1.getUsername());

        if(user.isPresent()){
            return null;
        }

        User nov_user = new User();
        nov_user.setUsername(korisnik1.getUsername());
        nov_user.setPassword(passwordEncoder.encode(korisnik1.getPassword()));
        nov_user.setUloga(Euloga.USER);
        nov_user.setFirstName(korisnik1.getFirstName());
        nov_user.setLastName(korisnik1.getLastName());
        nov_user.setEmail(korisnik1.getEmail());
        nov_user = korisnikRepository.save(nov_user);

        return nov_user;
    }
}
