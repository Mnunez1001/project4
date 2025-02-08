package com.example;

public class Plant extends Creature {

    // Constructor
    public Plant(double size, double growthRate) {
        super(size, growthRate);
    }

    // Method to reduce size when chewed on
    public void chewedOn(double amount) {
        if (amount > getSize()) {
            amount = getSize();
        }
        changeSize(-amount);
    }

    // toString method
    @Override
    public String toString() {
        return "Plant [size=" + getSize() + ", growthRate=" + getGrowthRate() + ", alive=" + isAlive() + ", age="
                + getAge() + "]";
    }

}
