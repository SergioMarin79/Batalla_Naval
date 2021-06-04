package com.battleShip.Controller;
import com.battleShip.Model.Entities.Game;
import com.battleShip.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "game")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<Object> findAllGame() {
        return ResponseEntity.ok().body(gameService.findAllGame());
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Object> create(@RequestBody Game game)
    {
        return ResponseEntity.ok().body(gameService.create(game));
    }
    @GetMapping(path = "{id}")
    public @ResponseBody
    ResponseEntity<Object> getGameById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(gameService.getMapById(id));
    }
}



