import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class BattleshipGUIView extends Application implements Observer {

    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 400;

    private BattleshipModel battleshipModel;
//    private BattleshipController battleshipController;

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        battleshipModel = new BattleshipModel();

        primaryStage.setTitle("Battleships MVC");

        GridPane root = new GridPane();

        for(int y = 0; y < 10; y++){
            for(int x = 0; x < 10; x++){



                // Create a new TextField in each Iteration
                TextField tf = new TextField();
                tf.setPrefHeight(50);
                tf.setPrefWidth(50);
                tf.setAlignment(Pos.CENTER);
                tf.setEditable(false);
                tf.setText("~");

                // Iterate the Index using the loops
                root.setRowIndex(tf,y);
                root.setColumnIndex(tf,x);
                root.getChildren().add(tf);
            }
        }
        Scene scene = new Scene(root,
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
