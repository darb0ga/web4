package com.example.web4.utils;

import com.example.web4.entity.Results;

import java.io.Serializable;

public class AreaCheck implements Serializable {

    public static void isHit(Results point) {
        var x = point.getX();
        var y = point.getY();
        var r = point.getR();
        point.setResult(((x >= 0 && y <= 0) && (x <= r && y >= -(r/2))) ||
                ( (x <= 0 && y >= 0) && (y <= x + r)) ||
                ((x <= 0 && y <= 0) && (x*x + y*y <= r*r / 4)));
    }

}
