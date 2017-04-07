package com.example.coledeman.homebrew.objects.Enums;

/**
 * Created by Cole DeMan on 4/7/2017.
 */

public enum Unit {
    GRAM(1,1,false,"grams"),
    KILOGRAM(2,1000,false,"kilograms"),
    OUNCE(3,28.3495,false,"ounces"),
    POUND(4, 453.592,false,"pounds"),
    MILLILITRES(5,1,true,"millilitres"),
    CUPS(6,236.588,true,"cups"),
    LITRES(7,1000,true,"litres");

    private final double q;   // in mililitres
    private final int id;
    private final boolean liquid;
    private final String unit;

    Unit(int id, double grams, boolean liquid, String unit) {
        this.q = grams;
        this.id = id;
        this.liquid = liquid;this.unit = unit;
    }

    public static Unit getUnitByInt(int id){
        switch (id) {
            case 1:
                return GRAM;
            case 2:
                return KILOGRAM;
            case 3:
                return OUNCE;
            case 4:
                return POUND;
            case 5:
                return MILLILITRES;
            case 6:
                return CUPS;
            case 7:
                return LITRES;
            default:
                return null;
        }
    }

    public int getId(){
        return id;
    }

    public double getGramsorMl(int quantity){
        return q *quantity;
    }
}
