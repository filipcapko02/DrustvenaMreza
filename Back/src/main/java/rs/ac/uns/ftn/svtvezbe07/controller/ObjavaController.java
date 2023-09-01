package rs.ac.uns.ftn.svtvezbe07.controller;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Grupa;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Komentar;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Like;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Objava;
import rs.ac.uns.ftn.svtvezbe07.service.GrupaService;
import rs.ac.uns.ftn.svtvezbe07.service.KomentarService;
import rs.ac.uns.ftn.svtvezbe07.service.LikeService;
import rs.ac.uns.ftn.svtvezbe07.service.ObjavaService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/objava")
@AllArgsConstructor
public class ObjavaController {

    private final ObjavaService service;

    private final KomentarService commentService;

    private final LikeService likeService;

    private final GrupaService groupService;
    @PostMapping("/new")
    public ResponseEntity<Objava> create(@RequestBody Objava newPost) {
        Objava addedPost = service.save(newPost);
        return new ResponseEntity<>(addedPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id) {
        Objava objava = service.getObjava(id);
        Grupa grupa = groupService.getGroupByPost(objava);
        if (grupa != null) {
            grupa.getObjave().remove(objava);
            groupService.save(grupa);
        }

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<Objava> updatePost(@RequestBody Objava updatedPost) {
        Objava newPost = service.save(updatedPost);
        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }


    @GetMapping("/allposts")
    public ResponseEntity<List<Objava>> allposts(){
        List<Objava> posts = service.getAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @PutMapping("/comment/{id}")
    public ResponseEntity<Komentar> komentar(@PathVariable("id") Long id, @RequestBody Komentar komentar) {
        Objava objava = service.getObjava(id);
        komentar.setObjava(objava);
        objava.getComments().add(komentar);
        service.save(objava);

        return new ResponseEntity<>(komentar, HttpStatus.CREATED);
    }

    @PutMapping("/like/{id}/{userId}")
    public ResponseEntity<Like> like(@PathVariable("id") Long id, @PathVariable("korisnik_id") Long korisnik_id) {
        Objava objava = service.getObjava(id);

        Like like = new Like();
        like.setObjava(objava);
        like.setKorisnik_id(korisnik_id);
        objava.getLikes().add(like);
        service.save(objava);



        return new ResponseEntity<>(like, HttpStatus.CREATED);
    }

    @DeleteMapping("/unlike/{id}/{userId}")
    public ResponseEntity<?> unlike(@PathVariable("id") Long id, @PathVariable("korisnik_id") Long korisnik_id) {
        Objava objava = service.getObjava(id);
        List<Like> likes = objava.getLikes();
        Like likeToRemove = new Like();

        for (Like like : likes) {
            if (like.getKorisnik_id().equals(korisnik_id)) {
                likeToRemove = like;
                break;
            }
        }
        likes.remove(likeToRemove);
        likeService.delete(likeToRemove);
        service.save(objava);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

