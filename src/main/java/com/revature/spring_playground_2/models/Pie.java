package com.revature.spring_playground_2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Pie {
    @Id
    @Column(name = "pie_name")
    private String name;
    private int calories;
    @Column(name = "slices_available")
    private int slicesAvailable;

    
    public Pie() {
    }

    public Pie(String name, int calories, int slicesAvailable) {
        this.name = name;
        this.calories = calories;
        this.slicesAvailable = slicesAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getSlicesAvailable() {
        return slicesAvailable;
    }

    public void setSlicesAvailable(int slicesAvailable) {
        this.slicesAvailable = slicesAvailable;
    }

    
}
