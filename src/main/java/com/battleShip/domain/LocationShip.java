package com.battleShip.domain;

import java.io.Serializable;

public class LocationShip implements Serializable {

    private int idShip;
    private int x;
    private int y;
    private boolean horizontal;
    private int idGame;
    private int idPlayer;

    public int getIdShip() {
        return idShip;
    }

    public void setIdShip(int idShip) {
        this.idShip = idShip;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    @Override
    public String toString() {
        return "LocationShip{" +
                "idShip=" + idShip +
                ", x=" + x +
                ", y=" + y +
                ", horizontal=" + horizontal +
                ", idGame=" + idGame +
                ", idPlayer=" + idPlayer +
                '}';
    }
}
