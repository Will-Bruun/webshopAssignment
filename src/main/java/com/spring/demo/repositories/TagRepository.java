package com.spring.demo.repositories;

import com.spring.demo.models.Tags;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TagRepository extends CrudRepository<Tags, String> {

    @Query(value = "select * from Tag tag where tag.name like %?1")
    Optional<Tags> getTagByName(String tagName);
}
