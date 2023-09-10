package rs.ac.uns.ftn.svtvezbe07.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Comment;
import rs.ac.uns.ftn.svtvezbe07.repository.KomentarRepository;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class KomentarService {
    private final KomentarRepository repository;

    @Transactional
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Transactional
    public void delete(Comment comment){repository.delete(comment);}

}
