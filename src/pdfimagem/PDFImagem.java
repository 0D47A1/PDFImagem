/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfimagem;

import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyph;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        ImageView icon = new ImageView("/img/PDFImagem_icon.png");
        icon.setFitWidth(30);
        icon.setFitHeight(30);
        decorator.setGraphic(icon);
        Scene scene = new Scene(decorator);
        scene.getStylesheets().add(getClass().getResource("Theme.css").toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(new Image("/img/PDFImagem_icon.png"));
        stage.setTitle("PDFImagem 1.0"); 
     
     
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
