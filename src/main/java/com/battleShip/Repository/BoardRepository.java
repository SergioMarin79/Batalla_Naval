package com.battleShip.Repository;
import com.battleShip.Model.Entities.Board;
import com.battleShip.Model.Entities.Game;
import com.battleShip.Model.Entities.UserLogin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  BoardRepository  extends CrudRepository<Board,Integer> {

    @Query("SELECT board FROM Board board WHERE board.id_game = ?1 and board.player = ?2 and board.x_coordinate = ?3 and board.y_coordinate = ?4")
    Board getBoardByShoot(Game idGame, UserLogin player, int cooX, int cooY);

    @Query("SELECT board From Board board WHERE board.player=?1 and board.id_game=?2 and board.previousElement is null")
    List<Board> getHeadShip(UserLogin player, Game idGame);


}
