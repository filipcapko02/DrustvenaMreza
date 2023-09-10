package rs.ac.uns.ftn.svtvezbe07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;

import java.util.Optional;
public interface KorisnikRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
