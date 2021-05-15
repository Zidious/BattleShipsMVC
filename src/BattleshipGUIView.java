import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

public class BattleshipGUIView extends Application implements Observer {

    private static final int WINDOW_WIDTH = 511;
    private static final int WINDOW_HEIGHT = 511;

    private BattleshipModel battleshipModel;
    private BattleshipController battleshipController;
    private final GridPane gridPane = new GridPane();


    @Override
    public void update(Observable o, Object arg) {
        if (!battleshipController.isGameOver()) {
            missileClickEvent();
        } else {
            Rectangle rectangle = new Rectangle(511, 511);
            StackPane stackPane = new StackPane();
            rectangle.setFill(Color.LIGHTBLUE);
            Label label = new Label("Fleet Destroyed. Total Missiles Fired: "
                    + battleshipModel.getTotalMissilesFired());
            label.setFont(new Font(20));
            label.setTextFill(Color.BLACK);
            label.setOpacity(1);
            rectangle.setOpacity(1);
            stackPane.getChildren().addAll(rectangle, label);
            gridPane.getChildren().add(stackPane);
            gridPane.setDisable(true);
        }

    }

    private void missileClickEvent() {
        gridPane.setOnMouseClicked(event -> {
            int x = GridPane.getRowIndex((Node) event.getTarget());
            int y = GridPane.getColumnIndex((Node) event.getTarget());

            Rectangle rectangle = new Rectangle(50, 50);

            battleshipController.setMissileCoordinates(x, y);
            battleshipController.change();

            boolean cellIsSunk = false;
            for (int[] coords : battleshipController.getSunkShipsCoords()) {
                if (coords[0] == x && coords[1] == y) {
                    cellIsSunk = true;
                    break;
                }
            }
            if (cellIsSunk && !battleshipController.isGameOver()) {
                for (int[] coords : battleshipController.getSunkShipsCoords()) {
                    Node sunkNode = getNode(gridPane, coords[1], coords[0]);
                    Rectangle recolourSunkNode = (Rectangle) sunkNode;
                    recolourSunkNode.setFill(Color.YELLOW);
                    recolourSunkNode.setStroke(Color.BLACK);

                }
            } else {
                if (battleshipController.isMissileHit()) {
                    rectangle.setFill(Color.RED);
                    rectangle.setStroke(Color.BLACK);

                } else {
                    if (battleshipController.isMissileMiss()) {
                        rectangle.setFill(Color.DARKBLUE);
                        rectangle.setStroke(Color.BLACK);

                    }
                }
                GridPane.setColumnIndex(rectangle, y);
                GridPane.setRowIndex(rectangle, x);

                gridPane.getChildren().add(rectangle);
            }
        });

    }


    private Node getNode(GridPane grid, int column, int row) {
        Node result = null;
        for (Node node : grid.getChildren()) {
            if (GridPane.getColumnIndex(node) == column
                    && GridPane.getRowIndex(node) == row) {
                result = node;
            }
        }
        return result;
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


    @Override
    public void start(Stage primaryStage) {
        battleshipModel = new BattleshipModel();
        battleshipController = new BattleshipController(battleshipModel);

        primaryStage.setTitle("Battleships MVC");
        GridPane gridPane = displayGrid();
        HBox menuPane = new HBox();

        Scene primaryScene = new Scene(gridPane,
                WINDOW_WIDTH, WINDOW_HEIGHT);

        Scene secondaryScene = new Scene(menuPane,
                WINDOW_WIDTH, WINDOW_HEIGHT);

        Button defaultFleetConfigurationButton = new Button("Load Default Fleet");
        Button customFleetConfigurationButton = new Button("Load Fleet From File");

        defaultFleetConfigurationButton.setOnMouseClicked(event -> {
            battleshipController.setShipConfigurationDecision(true);
            battleshipModel.loadShipConfiguration();
            primaryStage.setScene(primaryScene);
        });

        customFleetConfigurationButton.setOnMouseClicked(event -> {
            battleshipController.setShipConfigurationDecision(false);
            battleshipModel.loadShipConfiguration();
            primaryStage.setScene(primaryScene);
        });

        menuPane.getChildren().addAll(defaultFleetConfigurationButton, customFleetConfigurationButton);
        menuPane.setAlignment(Pos.CENTER);
        menuPane.setSpacing(10);

        primaryStage.setScene(secondaryScene);
        primaryStage.show();
        battleshipModel.addObserver(this);
        update(null, null);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
