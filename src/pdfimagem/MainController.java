/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfimagem;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author melqu
 */
public class MainController implements Initializable {

    @FXML
    private Pane drop_file;

    @FXML
    private Pane drop_file_drag;

    private List<File> pdf_drag = new ArrayList<>();
    @FXML
    private JFXButton btn_select_pdf;
    @FXML
    private Pane drop_file_drag1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        btn_select_pdf.setOnAction(event ->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.showOpenDialog((Stage) btn_select_pdf.getScene().getWindow());
        
        });
        drop_file.setOnDragOver( event -> {
            if (event.getDragboard().hasFiles()) {
                drop_file_drag.setVisible(true);
            }
        });

        drop_file_drag.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.COPY);

        });

        drop_file_drag.setOnDragExited( event -> {
            System.out.println(pdf_drag.size());
            if (pdf_drag.size() == 0) {
                drop_file_drag.setVisible(false);
            }

        });

        drop_file_drag.setOnDragDropped(event -> {
            if (pdf_drag.size() == 0) {
                pdf_drag.addAll(event.getDragboard().getFiles());
            }

        });

    }

}
