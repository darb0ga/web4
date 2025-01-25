package com.example.web4.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

public class
ResultDto {
    private Long id;

    private Float x;
    @Getter
    @Setter
    private Float y;
    @Getter
    @Setter
    private Float r;
    @Getter
    @Setter
     private Long owner;
    @Getter
    @Setter
     private boolean res;

    public ResultDto() {}

    public ResultDto(Float x, Float y, Float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public void setX(Float x){
        this.x = x;
    }

    public void setY(Float x){
        this.y = x;
    }
    public void setR(Float r){
        this.r = r;
    }

    public Float getX(){
        return this.x;
    }
    public Float getY(){
        return this.y;
    }
    public Float getR(){
        return this.r;
    }

}
