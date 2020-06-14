package ImageToText.ImageText;

import com.google.cloud.automl.v1.AnnotationPayload;
import com.google.cloud.automl.v1.ExamplePayload;
import com.google.cloud.automl.v1.ModelName;
import com.google.cloud.automl.v1.PredictResponse;
import com.google.cloud.automl.v1.PredictionServiceClient;
import com.google.cloud.automl.v1.TextSnippet;
import com.google.cloud.language.v1.AnalyzeEntitiesRequest;
import com.google.cloud.language.v1.AnalyzeEntitiesResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.Entity;
import com.google.cloud.language.v1.EntityMention;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1beta2.Sentiment;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.protobuf.ByteString;
import com.google.cloud.vision.v1.AnnotateImageResponse;

import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;

import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.ImageAnnotatorClient;

import com.google.cloud.vision.v1.TextAnnotation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


public class Detect {

	public static void main(String[] args) throws IOException, Exception {
		// TODO Auto-generated method stub

		//String f=pdftoImage("try.pdf");
		//imagetotext(f);
		  System.out.println(predict(result("outputOcr.txt")));
	}
public static String pdftoImage(String filepath) {
    PreProcess.pdfToPng(filepath,250);
    System.out.println("pdf converted into image demo.png");
    return "demo.png";
	
}
public static void imagetotext(String filePath) throws IOException, Exception {
	 PreProcess.greyScale(filePath);
    
	String filePath1="greyscale.png";
	String output = googleVisionAPI(filePath1);
    File file = new File(filePath1); 
    
    if(file.delete()) 
    { 
        System.out.println("File deleted '"+filePath1+"' successfully"); 
    } 
    else
    { 
        System.out.println("Failed to delete the file"); 
    } 
	
    System.out.println("file saved at location :- ");
    //String outputfilelocation ="outputocr.txt";
    System.out.println("output correcting characters");
    String outputfilelocation =postProcess.filterword(postProcess.generateTextFile(output));
    System.out.println(outputfilelocation);
    postProcess.validateOcrOutput(outputfilelocation);
    
    System.out.println("result in text store at location = "+outputfilelocation+"\n\n");
    System.out.println(result(outputfilelocation));
    File file1 = new File(postProcess.generateTextFile(output)); 
    
    if(file1.delete()) 
    { 
        System.out.println("File deleted '"+filePath1+"' successfully"); 
    } 
    
}
	 // [START vision_fulltext_detection]
	  public static String googleVisionAPI(String filePath) throws Exception, IOException {
	    List<AnnotateImageRequest> requests = new ArrayList<AnnotateImageRequest>();

	    ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

	    Image img = Image.newBuilder().setContent(imgBytes).build();
	    Feature feat = Feature.newBuilder().setType(Type.DOCUMENT_TEXT_DETECTION).build();
	    AnnotateImageRequest request =
	        AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
	    requests.add(request);

	    // Initialize client that will be used to send requests. This client only needs to be created
	    // once, and can be reused for multiple requests. After completing all of your requests, call
	    // the "close" method on the client to safely clean up any remaining background resources.
	    try (ImageAnnotatorClient client = ImageAnnotatorClient.create())
	    {
	      BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
	      List<AnnotateImageResponse> responses = response.getResponsesList();
	      client.close();

	      for (AnnotateImageResponse res : responses) {
	        if (res.hasError()) {
	          System.out.format("Error: %s%n", res.getError().getMessage());
	          return " error";
	        }

	        // For full list of available annotations, see http://g.co/cloud/vision/docs
	        TextAnnotation annotation = res.getFullTextAnnotation();
	   
	        return annotation.getText();
	      }
	    }
		return "output not come";
	  }
	  // [END vision_fulltext_detection]

	  public static String predict(String content) throws IOException {
		  
		  String projectId = "pirates-278907", computeRegion="us-central1", modelId="TEN8236663705018826752" ;
		  
		  
	        PredictionServiceClient predictionClient = PredictionServiceClient.create();
	        ModelName name = ModelName.of(projectId, computeRegion, modelId);
	       // String content = "";
	        TextSnippet textSnippet =
	                TextSnippet.newBuilder().setContent(content).setMimeType("text/plain").build();
	        ExamplePayload payload = ExamplePayload.newBuilder().setTextSnippet(textSnippet).build();

	        Map<String, String> params = new HashMap<String, String>();
	        PredictResponse response = predictionClient.predict(name, payload, params);

	        //System.out.println("Prediction results:");
	         String result="";
	         int count=0;
	        for (AnnotationPayload annotationPayload : response.getPayloadList()) {
	         if(count==0) {
	        result=result+annotationPayload.getDisplayName()+":"+annotationPayload.getTextExtraction().getTextSegment().getContent();
	        count++;
	         }//System.out.println(annotationPayload.getDisplayName()+"  "+annotationPayload.getTextExtraction().getTextSegment().getContent());
	        
	         result=result+"\n"+annotationPayload.getDisplayName()+":"+annotationPayload.getTextExtraction().getTextSegment().getContent();}
			return result;
	  }
		public static String result(String fileName) throws Exception {
			String whole="";
			try  
			{ 
			FileInputStream fis=new FileInputStream(fileName);       
			Scanner sc=new Scanner(fis);    //file to be scanned  
			//returns true if there is another line to read  
			while(sc.hasNextLine())  
			{  
			String line=sc.nextLine();
			whole=whole+line+" ";
		   // System.out.println(line);
			}  
			sc.close();       
			}  
			catch(IOException e)  
			{  
			e.printStackTrace();  
			} 
			return whole;
			//trial(whole);
		//	analyzeEntitiesText(whole);
		}
		


}
