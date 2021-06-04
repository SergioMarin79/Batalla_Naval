package com.battleShip.Service;

import com.battleShip.Model.Entities.Game;
import com.battleShip.Repository.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameService {
    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> findAllGame() {
        return gameRepository.getAllGame();
    }

    public Game create(Game game) {
        System.out.println("Saving with ID" + game.getId());
        gameRepository.save(game);
        return game;
    }

    public Game getMapById(int id) {
        return gameRepository.getById(id);

    }
}