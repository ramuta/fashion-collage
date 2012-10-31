package fashion.collage.app.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import fashion.collage.app.R;
import fashion.collage.app.data.CollageHelper;
import fashion.collage.app.data.FashionObjectsHelper;
import fashion.collage.app.fragment.CollagesFragment;
import fashion.collage.app.fragment.CreateFragment;
import fashion.collage.app.service.GetImagesService;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends SherlockFragmentActivity {
	private static final String TAG = "MainActivity";
	
	private ResponseReceiver receiver;
	
	// ActionBar
	private ActionBar actionBar;
	
	// fragments
	private Fragment createFragment;
	private Fragment collagesFragment;
	
	private FashionObjectsHelper helper = new FashionObjectsHelper();
	//private CollageHelper collageHelper = new
	
	// canvas
	private Bitmap collage;
	private Canvas canvas;
	private FileOutputStream fos;
	
	// UI
	//private ProgressDialog loadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Log.i(TAG, "Started MainActivity. Starting service...");
        Intent intent = new Intent(MainActivity.this, GetImagesService.class);
        startService(intent);
        
        // instanciiraj in registriraj response receiver
  		IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
  		filter.addCategory(Intent.ACTION_DEFAULT);
  		receiver = new ResponseReceiver();
  		registerReceiver(receiver, filter);
        
        // initialize actionbar
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        collage = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(collage);
        
        // set Create collage tab
        ActionBar.Tab create = getSupportActionBar().newTab();
        create.setText(R.string.main_tab_create);
        create.setTabListener(new ActionBar.TabListener() {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				createFragment = new CreateFragment();
				getSupportFragmentManager().beginTransaction().replace(R.id.container, createFragment).commit();
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {}
		});
        
        // set Collages tab
        ActionBar.Tab collages = getSupportActionBar().newTab();
        collages.setText(R.string.main_tab_collage);
        collages.setTabListener(new ActionBar.TabListener() {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				collagesFragment = new CollagesFragment();
				getSupportFragmentManager().beginTransaction().replace(R.id.container, collagesFragment).commit();
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {}
		});
        
        getSupportActionBar().addTab(create);
        getSupportActionBar().addTab(collages);
        
        //builder = new ProgressDialog.Builder(MainActivity.this, ProgressDialog.STYLE_SPINNER);
    }
    
    public class ResponseReceiver extends BroadcastReceiver {
    	public static final String ACTION_RESP = "app.fashion.collage.intent.action.MESSAGE_PROCESSED";
    	
		@Override
		public void onReceive(Context context, Intent intent) {
			createFragment = new CreateFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.container, createFragment).commit();
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_save:
        	Log.i(TAG, "SAVE");
        	Toast.makeText(MainActivity.this, "Creating your collage...", Toast.LENGTH_SHORT).show();
        	new CreateCanvasTask().execute();
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    public void onDestroy() {
    	this.unregisterReceiver(receiver);
    	super.onDestroy();
    }
    
    private class CreateCanvasTask extends AsyncTask<URL, Integer, Canvas> {
        protected Canvas doInBackground(URL... urls) {
        	Bitmap bitmap1 = CollageHelper.getBitmapFromURL(helper.getChosenFashionItem1().getLargeImageUrl());
        	Bitmap bitmap2 = CollageHelper.getBitmapFromURL(helper.getChosenFashionItem2().getLargeImageUrl());
        	Bitmap bitmap3 = CollageHelper.getBitmapFromURL(helper.getChosenFashionItem3().getLargeImageUrl());
        	Bitmap bitmap4 = CollageHelper.getBitmapFromURL(helper.getChosenFashionItem4().getLargeImageUrl());
        	
        	canvas.drawColor(Color.WHITE);
        	canvas.drawBitmap(bitmap1, 50f, 60f, null);
        	canvas.drawBitmap(bitmap2, 250f, 40f, null);
        	canvas.drawBitmap(bitmap3, 50f, 250f, null);
        	canvas.drawBitmap(bitmap4, 250f, 280f, null);
        	
        	convertToBytesAndSave();
            return canvas;
        }
        
        protected void onProgressUpdate(Integer... progress) {
        	//loadingDialog = ProgressDialog.show(MainActivity.this, "Please wait", "Creating collage...");
        	
        }

        protected void onPostExecute(Canvas canvas) {	
        	//image.setImageBitmap(collage);
        	Log.i(TAG, "pa poglejmo èe je ratalo :)");
        	//loadingDialog.dismiss();
        	
        	shareAlertDialog();
        }
    }
    
    private void convertToBytesAndSave() {
    	try {
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			//collage.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream("/mnt/sdcard/Pictures/testnicanvas.png"));
			
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			CollageHelper.setFbBytesArray(bytes.toByteArray());
			collage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
			
			File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "FashionCollage");
			mediaStorageDir.createNewFile();
			
			File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + "FC_"+timeStamp+".png");
			f.createNewFile();
			//write the bytes in file
			FileOutputStream fo = new FileOutputStream(f);
			fo.write(bytes.toByteArray());
		} catch (FileNotFoundException e) {
			Log.e(TAG, "ni ratalo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void shareAlertDialog() {
    	AlertDialog.Builder shareDialogBuilder = new AlertDialog.Builder(MainActivity.this);
    	shareDialogBuilder.setTitle("Share on Facebook").setMessage("Would you like to share your collage on Facebook?");
    	shareDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.i(TAG, "YEEEEEEEES");
			}
		});
    	
    	shareDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.i(TAG, "NOOOOOOOOOOOOOOOO");
			}
		});
    	
    	AlertDialog shareDialog = shareDialogBuilder.create();
    	shareDialog.show();
    }
}
