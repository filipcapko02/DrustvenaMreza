package rs.ac.uns.ftn.svtvezbe07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Dislike;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Like;

@Repository
public interface DislikeRepository extends JpaRepository<Dislike, Long>{
}
