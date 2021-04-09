package com.spring.demo.controllers;

import com.spring.demo.exceptions.EmployeeNotFoundException;
import com.spring.demo.exceptions.TagNotFoundException;
import com.spring.demo.models.Employees;
import com.spring.demo.models.Tags;
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
    public Tags getTag(@RequestParam String id){
        var userOpt = repo.findById(id);
        return userOpt.orElseThrow(() -> new TagNotFoundException(id));
    }

    @GetMapping("/index")
    public Iterable<Tags> getAllTags(){
        var userOpt = repo.findAll();
        return userOpt;
    }

    @PostMapping("/post")
    public Tags createTags(@RequestBody Tags tag){
        repo.save(tag);
        return tag;
    }

    @PutMapping("/edit")
    public Tags editEmployee(@RequestBody Tags tag){
        Optional<Tags> tagInDb = repo.findById(tag.getId());
        if (tagInDb.isPresent()){
            repo.save(tag);
            return tag;
        } else {
            throw new EmployeeNotFoundException(tag.getId());
        }
    }

    @DeleteMapping("/delete")
    public Tags deleteTag(@RequestBody Tags tag){
        repo.deleteById(tag.getId());
        return tag;
    }

}
