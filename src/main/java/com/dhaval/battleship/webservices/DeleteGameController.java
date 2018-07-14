package com.dhaval.battleship.webservices;

import com.dhaval.battleship.exceptions.GameDoesNotExistException;
import com.dhaval.battleship.beans.BattleGrid;
import com.dhaval.battleship.dao.BattlegridRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeleteGameController {
    @Autowired
    BattlegridRepository repository;

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public boolean deleteGame(@RequestParam(value = "gameId") int gameId) throws GameDoesNotExistException {
        List<BattleGrid> battleGrids = repository.findAllByGameId(gameId);
        if(null!=battleGrids) {
            for (BattleGrid currBattleGrid : battleGrids) {
                repository.delete(currBattleGrid);
            }
        }else
            throw new GameDoesNotExistException();
        return true;
    }
}
