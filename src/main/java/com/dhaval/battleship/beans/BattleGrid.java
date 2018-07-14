package com.dhaval.battleship.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(BattleGridCompositeKey.class)
public class BattleGrid implements Serializable {

    @Id
    private int gameId;

    private int gridSize;
    private String[][] grid;

    @Id
    @Column(length=20)
    private String playerId;

    public BattleGrid() {
    }

    public BattleGrid(int gameId, int gridSize, String playerId) {
        this.gameId = gameId;
        this.gridSize = gridSize;
        this.grid = new String[gridSize][gridSize];
        this.playerId = playerId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public String[][] getGrid() {
        return grid;
    }

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
