package com.battleShip.domain;

public class ShipNode {

    private ShipPosition ship;
    private ShipNode next;
    private ShipNode previous;


    public boolean isHead () {
        boolean head;
        if(previous == null){
            head = true;
        }else{
            head = false;
        }

        return head;
    }

    public  boolean isTail(){
        boolean tail;
        if(next == null){
            tail = true;
        }else{
            tail = false;
        }
        return tail;
    }

    public ShipPosition getShip() {
        return ship;
    }

    public void setShip(ShipPosition ship) {
        this.ship = ship;
    }

    public ShipNode getNext() {
        return next;
    }

    public void setNext(ShipNode next) {
        this.next = next;
    }

    public ShipNode getPrevious() {
        return previous;
    }

    public void setPrevious(ShipNode previous) {
        this.previous = previous;
    }

    public String printPositionOfShip(String positions, ShipNode shipNode) {
        ShipNode temp = shipNode;
        if(temp.isTail()) {
            return positions + temp.getShip().toString()  + " It is tail \n";
        } else {
           positions = positions + temp.getShip().toString() + printPositionOfShip(positions, temp.getNext());
        }
        return positions;
    }

    public void setShipsInBoard(Integer[][] positions, ShipNode shipNode) {
        ShipNode temp = shipNode;

        if(temp.getShip().getDamage()) {
            positions[shipNode.getShip().getCorX()][shipNode.getShip().getCorY()] = 2;
        } else {
            positions[shipNode.getShip().getCorX()][shipNode.getShip().getCorY()] = 1;
        }

        if(!temp.isTail()) {
           setShipsInBoard(positions, temp.getNext());
        }


    }

    @Override
    public String toString() {
        return "ShipNode{" +
                "ship=" + ship +
                '}';
    }
}
