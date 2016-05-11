/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Marcelo
 */
public class ImagenesServidor {
    
    public static String urlWS;
    public static String urlImgs;
    public static String urlImgsPr;
    
    public static BufferedImage leerImgURL(String imgURL){
        File imgF = new File(imgURL);
        try {
            return ImageIO.read(imgF);
        } catch (IOException ex) {
            return null;
        }
    }
    
    public static String escribirImagen(BufferedImage img, String nombre1, String nombre2, String tipo) throws IOException{
        /* GUARDA LA IMAGEN */
        
            String workingDir;
            String uploadFilePath = "";

                workingDir = System.getProperty("user.home") + urlImgs;
            
                switch (tipo){
                    case "cliente":
                        uploadFilePath = workingDir + "/QuickOrder/Clientes";
                        crearDirectorio(uploadFilePath);
                        ImageIO.write(img, "png",new File(uploadFilePath + "/" + nombre1 + "_perfil.png"));
                        break;
                    
                    case "restaurante":
                        uploadFilePath = workingDir + "/QuickOrder/Restaurantes/" + nombre1;
                        crearDirectorio(uploadFilePath);
                        ImageIO.write(img, "png",new File(uploadFilePath + "/" + nombre1 + "_" + nombre2 + "_restaurante.png"));
                        break;
                        
                    case "individual":
                        uploadFilePath = workingDir + "/QuickOrder/Restaurantes/" + nombre1;
                        crearDirectorio(uploadFilePath);
                        ImageIO.write(img, "png",new File(uploadFilePath + "/" + nombre2 + "_indi.png"));
                        break;
                        
                    case "promocion":
                        uploadFilePath = workingDir + "/QuickOrder/Restaurantes/" + nombre1;
                        crearDirectorio(uploadFilePath);
                        ImageIO.write(img, "png",new File(uploadFilePath + "/" + nombre2 + "_promo.png"));
                        break;
                }
                return uploadFilePath;
    }
    
    public static void crearDirectorio(String path){
        /*Crea la ruta si no existe*/
        File fileSaveDir = new File(path);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        /*******************/
    }
    
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        
        if (image != null){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                ImageIO.write(image, type, bos);
                byte[] imageBytes = bos.toByteArray();

                BASE64Encoder encoder = new BASE64Encoder();
                imageString = encoder.encode(imageBytes);

                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else imageString = "";
        return imageString;
    }
    
    public static BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        if (!"".equals(imageString)){
            byte[] imageByte;
            try {
                BASE64Decoder decoder = new BASE64Decoder();
                imageByte = decoder.decodeBuffer(imageString);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                image = ImageIO.read(bis);
                bis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return image;
    }
    
    
    public static void cargarConf() throws IOException {
        InputStream inputStream = null;
        String propFileName = System.getProperty("user.home") + "/config.properties";
        try {
            
            Properties prop = new Properties();
            inputStream = new FileInputStream(propFileName);
            prop.load(inputStream);
            String hn = prop.getProperty("hostName");
            if (hn == null) hn = InetAddress.getLocalHost().getHostName();
            String prt = prop.getProperty("port");
            if (prt == null) prt = "9128";
            String imgs = prop.getProperty("URLimgs");
            if (imgs == null) imgs = "";
            String imgsPr = prop.getProperty("URLimgsPr");
            if (imgsPr == null) 
                imgsPr = System.getProperty("user.dir"); 
            else imgsPr = System.getProperty("user.home") + "/" + imgsPr;
            
            urlWS = hn + ":" + prt;
            urlImgs = imgs;
            urlImgsPr = imgsPr;
            
        } catch (FileNotFoundException ex) {
            try (PrintWriter writer = new PrintWriter(propFileName, "UTF-8")) {
                //Si no se encuentra el archivo
                writer.println("hostName=" + InetAddress.getLocalHost().getHostName()+ "\nport=9128\nURLimages=\n");
                urlWS = InetAddress.getLocalHost().getHostName() + ":" + "9128";
                urlImgs = "";
                urlImgsPr = System.getProperty("user.dir");
            }
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ImagenesServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

