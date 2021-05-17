import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleshipModelTest {

    /*
 The test cases I will be testing:
      - Check if the ship has been hit
      - Check if the missile has missed
      - Check if the default placement of ships has indeed been placed
*/

    /*
     Check if the default of placements of ships been placed correctly
     Check game board has a CARRIER ship
    */

    @Test
    void defaultPlacementOfShips() {
        System.out.println("Test: Default Placement of ships");
        BattleshipModel battleshipModel = new BattleshipModel();
        battleshipModel.defaultPlacementOfShips();

        assertEquals(battleshipModel.getGameBoard()[1][1], BattleshipModel.CARRIER_INDICATOR);
        assertEquals(battleshipModel.getGameBoard()[1][2], BattleshipModel.CARRIER_INDICATOR);
        assertEquals(battleshipModel.getGameBoard()[1][3], BattleshipModel.CARRIER_INDICATOR);
        assertEquals(battleshipModel.getGameBoard()[1][4], BattleshipModel.CARRIER_INDICATOR);
        assertEquals(battleshipModel.getGameBoard()[1][5], BattleshipModel.CARRIER_INDICATOR);
    }

    /*
     Provide hit coordinates
     Load default ships for testing
     Check if invariant is true
     Check if Missiles has been incremented
     Check if Cell has been changed to HIT_INDICATOR which is 7
    */

    @Test
    void detectGameBoardShipHitOrSunk() {
        System.out.println("Test: Hit on Ship");
        BattleshipModel battleshipModel = new BattleshipModel();
        battleshipModel.defaultPlacementOfShips();
        battleshipModel.setIncomingMissileCoordinates(1, 1);
        battleshipModel.detectMissilesReceivedOnShips();

        assertTrue(battleshipModel.invariantTotalMissilesFired());
        assertEquals(battleshipModel.getTotalMissilesFired(), 1);
        assertEquals(battleshipModel.getGameBoard()
                        [battleshipModel.getLatestMissileX()][battleshipModel.getLatestMissileY()]
                , BattleshipModel.HIT_INDICATOR);
    }


    /*
    Provide miss coordinates
    Load default ships for testing
    Check if invariant is true
    Check if Missiles has been incremented
    Check if Cell has been changed to MISS_INDICATOR which is 0
   */

    @Test
    void detectGameBoardLatestMissileMiss() {
        System.out.println("Test: Miss on Ship");
        BattleshipModel battleshipModel = new BattleshipModel();
        battleshipModel.defaultPlacementOfShips();
        battleshipModel.setIncomingMissileCoordinates(0, 0);
        battleshipModel.detectGameBoardLatestMissileMiss();

        assertTrue(battleshipModel.invariantTotalMissilesFired());
        assertEquals(battleshipModel.getTotalMissilesFired(), 1);
        assertEquals(battleshipModel.getGameBoard()
                        [battleshipModel.getLatestMissileX()][battleshipModel.getLatestMissileY()]
                , BattleshipModel.MISS_INDICATOR);


    }
}