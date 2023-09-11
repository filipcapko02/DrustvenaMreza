package rs.ac.uns.ftn.svtvezbe07.controller;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.svtvezbe07.model.entity.*;
import rs.ac.uns.ftn.svtvezbe07.service.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/post")
@AllArgsConstructor
public class ObjavaController {

    private final ObjavaService service;

    private final KomentarService commentService;

    private final LikeService likeService;

    private final DislikeService dislikeService;

    private final GrupaService groupService;

    private final HeartService heartService;
    @PostMapping("/new")
    public ResponseEntity<Post> create(@RequestBody Post newPost) {
        Post addedPost = service.save(newPost);
        return new ResponseEntity<>(addedPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id) {
        Post post = service.getPost(id);
        Group group = groupService.getGroupByPost(post);
        if (group != null) {
            group.getPosts().remove(post);
            groupService.save(group);
        }

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<Post> updatePost(@RequestBody Post updatedPost) {
        Post newPost = service.save(updatedPost);
        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }


    @GetMapping("/allposts")
    public ResponseEntity<List<Post>> allposts(){
        List<Post> posts = service.getAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @PutMapping("/comment/{id}")
    public ResponseEntity<Comment> comment(@PathVariable("id") Long id, @RequestBody Comment comment) {
        Post post = service.getPost(id);
        comment.setPost(post);
        post.getComments().add(comment);
        service.save(post);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PutMapping("/like/{id}/{userId}")
    public ResponseEntity<Like> like(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        Post post = service.getPost(id);

        Like like = new Like();
        like.setPost(post);
        like.setUserId(userId);
        post.getLikes().add(like);
        service.save(post);



        return new ResponseEntity<>(like, HttpStatus.CREATED);
    }

    @DeleteMapping("/unlike/{id}/{userId}")
    public ResponseEntity<?> unlike(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        Post post = service.getPost(id);
        List<Like> likes = post.getLikes();
        Like likeToRemove = new Like();

        for (Like like : likes) {
            if (like.getUserId().equals(userId)) {
                likeToRemove = like;
                break;
            }
        }
        likes.remove(likeToRemove);
        likeService.delete(likeToRemove);
        service.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/dislike/{id}/{userId}")
    public ResponseEntity<Dislike> dislike(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        Post post = service.getPost(id);

        Dislike dislike = new Dislike();
        dislike.setPost(post);
        dislike.setUserId(userId);
        post.getDislikes().add(dislike);
        service.save(post);



        return new ResponseEntity<>(dislike, HttpStatus.CREATED);
    }

    @DeleteMapping("/undislike/{id}/{userId}")
    public ResponseEntity<?> undislike(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        Post post = service.getPost(id);
        List<Dislike> dislikes = post.getDislikes();
        Dislike dislikeToRemove = new Dislike();

        for (Dislike dislike : dislikes) {
            if (dislike.getUserId().equals(userId)) {
                dislikeToRemove = dislike;
                break;
            }
        }
        dislikes.remove(dislikeToRemove);
        dislikeService.delete(dislikeToRemove);
        service.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/heart/{id}/{userId}")
    public ResponseEntity<Heart> heart(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        Post post = service.getPost(id);

        Heart heart = new Heart();
        heart.setPost(post);
        heart.setUserId(userId);
        post.getHearts().add(heart);
        service.save(post);



        return new ResponseEntity<>(heart, HttpStatus.CREATED);
    }

    @DeleteMapping("/unheart/{id}/{userId}")
    public ResponseEntity<?> unheart(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        Post post = service.getPost(id);
        List<Heart> hearts = post.getHearts();
        Heart heartToRemove = new Heart();

        for (Heart heart : hearts) {
            if (heart.getUserId().equals(userId)) {
                heartToRemove = heart;
                break;
            }
        }
        hearts.remove(heartToRemove);
        heartService.delete(heartToRemove);
        service.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

