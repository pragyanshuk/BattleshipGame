package controller;

import models.BattleShip;
import service.BattleShipService;
import strategies.RandomFireStrategy;

public class BattleShipController {

    public BattleShip initGame(Integer dimension){
        return BattleShipService.createGame(dimension);
    }

    public void addShip(String shipId,Integer size,Integer xA,Integer yA,Integer xB,Integer yB,Long id){
        BattleShipService.placeShip(shipId,size,xA,yA,xB,yB,id);
    }

    public void startGame(Long id){
        BattleShipService.startGame(id,new RandomFireStrategy());
    }

    public void viewBattleField(Long id){
        BattleShipService.viewBattleField(id);
    }
}
