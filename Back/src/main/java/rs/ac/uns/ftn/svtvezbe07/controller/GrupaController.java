package rs.ac.uns.ftn.svtvezbe07.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Grupa;
import rs.ac.uns.ftn.svtvezbe07.service.GrupaService;
import rs.ac.uns.ftn.svtvezbe07.service.KorisnikService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/grupa")
@AllArgsConstructor
public class GrupaController {

    @Autowired
    GrupaService service;

    @Autowired
    KorisnikService userService;

    @Autowired
    GrupaService groupService;

    @PostMapping("/nova")
    public ResponseEntity<Grupa> create(@RequestBody Grupa grupa) {
        Grupa objava = groupService.save(grupa);
        return new ResponseEntity<>(objava, HttpStatus.CREATED);
    }

    @GetMapping("/sve")
    public ResponseEntity<List<Grupa>> sveGrupe() {
        return new ResponseEntity<>(groupService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/page/{id}")
    public ResponseEntity<Grupa> grupaById(@PathVariable("id") Long id) {
        Grupa objava = groupService.getGroup(id);
        return new ResponseEntity<>(objava, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>("Deleted", HttpStatus.GONE);
    }
}
