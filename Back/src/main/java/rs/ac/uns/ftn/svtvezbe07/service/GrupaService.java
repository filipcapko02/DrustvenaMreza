package rs.ac.uns.ftn.svtvezbe07.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Group;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Post;
import rs.ac.uns.ftn.svtvezbe07.repository.GrupaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class GrupaService {
    public final GrupaRepository repository;


    public List<Group> getAll() {
        return repository.findAll();
    }
    public Group getGroup(Long id) {
        return repository.findById(id).get();
    }
    @Transactional
    public Group save(Group group) {
        return repository.save(group);
    }
    public Group getGroupByPost(Post post) {
        List<Group> groups = getAll();
        for (Group group : groups) {
            List<Post> posts = group.getPosts();
            if (posts.contains(post)) {
                return group;
            }
        }
        return null;
    }


    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
