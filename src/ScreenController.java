import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        //DDL
        ddlSendBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ddlFileRadioBtn.isSelected()) {
                    executeScript(dbc);
                } else {
                    try {
                        dbc.sendDDLQuery(ddlQueryTxt.getText());
                        ddlAnswerTxt.setText("Success");
                    } catch (Exception e) {
                        ddlAnswerTxt.setText("Failed");
                    }
                }

            }
        });
        //DML
        dmlSendBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    String answer = dbc.sendDMLQuery(dmlQueryTxt.getText());
                    dmlAnswerTxt.setText(answer);
                } catch (Exception e) {
                    dmlAnswerTxt.setText("Failed");
                }
            }
        });
    }

        public void executeScript(DBClient dbc){
        try {
            if(!ddlFileChooserTxt.getText().isEmpty()){
                File script = new File(ddlFileChooserTxt.getText());
                List<String> commands = dbc.readScriptFromFile(script);
                int size = commands.size();
                for(int i =0; i<size ; i++)
                    dbc.sendDDLQuery(commands.get(i));
                ddlAnswerTxt.setText("Success");
            }
        }
        catch (Exception e){
            ddlAnswerTxt.setText("Failed");
        }
    }


}
