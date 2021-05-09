import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

public class BattleshipGUIView extends Application implements Observer {

    private static final int WINDOW_WIDTH = 511;
    private static final int WINDOW_HEIGHT = 511;

    private BattleshipModel battleshipModel;
    private BattleshipController battleshipController;
    private final GridPane gridPane = new GridPane();

    @Override
    public void update(Observable o, Object arg) {
        gridPane.setOnMouseClicked(event -> {
            int x = GridPane.getRowIndex((Node) event.getTarget());
            int y = GridPane.getColumnIndex((Node) event.getTarget());
            //Debugging
            System.out.println("COORDS: " + x + "," + y);
            Rectangle rectangle = new Rectangle(50, 50);


            battleshipController.setMissileCoordinates(x, y);
            battleshipModel.detectMissilesReceivedOnShips();
            boolean cellIsSunk = false;
            for (int[] coords : battleshipController.getSunkShipsCoords()) {
                if (coords[0] == x && coords[1] == y) {
                    cellIsSunk = true;
                }
            }
            if (cellIsSunk) {
                rectangle.setFill(Color.YELLOW);
                rectangle.setStroke(Color.YELLOW);
            } else {

                if (battleshipController.isMissileHit()) {
                    rectangle.setFill(Color.RED);
                    rectangle.setStroke(Color.RED);

                } else {
                    rectangle = new Rectangle(50, 50);
                    rectangle.setFill(Color.WHITE);
                }
            }

            GridPane.setRowIndex(rectangle, x);
            GridPane.setColumnIndex(rectangle, y);
            gridPane.getChildren().add(rectangle);
        });

    }

    public void fillSunkShips() {
        for (int[] coords : battleshipController.getSunkShipsCoords()) {
            Rectangle rectangle = new Rectangle();
            System.out.println(Arrays.toString(Arrays.stream(coords).toArray()));

            rectangle.setFill(Color.YELLOW);
            rectangle.setStroke(Color.YELLOW);
            GridPane.setRowIndex(rectangle, coords[0]);
            GridPane.setColumnIndex(rectangle, coords[1]);

            gridPane.getChildren().add(rectangle);

        }


    }

    public GridPane displayGrid() {
        for (int y = 0; y < battleshipModel.getGameBoard().length; y++) {
            for (int x = 0; x < battleshipModel.getGameBoard().length; x++) {

                Rectangle rectangle = new Rectangle(50, 50);
                rectangle.setFill(Color.LIGHTBLUE);
                rectangle.setStroke(Color.BLACK);


                GridPane.setRowIndex(rectangle, y);
                GridPane.setColumnIndex(rectangle, x);


                gridPane.getChildren().add(rectangle);
            }

        }
        return gridPane;
    }

    public void displayHits(GridPane gridPane) {

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
