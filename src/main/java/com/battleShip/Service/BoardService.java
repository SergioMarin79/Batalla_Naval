package com.battleShip.Service;
import com.battleShip.Model.Entities.*;
import com.battleShip.Repository.*;
import com.battleShip.domain.*;
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

    private ListDE listDE;

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
        Map map = mapRepository.getById(game.getId_map());

        //Obtener el mapa con las dimensiones.
        locationShip.getX();
        locationShip.getY();
        if(locationShip.isHorizontal())
        if (locationShip.getX() > map.getWidth() - 1 - ship.getNumberBox() || locationShip.getY() > map.getHeight() - 1)
            return "The coordinate is out the limits of map ";
        if(!locationShip.isHorizontal())
            if (locationShip.getX() > map.getWidth() - 1 || locationShip.getY() > map.getHeight() - 1 - ship.getNumberBox() )
                return "The coordinate is out the limits of map ";

        if (locationShip.isHorizontal()) {
            for (int i = 0; i < ship.getNumberBox(); i++) {
                Board validate = boardRepository.getBoardByShoot(game, player, locationShip.getX() + i, locationShip.getY());
                if (validate != null) {
                    return "The box in " + validate.getX_coordinate() + " and " + validate.getY_coordinate() + " are full";
                }
            }


        } else {
            for (int i = 0; i < ship.getNumberBox(); i++) {
                Board validate = boardRepository.getBoardByShoot(game, player, locationShip.getX(), locationShip.getY() + i);
                if (validate != null) {
                    return "The box in " + validate.getX_coordinate() + " and " + validate.getY_coordinate() + " are full";
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
        Integer[][] positions;
        Game game = gameRepository.getById(idGame);
        UserLogin player = userRepository.getUserById(id_player);
        Map map = mapRepository.getById(game.getId_map());
        positions = new Integer[map.getHeight()][map.getWidth()];
        List<Board> boards = boardRepository.getAllShips(player, game);

        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                positions[i][j] = 0;
            }
        }

        //Create ListDE
        listBuildShips(boards);

        listDE.setShipsInBoard(positions, listDE.getHead());

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

    public String getAllShips(int idGame, int idPlayer) {
        Game game = gameRepository.getById(idGame);
        UserLogin player = userRepository.getUserById(idPlayer);
        List<Board> boards = boardRepository.getAllShips(player, game);
        listBuildShips(boards);
        System.out.println("Total of ships " + listDE.countShipHead(listDE.getHead()));
        return listDE.printPositionOfShip("", listDE.getHead());
    }

    private void listBuildShips(List<Board> boards) {
        listDE = new ListDE();
        System.out.println("Number of ship position " + boards.size());
        for(int i = 0; i < boards.size(); i++) {
            Board board = boards.get(i);
            if(board.getPreviousElement() == null) {
                ShipNode shipNodeHead = new ShipNode();
                ShipPosition shipPositionHead = new ShipPosition();
                shipPositionHead.setCorX(board.getX_coordinate());
                shipPositionHead.setCorY(board.getY_coordinate());
                shipPositionHead.setDamage(board.isDamage());
                PositionNode positionNode = new PositionNode();
                positionNode.setShip(shipPositionHead);
                positionNode.setPrevious(null);

                shipNodeHead.setPositionNode(positionNode);
                PositionNode positionNodePrevious = positionNode;
                do {
                    PositionNode positionNodeNexElement = new PositionNode();
                    for(int j = 0; j < boards.size(); j++) {
                        Board board2 = boards.get(j);
                        if(board2.getId() == board.getNextElement()) {
                            System.out.println("I found the next element");
                            ShipPosition shipPositionTemp = new ShipPosition();
                            shipPositionTemp.setCorY(board2.getY_coordinate());
                            shipPositionTemp.setCorX(board2.getX_coordinate());
                            shipPositionTemp.setDamage(board2.isDamage());
                            positionNodeNexElement.setShip(shipPositionTemp);
                            positionNodeNexElement.setPrevious(positionNodePrevious);
                            positionNodeNexElement.setNext(null);
                            positionNodePrevious.setNext(positionNodeNexElement);
                            board = board2;
                            break;
                        }
                    }
                    positionNodePrevious = positionNodeNexElement;
                } while(board.getNextElement() != null);
                listDE.addNode(shipNodeHead);
            }
            System.out.println("i " + i + " size " + boards.size());
        }
    }


}

