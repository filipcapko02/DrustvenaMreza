package rs.ac.uns.ftn.svtvezbe07.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Komentar;
import rs.ac.uns.ftn.svtvezbe07.repository.KomentarRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class KomentarService {
    private final KomentarRepository repository;

    @Transactional
    public Komentar save(Komentar komentar) {
        return repository.save(komentar);
    }

    @Transactional
    public void delete(Komentar komentar){repository.delete(komentar);}

}
