public class BattleshipController {

    private BattleshipModel nBattleshipModel;
    private BattleshipGUIView nBattleshipGUIView;

    public BattleshipController (BattleshipModel battleshipModel) {
        nBattleshipModel = battleshipModel;
    }

    public void setView (BattleshipGUIView battleshipGUIView) {
        nBattleshipGUIView = battleshipGUIView;
    }

    public void change() {
        nBattleshipModel.change();
    }
    public void initialise() {
        nBattleshipModel.initialise();
    }

}
