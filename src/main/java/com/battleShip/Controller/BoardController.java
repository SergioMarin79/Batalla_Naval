package com.battleShip.Controller;
import com.battleShip.Service.BoardService;
import com.battleShip.domain.LocationShip;
import com.battleShip.domain.ShootShip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "board")
public class BoardController {
    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping(path = "/createLocationShip")
    public @ResponseBody
    ResponseEntity<Object> createLocationShip(@RequestBody LocationShip locationShip)
    {
        return ResponseEntity.ok().body(boardService.createLocationShip(locationShip));
    }

    @PostMapping(path = "/shoot")
    public @ResponseBody
    ResponseEntity<Object> shoot(@RequestBody ShootShip shootShip)
    {
        return ResponseEntity.ok().body(boardService.shoot(shootShip));
    }

    @GetMapping(path = "{id_game}/{player}")
    public @ResponseBody
    ResponseEntity<Object> findHeadShip(@PathVariable("id_game") int idGame, @PathVariable("player") int id_player) {
        return ResponseEntity.ok().body(boardService.findHeadShip(idGame,id_player));
    }

    @GetMapping(path = "win/{id_game}/{player}")
    public @ResponseBody
    ResponseEntity<Object> findWinner(@PathVariable("id_game") int idGame, @PathVariable("player") int id_player) {
        return ResponseEntity.ok().body(boardService.findWinner(idGame,id_player));
    }

    @GetMapping("shipList/{id_game}/{id_player}")
    public @ResponseBody
    ResponseEntity<Object> getAllShips(@PathVariable("id_game") int idGame, @PathVariable("id_player") int idPlayer ) {
        return ResponseEntity.ok(boardService.getAllShips(idGame, idPlayer));

    }

}
