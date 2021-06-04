package com.battleShip.Model.Entities;
import javax.persistence.*;
import java.io.Serializable;

@Entity
    public class Game implements Serializable {
        private int id;
        private int id_map;
        private UserLogin createdBy;
        private UserLogin player1;
        private UserLogin player2;


        @Id
        @Column(name = "id", nullable = false)
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Column(name = "id_map", nullable = false)
        public int getId_map() {
            return id_map;
        }

        public void setId_map(int id_map) {
            this.id_map = id_map;
        }


        @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
        @JoinColumn(name = "created_by")
        public UserLogin getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(UserLogin createdBy) {
            this.createdBy = createdBy;
        }

        @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
        @JoinColumn(name = "player1")
        public UserLogin getPlayer1() {
            return player1;
        }

        public void setPlayer1(UserLogin player1) {
            this.player1 = player1;
        }



        @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
        @JoinColumn(name = "player2")
        public UserLogin getPlayer2() {
            return player2;
        }

        public void setPlayer2(UserLogin player2) {
            this.player2 = player2;
        }

        @Override
        public String toString() {
            return "Game{" +
                    "id=" + id +
                    ", createdBy=" + createdBy +
                    ", player1=" + player1 +
                    ", player2=" + player2 +
                    '}';
        }
    }
