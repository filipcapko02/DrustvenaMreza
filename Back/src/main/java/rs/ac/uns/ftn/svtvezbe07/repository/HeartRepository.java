package rs.ac.uns.ftn.svtvezbe07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Heart;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Like;

import org.springframework.stereotype.Repository;
@Repository

public interface HeartRepository extends JpaRepository<Heart, Long> {
}
