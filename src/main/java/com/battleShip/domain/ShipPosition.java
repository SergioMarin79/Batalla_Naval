package com.battleShip.domain;

public class ShipPosition {

    private int corX;
    private int corY;
    private ShipPosition nextElement;
    private ShipPosition previousElement;

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

    public ShipPosition getNextElement() {
        return nextElement;
    }

    public void setNextElement(ShipPosition nextElement) {
        this.nextElement = nextElement;
    }

    public ShipPosition getPreviousElement() {
        return previousElement;
    }

    public void setPreviousElement(ShipPosition previousElement) {
        this.previousElement = previousElement;
    }

    @Override
    public String toString() {
        return "ShipPosition{" +
                "corX=" + corX +
                ", corY=" + corY +
                ", nextElement=" + nextElement +
                ", previousElement=" + previousElement +
                '}';
    }

    public boolean isHead () {
        boolean head;
        if(previousElement == null){
            head = true;
        }else{
            head = false;
        }

        return head;
    }

    public  boolean isTail(){
        boolean tail;
        if(nextElement == null){
            tail = true;
        }else{
            tail = false;
        }

        return tail;
    }
}
