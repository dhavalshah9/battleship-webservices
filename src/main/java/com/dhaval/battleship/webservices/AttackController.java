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
        if(!battleGrid.getGrid()[row][column].equals("0")){
            resultOfAttack="Hit";
        }
        return resultOfAttack;
    }
}
