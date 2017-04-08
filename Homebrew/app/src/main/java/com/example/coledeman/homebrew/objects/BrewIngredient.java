package com.example.coledeman.homebrew.objects;

import com.example.coledeman.homebrew.objects.Enums.Unit;

/**
 * Created by Cole DeMan on 4/7/2017.
 */

public class BrewIngredient {

    private long id;
    private long brewID;
    private String name;
    private Unit unit;
    private int quantity;

    public BrewIngredient(long id, long brewID, String name, Unit unit, int quantity) {
        this.id = id;
        this.brewID = brewID;
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
    }

    public BrewIngredient(int brewID, String name, Unit unit, int quantity) {
        this.brewID = brewID;
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBrewID() {
        return brewID;
    }

    public void setBrewID(int brewID) {
        this.brewID = brewID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
