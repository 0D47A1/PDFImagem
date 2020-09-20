/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfimagem;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author melqu
 */
public class PDFImagem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        
     
        JFXDecorator decorator = new JFXDecorator(stage, root, false, false, true);;
        decorator.setCustomMaximize(false);
     
        Scene scene = new Scene(decorator);
        scene.getStylesheets().add(getClass().getResource("Theme.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("PDFImagem"); 
     
     
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}