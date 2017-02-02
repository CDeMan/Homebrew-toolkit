package com.example.coledeman.homebrew.objects;

import java.util.Date;

/**
 * Created by Cole DeMan on 1/24/2017.
 */

public class Brew {

    Date d;
    Double i;
    Double f;
    String name;

    public Brew(String name){
        d = new Date();
    }

    public void setD(Date d) {
        this.d = d;
    }

    public void setI(Double i) {
        this.i = i;
    }

    public void setF(Double f) {
        this.f = f;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getD() {

        return d;
    }

    public Double getI() {
        return i;
    }

    public Double getF() {
        return f;
    }

    public String getName() {
        return name;
    }
}
