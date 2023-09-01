package rs.ac.uns.ftn.svtvezbe07.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Grupa;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Objava;
import rs.ac.uns.ftn.svtvezbe07.repository.GrupaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class GrupaService {
    public final GrupaRepository repository;


    public List<Grupa> getAll() {
        return repository.findAll();
    }
    public Grupa getGroup(Long id) {
        return repository.findById(id).get();
    }
    @Transactional
    public Grupa save(Grupa group) {
        return repository.save(group);
    }
    public Grupa getGroupByPost(Objava objava) {
        List<Grupa> grupe = getAll();
        for (Grupa grupa : grupe) {
            List<Objava> posts = grupa.getObjave();
            if (posts.contains(objava)) {
                return grupa;
            }
        }
        return null;
    }


    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
