package com.dhaval.battleship.webservices;

import com.dhaval.battleship.Exceptions.BattleGridException;
import com.dhaval.battleship.Exceptions.ShipPlacementException;
import com.dhaval.battleship.beans.BattleGrid;
import com.dhaval.battleship.beans.ShipPosition;
import com.dhaval.battleship.dao.BattlegridRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BattlegridController {
    private final Log log = LogFactory.getLog(BattlegridController.class);

    @Autowired
    BattlegridRepository repository;

    @RequestMapping(value = "/battlegrid", method = RequestMethod.POST)
    public BattleGrid updateBattleGrid(@RequestParam(value = "gameId") int gameId,
                                       @RequestParam(value = "playerId") String playerId,
                                       @RequestBody List<ShipPosition> shipPositions) throws ShipPlacementException, BattleGridException {
        BattleGrid battleGrid = repository.findByGameIdAndPlayerId(gameId, playerId);
        if (battleGrid!=null) {
            for (ShipPosition currShipPosition : shipPositions) {
                switch (currShipPosition.getDirection()) {
                    case Down: {
                        for (int i = 0; i < currShipPosition.getLength(); i++) {
                            placeShip(battleGrid, currShipPosition.getShipId(), currShipPosition.getStartRow() + i, currShipPosition.getStartColumn());
                        }
                        break;
                    }
                    case Right: {
                        for (int i = 0; i < currShipPosition.getLength(); i++) {
                            placeShip(battleGrid, currShipPosition.getShipId(), currShipPosition.getStartRow(), currShipPosition.getStartColumn() + i);
                        }
                        break;
                    }
                    default: {
                        if (log.isErrorEnabled()) {
                            log.error("Invalid direction for the ship");
                        }
                        break;
                    }
                }
            }
        }else
            throw new BattleGridException("No battlegrid found for gameId & playerId combination");

        return battleGrid;
    }

    private void placeShip(BattleGrid battleGrid, String shipId, int x, int y) throws ShipPlacementException {

        if(battleGrid.getGrid()[x][y].equals("0"))
            battleGrid.getGrid()[x][y] = shipId;
        else
            throw new ShipPlacementException("Ship placement collides with an existing ship");

    }
}
