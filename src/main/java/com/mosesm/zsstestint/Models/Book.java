package com.mosesm.zsstestint.Models;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String title;
    @Column
    private String descript;
    @Column
    private double price;
    @Column
    private long category;

    public long getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getDescript(){
        return this.descript;
    }
    public double getPrice(){
        return this.price;
    }
    public long getCategory(){
        return this.category;
    }

    public void setId(long id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setDescript (String descript){
        this.descript = descript;

    }

    public void setPrice(double price){
        this.price = price;
    }
    public void setCategory(long category){
        this.category = category;
    }
}
