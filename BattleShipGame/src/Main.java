import controller.BattleShipController;
import models.BattleShip;

public class Main {
    public static void main(String[] args) {
        //Test0
        BattleShipController battleShipController = new BattleShipController();
        BattleShip battleShip0 = battleShipController.initGame(8);
        battleShipController.addShip("SH1",3,3,2,5,6,battleShip0.getId());
        battleShipController.addShip("SH2",2,7,1,2,7,battleShip0.getId());
        battleShipController.viewBattleField(battleShip0.getId());
        System.out.println("Base Constructed");
        battleShipController.startGame(battleShip0.getId());

        //Test1
        BattleShip battleShip1 = battleShipController.initGame(8);
        battleShipController.addShip("SH1",1,0,0,0,7,battleShip1.getId());
        battleShipController.addShip("SH2",4,3,2,5,6,battleShip1.getId());
        battleShipController.viewBattleField(battleShip1.getId());
        System.out.println("Base Constructed");
        battleShipController.startGame(battleShip1.getId());



        //Test2
//      BattleShip battleShip2 = battleShipController.initGame(8);
//      battleShipController.addShip("SH1",1,4,3,0,7,battleShip2.getId());
//      battleShipController.addShip("SH2",4,3,2,5,6,battleShip2.getId());
//      battleShipController.viewBattleField(battleShip2.getId());
//      System.out.println("Base Constructed");
//      battleShipController.startGame(battleShip2.getId());

        //Test3
//        BattleShip battleShip2 = battleShipController.initGame(8);
//        battleShipController.addShip("SH1",1,0,0,0,3,battleShip2.getId());
//        battleShipController.addShip("SH2",4,3,2,5,6,battleShip2.getId());
//        battleShipController.viewBattleField(battleShip2.getId());
//        System.out.println("Base Constructed");
//        battleShipController.startGame(battleShip2.getId());
    }
}