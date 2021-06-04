package com.battleShip.Model.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserLogin implements Serializable {
        private int id;
        private String email;
        private String password;
        private UserType userType;

        @Id
        @Column(name = "id", nullable = false)
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }



        @Basic
        @Column(name = "email", nullable = false, length = 30)
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Basic
        @Column(name = "password", nullable = false)
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_type_id")
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

        @Override
        public String toString() {
            return "user{" +
                    "id=" + id +
                    ", email='" + email + '\'' +
                    ", password=" + password + ", userType=" + userType +
                    '}';
        }
    }
