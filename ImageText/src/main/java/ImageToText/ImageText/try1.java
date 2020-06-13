package ImageToText.ImageText;

import com.google.cloud.automl.v1.AnnotationPayload;
import com.google.cloud.automl.v1.AutoMlClient;
import com.google.cloud.automl.v1.ExamplePayload;
import com.google.cloud.automl.v1.ModelEvaluation;
import com.google.cloud.automl.v1.ModelEvaluationName;
import com.google.cloud.automl.v1.ModelName;
import com.google.cloud.automl.v1.PredictResponse;
import com.google.cloud.automl.v1.PredictionServiceClient;
import com.google.cloud.automl.v1.TextSnippet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class try1 {

	
	public static void main(String[] arg) throws IOException {
		getModelEvaluation();
	}
	
	
  static void getModelEvaluation() throws IOException {
    // TODO(developer): Replace these variables before running the sample.
	      String data="Bristol-Myers Squibb \r\n" + 
	      		"Patient Assistance Foundation \r\n" + 
	      		"Phone: 800-736-0003 \r\n" + 
	      		"Monday to Friday, 8:00 AM -- 8:00 PM ET \r\n" + 
	      		"(excluding holidays) \r\n" + 
	      		"BMSPAF Case #: \r\n" + 
	      		"PO Box 220769, Charlotte, NC 28222-0769 | Phone: 800-736-0003 Fax: 800-736-1611 \r\n" + 
	      		"Section 1: Patient Information \r\n" + 
	      		"TO BE COMPLETED BY PATIENT ALL BOXES ARE REQUIRED EXCEPT WHERE NOTED). \r\n" + 
	      		"Patient Name: \r\n" + 
	      		"Ashley Williams \r\n" + 
	      		"Date of Birth:08-03-1973\r\n" + 
	      		"Social Security Number (Providing SSN is optional) \r\n" + 
	      		"5432176543 \r\n" + 
	      		"Female O Male \r\n" + 
	      		"Gender: \r\n" + 
	      		"Email Address (optional) \r\n" + 
	      		"Patient Address (no PO Boxes): \r\n" + 
	      		"City: \r\n" + 
	      		//"New York \r\n" + 
	      		"State: OH \r\n" + 
	      		"Home Phone: \r\n" + 
	      		"Cell Phone (optional) \r\n" + 
	      		"6153215432\r\n" + 
	      		"6153215431\r\n" + 
	      		"Alternate Contact Name (optional) \r\n" + 
	      		"Relationship (optional) \r\n" + 
	      		"Brian Williams \r\n" + 
	      		"spouse\r\n" + 
	      		"Allergies (you may attach a list on a separate page if more space is needed): \r\n" + 
	      		"Zip: 53317 \r\n" + 
	      		"ashley.Williams@hotmail.com\r\n" + 
	      		"6157275432\r\n" + 
	      		"Phone (optional \r\n" + 
	      		"Allegra \r\n" + 
	      		"All Current Medications (you may aftach a list on a separate page it more space is needed) \r\n" + 
	      		"Do you have insurance through any of these providers? \r\n" + 
	      		"PATIENT INSURANCE INFORMATION \r\n" + 
	      		"(Check all that apply) \r\n" + 
	      		"Z Medicaid \r\n" + 
	      		"Medicare: \r\n" + 
	      		"O Part A \r\n" + 
	      		"Part B \r\n" + 
	      		"Part D Part C/Medicare Advantage \r\n" + 
	      		"VA or Military \r\n" + 
	      		"Private Insurance \r\n" + 
	      		"None \r\n" + 
	      		"State Assistance Program for Medication \r\n" + 
	      		"Other: Cigna ABP \r\n" + 
	      		"PHONE # \r\n" + 
	      		"ID/POLICY # \r\n" + 
	      		"INSURANCE NAME \r\n" + 
	      		"Primary: \r\n" + 
	      		"Medicaid \r\n" + 
	      		"Secondary: Medicare Part D \r\n" + 
	      		"Prescription Coverage: \r\n" + 
	      		"(Optional: Attach a copy of both sides of your \r\n" + 
	      		"prescription insurance card) \r\n" + 
	      		"ID/Policy # 43758912 \r\n" + 
	      		"RxBIN: \r\n" + 
	      		"RxPCN: \r\n" + 
	      		"Number of people living in your home: \r\n" + 
	      		"(Include yourself, your spouse, and any dependents currently living with you) \r\n" + 
	      		"$ 54,000 \r\n" + 
	      		"TOTAL YEARLY HOUSEHOLD INCOME: \r\n" + 
	      		"OR TOTAL MONTHLY HOUSEHOLD INCOME: \r\n" + 
	      		"$ 5,000 \r\n" + 
	      		"Proof of income inay be required: Please provide your most recent federal tax return. If your federal tax return is not available, \r\n" + 
	      		"please provide as many of the following as available: W2, 1099, pension statement, Social Security statement, at least 2 consecutive \r\n" + 
	      		"pay stubs. \r\n" + 
	      		"Medicare Part D recipient: If you have spent 3% of your annual income on out-of-pocket prescription costs, please contact your \r\n" + 
	      		"pharmacy to provide you with a report to document your yearly out-of-pocket expenses. Report must be attached to this application. \r\n" + 
	      		"Please continue to the next page to read, sign, and date the Patient Agreement & Consent. \r\n" + 
	      		"ELIQUIS®, NULOJIX, and ORENCIA are trademarks of Bristol-Myers Squibb Company \r\n" + 
	      		"NOUS1900997-02 12/19 \r\n" + 
	      		"Downloaded from Needy Meds.org \r\n" + 
	      		" \r\n";
	    System.out.println(predict("pirates-278907", "us-central1", "TEN8236663705018826752",data));
  }


    
    public static String predict(String projectId, String computeRegion, String modelId ,String content) throws IOException {
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
        for (AnnotationPayload annotationPayload : response.getPayloadList()) {
        result=result+"\n"+annotationPayload.getDisplayName()+"  "+annotationPayload.getTextExtraction().getTextSegment().getContent();
        //System.out.println(annotationPayload.getDisplayName()+"  "+annotationPayload.getTextExtraction().getTextSegment().getContent());
        	}
		return result;
  }
}

