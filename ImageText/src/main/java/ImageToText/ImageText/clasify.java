package ImageToText.ImageText;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class clasify {
	public static String getEmail(String fileName) {
String mail="";
		try  
		{  
		//the file to be opened for reading  
		FileInputStream fis=new FileInputStream(fileName);       
		Scanner sc=new Scanner(fis);   
		while(sc.hasNextLine())  
		{  
		String line=sc.nextLine();
		if(line.contains("@")) {
            mail=line;
			break;
		}
		}  
		sc.close();     //closes the scanner  
		}  
		catch(IOException e)  
		{  
		e.printStackTrace();  
		}  


		return mail;

		}

	
	public static HashMap<String, String> classified(String txt) {
		
		
    	String[] r1 = txt.split("\n");
    	ArrayList<String> out1 =new ArrayList<String>();
    	HashMap<String, String> out=new HashMap<String, String>() ;
    	//String[] out = new String[r1.length];
    	String a="";
    	for(int i=0;i<r1.length;i++) {
    		//System.out.println(r1[i]);
    		String[] r2 =r1[i].split(":");
    		if(r2[0].contains("name")){out.put("name", r2[1]);}//{String a="";a=a+r2[1];out1.add(a);/*out[0]=a;*/System.out.println("name= " + r2[1]);}
    		if(r2[0].contains("State")){out.put("state", r2[1]);}//{String a="";a=a+r2[1];out[3]=a;System.out.println("state : " + r2[1]);}
    		if(r2[0].contains("Relationship")){out.put("relation", r2[1]);}//{String a="";a=a+r2[1];out[5]=a;System.out.println("relationship= " + r2[1]);}
    		if(r2[0].contains("Date")) {
    			if(r2[1].contains("-")){out.put("date", r2[1]);}//{String a="";a=a+r2[1];out[1]=a;System.out.println("date= " + r2[1]);}
    			else {out.put("city", r2[1]);}// {String a="";a=a+r2[1];out[2]=a;System.out.println("city: "+r2[1]);}
    		}
    		if(r2[0].contains("Phone")){
    			if(r2[1].length()>5){a=a+r2[1]+"\n";}//{String a="";a=a+r2[1];out[6]=a;System.out.println("no : " + r2[1]);}
    			else {out.put("zip", r2[1]);}//{String a="";a=a+r2[1];out[7]=a;System.out.println("zip : " + r2[1]);}    
    		}
    		}
    	out.put("phone", a);
		return out;
		
	}
}
