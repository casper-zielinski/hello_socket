package at.fhj.msd.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainClientSceneController {

    @FXML
    private Button btn_client;

    @FXML
    private Label lb_clienttext;

    @FXML
    private TextField tf_send;

    @FXML
    void btn_send(ActionEvent event) {
       Client client = new Client ("localhost", 1234);
            String response = client.ask(tf_send.getText());
            lb_clienttext.setText(response);
    }

}
