package com.dhaval.battleship.dao;
import com.dhaval.battleship.beans.BattleGrid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattlegridRepository extends CrudRepository<BattleGrid,Long> {

    BattleGrid findByGameIdAndPlayerId(int gameId, String playerId);
}
