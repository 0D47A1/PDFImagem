/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfimagem;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import static pdfimagem.MainController.controller;

/**
 *
 * @author melqu
 */
public class PDFtoJPEG {

    private Integer index = 0;

    public PDFtoJPEG(List<File> files) {

        new Thread() {
            @Override
            public void run() {

                files.forEach(file -> {
                    File Diretorio = new File(file.getParent() + "\\" + file.getName().replace(".pdf", "") + " Convertido");
                    Diretorio.mkdir();

                    try {

                        PDDocument document = PDDocument.load(file);
                        PDFRenderer PDFRenderer = new PDFRenderer(document);

                        PDPageTree PageList = document.getDocumentCatalog().getPages();

                        PageList.forEach((Page) -> {
                            try {
                                BufferedImage pdf_image = PDFRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
                                ImageIOUtil.writeImage(pdf_image, Diretorio.getPath() + "\\Pagina " + index + ".jpg", 300);
                                index++;

                            } catch (IOException ex) {
                                Logger.getLogger(PDFtoJPEG.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(Diretorio);

                    } catch (IOException ex) {
                        Logger.getLogger(PDFtoJPEG.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    System.out.println(file.getParent() + "\\" + file.getName().replace(".pdf", ""));
                });

                controller.pane_status.setVisible(false);
                controller.pdf_drag.clear();
                
                System.out.println(controller.pdf_drag.size());
            }
        }.start();

    }

}
