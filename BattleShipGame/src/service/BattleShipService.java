package service;

import exceptions.ShipBoundaryExceedException;
import exceptions.ShipOverlapException;
import models.*;
import repository.BattleShipRepository;
import strategies.FireStrategy;

import java.util.List;

public class BattleShipService {
    private static Long id = 0L;

    public static BattleShip createGame(Integer dimension){
        BattleShip battleShip = new BattleShip.BattleShipBuilder()
                .addDimension(dimension)
                .addPlayer("A")
                .addPlayer("B")
                .build();
        id++;
        battleShip.setId(id);
        BattleShipRepository.addBattleShip(id,battleShip);
        return BattleShipRepository.getById(id);
    }

    public static void placeShip(String shipId,Integer size,Integer xA,Integer yA,Integer xB,Integer yB,Long id){
        BattleShip battleShip = BattleShipRepository.getById(id);
        for(Player player : battleShip.getPlayerList()){
            player.setSafeShipCnt(player.getSafeShipCnt()+1);
        }
        placeShipForSinglePlayer(shipId,size,xA,yA,battleShip,battleShip.getPlayerList().get(0),0);
        placeShipForSinglePlayer(shipId,size,xB,yB,battleShip,battleShip.getPlayerList().get(1),1);
    }

    public static void placeShipForSinglePlayer(String shipId, Integer size, Integer x, Integer y, BattleShip battleShip, Player player,int playerId){
        int topLeftX = x - size/2,topLeftY = y - size/2;
        int bottomRightX,bottomRightY;
        if(size%2 == 0){
            bottomRightX = x + (size/2 - 1);
            bottomRightY = y + (size/2 - 1);
        }
        else{
            bottomRightX = x + size/2;
            bottomRightY = y + size/2;
        }
        if(!isValidCoordinate(topLeftX,topLeftY,bottomRightX,bottomRightY,battleShip.getBoard().size(),playerId)){
            throw new ShipBoundaryExceedException("Player "+player.getName() + " : Ship cannot be placed at coordinate (" + x + "," + y + ").");
        }
        List<List<Cell>> board = battleShip.getBoard();
        Ship ship = new Ship();
        ship.setShipId(shipId);
        ship.setShipStatus(ShipStatus.SAFE);
        ship.setOwnerName(player.getName());
        for(int i=topLeftX;i<=bottomRightX;i++){
            for(int j=topLeftY;j<=bottomRightY;j++){
                Cell cell = board.get(i).get(j);
                if(cell.getX() != null){
                    throw new ShipOverlapException("Ships are overlapping for Player " + player.getName());
                }
                cell.setX(i);
                cell.setY(j);
                cell.setShip(ship);
            }
        }
    }

    public static boolean isValidCoordinate(int topLeftX,int topLeftY,int bottomRightX,int bottomRightY,int dimension,int playerId){
        if(playerId%2 == 0){
            return topLeftX >= 0 && topLeftY >= 0 && bottomRightX >= 0 && bottomRightY >= 0 && topLeftX < dimension && topLeftY < dimension / 2
                    && bottomRightX < dimension && bottomRightY < dimension / 2;
        }
        else{
            return topLeftX >= 0 && topLeftY >= dimension / 2 && bottomRightX >= 0 && bottomRightY >= dimension / 2 && topLeftX < dimension && topLeftY < dimension
                    && bottomRightX < dimension && bottomRightY < dimension;
        }
    }

    public static void startGame(Long id,FireStrategy fireStrategy){
        BattleShip battleShip = BattleShipRepository.getById(id);
        battleShip.setGameStatus(GameStatus.IN_PROGRESS);
        int lastMovedPlayerIndex = 0;
        while(battleShip.getGameStatus() == GameStatus.IN_PROGRESS){
            fireStrategy.launchFire(battleShip,lastMovedPlayerIndex);
            lastMovedPlayerIndex = (lastMovedPlayerIndex+1)%battleShip.getPlayerList().size();
        }
    }

    public static void viewBattleField(Long id){
        BattleShip battleShip = BattleShipRepository.getById(id);
        System.out.println();
        for(List<Cell> cellList : battleShip.getBoard()){
            for(Cell cell : cellList){
                if(cell.getX() == null){
                    System.out.print("     | ");
                }
                else{
                    Ship ship = cell.getShip();
                    System.out.print(ship.getOwnerName()+"-"+ship.getShipId()+"| ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}














