package fashion.collage.app.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import fashion.collage.app.activity.MainActivity.ResponseReceiver;
import fashion.collage.app.data.FashionObjectsHelper;
import fashion.collage.app.data.UrlsData;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class GetImagesService extends IntentService {
	private static final String TAG = "GetImagesService";
	
	private String response;
	// connection variables
	private InputStream is = null;
	private StringBuilder sb = null;
	
	// helper
	private FashionObjectsHelper helper = new FashionObjectsHelper();
	
	// constructor
	public GetImagesService() {
		super("GetImagesService");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub

		getShopStyleImages(UrlsData.getShopstyleTopsImages());
		getShopStyleImages(UrlsData.getShopstyleBottomsImages());
		getShopStyleImages(UrlsData.getShopstyleShoesImages());
		getShopStyleImages(UrlsData.getShopstyleAccessoriesImages());
		
		// pošlji intent
		Intent broadcastIntent = new Intent();
		broadcastIntent.setAction(ResponseReceiver.ACTION_RESP);
		broadcastIntent.addCategory(Intent.ACTION_DEFAULT);
		sendBroadcast(broadcastIntent);
	}

	private void getShopStyleImages(String URL) {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	   	
    	// http post 
    	try {
    	     HttpClient httpclient = new DefaultHttpClient();
    	     HttpPost httppost = new HttpPost(URL);
    	     httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    	     HttpResponse response = httpclient.execute(httppost);
    	     HttpEntity entity = response.getEntity();
    	     is = entity.getContent();
    	} catch(Exception e){
    	     Log.e(TAG, "Error in http connection"+e.toString());
    	}
    	
    	//convert response to string
    	try {
    		BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
    		sb = new StringBuilder();
    	    sb.append(reader.readLine() + "\n");
	        String line="0";	        
	        while ((line = reader.readLine()) != null) {
	        	sb.append(line + "\n");
	        }        
	        is.close();
	        response = sb.toString(); // odgovor (rezultat) ki ga dobimo po poslanem zahtevku
	        Log.i(TAG, "PHOTOS: " + response);
	        
	        if (URL.equals(UrlsData.getShopstyleShoesImages())) {
	        	helper.parseShopStyleData(response, helper.SHOES);
	        } else if (URL.equals(UrlsData.getShopstyleAccessoriesImages())) {
	        	helper.parseShopStyleData(response, helper.ACCESSORIES);
	        } else if (URL.equals(UrlsData.getShopstyleBottomsImages())) {
	        	helper.parseShopStyleData(response, helper.BOTTOMS);
	        } else if (URL.equals(UrlsData.getShopstyleTopsImages())) {
	        	helper.parseShopStyleData(response, helper.TOPS);
	        }
	        
	        Log.i(TAG, "the end :)");
    	} catch(Exception e){
    		Log.e(TAG, "Error converting result "+e.toString());
    	}
	}
}
