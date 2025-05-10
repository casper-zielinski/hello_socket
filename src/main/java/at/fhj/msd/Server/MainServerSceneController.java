package at.fhj.msd.Server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MainServerSceneController {

    public Server server;

    @FXML
    private Button btn_connect;

    @FXML
    private Button btn_disconnect;

    @FXML
    private Label lb_servertext;

    @FXML
    private TextArea ta_receive;

    @FXML
    void btn_onClickDisconnect(ActionEvent event) {
       server.setRunning(false); 
    }

    @FXML
    void btn_onClickconnect(ActionEvent event) throws InterruptedException {
        server = new Server(1234);
        server.ReadingfromClient();
    }

}