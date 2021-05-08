import java.util.Observable;
import java.util.Observer;

public class BattleshioCLIView implements Observer {

    private BattleshipModel battleshipModel;
    public void start() {
         BattleshipModel battleshipModel = new BattleshipModel();
        battleshipModel.addObserver(this);
    }
    @Override
    public void update(Observable o, Object arg) {

    }


}
