import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class BattleshipGUIView extends Application implements Observer {

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;

    private BattleshipModel battleshipModel;
    private BattleshipController battleshipController;


    @Override
    public void update(Observable o, Object arg) {
        battleshipModel.displayBoard();
    }

    public GridPane displayGrid() {
        final int SIZE = 10;

        GridPane gridPane = new GridPane();
        for (int y = 0; y < battleshipModel.getGameBoard().length; y++) {
            for (int x = 0; x < battleshipModel.getGameBoard().length; x++) {

                Rectangle border = new Rectangle(50, 50);
                border.setFill(battleshipModel.getGameBoard()[y][x] == 0 ? Color.LIGHTBLUE : Color.BLACK);
                border.setStroke(Color.BLACK);


                GridPane.setRowIndex(border, y);
                GridPane.setColumnIndex(border, x);


                gridPane.getChildren().addAll(border);
            }
        }

        return gridPane;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        battleshipModel = new BattleshipModel();
        battleshipController = new BattleshipController(battleshipModel);

        primaryStage.setTitle("Battleships MVC");

        GridPane gridPane = displayGrid();
        Scene scene = new Scene(gridPane,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();


        battleshipModel.addObserver(this);
        update(null, null);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
