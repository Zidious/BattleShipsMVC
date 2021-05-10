import java.util.ArrayList;

public class BattleshipController {

    private BattleshipModel nBattleshipModel;
    private BattleshipGUIView nBattleshipGUIView;

    public BattleshipController(BattleshipModel battleshipModel) {
        nBattleshipModel = battleshipModel;
    }

    public void setView(BattleshipGUIView battleshipGUIView) {
        nBattleshipGUIView = battleshipGUIView;
    }

    public void change() {
        nBattleshipModel.change();
    }

    public void initialise() {
        nBattleshipModel.initialise();
    }

    public void setMissileCoordinates(int x, int y) {
        nBattleshipModel.setIncomingMissileCoordinates(x, y);
    }

    public int getMissileSentX() {
        return nBattleshipModel.getNewestMissileAtX();
    }

    public int getMissileSentY() {
        return nBattleshipModel.getLatestMissileAtY();
    }

    public boolean isMissileHit() {
        return nBattleshipModel.isLatestMissileHit();
    }

    public boolean isMissileMiss() {
        return nBattleshipModel.isLatestMiss();
    }

    public ArrayList<int[]> getSunkShipsCoords() {
        return nBattleshipModel.getSunkShipCoordinates();
    }

    public boolean isGameOver() {
        return nBattleshipModel.isGameOver();
    }

}
