package rs.ac.uns.ftn.svtvezbe07.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Dislike;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Like;
import rs.ac.uns.ftn.svtvezbe07.repository.DislikeRepository;
import rs.ac.uns.ftn.svtvezbe07.repository.LikeRepository;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class DislikeService
{
    private final DislikeRepository dislikeRepository;

    @Transactional
    public Dislike save(Dislike dislike) {
        return dislikeRepository.save(dislike);
    }

    @Transactional
    public void delete(Dislike dislike){dislikeRepository.delete(dislike);}

}