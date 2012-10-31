package fashion.collage.app.data;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.impl.conn.DefaultClientConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class CollageHelper {
	private static final String TAG = "CollageHelper";
	
	private static byte[] fbBytesArray;
	
	public static Bitmap getBitmapFromURL(String src) {
		HttpURLConnection connection = null;
		try {
	        URL url = new URL(src);
	        connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
	        return myBitmap;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	    	connection.disconnect();
	    }
	}

	/**
	 * @return the fbBytesArray
	 */
	public static byte[] getFbBytesArray() {
		return fbBytesArray;
	}

	/**
	 * @param fbBytesArray the fbBytesArray to set
	 */
	public static void setFbBytesArray(byte[] fbBytesArray) {
		CollageHelper.fbBytesArray = fbBytesArray;
	}
}
