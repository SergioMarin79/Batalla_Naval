package com.battleShip.Service;
import com.battleShip.Model.Entities.*;
import com.battleShip.Repository.*;
import com.battleShip.domain.ShootShip;
import com.battleShip.domain.LocationShip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    private GameRepository gameRepository;
    private UserRepository userRepository;
    private ShipRepository shipRepository;
    private ShootRepository shootRepository;
    private MapRepository mapRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, GameRepository gameRepository, UserRepository userRepository, ShipRepository shipRepository, ShootRepository shootRepository, MapRepository mapRepository) {
        this.boardRepository = boardRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.shipRepository = shipRepository;
        this.shootRepository = shootRepository;
        this.mapRepository = mapRepository;
    }

    public String createLocationShip(LocationShip locationShip) {
        Game game = gameRepository.getById(locationShip.getIdGame());
        Ship ship = shipRepository.getShipById(locationShip.getIdShip());
        UserLogin player = userRepository.getUserById(locationShip.getIdPlayer());


        if(locationShip.isHorizontal()){
            for (int i = 0; i < ship.getNumberBox(); i++) {
                Board validate = boardRepository.getBoardByShoot(game,player,locationShip.getX() + i,locationShip.getY());
                if(validate != null){
                    return "The box in " + validate.getX_coordinate() + " and "+ validate.getY_coordinate() + " are full";
                }
            }


        }else{
            for (int i = 0; i < ship.getNumberBox(); i++) {
                Board validate = boardRepository.getBoardByShoot(game,player,locationShip.getX() ,locationShip.getY()+ i);
                if(validate != null){
                    return "The box in " + validate.getX_coordinate() + " and "+ validate.getY_coordinate() + " are full";
                }
            }
        }

        if (locationShip.isHorizontal()) {
            Integer currentBox = new Random().nextInt(32767);
            Integer nextElement = new Random().nextInt(32767);
            Integer previousElement = null;
            for (int i = 0; i < ship.getNumberBox(); i++) {
                int coorX = locationShip.getX() + i;
                Board board = new Board();
                board.setId(currentBox);
                board.setId_ship(ship);
                board.setPlayer(player);
                board.setId_game(game);
                board.setX_coordinate(coorX);
                board.setY_coordinate(locationShip.getY());
                board.setPreviousElement(previousElement);
                board.setNextElement(nextElement);
                board.setDamage(false);
                previousElement = currentBox;
                currentBox = nextElement;
                nextElement = new Random().nextInt(32767);

                if (i == ship.getNumberBox() - 1) {
                    board.setNextElement(null);
                }
                boardRepository.save(board);

            }

        } else {
            Integer currentBox = new Random().nextInt(32767);
            Integer nextElement = new Random().nextInt(32767);
            Integer previousElement = null;
            for (int i = 0; i < ship.getNumberBox(); i++) {
                int coorY = locationShip.getY() + i;
                Board board = new Board();
                board.setId(currentBox);
                board.setId_ship(ship);
                board.setPlayer(player);
                board.setId_game(game);
                board.setX_coordinate(locationShip.getX());
                board.setY_coordinate(coorY);
                board.setPreviousElement(previousElement);
                board.setNextElement(nextElement);
                board.setDamage(false);
                previousElement = currentBox;
                currentBox = nextElement;
                nextElement = new Random().nextInt(32767);

                if (i == ship.getNumberBox() - 1) {
                    board.setNextElement(null);
                }
                boardRepository.save(board);
            }

        }
        return "The ship is save successful";
    }

    public String shoot(ShootShip shootShip) {
        Integer shootId = new Random().nextInt(32767);
        Game game = gameRepository.getById(shootShip.getIdGame());
        UserLogin player = userRepository.getUserById(shootShip.getIdPlayer());
        Board board = boardRepository.getBoardByShoot(game, player, shootShip.getX(), shootShip.getY());
        Shoot shoot = new Shoot();
        shoot.setId(shootId);
        shoot.setIdGame(game);
        shoot.setPlayer(player);
        shoot.setX(shootShip.getX());
        shoot.setY(shootShip.getY());
        if (board == null) {
            shoot.setSuccessfulShoot(false);
            shootRepository.save(shoot);
            return "The shoot is not successful";
        } else {
            shoot.setSuccessfulShoot(true);
            board.setDamage(true);
            boardRepository.save(board);
            shootRepository.save(shoot);
            return "The shoot is successful";

        }


    }

    public String findHeadShip(int idGame, int id_player) {
        int[][] positions;
        Game game = gameRepository.getById(idGame);
        UserLogin player = userRepository.getUserById(id_player);
        List<Board> headShip = boardRepository.getHeadShip(player, game);
        Map map = mapRepository.getById(game.getId_map());
        positions = new int[map.getHeight()][map.getWidth()];

        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                positions[i][j] = 0;
            }

        }


        for (int i = 0; i < headShip.size(); i++) {
            Board board = headShip.get(i);

            do {
                if (board.isDamage() == false) {
                    positions[board.getX_coordinate()][board.getY_coordinate()] = 1;
                } else {
                    positions[board.getX_coordinate()][board.getY_coordinate()] = 2;
                }
                board = boardRepository.findById(board.getNextElement()).get();
            } while (board.getNextElement() != null);

            if (board.isDamage() == false) {
                positions[board.getX_coordinate()][board.getY_coordinate()] = 1;
            } else {
                positions[board.getX_coordinate()][board.getY_coordinate()] = 2;
            }
        }

        String printMap = "";
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                printMap = printMap + positions[j][i] + ",";
            }
            printMap = printMap + "\n";
        }

        return printMap;


    }

    public String findWinner(int idGame, int id_player) {
        Game game = gameRepository.getById(idGame);
        UserLogin player = userRepository.getUserById(id_player);
        List<Board> validateWin = boardRepository.getHeadShip(player, game);
        for (int i = 0; i < validateWin.size(); i++) {
            Board board = validateWin.get(i);
            do {
                if (board.isDamage() == false) {
                    return "The player " + player.getId() + " is NOT WINNER yet the game. " + game.getId();
                }
                board = boardRepository.findById(board.getNextElement()).get();
            } while (board.getNextElement() != null);
            if (board.isDamage() == false) {
                return "The player " + player.getId() + " lose in the map ";
            }
        }
        return "The player " + player.getId() + " is the winner in the game " + game.getId();
    }
}