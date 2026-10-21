package com.example.demo.entity;
import jakarta.persistence.Embeddable;


@Embeddable
public record CartItem(Integer id,String name,int quantity,double price) {

}
