package com.battleShip.domain;

import java.io.Serializable;

public class ListDE implements Serializable {

    private ShipNode head;

    public void addNode(ShipNode node) {
        if (head == null) {
            head = node;
        } else {
            ShipNode temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }

            temp.setNext(node);
            temp.getNext().setPrevious(temp);
        }
    }

    public ShipNode getHead() {
        return head;
    }

    public int countShipHead(ShipNode shipNode) {
        if (shipNode.getNext() == null) {
            return 1;
        } else {
            return 1 + countShipHead(shipNode.getNext());
        }
    }

    public String printPositionOfShip(String positions, ShipNode shipNode) {
        if (shipNode.getNext() == null) {
            return positions + shipNode.getPositionNode().printPositionOfShip(positions, shipNode.getPositionNode());
        } else {
            return positions + shipNode.getPositionNode().printPositionOfShip(positions, shipNode.getPositionNode()) + printPositionOfShip(positions, shipNode.getNext());
        }
    }

    public void setShipsInBoard(Integer[][] positions, ShipNode shipNode) {
        if (shipNode.getNext() == null){
             shipNode.getPositionNode().setShipsInBoard(positions, shipNode.getPositionNode());
        }else{
               shipNode.getPositionNode().setShipsInBoard(positions, shipNode.getPositionNode()) ;
               setShipsInBoard(positions, shipNode.getNext());
        }
    }
}