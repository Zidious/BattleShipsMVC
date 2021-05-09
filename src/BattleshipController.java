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
        nBattleshipModel.setMissileCoordinates(x, y);
    }

    public int getMissileSentX() {
        return nBattleshipModel.getMissileSentX();
    }

    public int getMissileSentY() {
        return nBattleshipModel.getMissileSentY();
    }

    public boolean isMissileHit() {
        return nBattleshipModel.isMissileHit();
    }

    public boolean isMissileMiss() {
        return nBattleshipModel.isMissileMiss();
    }

    public ArrayList<int[]> getSunkShipsCoords() {
        return nBattleshipModel.getSunkShipsCoords();
    }

    public boolean isGameOver() {
        return nBattleshipModel.isGameOver();
    }

}
