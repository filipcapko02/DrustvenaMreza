package rs.ac.uns.ftn.svtvezbe07.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Like;
import rs.ac.uns.ftn.svtvezbe07.repository.LikeRepository;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    @Transactional
    public Like save(Like like) {
        return likeRepository.save(like);
    }

    @Transactional
    public void delete(Like like){likeRepository.delete(like);}

}
