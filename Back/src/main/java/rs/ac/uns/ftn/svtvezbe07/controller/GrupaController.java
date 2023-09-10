package rs.ac.uns.ftn.svtvezbe07.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Group;
import rs.ac.uns.ftn.svtvezbe07.service.GrupaService;
import rs.ac.uns.ftn.svtvezbe07.service.KorisnikService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/group")
@AllArgsConstructor
public class GrupaController {

    @Autowired
    GrupaService service;

    @Autowired
    KorisnikService userService;

    @Autowired
    GrupaService groupService;

    @PostMapping("/new")
    public ResponseEntity<Group> create(@RequestBody Group group) {
        Group objava = groupService.save(group);
        return new ResponseEntity<>(objava, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Group>> allGroups() {
        return new ResponseEntity<>(groupService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/page/{id}")
    public ResponseEntity<Group> groupById(@PathVariable("id") Long id) {
        Group objava = groupService.getGroup(id);
        return new ResponseEntity<>(objava, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>("Deleted", HttpStatus.GONE);
    }
}
