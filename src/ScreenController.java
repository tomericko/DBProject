import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.List;

/**
 * Created by roi on 16/06/16.
 */
public class ScreenController {
    enum TYPE{DDL,DML};

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
    void initialize() throws Exception {
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

        ddlFileChooserTxt.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        // Dropping over surface
        ddlFileChooserTxt.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    String filePath = null;
                    for (File file:db.getFiles()) {
                        filePath = file.getAbsolutePath();
                        dmlFileChooserTxt.setText(filePath);
                        //System.out.println(filePath);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        dmlFileChooserTxt.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        // Dropping over surface
        dmlFileChooserTxt.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    String filePath = null;
                    for (File file:db.getFiles()) {
                        filePath = file.getAbsolutePath();
                        dmlFileChooserTxt.setText(filePath);
                        //System.out.println(filePath);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        final DBClient dbc = new DBClient();
        //DDL
        ddlSendBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ddlFileRadioBtn.isSelected()) {
                    executeScript(dbc, TYPE.DDL);
                } else {
                    try {
                        dbc.sendDDLQuery(ddlQueryTxt.getText());
                        ddlAnswerTxt.setText("Success");
                    } catch (Exception e) {
                        if(e.getMessage().contains("syntax")) {
                            new errorMsg("WRONG QUERY STRUCTURE", e.getMessage()).show();
                        }
                        else {
                            new errorMsg("LOGICAL ERROR", e.getMessage()).show();
                        }
                        ddlAnswerTxt.setText("Failed");
                    }
                }

            }
        });
        //DML
        dmlSendBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ddlFileRadioBtn.isSelected()) {
                    executeScript(dbc, TYPE.DML);
                } else {
                    try {
                        String answer = dbc.sendDMLQuery(dmlQueryTxt.getText());
                        dmlAnswerTxt.setText(answer);
                    } catch (Exception e) {
                        if(e.getMessage().contains("syntax")) {
                            new errorMsg("WRONG QUERY STRUCTURE", e.getMessage()).show();
                        }
                        else {
                            new errorMsg("LOGICAL ERROR", e.getMessage()).show();
                        }
                        dmlAnswerTxt.setText("Failed");
                    }
                }
            }
        });


}

        public void executeScript(DBClient dbc, TYPE type){
        try {
            if(!ddlFileChooserTxt.getText().isEmpty()){
                File script = new File(ddlFileChooserTxt.getText());
                List<String> commands = dbc.readScriptFromFile(script);
                int size = commands.size();
                for(int i =0; i<size ; i++) {
                    try {
                        if (type == TYPE.DDL) {
                            dbc.sendDDLQuery(commands.get(i));
                        } else {
                            dbc.sendDMLQuery(commands.get(i));
                        }
                    } catch (Exception e) {
                        if (e.getMessage().contains("syntax")) {
                            new errorMsg("WRONG QUERY STRUCTURE", e.getMessage(), commands.get(i)).show();
                        } else {
                            new errorMsg("LOGICAL ERROR", e.getMessage(), commands.get(i)).show();
                        }
                        return;
                    }
                }
                ddlAnswerTxt.setText("Success");
            }
        }
        catch (Exception e){
            ddlAnswerTxt.setText("Failed");
        }
    }


}

