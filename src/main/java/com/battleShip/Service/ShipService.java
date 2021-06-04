package com.battleShip.Service;
import com.battleShip.Model.Entities.Ship;
import com.battleShip.Repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipService {

    private ShipRepository shipRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

        public Ship getShipById (int id) {

            return shipRepository.getShipById(id);


        }
    }
