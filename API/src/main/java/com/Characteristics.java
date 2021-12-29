package com;

public class Characteristics {
    //defining Characteristics attributes
    private String name;
    private float defaultValue;
    private float defaultMax;
    private float defaultMin;
    //Characteristics default and parameterized Constructors
    public Characteristics() {

    }
    public Characteristics(String name, float defaultValue, float defaultMax, float defaultMin) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.defaultMax = defaultMax;
        this.defaultMin = defaultMin;
    }
    //setter and getter for Characteristics attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(float defaultValue) {
        this.defaultValue = defaultValue;
    }

    public float getDefaultMax() {
        return defaultMax;
    }

    public void setDefaultMax(float defaultMax) {
        this.defaultMax = defaultMax;
    }

    public float getDefaultMin() {
        return defaultMin;
    }

    public void setDefaultMin(float defaultMin) {
        this.defaultMin = defaultMin;
    }
}
