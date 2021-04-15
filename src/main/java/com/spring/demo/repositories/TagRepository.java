package com.spring.demo.repositories;

import com.spring.demo.models.Product;
import com.spring.demo.models.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends CrudRepository<Tag, String> {

    @Query(value = "SELECT tag from Tags tag where tag.tag like %?1")
    Optional<Tag> getTagByName(String tagName);

    @Query(value = "SELECT tag from Tags tag where tag.products like %?")
    List<Product> getProductsByTag(String tagName);
}
