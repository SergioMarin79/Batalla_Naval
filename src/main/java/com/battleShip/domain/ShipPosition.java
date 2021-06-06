package com.battleShip.domain;

public class ShipPosition {

    private int corX;
    private int corY;
    private Boolean isDamage;

    public int getCorX() {
        return corX;
    }

    public void setCorX(int corX) {
        this.corX = corX;
    }

    public int getCorY() {
        return corY;
    }

    public void setCorY(int corY) {
        this.corY = corY;
    }

    public Boolean getDamage() {
        return isDamage;
    }

    public void setDamage(Boolean damage) {
        isDamage = damage;
    }

    @Override
    public String toString() {
        return "ShipPosition{" +
                "corX=" + corX +
                ", corY=" + corY +
                '}';
    }
}
