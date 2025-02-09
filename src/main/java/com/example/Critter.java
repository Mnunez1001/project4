package com.example;

public abstract class Critter extends Creature {
    // Attributes
    private double foodNeed;
    private double foodEaten;

    // Constructor
    public Critter(double size, double growthRate, double foodNeed) {
        super(size, growthRate);
        this.foodNeed = foodNeed;
        this.foodEaten = 0;
    }

    // Getters and Setters
    public double getFoodNeed() {
        return Math.round(foodNeed * 100.0) / 100.0;
    }

    public void setFoodNeed(double foodNeed) {
        this.foodNeed = foodNeed;
    }

    // Override simulateDay
    @Override
    public void simulateDay() {
        if (isAlive()) {
            if (foodEaten < foodNeed) {
                die();
            } else {
                foodEaten = 0;
                super.simulateDay();
            }
        }
    }

    // Method to eat food
    public void eat(double amount) {
        foodEaten += amount;
    }

    // Method to check food still needed
    public double stillNeed() {
        return Math.round((foodNeed - foodEaten) * 100.0) / 100.0;
    }


}
