package repository;

import models.BattleShip;

import java.util.HashMap;
import java.util.Map;

public class BattleShipRepository {
    public static Map<Long, BattleShip> battleShipMap = new HashMap<>();

    public static void addBattleShip(Long id,BattleShip battleShip){
        battleShipMap.put(id,battleShip);
    }
    public static BattleShip getById(Long id){
        return battleShipMap.get(id);
    }
}
