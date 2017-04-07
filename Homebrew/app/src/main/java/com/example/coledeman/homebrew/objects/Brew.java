package com.example.coledeman.homebrew.objects;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Cole DeMan on 1/24/2017.
 */

public class Brew {

    long id;
    Date date;
    Double initialGravity;
    Double finalGravity;
    String name;
    String description;

    ArrayList<GravityMeasurement> gravityMeasurements;
    ArrayList<BrewIngredient> ingredients;



    public Brew(String name){
        date = new Date();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setInitialGravity(Double initialGravity) {
        this.initialGravity = initialGravity;
    }

    public void setFinalGravity(Double finalGravity) {
        this.finalGravity = finalGravity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {

        return date;
    }

    public Double getInitialGravity() {
        return initialGravity;
    }

    public Double getFinalGravity() {
        return finalGravity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<GravityMeasurement> getGravityMeasurements() {
        return gravityMeasurements;
    }

    public ArrayList<BrewIngredient> getIngredients() {
        return ingredients;
    }
}
