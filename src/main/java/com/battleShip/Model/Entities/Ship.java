package com.battleShip.Model.Entities;

import javax.persistence.*;

@Entity
public class Ship {

    private int id;
    private String name;
    private int numberBox;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "number_box", nullable = false)
    public int getNumberBox() {
        return numberBox;
    }

    public void setNumberBox(int numberBox) {
        this.numberBox = numberBox;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberBox=" + numberBox +
                '}';
    }
}
