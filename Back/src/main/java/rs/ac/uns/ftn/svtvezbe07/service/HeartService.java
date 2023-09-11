package rs.ac.uns.ftn.svtvezbe07.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Heart;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Like;
import rs.ac.uns.ftn.svtvezbe07.repository.HeartRepository;
import rs.ac.uns.ftn.svtvezbe07.repository.LikeRepository;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;

    @Transactional
    public Heart save(Heart heart) {
        return heartRepository.save(heart);
    }

    @Transactional
    public void delete(Heart heart){heartRepository.delete(heart);}

}
