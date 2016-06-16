import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by roi on 16/06/16.
 */
public class ScreenController {

    Stage primaryStage;

    public ScreenController(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

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
    void initialize() {
        final ToggleGroup ddlTg = new ToggleGroup();
        ddlFileRadioBtn.setToggleGroup(ddlTg);
        ddlQueryRadioBtn.setToggleGroup(ddlTg);

        final ToggleGroup dmlTg = new ToggleGroup();
        dmlFileRadioBtn.setToggleGroup(dmlTg);
        dmlQueryRadioBtn.setToggleGroup(dmlTg);

        ddlAnswerTxt.setEditable(false);
        ddlFileChooserTxt.setEditable(false);
        dmlAnswerTxt.setEditable(false);
        dmlFileChooserTxt.setEditable(false);

        ddlFileRadioBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ddlTg.getSelectedToggle() == ddlFileRadioBtn)
                {
                    ddlQueryTxt.setDisable(true);
                    ddlFileChooserTxt.setDisable(false);
                    ddlFileChooserBtn.setDisable(false);
                }
                else
                {
                    ddlQueryTxt.setDisable(false);
                    ddlFileChooserTxt.setDisable(true);
                    ddlFileChooserBtn.setDisable(true);
                }
            }
        });

        ddlQueryRadioBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ddlTg.getSelectedToggle() == ddlQueryRadioBtn)
                {
                    ddlQueryTxt.setDisable(false);
                    ddlFileChooserTxt.setDisable(true);
                    ddlFileChooserBtn.setDisable(true);
                }
                else
                {
                    ddlQueryTxt.setDisable(true);
                    ddlFileChooserTxt.setDisable(false);
                    ddlFileChooserBtn.setDisable(false);
                }
            }
        });



        dmlQueryRadioBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (dmlTg.getSelectedToggle() == dmlQueryRadioBtn)
                {
                    dmlQueryTxt.setDisable(false);
                    dmlFileChooserTxt.setDisable(true);
                    dmlFileChooserBtn.setDisable(true);
                }
                else
                {
                    dmlQueryTxt.setDisable(true);
                    dmlFileChooserTxt.setDisable(false);
                    dmlFileChooserBtn.setDisable(false);
                }
            }
        });

        dmlFileRadioBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (dmlTg.getSelectedToggle() == dmlFileRadioBtn)
                {
                    dmlQueryTxt.setDisable(true);
                    dmlFileChooserTxt.setDisable(false);
                    dmlFileChooserBtn.setDisable(false);
                }
                else
                {
                    dmlQueryTxt.setDisable(false);
                    dmlFileChooserTxt.setDisable(true);
                    dmlFileChooserBtn.setDisable(true);
                }
            }
        });

        ddlTg.selectToggle(ddlFileRadioBtn);
        dmlTg.selectToggle(dmlFileRadioBtn);

        if (ddlTg.getSelectedToggle() == ddlFileRadioBtn)
        {
            ddlQueryTxt.setDisable(true);
            ddlFileChooserTxt.setDisable(false);
            ddlFileChooserBtn.setDisable(false);
        }
        else
        {
            ddlQueryTxt.setDisable(false);
            ddlFileChooserTxt.setDisable(true);
            ddlFileChooserBtn.setDisable(true);
        }

        if (dmlTg.getSelectedToggle() == dmlFileRadioBtn)
        {
            dmlQueryTxt.setDisable(true);
            dmlFileChooserTxt.setDisable(false);
            dmlFileChooserBtn.setDisable(false);
        }
        else
        {
            dmlQueryTxt.setDisable(false);
            dmlFileChooserTxt.setDisable(true);
            dmlFileChooserBtn.setDisable(true);
        }

        ddlFileChooserBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Text Files", "*.txt"));
                File selectedFile = fileChooser.showOpenDialog(primaryStage);
                ddlFileChooserTxt.setText(selectedFile.getAbsolutePath().toString());
            }
        });

        dmlFileChooserBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Text Files", "*.txt"));
                File selectedFile = fileChooser.showOpenDialog(primaryStage);
                dmlFileChooserTxt.setText(selectedFile.getAbsolutePath().toString());
            }
        });


        /*final DBClient dbc = new DBClient();
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
*/

    }

}
