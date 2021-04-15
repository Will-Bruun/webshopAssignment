package com.spring.demo.services;

import com.spring.demo.models.Product;
import com.spring.demo.models.Tag;

public class TaggingService {

    public static Product appendTag(Tag tag, Product prod){
        var tags = prod.getTags();
        tags.add(tag);
        prod.setTags(tags);
        return prod;
    }

    public static Product removeTag(Tag tag, Product prod){
        var tags = prod.getTags();
        tags.remove(tag);
        prod.setTags(tags);
        return prod;
    }
}
