package com.battleShip.domain;

import java.io.Serializable;

    public class ShootShip implements Serializable {

        private int idGame;
        private int idPlayer;
        private int x;
        private int y;

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



        @Override
        public String toString() {
            return "Shoot{" +
                    "idGame=" + idGame +
                    ", idPlayer=" + idPlayer +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
