package com.battleShip.Model.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Map {
    private int id;
    private String name;
    private int width;
    private int height;

    private List<Ship> ships;

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
    @Column(name = "width", nullable = false)
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Basic
    @Column(name = "height", nullable = false)
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity =   Ship.class)
    @JoinTable(name = "map_ship", joinColumns = { @JoinColumn(name = "id_map") }, inverseJoinColumns = { @JoinColumn(name = "id_ship") })
    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    @Override
    public String toString() {
        return "map{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", width=" + width + ", width=" + height +
                '}';
    }
}
