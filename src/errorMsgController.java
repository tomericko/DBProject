import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by roi on 11/01/16.
 */
public class errorMsgController {
    String tag;
    String msg;
    String query;

    public errorMsgController(String tag, String msg) {
        this.tag = tag;
        this.msg = msg;
        query = null;
    }

    public errorMsgController(String tag, String msg, String query) {
        this.tag = tag;
        this.msg = msg;
        this.query = query;
    }

    @FXML
    Label lblQuery;

    @FXML
    Label lblError;

    @FXML
    Label titleError;

    @FXML
    Button btnError;

    @FXML
    void initialize(){
        titleError.setText(this.tag);
        if(this.query != null) {
            lblQuery.setText("Script in line:" + this.query);
        }
        lblError.setText(this.msg);
        btnError.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((Stage)(btnError.getScene().getWindow())).close();
            }
        });
        lblError.setPrefWidth(450);
        lblError.setWrapText(true);
        lblQuery.setPrefWidth(450);
        lblQuery.setWrapText(true);
    }
}
