package ImageToText.ImageText;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;



public class App extends JFrame{

	static String path="";
	 static JLabel label;
	    
	 
	 
    public static void gui(){
    	
    	final int dpi;
    final JFrame frame=new JFrame("Data Pirate");
    final JButton button ;
    final JButton button1;
   
	final JLabel label1;
    
    button = new JButton("browse file ");
    button.setBounds(300,35,100,20);
    button.setToolTipText("load image :- .png , .jpeg , .jpg , .pdf");
    frame.add(button);
    
    label = new JLabel();
    label.setBounds(10,80,600,600);
    frame.add(label);
    
    label1=new JLabel("Loading...");
    label1.setBounds(500, 40, 100, 40);
    label1.setFont(new Font("Verdana", Font.PLAIN, 20));
    label1.setForeground(Color.MAGENTA);
    label1.setVisible(false);
    frame.add(label1);
   
    
    final JLabel label2=new JLabel("Load");
    label2.setBounds(1000, 40, 100, 40);
    label2.setFont(new Font("Verdana", Font.PLAIN, 20));
    label2.setForeground(Color.MAGENTA);
    label2.setVisible(false);
    frame.add(label2);
    
    final JLabel caption1=new JLabel("( allow only .png , .jpeg , .jpg , .pdf )");
    caption1.setBounds(250, 50, 500, 40);
    caption1.setFont(new Font("Verdana", Font.PLAIN, 14));
    caption1.setForeground(Color.BLACK);
    caption1.setVisible(true);
    frame.add(caption1);
    
    
    final JLabel caption2=new JLabel("( Convert image to Text... )");
    caption2.setBounds(750, 50, 500, 40);
    caption2.setFont(new Font("Verdana", Font.PLAIN, 14));
    caption2.setForeground(Color.BLACK);
    caption2.setVisible(false);
    frame.add(caption2);
    
    
    button1 = new JButton("convert");
    button1.setBounds(800,35,100,20);
    button1.setToolTipText("convert image to text");
    button1.setVisible(false);
    frame.add(button1);
    
	final JTextArea edit = new JTextArea();
	edit.setBounds(700, 40, 100, 650);
   
	final JScrollPane scrollPane = new JScrollPane(edit);
    edit.setVisible(false);
	
	
	
    
    
    //dpi
    final JLabel dpi1=new JLabel("set dpi : ");
    dpi1.setBounds(250, 70, 100, 30);
    dpi1.setFont(new Font("Verdana", Font.PLAIN, 16));
    dpi1.setForeground(Color.BLACK);
    dpi1.setVisible(false);
    frame.add(dpi1);
    final JTextField dpi2=new JTextField(200);
    dpi2.setBounds(330, 70, 50, 30);
    dpi2.setForeground(Color.black);
    dpi2.setVisible(false);
    frame.add(dpi2);
    final JButton dpi3=new JButton();
    dpi3.setText("Go");
    dpi3.setBounds(400,70,50,30);
    dpi3.setToolTipText("convert image to text");
    dpi3.setVisible(false);
    frame.add(dpi3);
    



  
    button1.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
        	caption2.setText("Converted into Text...");
        	caption2.setFont(new Font("Verdana", Font.PLAIN, 25));
            edit.setText("");
            label2.setVisible(true);
        	
        	
        	try {
        		Detect d=new Detect();
				d.imagetotext(path);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	button1.setVisible(false);

        	edit.setVisible(true);
        	scrollPane.setVisible(true);
        	scrollPane.setBounds(650,80,600,600);
        	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        	scrollPane.setVisible(true);
        	
        	frame.add(scrollPane);
        	
            try
            {
            	File f=new File("outputocr.txt");
            	if(f.exists()) {
                FileReader reader = new FileReader( f );
                BufferedReader br = new BufferedReader(reader);
                edit.read( br, null );
                br.close();
                edit.requestFocus();
            	}
            	else {
            		System.out.println("file not exists");
            	}
            }
            catch(Exception e2) { System.out.println(e2); }
            File file = new File("outputocr.txt"); 
            label2.setVisible(false);
        }
        });
    
    button.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
        caption2.setText("Convert into Text...");
        caption2.setFont(new Font("Verdana", Font.PLAIN, 14));
        caption2.setVisible(false);
        button1.setVisible(false);
        scrollPane.setVisible(false);
        label.setVisible(false);
        
        button.setText("new file");
          edit.setText("");
          JFileChooser file = new JFileChooser();
          file.setCurrentDirectory(new File(System.getProperty("user.home")));
          //filter the files
          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png","pdf");
          file.addChoosableFileFilter(filter);
          label1.setVisible(true);
          int result = file.showSaveDialog(null);
           //if the user click on save in Jfilechooser
          if(result == JFileChooser.APPROVE_OPTION){
        	  caption1.setVisible(false);
              File selectedFile = file.getSelectedFile();
              path = selectedFile.getAbsolutePath();
              	if (path.contains(".pdf") || path.contains(".PDF")){
              		/*path=Detect.pdftoImage(path);
              		path=PreProcess.greyScale(path);
              		label.setIcon(ResizeImage(path));
              		*/
              		caption1.setVisible(false);
              		dpi1.setVisible(true);
              		dpi2.setVisible(true);
              		dpi3.setVisible(true);
              	
              		frame.add(label);
              	}
              	else {
              		System.out.println(path);
              		String path1=PreProcess.greyScale(path);
              		System.out.println(path1);
              		label.setIcon(ResizeImage(path1));
              		label1.setVisible(false);
              		frame.add(label);
              }
          }

          else if(result == JFileChooser.CANCEL_OPTION){
              System.out.println("No File Select");
          }
          label.setVisible(true);
          button1.setVisible(true);
          caption2.setVisible(true);
        }
    });

    
    dpi3.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
        //	dpi=Integer.parseInt(dpi2.getText());
      		caption1.setVisible(true);
      		dpi1.setVisible(false);
      		dpi2.setVisible(false);
      		dpi3.setVisible(false);
      		path=Detect.pdftoImage(path);
      		path=PreProcess.greyScale(path);
      		label.setIcon(ResizeImage(path));
      		
        }
    });


    frame.add(label2);
    frame.add(label1);
	frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    //setUndecorated(true);
    frame.setVisible(true);
    
    }

    
    
    

    public static void homePage() {
    	final JFrame page1=new JFrame();
    	ImageIcon imgThisImg = new ImageIcon("ntsicon.png");
    	JLabel icon=new JLabel();
    	icon.setIcon(imgThisImg);
    	icon.setBounds(400, 100, 700, 200);
    	page1.add(icon);
    	
       JButton button1 = new JButton("Start");
        button1.setBounds(550,400,100,20);
        button1.setToolTipText("convert image to text");
        button1.setVisible(true);
        page1.add(button1);
        
        
        JLabel label1=new JLabel("Image To Text ");
        label1.setBounds(500, 300, 500, 40);
        label1.setFont(new Font("Verdana", Font.PLAIN, 30));
        label1.setForeground(Color.MAGENTA);
        label1.setVisible(true);
        page1.add(label1);
        
        
        button1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	page1.setVisible(false);
             gui();
             
            }
            });
    	
    	page1.setLayout(null);
    	page1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	page1.setLocationRelativeTo(null);
    	page1.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //setUndecorated(true);
    	page1.setVisible(true);
        
    }
     // Methode to resize imageIcon with the same size of a Jlabel
    public static ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(),200);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
    
    public static void main(String[] args){
       // new App();
    	homePage();
           //gui();   
    }
   }


///////////////END