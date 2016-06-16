import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by roi on 16/06/16.
 */

public class Screen extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Screen.fxml"));
        //loader.setController(new ScreenController());
        Parent root = loader.load(getClass().getResource("Screen.fxml"));
        primaryStage.setTitle("DB-Project");
        primaryStage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(root, 700, 350);
        scene.getStylesheets().add(getClass().getResource("Screen.css").toExternalForm());
        primaryStage.setScene(scene);
        //primaryStage.setResizable(false);
        primaryStage.show();
    }
}