package com.battleShip.Model.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserType implements Serializable {
    private int id;
    private String description;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 30)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
