package com.battleShip.domain;

public class ShipNode {

    private PositionNode positionNode;
    private ShipNode next;
    private ShipNode previous;

    public PositionNode getPositionNode() {
        return positionNode;
    }

    public void setPositionNode(PositionNode shipPosition) {
        this.positionNode = shipPosition;
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
}
