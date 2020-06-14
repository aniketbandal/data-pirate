package ImageToText.ImageText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class postProcess {

public static String generateTextFile(String data) throws FileNotFoundException, UnsupportedEncodingException {
	
	PrintWriter writer = new PrintWriter("outputocrs.txt", "UTF-8");
	writer.println(data);
	writer.close();
	return "outputocrs.txt";
	
	
}

public static String validateOcrOutput(String filePath) {

System.out.println("validating phone no........\n");
correctPhone(filePath);
System.out.println("phone no is above okk ......\n");
System.out.println("validating date ...........\n");
correctDate(filePath);
System.out.println("date ok..........\n");
System.out.println("validating email");
correctEmail(filePath);
System.out.println("email id is okk..........\n");
System.out.println("validating relationship.......\n");
correctRelationship(filePath);
System.out.println("rellationship ok ......\n\n");
	return filePath;
	
}




//correcting each word and digits
public static String filterword(String fileName) 
{
	//String fileName="outputocr.txt";
	try  
	{ 
		

	FileInputStream fis=new FileInputStream(fileName);       
	Scanner sc=new Scanner(fis);    //file to be scanned  
	//returns true if there is another line to read  
	PrintWriter ps=new PrintWriter("outputocr.txt");
	while(sc.hasNextLine())  
	{  
	String line=sc.nextLine();
	//ps.writeln(trry(line));
	ps.println(trry(line));
	//PreProcess.modifyFile(fileName,line,trry(line));
	//System.out.println(trry(line));
   // System.out.println(line);
	}  
	sc.close(); 
	ps.flush();
	ps.close();
	}  
	catch(IOException e)  
	{  
	e.printStackTrace();  
	} 
	
	return "outputocr.txt";
	//System.out.println(trry("Patient Name:"));
	
	//String input="su543";
  //  filterWord(input);
}

public static String trry(String line) {
	System.out.println(line);
	line=line.replaceAll("//s", " ");
	String word[]=line.split(" ");
	line="";
	for(int i=0;i<word.length;i++) {
	 word[i]=correctChar(word[i]);
	 line=line+""+word[i]+" ";
	}
	
	return line;
	
}


 public static String correctChar(String input) {
	
    if(getInfo(input)) {
    	//System.out.println("convert digit to alphabets");
    		input=input.replaceAll("1","i" );
    		input=input.replaceAll("5","s" );
    		input=input.replaceAll("0","o" );
    		input=input.replaceAll("4","u" );
    	//System.out.println(input);
    }
    else {
    	//System.out.println("convert alphabet to digit");
    	input=input.replaceAll("u","4" );
		input=input.replaceAll("i","1" );
		input=input.replaceAll("s","5" );
		input=input.replaceAll("o","0" );
		input=input.replaceAll("l","1" );
		//System.out.println(input);
    }
    return input;
}

public static boolean getInfo(String a) {
	int alpha=0,digit=0;
	for(int j=0;j<a.length();j++) {
		 if ((a.charAt(j) >= 65 && a.charAt(j) <= 90) 
		            || (a.charAt(j) >= 97 && a.charAt(j) <= 122)) 
		              alpha=alpha+1;
		        // CHECKING FOR DIGITS 
		        else if (a.charAt(j) >= 48 && a.charAt(j) <= 57) 
		            digit=digit+1;
		  
		        // OTHERWISE SPECIAL CHARACTER 
		        
		    }  
    //System.out.println(a);
    //System.out.println("alphabet= "+alpha+" Digit= "+digit);
    
    return alpha>digit;
}









//checking phone no
public static String correctPhone(String fileName) {
	try  
	{ 
	FileInputStream fis=new FileInputStream(fileName);       
	Scanner sc=new Scanner(fis);    //file to be scanned  
	//returns true if there is another line to read  
	while(sc.hasNextLine())  
	{  
	String line=sc.nextLine();

	if(line.length()>=10) {
		String result=checkPhoneNo(line);
		if(result.matches("[0-9]+") && result.length()==10) {
			
		System.out.println(result);
		modifyFile(fileName, line, result);
		}
		}
	}  
	sc.close();       
	}  
	catch(IOException e)  
	{  
	e.printStackTrace();  
	}  
	
	
	return fileName;
	
}



  public static String checkPhoneNo(String str) {

		str=str.replaceAll("\\s", "");
		if(str.contains("/")) {
		     str=str.replaceAll("/", "1");
		}
		     if(str.contains("\\"))
		     str=str.replaceAll("\\", "1");
		if(str.contains("l"))
			str=str.replaceAll("l", "1");
		if(str.contains("I"))
			str=str.replaceAll("I", "1");
		if(str.contains("|"))
			str=str.replaceAll("|", "1");
		
		if(str.matches("[0-9]+") && str.length() >2) {
			if(str.length()==10 && Integer.parseInt( String.valueOf(str.charAt(0)))>= 6) {
			//	System.out.println("this is phone no");
				return str;
			}
			  // System.out.println(str);
			}
		return "no phoneno";

			
	}
	    







//checking date
public static String correctDate(String fileName) {

try  
{  
//the file to be opened for reading  
FileInputStream fis=new FileInputStream(fileName);       
Scanner sc=new Scanner(fis);    //file to be scanned  
//returns true if there is another line to read  
while(sc.hasNextLine())  
{  
String line=sc.nextLine();





if(line.contains("Date of Birth")) {
	//System.out.println(line);
	String[] a = line.split(":");
	System.out.println("orignal date="+a[1]);
	String result= validateDate(a[1]);
	System.out.println("new date =  "+result);
	modifyFile(fileName, line, a[0]+":"+result);
	break;
}  
}  
sc.close();     //closes the scanner  
}  
catch(IOException e)  
{  
e.printStackTrace();  
}  


return fileName;

}


public static String validateDate(String date) {
date=date.replaceAll("\\s", "");
if(date.length()<=10) {
	
	if(date.length()==10) {
	date=charRemoveAt(date, 2);
	date=charRemoveAt(date, 4);  
	}
while (date.length()==9) {
	if(date.charAt(2)=='/'||date.charAt(2)=='|'||date.charAt(2)=='-') 
		{
			date=charRemoveAt(date, 2);
			break;  
		}
	if(date.charAt(4)=='/'||date.charAt(4)=='|'||date.charAt(4)=='-' )
		{
			date=charRemoveAt(date, 4);
			break;  
		}
	if(date.charAt(2)=='0') 
		{
			date=charRemoveAt(date, 4);
			break;  
		}
	if(date.charAt(2)=='1'&& date.charAt(3)=='1'&& date.charAt(4)=='1') 
	{
		date=charRemoveAt(date, 2);
		break;  
	}
	if(date.charAt(2)=='1' && date.charAt(3)=='1' && date.charAt(4)=='2') 
	{
		date=charRemoveAt(date, 2);
		break;  
	}
	if(date.charAt(2)=='1'&& date.charAt(3)=='0' && date.charAt(4)=='2') 
	{
		date=charRemoveAt(date, 2);
		break;  
	}
	if(date.charAt(2)=='1' && date.charAt(3)=='2' && date.charAt(4)=='1') 
	{
		date=charRemoveAt(date, 5);
		break;  
	}
	if(date.charAt(2)=='1' && date.charAt(3)=='0' && date.charAt(4)=='1') 
	{
		Date date1 = new Date();
	     String strDateFormat = "MM";
	     SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
	     sdf = new SimpleDateFormat(strDateFormat);
	     int month=Integer.parseInt( sdf.format(date1));
	     //test
	       month=10;
	     if(month >= 1 && month < 10) {
	    	 date=charRemoveAt(date, 2);
	    	 break;
	     }
	     if(month >= 10 && month <= 12) {
	    	 date=charRemoveAt(date, 4);
	    	 break;
	     }
	}
}
	
}


date=insertString(date,"-",1);
date=insertString(date,"-",4);
return date;

}

public static String charRemoveAt(String str, int p) {  
return str.substring(0, p) + str.substring(p + 1);  
} 
public static String insertString(String originalString, String stringToBeInserted, int index) { 

    // Create a new string
    String newString = originalString.substring(0, index + 1) + stringToBeInserted + originalString.substring(index + 1); 
    return newString; 
} 





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
		//System.out.println("original email: "+line);
		//modifyFile(fileName, line,validateEmail(line));
		mail=mail+""+line+"\n";
		
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

//correct email
public static String correctEmail(String fileName) {

try  
{  
//the file to be opened for reading  
FileInputStream fis=new FileInputStream(fileName);       
Scanner sc=new Scanner(fis);   
while(sc.hasNextLine())  
{  
String line=sc.nextLine();
if(line.contains("@")) {
	System.out.println("original email: "+line);
	modifyFile(fileName, line,validateEmail(line));
	break;
}
}  
sc.close();     //closes the scanner  
}  
catch(IOException e)  
{  
e.printStackTrace();  
}  


return fileName;

}

public static String validateEmail(String str) {
String[] emailString = str.split("@");
//System.out.println(emailString[0]+ "   "+emailString[1]);
emailString[0]=emailString[0].replaceAll("\\s", "");
for(int i=0;i<emailString[0].length();i++) {
	if(emailString[0].charAt(i)==',') {
		emailString[0]=charRemoveAt(emailString[0],i);
		emailString[0]=insertString(emailString[0], ".", i-1);
	}
}
if(emailString[1].substring(0, 2).equalsIgnoreCase("ho"))
{
	emailString[1]= "hotmail.com";
	str=emailString[0]+ "@"+emailString[1];
}
if(emailString[1].substring(0, 2).equalsIgnoreCase("gm"))
{
	emailString[1]= "gmail.com";
	str=emailString[0]+ "@"+emailString[1];
}
if(emailString[1].substring(0, 2).equalsIgnoreCase("ya"))
{
	emailString[1]= "yahoo.com";
	str=emailString[0]+ "@"+emailString[1];
}
if(emailString[1].substring(0, 2).equalsIgnoreCase("ou"))
{
	emailString[1]= "outlook.com";
	str=emailString[0]+ "@"+emailString[1];
}
System.out.println("correct email: "+str);

return str;

}






//correctrelationship
public static String correctRelationship(String fileName) {
	 List<String>sample= new ArrayList<>();   
	 sample.add("father"); sample.add("mother");sample.add("parent");  
	 sample.add("son");    sample.add("daughter"); sample.add("child"); 
	 sample.add("husband");  sample.add("wife");sample.add("spouse");    
	 sample.add("brother");sample.add("sister"); sample.add("sibling"); 
	 sample.add("grandson"); sample.add("grandmother"); sample.add("grandparents"); 
	 sample.add("uncle"); sample.add("granddaughter"); sample.add("grandchild"); 
	 sample.add("grandfather"); sample.add("aunt"); sample.add("parent's sibling"); 
	 sample.add("nephew"); sample.add("niece"); sample.add("sibling's child"); 
	 sample.add("ousin"); sample.add("cousin"); sample.add("uncle's child");
	 sample.add("aunt's child");

	 //Converting ArrayList to Array  
	 String[] array = sample.toArray(new String[sample.size()]); 
try  
{  
//the file to be opened for reading  
FileInputStream fis=new FileInputStream(fileName);       
Scanner sc=new Scanner(fis);   
int flag=0;
while(sc.hasNextLine())  
{  
	
String line=sc.nextLine();
line=line.replaceAll("//s", "");
if(line.equalsIgnoreCase("none"))
	line=":"+line;
for(int i=0;i<array.length-1;i++) {
if(stringCompare(line, array[i])<=3) {
	System.out.println("orignal relationship = "+line);
	modifyFile(fileName, line,checkString(line,array));
	flag=1;
	break;
}
} 
if(flag==1)
	break;
}  
sc.close();     //closes the scanner  
}  
catch(IOException e)  
{  
e.printStackTrace();  
}  


return fileName;

}



public static String checkString(String input,String[] array) {

 int[] a=new int[array.length];
// System.out.println(array.length);
 for(int i=0;i<array.length;i++) {
a[i]=stringCompare(input, array[i]);
//System.out.println(a[i]);

 }
// System.out.println("length= "+a.length+"  "+Arrays.);
/*//	return array[9];//array[findSmallest(a)];
int index = Arrays.binarySearch(a, getSmallest(a, a.length)); 
System.out.println(index);*/
 System.out.println("modified relationship = "+array[findSmallest(a)]);
return array[findSmallest(a)]; 
}


//for validate relationship  
public static int findSmallest (int [] arr){//start method

int min = arr[0];  
int index =0;
//Loop through the array  
for (int i = 0; i < arr.length; i++) {  
   //Compare elements of array with min  
  if(arr[i] <min)  {
	   index=i;
      min = arr[i];  
}  
}
return index;
}







//for validate relationship 
public static int stringCompare(String str,String str1) {
//	String str="London";
//	String str1 = "lindoni";
int len=0;
int count=0;
if(str.length()>=str1.length()) {
	len=str.length();
	str1=pad(str1, '*', str.length(), true);
}
else {
	len=str1.length();
	str=pad(str, '*', str1.length(), true);
}

//System.out.println(len);
for(int i=0;i<len;i++) {
	if(str.charAt(i)==str1.charAt(i)||(char)str.charAt(i)+32==str1.charAt(i)) {
		count++;
	}
}
return len-count;
}
//for validate relationship
public static String pad(String source, char fill, int length, boolean right){
if(source.length() > length) return source;
char[] out = new char[length];
if(right){
   System.arraycopy(source.toCharArray(), 0, out, 0, source.length());
   Arrays.fill(out, source.length(), length, fill);
}else{
   int sourceOffset = length - source.length();
   System.arraycopy(source.toCharArray(), 0, out, sourceOffset, source.length());
   Arrays.fill(out, 0, sourceOffset, fill);
}
return new String(out);
}







static void modifyFile(String filePath, String oldString, String newString)
{
File fileToBeModified = new File(filePath);
 
String oldContent = "";
 
BufferedReader reader = null;
 
FileWriter writer = null;
 
try
{
    reader = new BufferedReader(new FileReader(fileToBeModified));
     
    //Reading all the lines of input text file into oldContent
     
    String line = reader.readLine();
     
    while (line != null) 
    {
        oldContent = oldContent + line + System.lineSeparator();
         
        line = reader.readLine();
    }
     
    //Replacing oldString with newString in the oldContent
     
    String newContent = oldContent.replaceAll(oldString, newString);
     
    //Rewriting the input text file with newContent
     
    writer = new FileWriter(fileToBeModified);
     
    writer.write(newContent);
}
catch (IOException e)
{
    e.printStackTrace();
}
finally
{
    try
    {
        //Closing the resources
         
        reader.close();
         
        writer.close();
    } 
    catch (IOException e) 
    {
        e.printStackTrace();
    }
}
}







}
