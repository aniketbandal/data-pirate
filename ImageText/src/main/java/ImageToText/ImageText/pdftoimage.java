package ImageToText.ImageText;

		import java.awt.image.BufferedImage;
		import java.io.File;
		import java.util.List;

		import javax.imageio.ImageIO;

		import org.apache.pdfbox.pdmodel.PDDocument;
		import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

		@SuppressWarnings("unchecked")
		public class pdftoimage {
		    public static void main(String[] args) {
		    	System.out.println(pdftoimage.pdfToPng("try.pdf",220));
		    }
		/*    public static String pdfToImage(String filepath) {
		        try {
		       // String sourceDir = "C:\\Users\\anike\\Desktop\\Java_imagetotext\\new\\pdftoimage\\OriginalDocument.pdf"; // Pdf files are read from this folder
		        String destinationDir = ""; // converted images from pdf document are saved here
		        String sourceDir = filepath;
		        
		        File sourceFile = new File(sourceDir);
		        File destinationFile = new File(destinationDir);
		        if (!destinationFile.exists()) {
		            destinationFile.mkdir();
		           // System.out.println("Folder Created -> "+ destinationFile.getAbsolutePath());
		        }
		        if (sourceFile.exists()) {
		            //System.out.println("Images copied to Folder: "+ destinationFile.getName());             
		            PDDocument document = PDDocument.load(sourceDir);
		            List<PDPage> list = document.getDocumentCatalog().getAllPages();
		            //System.out.println("Total files to be converted -> "+ list.size());

		            String fileName = sourceFile.getName().replace(".pdf", "");             
		            int pageNumber = 1;
		            for (PDPage page : list) {
		                BufferedImage image = page.convertToImage();
		                File outputfile = new File(destinationDir +"demo.png");
		              //  System.out.println("Image Created -> "+ outputfile.getName());
		                ImageIO.write(image, "png", outputfile);
		                pageNumber++;
		            }
		            document.close();
		            System.out.println("Converted Images are saved at -> "+ destinationFile.getAbsolutePath());
		        } else {
		            System.err.println(sourceFile.getName() +" File not exists");
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
				String imagePath="demo.png";
				return imagePath ;
		}
		    */
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