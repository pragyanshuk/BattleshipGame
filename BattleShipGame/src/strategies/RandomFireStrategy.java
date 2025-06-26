package strategies;

import models.*;

import java.util.List;

public class RandomFireStrategy implements FireStrategy{
    @Override
    public void launchFire(BattleShip battleShip, int playerIndex) {
        int startCol = 0;
        if(playerIndex%2 != 0){
            startCol = 4;
        }
        int endCol = startCol - 1 + battleShip.getBoard().size()/2;

        List<List<Cell>> board = battleShip.getBoard();
        List<Player> playerList = battleShip.getPlayerList();
        for(int i=0;i<board.size();i++){
            for(int j=startCol;j<=endCol;j++){
                Cell cell = board.get(i).get(j);
                Player player = playerList.get(playerIndex);
                if(cell.getCellStatus() == CellStatus.NOT_FIRED){
                    if(cell.getX() == null){
                        cell.setCellStatus(CellStatus.FIRED);
                        System.out.println("Player " + player.getName() + " turn: Missile fired at (" + i +"," + j +") : Miss : Ships Remaining - Player A: " + playerList.get(0).getSafeShipCnt() + ", Player B: " + playerList.get(1).getSafeShipCnt());
                        return;
                    }
                    else{
                        Ship ship = cell.getShip();
                        if(ship.getShipStatus() == ShipStatus.SAFE){
                            ship.setShipStatus(ShipStatus.DESTROYED);
                            cell.setCellStatus(CellStatus.FIRED);
                            player.setSafeShipCnt(player.getSafeShipCnt()-1);
                            System.out.println("Player " + player.getName() + " turn: Missile fired at (" + i +"," + j +") : Hit new ship : Ships Remaining - Player A: " + playerList.get(0).getSafeShipCnt() + ", Player B: " + playerList.get(1).getSafeShipCnt());
                            if(player.getSafeShipCnt() == 0){
                                battleShip.setGameStatus(GameStatus.ENDED);
                                System.out.println("Game over, Player " + player.getName() + " wins.");
                            }
                            return;
                        }
                        else{
                            cell.setCellStatus(CellStatus.FIRED);
                            System.out.println("Player " + player.getName() + " turn: Missile fired at (" + i +"," + j +") : Hit already destroyed ship : Ships Remaining - Player A: " + playerList.get(0).getSafeShipCnt() + ", Player B: " + playerList.get(1).getSafeShipCnt());
                            return;
                        }
                    }
                }
            }
        }
    }
}
