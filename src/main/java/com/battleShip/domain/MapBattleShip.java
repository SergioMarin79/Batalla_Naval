package com.battleShip.domain;

import com.battleShip.Model.Entities.Ship;

import java.util.Map;

public class MapBattleShip {

    private int width;
    private int height;
    private String name;
    private Map<Ship, Long> ships;

    public MapBattleShip() {

    }
    public MapBattleShip(int width, int height, String name,  Map<Ship, Long> ships) {
        this.width = width;
        this.height = height;
        this.name = name;
        this.ships = ships;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Ship, Long> getShips() {
        return ships;
    }

    public void setShips(Map<Ship, Long> ships) {
        this.ships = ships;
    }
}
