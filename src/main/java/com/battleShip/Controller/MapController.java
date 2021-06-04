package com.battleShip.Controller;

import com.battleShip.Service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "map")
public class MapController {

    private MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<Object> findAll() {
        return ResponseEntity.ok().body(mapService.getAllMaps());
    }

    @GetMapping(path = "{id}")
    public @ResponseBody
    ResponseEntity<Object> getMapById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(mapService.getMapById(id));
    }
}
