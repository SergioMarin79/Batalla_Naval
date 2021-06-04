package com.battleShip.Model.Entities;
import javax.persistence.*;

@Entity
public class Board {
    private int id;
    private UserLogin player;
    private Game id_game;
    private Ship id_ship;
    private int x_coordinate;
    private int y_coordinate;
    private Integer nextElement;
    private Integer previousElement;
    private boolean damage;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "player")
    public UserLogin getPlayer() {
        return player;
    }

    public void setPlayer(UserLogin player) {
        this.player = player;
    }


    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_game")
    public Game getId_game() {
        return id_game;
    }
    public void setId_game(Game id_game) {
        this.id_game = id_game;
    }



    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ship")
    public Ship getId_ship() {
        return id_ship;
    }

    public void setId_ship(Ship id_ship) {
        this.id_ship = id_ship;
    }

    @Basic
    @Column(name = "x_coordinate", nullable = false)
    public int getX_coordinate() {
        return x_coordinate;
    }
    public void setX_coordinate(int x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    @Basic
    @Column(name = "y_coordinate", nullable = false)

    public int getY_coordinate() {
        return y_coordinate;
    }

    public void setY_coordinate(int y_coordinate) {
        this.y_coordinate = y_coordinate;
    }

    @Basic
    @Column(name = "next_element")
    public Integer getNextElement() {
        return nextElement;
    }
    public void setNextElement(Integer nextElement) {
        this.nextElement = nextElement;
    }



    @Basic
    @Column(name = "previous_element")
    public Integer getPreviousElement() {
        return previousElement;
    }

    public void setPreviousElement(Integer previousElement) {
        this.previousElement = previousElement;
    }



    @Basic
    @Column(name = "damage")
    public boolean isDamage() {
        return damage;
    }

    public void setDamage(boolean damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", player=" + player +
                ", id_game=" + id_game +
                ", id_ship=" + id_ship +
                ", x_coordinate=" + x_coordinate +
                ", y_coordinate=" + y_coordinate +
                ", nextElement=" + nextElement +
                ", previousElement=" + previousElement +
                ", damage=" + damage +
                '}';
    }
}
