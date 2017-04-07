package com.example.coledeman.homebrew.objects;

import com.example.coledeman.homebrew.objects.Enums.Unit;

/**
 * Created by Cole DeMan on 4/7/2017.
 */

public class BrewIngredient {

    int id;
    int brewID;
    String name;
    Unit unit;
    int quantity;

    public BrewIngredient(int id, int brewID, String name, Unit unit, int quantity) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrewID() {
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
