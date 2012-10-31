package fashion.collage.app.adapter;

import java.util.ArrayList;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import fashion.collage.app.R;
import fashion.collage.app.object.FashionItem;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ItemAdapter extends BaseAdapter {
	private static final String TAG = "ItemAdapter";
	
	private Context mContext;
	
	private ArrayList<FashionItem> items;
	private LayoutInflater li;
	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	
	public ItemAdapter(Context c, ArrayList<FashionItem> items) {
		mContext = c;
		li = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.items = items;
		
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
	}
	
	public int getCount() {
        return items.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		if (view==null) {
			view = li.inflate(R.layout.chooseitembox_image_item, null);
		}
		
		FashionItem item = items.get(position);
		
		ImageView image = (ImageView)view.findViewById(R.id.cit_image_item_image);
		imageLoader.displayImage(item.getMediumImageUrl(), image, options);
		
		return view;
	}
}
