package com.battleShip.Repository;

import com.battleShip.Model.Entities.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface  GameRepository  extends CrudRepository<Game,Integer> {

    @Query("SELECT game FROM Game game")
    List<Game> getAllGame();

    @Query("SELECT game FROM Game game WHERE game.id=?1")
    Game getById(int id);
}
