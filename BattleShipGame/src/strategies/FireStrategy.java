package strategies;

import models.BattleShip;
import models.Cell;
import models.Player;

import java.util.List;

public interface FireStrategy {
    void launchFire(BattleShip battleShip, int lastMovedPlayerIndex);
}
