/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Marcelo
 */
public class Imagenes {
    
    public static BufferedImage leerImgURL(String imgURL){
        File imgF = new File(imgURL);
        try {
            return ImageIO.read(imgF);
        } catch (IOException ex) {
            return null;
        }
    }
    
}
