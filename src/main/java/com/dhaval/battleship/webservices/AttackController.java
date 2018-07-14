package com.dhaval.battleship.webservices;

import com.dhaval.battleship.beans.BattleGrid;
import com.dhaval.battleship.dao.BattlegridRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttackController {
    private final Log log = LogFactory.getLog(BattlegridController.class);

    @Autowired
    BattlegridRepository repository;

    @RequestMapping(value = "/attack", method = RequestMethod.POST)
    public String executeAttack(@RequestParam(value = "gameId") int gameId,
                                 @RequestParam(value = "playerId") String playerId,
                                 @RequestParam(value = "row") int row,
                                 @RequestParam(value = "column") int column){

        BattleGrid battleGrid = repository.findByGameIdAndPlayerId(gameId, playerId);
        String resultOfAttack = "Miss";
        String shipId = battleGrid.getGrid()[row][column];

        if(!shipId.equals("0") && !shipId.equals("-1")){
            battleGrid.getGrid()[row][column] = "-1";
            repository.save(battleGrid);
            if(isShipSunk(battleGrid, shipId, row, column))
                resultOfAttack="ShipSunk";
            else
                resultOfAttack="Hit";
        }
        return resultOfAttack;
    }

    private boolean isShipSunk(BattleGrid battleGrid, String shipId, int row, int column){
        boolean shipSunk = true;
        if(row-1>=0 && row-1<battleGrid.getGridSize()){
            if(shipId.equals(battleGrid.getGrid()[row-1][column])){
                shipSunk = false;
            }
        }
        if(row+1>=0 && row+1<battleGrid.getGridSize()){
            if(shipId.equals(battleGrid.getGrid()[row+1][column])){
                shipSunk = false;
            }
        }
        if(column-1>=0 && column-1<battleGrid.getGridSize()){
            if(shipId.equals(battleGrid.getGrid()[row][column-1])){
                shipSunk = false;
            }
        }
        if(column+1>=0 && column+1<battleGrid.getGridSize()){
            if(shipId.equals(battleGrid.getGrid()[row][column+1])){
                shipSunk = false;
            }
        }
        return shipSunk;
    }
}
