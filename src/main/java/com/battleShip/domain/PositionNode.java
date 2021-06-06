package com.battleShip.domain;

public class PositionNode {

    private ShipPosition ship;
    private PositionNode next;
    private PositionNode previous;


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

    public PositionNode getNext() {
        return next;
    }

    public void setNext(PositionNode next) {
        this.next = next;
    }

    public PositionNode getPrevious() {
        return previous;
    }

    public void setPrevious(PositionNode previous) {
        this.previous = previous;
    }

    public String printPositionOfShip(String positions, PositionNode positionNode) {
        PositionNode temp = positionNode;
        if(temp.isTail()) {
            return positions + temp.getShip().toString()  + " It is tail \n";
        } else {
           positions = positions + temp.getShip().toString() + printPositionOfShip(positions, temp.getNext());
        }
        return positions;
    }

    public void setShipsInBoard(Integer[][] positions, PositionNode positionNode) {
        PositionNode temp = positionNode;

        if(temp.getShip().getDamage()) {
            positions[positionNode.getShip().getCorX()][positionNode.getShip().getCorY()] = 2;
        } else {
            positions[positionNode.getShip().getCorX()][positionNode.getShip().getCorY()] = 1;
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
