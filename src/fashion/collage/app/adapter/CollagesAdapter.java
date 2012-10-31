package fashion.collage.app.adapter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import fashion.collage.app.R;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CollagesAdapter extends BaseAdapter {
	private static final String TAG = "CollagesAdapter";
	
	private LayoutInflater li;
	
	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	
	// data
	private File[] files;
	
	public CollagesAdapter(Context c) {
		li = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		imageLoader = ImageLoader.getInstance();
		
 		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.ic_launcher)
		.cacheInMemory()
		.cacheOnDisc()
		.build();
 		
 		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(c)
        .defaultDisplayImageOptions(options)
        //.memoryCache(new WeakMemoryCache())
        .build();
 		imageLoader.init(config);
 		
 		FilenameFilter filter = new PngFileFilter("png");
        
        files = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).listFiles(filter);
        Collections.reverse(Arrays.asList(files));
        
        File prva = files[files.length-1];
        
        Log.i(TAG, "koliko fajlov: "+files.length+", pot do prve: "+prva.getAbsolutePath());
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return files.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		if (view==null) {
			view = li.inflate(R.layout.single_collage, null);
		}
		
		ImageView image = (ImageView)view.findViewById(R.id.single_collage_image);
		
		//image.setImageURI(Uri.parse(files[position].getAbsolutePath()));
		
		imageLoader.displayImage("file://"+files[position].getAbsolutePath(), image, options);
		
		return view;
	}

	public class PngFileFilter implements FilenameFilter { 
    	String ext; 
    	
    	public PngFileFilter(String ext) { 
	    	this.ext = "." + ext; 
	    } 
    	
    	public boolean accept(File dir, String name) { 
    		return name.endsWith(ext); 
    	} 
    }
}
