package models;

import exceptions.InvalidPlayerCountException;
import exceptions.OddDimensionBoardException;

import java.util.ArrayList;
import java.util.List;

public class BattleShip extends BaseModel{
    private List<List<Cell>> board;
    private List<Player> playerList;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    private GameStatus gameStatus;
    private String winner;

    private BattleShip(){
        this.board = new ArrayList<>();
        this.playerList = new ArrayList<>();
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public String getWinner() {
        return winner;
    }

    public static class BattleShipBuilder{
        private List<List<Cell>> board;
        private List<Player> playerList;

        public BattleShipBuilder(){
            this.board = new ArrayList<>();
            this.playerList = new ArrayList<>();
        }

        public BattleShipBuilder addDimension(Integer dimension){
            for(int i=0;i<dimension;i++){
                this.board.add(new ArrayList<>(dimension));
                for(int j=0;j<dimension;j++){
                    this.board.get(i).add(new Cell());
                }
            }
            return this;
        }

        public BattleShipBuilder addPlayer(String name){
            this.playerList.add(new Player(name));
            return this;
        }

        public BattleShip build(){
            //validation
            if(this.board.size()%2 !=0){
                throw new OddDimensionBoardException("Dimension of board is odd");
            }
            if(this.playerList.size() != 2){
                throw new InvalidPlayerCountException("Player count is not 2");
            }

            //creation
            BattleShip battleShip = new BattleShip();
            battleShip.board = this.board;
            battleShip.playerList = this.playerList;
            return battleShip;
        }
    }
}
