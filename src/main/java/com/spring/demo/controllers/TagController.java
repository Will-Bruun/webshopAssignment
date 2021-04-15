package com.spring.demo.controllers;

import com.spring.demo.exceptions.EmployeeNotFoundException;
import com.spring.demo.exceptions.TagNotFoundException;
import com.spring.demo.models.Tag;
import com.spring.demo.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagRepository repo;

    @Autowired
    public TagController(TagRepository repo){
        this.repo = repo;
    }

    @GetMapping("/get")
    public Tag getTag(@RequestParam String id){
        var userOpt = repo.findById(id);
        return userOpt.orElseThrow(() -> new TagNotFoundException(id));
    }

    @GetMapping("/index")
    public Iterable<Tag> getAllTags(){
        var userOpt = repo.findAll();
        return userOpt;
    }

    @PostMapping("/create")
    public Tag createTags(@RequestBody Tag tag){
        repo.save(tag);
        return tag;
    }

    @PutMapping("/edit")
    public Tag editEmployee(@RequestBody Tag tag){
        Optional<Tag> tagInDb = repo.findById(tag.getId());
        if (tagInDb.isPresent()){
            repo.save(tag);
            return tag;
        } else {
            throw new EmployeeNotFoundException(tag.getId());
        }
    }

    @DeleteMapping("/delete")
    public Tag deleteTag(@RequestBody Tag tag){
        repo.deleteById(tag.getId());
        return tag;
    }

}
