package com.battleShip.Model.Entities;


import javax.persistence.*;

@Entity
public class Shoot {
    private int id;
    private Game idGame;
    private UserLogin player;
    private int x;
    private int y;
    private boolean SuccessfulShoot;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_game")
    public Game getIdGame() {
        return idGame;
    }

    public void setIdGame(Game idGame) {
        this.idGame = idGame;
    }

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_player")

    public UserLogin getPlayer() {
        return player;
    }

    public void setPlayer(UserLogin player) {
        this.player = player;
    }

    @Basic
    @Column(name = "x")

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }



    @Basic
    @Column(name = "y")
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Basic
    @Column(name = "successful_shot")

    public boolean isSuccessfulShoot() {
        return SuccessfulShoot;
    }

    public void setSuccessfulShoot(boolean successfulShoot) {
        SuccessfulShoot = successfulShoot;
    }
}

