package com.dhaval.battleship.webservices;

import com.dhaval.battleship.beans.BattleGrid;
import com.dhaval.battleship.dao.BattlegridRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Arrays;

@RestController
public class NewGameController {
    @Autowired
    BattlegridRepository repository;

    private final SecureRandom secureRandom = new SecureRandom();

    /**
     * Endpoint to initialize a new game.
     * @param gridSize The size of the battlegrid for the game
     * @param player1Id Player 1 Id
     * @param player2Id Player 2 Id
     * @return gameId for the new game
     */
    @RequestMapping(value = "/newgame", method = RequestMethod.POST)
    public int updateBattleGrid(@RequestParam(value = "gridSize") int gridSize,
                                       @RequestParam(value = "player1_Id") String player1Id,
                                       @RequestParam(value = "player2_Id") String player2Id) {

        //Generate a new unique game id
        int gameId = secureRandom.nextInt(999999);

        //Initialize a new battle grid for both players
        BattleGrid player1BattleGrid = initializeBattleGrid(gameId, gridSize, player1Id);
        BattleGrid player2BattleGrid = initializeBattleGrid(gameId, gridSize, player2Id);

        return gameId;
    }

    private BattleGrid initializeBattleGrid(int gameId, int gridSize, String playerId){
        BattleGrid battleGrid = new BattleGrid(gameId, gridSize, playerId);
        for (String[] gridRow:battleGrid.getGrid()){
            Arrays.fill(gridRow, "0");
        }
        battleGrid = repository.save(battleGrid);
        return battleGrid;
    }
}