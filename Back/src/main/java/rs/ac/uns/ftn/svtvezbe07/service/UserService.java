package rs.ac.uns.ftn.svtvezbe07.service;

import rs.ac.uns.ftn.svtvezbe07.model.dto.UserDTO;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Korisnik;

import java.util.List;

public interface UserService {

    Korisnik findByUsername(String username);

    Korisnik createUser(UserDTO userDTO);

    List<Korisnik> findAll();
}
