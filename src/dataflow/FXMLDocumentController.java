/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataflow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Shreyas
 */

// shreyasdobhal@gmail.com

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Label txtDisplay;
    
    private boolean fx=false;
    
    @FXML
    private void onLoginButton(ActionEvent evt) {
        String user=txtUsername.getText();
        String pass=txtPassword.getText();
        System.out.println("Username "+user);
        System.out.println("Password "+pass);
        
        Database db=new Database();
        boolean result=db.loginRequest(user, pass);
        if (result) {
            System.out.println("Logged in");
            openMainPage();
            //txtDisplay.getScene().getWindow().hide();
            ((Stage)txtDisplay.getScene().getWindow()).close();
        }
        else {
            System.out.println("Failed");
            txtDisplay.setText("Password or Username Incorrect !");
        }
    }
    
    @FXML
    private void onSignButton(ActionEvent evt) {
        System.out.println("Login "+txtUsername.getText());
    }
    
    private void openMainPage() {
        if (fx) {
            try {
                Stage stage=new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            catch (Exception e) {

            }
        }
        MainWindow mwin=new MainWindow();
        mwin.setVisible(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
