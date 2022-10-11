package com.coffeemaker.coffeemaker.enums;

public enum DrinkTypes {

    TEA("T", "tea",0.4f),CHOCOLATE("H", "chocolate",0.5f),COFFEE("C","coffee",0.6f),ORANGE("O","orange",0.6f);

    String code;

    String description;

    float price;

    DrinkTypes(String code, String description, float price) {
        this.code = code;
        this.description = description;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
