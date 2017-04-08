package com.example.coledeman.homebrew.objects;

import java.util.Date;

/**
 * Created by Cole DeMan on 4/7/2017.
 */

public class GravityMeasurement {

    long id;
    long brewId;
    Date date;
    double gravity;
    int temp;

    public GravityMeasurement(long id, long brewId, Date date, double gravity, int temp) {
        this.id = id;
        this.brewId = brewId;
        this.date = date;
        this.gravity = gravity;
        this.temp = temp;
    }

    public long getBrewId() {
        return brewId;
    }

    public void setBrewId(int brewId) {
        this.brewId = brewId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBrewId(long brewId) {
        this.brewId = brewId;
    }
}
