package com.example.coledeman.homebrew.objects;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Cole DeMan on 1/24/2017.
 */

public class Brew {

    private long id;
    private Date date;
    private Double initialGravity;
    private Double finalGravity;
    private String name;
    private String description;

    private ArrayList<GravityMeasurement> gravityMeasurements;
    private ArrayList<BrewIngredient> ingredients;

    public Brew(long id, Date date, Double initialGravity, Double finalGravity, String name, String description, ArrayList<GravityMeasurement> gravityMeasurements, ArrayList<BrewIngredient> ingredients) {
        this.id = id;
        this.date = date;
        this.initialGravity = initialGravity;
        this.finalGravity = finalGravity;
        this.name = name;
        this.description = description;
        this.gravityMeasurements = gravityMeasurements;
        this.ingredients = ingredients;
    }

    public Brew(Date date, String name, String description) {
        this.date = date;
        this.name = name;
        this.description = description;
        this.initialGravity = -1.0;
        this.finalGravity = -1.0;

        this.gravityMeasurements = new ArrayList<GravityMeasurement>();
        this.ingredients = new ArrayList<BrewIngredient>();
    }

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

    @Override
    public String toString() {
        return "Brew{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", date=" + date +
                ", initialGravity=" + initialGravity +
                ", finalGravity=" + finalGravity +
                ", name='" + name + '\'' +
                '}';
    }
}
