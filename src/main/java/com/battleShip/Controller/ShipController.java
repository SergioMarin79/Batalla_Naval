package com.battleShip.Controller;

import com.battleShip.Service.ShipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "ship")
public class ShipController {

    private ShipService shipService;

    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping(path = "{id}")
    public @ResponseBody
    ResponseEntity<Object> getShipById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(shipService.getShipById(id));
    }
}
