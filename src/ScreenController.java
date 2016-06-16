import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Created by roi on 16/06/16.
 */
public class ScreenController {

    @FXML
    ImageView dbLogo;

    @FXML
    ImageView ddlLogo;

    @FXML
    RadioButton ddlFileRadioBtn;

    @FXML
    TextField ddlFileChooserTxt;

    @FXML
    Button ddlFileChooserBtn;

    @FXML
    RadioButton ddlQueryRadioBtn;

    @FXML
    TextField ddlQueryTxt;

    @FXML
    Button ddlSendBtn;

    @FXML
    ImageView dmlLogo;

    @FXML
    RadioButton dmlFileRadioBtn;

    @FXML
    TextField dmlFileChooserTxt;

    @FXML
    Button dmlFileChooserBtn;

    @FXML
    RadioButton dmlQueryRadioBtn;

    @FXML
    TextField dmlQueryTxt;

    @FXML
    Button dmlSendBtn;

    @FXML
    TextField ddlAnswerTxt;

    @FXML
    TextField dmlAnswerTxt;

    @FXML
    public void initialize() throws Exception {
        final DBClient dbc = new DBClient();
        ddlSendBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    dbc.sendDDLQuery(ddlQueryTxt.getText());
                    ddlAnswerTxt.setText("Success");
                }
                catch (Exception e) {
                    ddlAnswerTxt.setText("Failed");
                }
            }
        });
        dmlSendBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    String answer = dbc.sendDMLQuery(dmlQueryTxt.getText());
                    dmlAnswerTxt.setText(answer);
                }
                catch (Exception e) {
                    dmlAnswerTxt.setText("Failed");
                }
            }
        });
    }

}
