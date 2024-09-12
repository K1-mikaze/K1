package interfaces.company_remake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class LogIn extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LogIn-view.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT); //This remove application brinks
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        //root.setOnMouseDragged(mouseEvent -> {
          //stage.setX(mouseEvent.getSceneX());
            //stage.setY(mouseEvent.getSceneY());
        //});
    }

    public static void main(String[] args) {
        launch();
    }
}