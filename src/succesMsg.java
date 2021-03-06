

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by roi on 11/01/16.
 */
public class succesMsg implements Runnable{
    String tag;
    String msg;

    public succesMsg(String tag, String msg) {
        this.msg = msg;
        this.tag = tag;
    }

    public void show() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("succesMsg.fxml"));
            succesMsgController controller = new succesMsgController(this.tag, this.msg);
            loader.setController(controller);
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Success");
        Scene scene = new Scene(root, 250, 150);
        scene.getStylesheets().add(getClass().getResource("succesMsg.css").toExternalForm());
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();



    }

    @Override
    public void run() {
        show();
    }
}
