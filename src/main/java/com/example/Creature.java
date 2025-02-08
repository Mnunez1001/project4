package com.example;

public abstract class Creature {

    // Attributes
    private double size;
    private double growthRate;
    private boolean alive;
    private int age;

    // Constructor
    public Creature(double size, double growthRate) {
        this.size = size;
        this.growthRate = growthRate;
        this.alive = true;
        this.age = 0;
    }

    // Getters
    public double getSize() {
        return size;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getAge() {
        return age;
    }

    // Setter for growthRate
    public void setGrowthRate(double growthRate) {
        this.growthRate = growthRate;
    }

    // Method to change the size
    public void changeSize(double amount) {
        this.size += amount;
        if (this.size <= 0) {
            die();
        }
    }

    // Method to simulate one day
    public void simulateDay() {
        if (alive) {
            changeSize(growthRate);
            age++;
        }
    }

    // Method to handle death
    public void die() {
        this.size = 0;
        this.growthRate = 0;
        this.alive = false;
    }

}
