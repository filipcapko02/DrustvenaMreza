package rs.ac.uns.ftn.svtvezbe07.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Grupa;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Objava;
import rs.ac.uns.ftn.svtvezbe07.repository.GrupaRepository;
import rs.ac.uns.ftn.svtvezbe07.repository.ObjavaRepository;


import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ObjavaService {


    public final ObjavaRepository objavaRepository;

    public final GrupaRepository grupaRepository;

    public List<Objava> getAll() {
        return objavaRepository.findAll();
    }
    public Objava getObjava(Long id) {
        return objavaRepository.findById(id).get();
    }
    @Transactional
    public Objava save(Objava post) {
        return objavaRepository.save(post);
    }


    @Transactional
    public void delete(Long id) {
        objavaRepository.deleteById(id);
    }



    public List<Objava> getGroupPosts(Long id) {
        Grupa grupa = grupaRepository.findById(id).get();
        return grupa.getObjave();
    }

}

