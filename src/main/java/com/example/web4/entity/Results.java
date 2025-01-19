package com.example.web4.entity;


import com.example.web4.utils.AreaCheck;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "results")
public class Results implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Float x;
    @Column(nullable = false)
    private Float y;

    @Column(name="r", nullable=false)
    private Float r;

    @Column(nullable = false)
    private Boolean result;
    @Column(nullable = false)
    private Long owner;


    public Results(){}

    public Results(Float x, Float y, Float r, User owner){
        this.x = x;
        this.y = y;
        this.r = r;
        AreaCheck.isHit(this);
        this.owner = owner.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float newX) {
        this.x = newX;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float newY) {
        this.y = newY;
    }

    public Float getR() {
        return r;
    }

    public void setR(Float newR) {
        this.r = newR;
    }

    public void setResult(Boolean res){
        this.result = res;
    }

    public Boolean getResult(){
        return this.result;
    }

}
