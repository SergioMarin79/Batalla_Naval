package com.battleShip.Repository;
import com.battleShip.Model.Entities.Ship;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ShipRepository  extends CrudRepository<Ship,Integer> {

    @Query("SELECT ship FROM Ship ship WHERE ship.id=?1")
    Ship getShipById(int id);
}