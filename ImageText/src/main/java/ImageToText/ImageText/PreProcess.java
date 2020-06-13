package ImageToText.ImageText;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PreProcess {
	public static String greyScale(String filepath) {
		File output = new File("greyscale.png");
        try {

            File input = new File(filepath);
            BufferedImage image = ImageIO.read(input);

            BufferedImage result = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);

            Graphics2D graphic = result.createGraphics();
            graphic.drawImage(image, 0, 0, Color.WHITE, null);

            for (int i = 0; i < result.getHeight(); i++) {
                for (int j = 0; j < result.getWidth(); j++) {
                    Color c = new Color(result.getRGB(j, i));
                    int red = (int) (c.getRed() * 0.299);
                    int green = (int) (c.getGreen() * 0.587);
                    int blue = (int) (c.getBlue() * 0.114);
                    Color newColor = new Color(
                            red + green + blue,
                            red + green + blue,
                            red + green + blue);
                    result.setRGB(j, i, newColor.getRGB());
                }
            }

            //File output = new File("greyscale.png");
            ImageIO.write(result, "png", output);
            System.out.println("successfully convert in greyscale");

        }  catch (IOException e) {
            e.printStackTrace();
        }
		return output.getAbsolutePath();

    }


    public static String pdfToPng(String pdffile,int dpi) {
    	String resultfile;
        try {
            String sourceDir = pdffile; // Pdf files are read from this folder
            //String destinationDir = "C:\\Users\\mandy\\OneDrive\\Pictures\\Converted_PdfFiles_to_Image/"; // converted images from pdf document are saved here
            String destinationDir = ""; // converted images from pdf document are saved here
            File sourceFile = new File(sourceDir);
            File destinationFile = new File(destinationDir);
            if (!destinationFile.exists()) {
                destinationFile.mkdir();
                System.out.println("Folder Created -> "+ destinationFile.getAbsolutePath());
            }
            if (sourceFile.exists()) {
                System.out.println("Images copied to Folder Location: "+ destinationFile.getAbsolutePath());
                PDDocument document = PDDocument.load(sourceFile);
                PDFRenderer pdfRenderer = new PDFRenderer(document);
                int numberOfPages = document.getNumberOfPages();
                System.out.println("Total files to be converting -> "+ numberOfPages);
                String fileName = sourceFile.getName().replace(".pdf", "");
                String fileExtension= "png";
                /*
                 * 600 dpi give good image clarity but size of each image is 2x times of 300 dpi.
                 * Ex:  1. For 300dpi 04-Request-Headers_2.png expected size is 797 KB
                 *      2. For 600dpi 04-Request-Headers_2.png expected size is 2.42 MB
                 */
                // use less dpi for to save more space in harddisk. For professional usage you can use more than 300dpi
                for (int i = 0; i < numberOfPages; ++i) {
                    File outPutFile = new File("demo.png");
                    BufferedImage bImage = pdfRenderer.renderImageWithDPI(i, dpi, ImageType.RGB);
                    ImageIO.write(bImage, fileExtension, outPutFile);
                }
                document.close();
                pdffile=destinationFile.getAbsolutePath();
                System.out.println("Converted Images are saved at -> "+ destinationFile.getAbsolutePath());
            } else {
                System.err.println(sourceFile.getName() +" File not exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdffile;
    }



}
