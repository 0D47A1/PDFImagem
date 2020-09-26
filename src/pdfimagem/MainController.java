/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfimagem;

import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.sound.midi.Patch;

/**
 *
 * @author melqu
 */
public class MainController implements Initializable {

    @FXML
    private Pane drop_file;

    @FXML
    private Pane drop_file_drag;

    
    @FXML
    private JFXButton btn_select_pdf;
    @FXML
    public Pane pane_status;
    
    @FXML
    private Label link_autor;
    
    public static MainController controller;
    public List<File> pdf_drag = new ArrayList<>();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = this;

        btn_select_pdf.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            List<File> pdf = new ArrayList<>();
                   pdf.add(fileChooser.showOpenDialog((Stage) btn_select_pdf.getScene().getWindow()));
                   new PDFtoJPEG(pdf);
            
            pane_status.setVisible(true);

        });
        drop_file.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles()) {
                drop_file_drag.setVisible(true);
            }
        });

        drop_file_drag.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.COPY);

        });

        drop_file_drag.setOnDragExited(event -> {

            if (pdf_drag.size() == 0) {
                drop_file_drag.setVisible(false);
            }

        });

        drop_file_drag.setOnDragDropped(event -> {
            if (pdf_drag.size() == 0) {
                event.getDragboard().getFiles().forEach(file -> {
                 
                    if (Pattern.compile("([^\\s]+(\\.(?i)(pdf))$)").matcher(file.getName()).matches()) {                      
                        pdf_drag.add(file);
                    }
                });
                
                drop_file_drag.setVisible(false);
                
                if(pdf_drag.size() != 0){
                     pane_status.setVisible(true);
                    new PDFtoJPEG(pdf_drag);
                  
                
                }
                

            }

        });

    }

    @FXML
    private void go_autor(MouseEvent event) throws MalformedURLException, URISyntaxException, IOException {
        
        URL url = new URL("https://github.com/Melksedeklobo/PDFImagem");
        
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(url.toURI());
        
    }

}
