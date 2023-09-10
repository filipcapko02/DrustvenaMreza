package rs.ac.uns.ftn.svtvezbe07.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Group;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Post;
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

    public List<Post> getAll() {
        return objavaRepository.findAll();
    }
    public Post getPost(Long id) {
        return objavaRepository.findById(id).get();
    }
    @Transactional
    public Post save(Post post) {
        return objavaRepository.save(post);
    }


    @Transactional
    public void delete(Long id) {
        objavaRepository.deleteById(id);
    }



    public List<Post> getGroupPosts(Long id) {
        Group group = grupaRepository.findById(id).get();
        return group.getPosts();
    }

}

