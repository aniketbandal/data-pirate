package ImageToText.ImageText;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;



public class App extends JFrame{

	static String path="";
	 static JLabel label;
	    
	 
	 
    public static void gui(){
    	
    	final int dpi;
    final JFrame frame=new JFrame("Data Pirate");
    try {
        frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("back.jpg")))));
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    final JButton button ;
    final JButton button1;
   
	final JLabel label1;
    
    button = new JButton();
    button.setBounds(300,15,100,50);
    //button.setBackground(Color.blue);
   // button.setBorderPainted(false);
    //button.setFocusPainted(false);
    button.setContentAreaFilled(false);
    ImageIcon MyImage1 = new ImageIcon("upload.png");
    Image img1 = MyImage1.getImage();
    Image newImg1 = img1.getScaledInstance(button.getWidth(), button.getHeight(),200);
    ImageIcon image1 = new ImageIcon(newImg1);
    button.setIcon(image1);
    button.setToolTipText("load image :- .png , .jpeg , .jpg , .pdf");
    frame.add(button);
    
    label = new JLabel();
    label.setBounds(10,100,600,600);
    frame.add(label);
    
   /* label1=new JLabel("Loading...");
    label1.setBounds(500, 40, 100, 40);
    label1.setFont(new Font("Verdana", Font.PLAIN, 20));
    label1.setForeground(Color.MAGENTA);
    label1.setVisible(false);
    frame.add(label1);
   */
    
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
    
    
    final JLabel caption2=new JLabel("( Convert into Text )");
    caption2.setBounds(790, 50, 500, 40);
    caption2.setFont(new Font("Verdana", Font.PLAIN, 14));
    caption2.setForeground(Color.BLACK);
    caption2.setVisible(false);
    frame.add(caption2);
    
    final JLabel caption3=new JLabel(" Text -> Classify ");
    caption3.setBounds(1190, 50, 500, 40);
    caption3.setFont(new Font("Verdana", Font.PLAIN, 14));
    caption3.setForeground(Color.BLACK);
    caption3.setVisible(false);
    frame.add(caption3);
    
    button1 = new JButton("convert");
    button1.setBounds(800,15,100,50);
   // button1.setBackground(Color.blue);
    //button1.setBorderPainted(false);
    button1.setFocusPainted(false);
    button1.setContentAreaFilled(false);
    ImageIcon MyImage2 = new ImageIcon("convert.png");
    Image img2 = MyImage2.getImage();
    Image newImg2 = img2.getScaledInstance(button1.getWidth(), button1.getHeight(),200);
    ImageIcon image2 = new ImageIcon(newImg2);
    button1.setIcon(image2);
    button1.setToolTipText("convert image to text");
    button1.setVisible(false);
    frame.add(button1);
    
    
    final JButton button2 = new JButton();
    button2.setBounds(1200,15,100,50);
    button2.setBackground(Color.blue);
    //button2.setBorderPainted(false);
    button2.setFocusPainted(false);
    button2.setContentAreaFilled(false);
    ImageIcon MyImage3 = new ImageIcon("classify.png");
    Image img3 = MyImage3.getImage();
    Image newImg3 = img3.getScaledInstance(button2.getWidth(), button2.getHeight(),200);
    ImageIcon image3 = new ImageIcon(newImg3);
    button2.setIcon(image3);
    button2.setToolTipText("clasify data");
    button2.setVisible(false);
    frame.add(button2);
    
	final JTextArea edit = new JTextArea();
	edit.setBounds(700, 120, 100, 650);
   
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
        	caption3.setVisible(true);
        	caption2.setText("Transformed Document ");
        	caption2.setFont(new Font("Verdana", Font.BOLD, 27));
        	caption2.setForeground(Color.BLACK);
        	caption2.setBounds(750, 30, 500, 40);
            edit.setText("");
            label2.setVisible(true);
            button1.setBounds(600,15,100,50);
        	
        	
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
        	//button1.setVisible(false);

        	edit.setVisible(true);
        	scrollPane.setVisible(true);
        	scrollPane.setBounds(650,100,600,600);
        	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        	scrollPane.setVisible(true);
        	button2.setVisible(true);
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
        	caption2.setBounds(790, 50, 500, 40);
        	button1.setBounds(800,15,100,50);
        	caption3.setVisible(false);
        	button2.setVisible(false);
        caption2.setText("Convert -> Text.");
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
    //      label1.setVisible(true);
          int result = file.showSaveDialog(null);
           //if the user click on save in Jfilechooser
          if(result == JFileChooser.APPROVE_OPTION){
        	 // caption1.setVisible(false);
              File selectedFile = file.getSelectedFile();
              path = selectedFile.getAbsolutePath();
              	if (path.contains(".pdf") || path.contains(".PDF")){
              		path=Detect.pdftoImage(path);
              		path=PreProcess.greyScale(path);
              		label.setIcon(ResizeImage(path));
              		
              	//	caption1.setVisible(false);
              	/*	dpi1.setVisible(true);
              		dpi2.setVisible(true);
              		dpi3.setVisible(true);
              	*/
              		frame.add(label);
              	}
              	else {
              		System.out.println(path);
              		String path1=PreProcess.greyScale(path);
              		System.out.println(path1);
              		label.setIcon(ResizeImage(path1));
              		label.setVisible(false);
              		
              		frame.add(label);
              }
          }

          else if(result == JFileChooser.CANCEL_OPTION){
              System.out.println("No File Select");
          }
          caption1.setBounds(250, 30, 500, 40);
          caption1.setText("Image File");
      	caption1.setFont(new Font("Verdana", Font.BOLD, 27));
      	caption1.setForeground(Color.BLACK);
          
          label.setVisible(true);
          button1.setVisible(true);
          caption2.setVisible(true);
          button.setBounds(10,15,100,50);
        }
        
    });

    
    button2.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
        	caption3.setVisible(false);
        	 String result = "";
        	try {
				result=Detect.predict(Detect.result("outputOcr.txt"));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
          clasify( clasify.classified(result));//clasified();
      		
        }
    });


    frame.add(label2);
 //   frame.add(label1);
	frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    //setUndecorated(true);
    frame.setVisible(true);
    
    }

    /*
DateOfBirth  08-03-1973
State  OH
Home_Phone  6153215432
Cell_Phone  6153215431
Alternate_name  Brian Williams
Relationship  spouse
Cell_Phone  53317
*/
    
    

    public static void homePage() {
    	final JFrame page1=new JFrame("PicturePerfect -Data Pirate");
        try {
            page1.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("back.jpg")))));
           // img3.getScaledInstance(button1.getWidth(), page1.getHeight(),20 img3.getScaledInstance(page1.getWidth(), page1.getHeight(),200);0);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    	ImageIcon imgThisImg = new ImageIcon("ntsicon.png");
    	JLabel icon=new JLabel();
    	icon.setIcon(imgThisImg);
    	icon.setBounds(430, 100, 700, 200);
    	page1.add(icon);
    	
       JButton button1 = new JButton("Start");
        button1.setBounds(570,400,100,70);
        button1.setFont(new Font("Verdana", Font.BOLD, 18));
        button1.setContentAreaFilled(false);
        button1.setToolTipText("convert image to text");
        button1.setVisible(true);
        page1.add(button1);
        
        
        JLabel label1=new JLabel("Image/Pdf Converter ");
        label1.setBounds(480, 300, 500, 40);
        label1.setFont(new Font("Verdana", Font.BOLD, 30));
        label1.setForeground(Color.gray);
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
    
    public static void clasify(HashMap<String, String> out) {
    /*	String name="";
    	String[] r1 = result.split("\n");
    	for(int i=0;i<=r1.length;i++) {
    		String[] r2 =r1[i].split(":");
    		if(r2[0].contains("name"))
    			name=r2[1];
    	}
    	*/
    	
    	
    	JFrame page2=new JFrame(); 
    	  try {
              page2.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("back.jpg")))));
          } catch (IOException e) {
              e.printStackTrace();
          }
    	JLabel l1=new JLabel("Name :-");
    	l1.setBounds(100, 100, 200, 50);
    	l1.setVisible(true);
    	page2.add(l1);
    	
    	final JTextArea edit = new JTextArea();
    	edit.setText(out.get("name"));
    	//edit.setText(result);
       
    	final JScrollPane s1 = new JScrollPane(edit);
    	s1.setBounds(250, 100, 200, 50);
        edit.setVisible(true);
    	page2.add(s1);
    	
    	
    	JLabel l2=new JLabel("Date of Birth :-");
    	l2.setBounds(600, 100, 200, 50);
    	l1.setVisible(true);
    	page2.add(l2);
    	
    	final JTextArea edit1 = new JTextArea();
    	edit1.setText(out.get("date"));
       
    	final JScrollPane s2 = new JScrollPane(edit1);
    	s2.setBounds(750, 100, 200, 50);
        edit1.setVisible(true);
    	page2.add(s2);
    	
    	
    	JLabel l3=new JLabel("City :-");
    	l3.setBounds(100, 200, 200, 50);
    	l3.setVisible(true);
    	page2.add(l3);
    	
    	final JTextArea edit3 = new JTextArea();
    	edit3.setText(out.get("city"));
       
    	final JScrollPane s3 = new JScrollPane(edit3);
    	s3.setBounds(250, 200, 200, 50);
        edit3.setVisible(true);
    	page2.add(s3);
    	
    	
    	JLabel l4=new JLabel("State :-");
    	l4.setBounds(600, 200, 200, 50);
    	l4.setVisible(true);
    	page2.add(l4);
    	
    	final JTextArea edit4 = new JTextArea();
    	edit4.setText(out.get("state"));
       
    	final JScrollPane s4 = new JScrollPane(edit4);
    	s4.setBounds(750, 200, 200, 50);
        edit4.setVisible(true);
    	page2.add(s4);
    	
    	
    	
    	JLabel l5=new JLabel("Email :-");
    	l5.setBounds(100, 300, 200, 50);
    	l5.setVisible(true);
    	page2.add(l5);
    	
    	final JTextArea edit5 = new JTextArea();
    	edit5.setText(clasify.getEmail("outputOcr.txt"));
       
    	final JScrollPane s5 = new JScrollPane(edit5);
    	s5.setBounds(250, 300, 200, 50);
        edit5.setVisible(true);
    	page2.add(s5);
    	
    	
    	JLabel l6=new JLabel("Relationship :-");
    	l6.setBounds(600, 300, 200, 50);
    	l6.setVisible(true);
    	page2.add(l6);
    	
    	final JTextArea edit6 = new JTextArea();
    	edit6.setText(out.get("relation"));
       
    	final JScrollPane s6 = new JScrollPane(edit6);
    	s6.setBounds(750, 300, 200, 50);
        edit6.setVisible(true);
    	page2.add(s6);
    	
    	
    	
    	JLabel l7=new JLabel("Phone no :-");
    	l7.setBounds(100, 400, 200, 50);
    	l7.setVisible(true);
    	page2.add(l7);
    	
    	final JTextArea edit7 = new JTextArea();
    	edit7.setText(out.get("phone"));
       
    	final JScrollPane s7 = new JScrollPane(edit7);
    	s7.setBounds(250, 400, 200, 50);
        edit7.setVisible(true);
    	page2.add(s7);
    	
    	
    	JLabel l8=new JLabel("zip :-");
    	l8.setBounds(600, 400, 200, 50);
    	l8.setVisible(true);
    	page2.add(l8);
    	
    	final JTextArea edit8 = new JTextArea();
    	edit8.setText(out.get("zip"));
       
    	final JScrollPane s8 = new JScrollPane(edit8);
    	s8.setBounds(750, 400, 200, 50);
        edit8.setVisible(true);
    	page2.add(s8);
    	
       	l1.setFont(new Font("Verdana", Font.BOLD, 13));
       	l2.setFont(new Font("Verdana", Font.BOLD, 13));
       	l3.setFont(new Font("Verdana", Font.BOLD, 13));
       	l4.setFont(new Font("Verdana", Font.BOLD, 13));
       	l5.setFont(new Font("Verdana", Font.BOLD, 13));
       	l6.setFont(new Font("Verdana", Font.BOLD, 13));
       	l7.setFont(new Font("Verdana", Font.BOLD, 13));
       	l8.setFont(new Font("Verdana", Font.BOLD, 13));
       	JLabel l=new JLabel("Classified Data");
    	l.setFont(new Font("Verdana", Font.BOLD, 40));
    	l.setForeground(Color.BLACK);
       	l.setBounds(420, 20, 500, 50);
       	l.setVisible(true);
       	page2.add(l);
    	
    	page2.setLayout(null);
    	page2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	page2.setLocationRelativeTo(null);
    	page2.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //setUndecorated(true);
    	page2.setVisible(true);
    }
    public static void tryy() {
    	JFrame page2=new JFrame(); 
   	 /* try {
             page2.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("back1.png")))));
         } catch (IOException e) {
             e.printStackTrace();
         }*/

    	
   	JLabel l1=new JLabel("Name :-");
   	l1.setBounds(150, 100, 200, 50);
   	l1.setVisible(true);
   	page2.add(l1);
   	
   	final JTextArea edit = new JTextArea();
 //  	edit.setText(out.get("name"));
   	//edit.setText(result);
      
   	final JScrollPane s1 = new JScrollPane(edit);
   	s1.setBounds(250, 100, 200, 50);
       edit.setVisible(true);
   	page2.add(s1);
   	
   	
   	JLabel l2=new JLabel("Birth Date :-");
   	l2.setBounds(650, 100, 200, 50);
   	l1.setVisible(true);
   	page2.add(l2);
   	
   	final JTextArea edit1 = new JTextArea();
  // 	edit1.setText(out.get("date"));
      
   	final JScrollPane s2 = new JScrollPane(edit1);
   	s2.setBounds(750, 100, 200, 50);
       edit1.setVisible(true);
   	page2.add(s2);
   	
   	
   	JLabel l3=new JLabel("City :-");
   	l3.setBounds(150, 200, 200, 50);
   	l3.setVisible(true);
   	page2.add(l3);
   	
   	final JTextArea edit3 = new JTextArea();
 //  	edit3.setText(out.get("city"));
      
   	final JScrollPane s3 = new JScrollPane(edit3);
   	s3.setBounds(250, 200, 200, 50);
       edit3.setVisible(true);
   	page2.add(s3);
   	
   	
   	JLabel l4=new JLabel("State :-");
   	l4.setBounds(650, 200, 200, 50);
   	l4.setVisible(true);
   	page2.add(l4);
   	
   	final JTextArea edit4 = new JTextArea();
  // 	edit4.setText(out.get("state"));
      
   	final JScrollPane s4 = new JScrollPane(edit4);
   	s4.setBounds(750, 200, 200, 50);
       edit4.setVisible(true);
   	page2.add(s4);
   	
   	
   	
   	JLabel l5=new JLabel("Email :-");
   	l5.setBounds(150, 300, 200, 50);
   	l5.setVisible(true);
   	page2.add(l5);
   	
   	final JTextArea edit5 = new JTextArea();
 //  	edit5.setText(try1.getEmail("outputOcr.txt"));
      
   	final JScrollPane s5 = new JScrollPane(edit5);
   	s5.setBounds(250, 300, 200, 50);
       edit5.setVisible(true);
   	page2.add(s5);
   	
   	
   	JLabel l6=new JLabel("Relationship:-");
   	l6.setBounds(650, 300, 200, 50);
   	l6.setVisible(true);
   	page2.add(l6);
   	
   	final JTextArea edit6 = new JTextArea();
   //	edit6.setText(out.get("relation"));
      
   	final JScrollPane s6 = new JScrollPane(edit6);
   	s6.setBounds(750, 300, 200, 50);
       edit6.setVisible(true);
   	page2.add(s6);
   	
   	
   	
   	JLabel l7=new JLabel("Phone no :-");
   	l7.setBounds(150, 400, 200, 50);
   	l7.setVisible(true);
   	page2.add(l7);
   	
   	final JTextArea edit7 = new JTextArea();
  // 	edit7.setText(out.get("phone"));
      
   	final JScrollPane s7 = new JScrollPane(edit7);
   	s7.setBounds(250, 400, 200, 50);
       edit7.setVisible(true);
   	page2.add(s7);
   	
   	
   	JLabel l8=new JLabel("zip :-");
   	l8.setBounds(650, 400, 200, 50);
   	l8.setVisible(true);
   	page2.add(l8);
   	
   	final JTextArea edit8 = new JTextArea();
  // 	edit8.setText(out.get("zip"));
      
   	final JScrollPane s8 = new JScrollPane(edit8);
   	s8.setBounds(750, 400, 200, 50);
       edit8.setVisible(true);
   	page2.add(s8);
   	
   	l1.setFont(new Font("Verdana", Font.BOLD, 13));
   	l2.setFont(new Font("Verdana", Font.BOLD, 13));
   	l3.setFont(new Font("Verdana", Font.BOLD, 13));
   	l4.setFont(new Font("Verdana", Font.BOLD, 13));
   	l5.setFont(new Font("Verdana", Font.BOLD, 13));
   	l6.setFont(new Font("Verdana", Font.BOLD, 13));
   	l7.setFont(new Font("Verdana", Font.BOLD, 13));
   	l8.setFont(new Font("Verdana", Font.BOLD, 13));
   	JLabel l=new JLabel("Clasified Data");
	l.setFont(new Font("Verdana", Font.BOLD, 40));
	l.setForeground(Color.BLACK);
   	l.setBounds(420, 20, 500, 50);
   	l.setVisible(true);
   	page2.add(l);
   	
   	page2.setLayout(null);
   	page2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   	page2.setLocationRelativeTo(null);
   	page2.setExtendedState(JFrame.MAXIMIZED_BOTH); 
       //setUndecorated(true);
   	page2.setVisible(true);
    }
    
    
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
        //   gui(); 
    	//clasify();
    	//tryy();
    }
   }


///////////////END