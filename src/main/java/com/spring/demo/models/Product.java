package com.spring.demo.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "products")
public class Product {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid2")
    @Column(length = 64)
    private String id;

    private String Name;
    private Double Price;
    private Integer Stock;
    @ManyToOne
    private Manufacturer manufacturer;
    @ManyToMany()
    private List<Tag> tags;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return Name;
    }

    public void setName(String name){
        this.Name = name;
    }

    public void setTags(List tags) {
        this.tags = tags;
    }

    public List getTags() {
        return tags;
    }

    public void appendTag(Tag tag){
        this.tags.add(tag);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Double getPrice(){
        return Price;
    }

    public void setPrice(Double price) {
        this.Price = price;
    }

    public Integer getStock(){
        return Stock;
    }

    public void setStock(Integer stock){
        this.Stock = stock;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
