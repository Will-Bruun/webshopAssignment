package com.spring.demo.models;

public class Product {

    private String ID;
    private String Name;
    private Integer Price;
    private Integer Stock;

    public String getID(){
        return ID;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public String getName(){
        return Name;
    }

    public void setName(String name){
        this.Name = name;
    }

    public Integer getPrice(){
        return Price;
    }

    public void setPrice(Integer price) {
        this.Price = price;
    }

    public Integer getStock(){
        return Stock;
    }

    public void setStock(Integer stock){
        this.Stock = stock;
    }
}
