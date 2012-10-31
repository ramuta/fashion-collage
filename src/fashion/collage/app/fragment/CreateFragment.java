package fashion.collage.app.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.actionbarsherlock.app.SherlockFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import fashion.collage.app.R;
import fashion.collage.app.activity.ChooseItemBox;
import fashion.collage.app.data.FashionObjectsHelper;

public class CreateFragment extends SherlockFragment {
	private static final String TAG = "CreateFragment";
	
	public static final int IMAGE_FRAME1_RESULT = 11;
	public static final int IMAGE_FRAME2_RESULT = 22;
	public static final int IMAGE_FRAME3_RESULT = 33;
	public static final int IMAGE_FRAME4_RESULT = 44;
	
	// image loading
	private ImageLoader imageLoader;
    private DisplayImageOptions options;
    
    // UI
    private ImageView imageFrame1;
    private ImageView imageFrame2;
    private ImageView imageFrame3;
    private ImageView imageFrame4;
    private ProgressBar spinner;
	
    // data
	private FashionObjectsHelper helper = new FashionObjectsHelper();

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_create, container, false);
		
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.ic_launcher)
		//.cacheInMemory()
		//.cacheOnDisc()
		.build();
		imageLoader.init(ImageLoaderConfiguration.createDefault(this.getActivity()));
		
		//image = (ImageView)view.findViewById(R.id.create_image);
		
		imageFrame1 = (ImageView)view.findViewById(R.id.create_image_frame1);
		imageFrame2 = (ImageView)view.findViewById(R.id.create_image_frame2);
		imageFrame3 = (ImageView)view.findViewById(R.id.create_image_frame3);
		imageFrame4 = (ImageView)view.findViewById(R.id.create_image_frame4);
		
		spinner = (ProgressBar)view.findViewById(R.id.create_image_spinner);
		spinner.setVisibility(View.INVISIBLE);

        // Inflate the layout for this fragment
        return view;
    }
	
	@Override
	public void onActivityCreated (Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// TODO: dobi sliko iz arraylista
		if (!helper.getShoesArrayList().isEmpty()) {
			Log.i(TAG, "Ni prazna!!!");
			/**
			imageLoader.displayImage(helper.getShoesArrayList().get(0).getLargeImageUrl(), image);
			*/
			imageFrame1.setOnClickListener(imageFrameListener);
			imageFrame2.setOnClickListener(imageFrameListener);
			imageFrame3.setOnClickListener(imageFrameListener);
			imageFrame4.setOnClickListener(imageFrameListener);
		} else {
			Log.i(TAG, "PRAZNA!!!");
		}
	}
	
	OnClickListener imageFrameListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			
			Intent intent = new Intent(getActivity(), ChooseItemBox.class);
			
			switch(v.getId()) {
			case R.id.create_image_frame1:
				startActivityForResult(intent, IMAGE_FRAME1_RESULT);
				break;
			case R.id.create_image_frame2:
				startActivityForResult(intent, IMAGE_FRAME2_RESULT);
				break;
			case R.id.create_image_frame3:
				startActivityForResult(intent, IMAGE_FRAME3_RESULT);
				break;
			case R.id.create_image_frame4:
				startActivityForResult(intent, IMAGE_FRAME4_RESULT);
				break;
			}

			Log.i(TAG, "Go to ChooseImageBox");
	}};
	
	@Override
    public void startActivityForResult(Intent intent, int requestCode) {
        intent.putExtra("requestCode", requestCode);
        super.startActivityForResult(intent, requestCode);
    }
		
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		// spinner currently not in use
		ImageLoadingListener loadingListener = new ImageLoadingListener() {
		    @Override
		    public void onLoadingStarted() {
		    	spinner.setVisibility(View.INVISIBLE);
		    }
		    @Override
		    public void onLoadingFailed(FailReason failReason) {
		    	spinner.setVisibility(View.INVISIBLE);
		    }
		    @Override
		    public void onLoadingComplete(Bitmap loadedImage) {
		    	spinner.setVisibility(View.INVISIBLE);
		    }
		    @Override
		    public void onLoadingCancelled() {
		        // Do nothing
		    }
		};
		
		 try {
			if (requestCode == IMAGE_FRAME1_RESULT) {
				 String chosenUrl = helper.getChosenFashionItem1().getLargeImageUrl();
				 imageLoader.displayImage(chosenUrl, imageFrame1, options, loadingListener);
			 } else if (requestCode == IMAGE_FRAME2_RESULT) {
				 String chosenUrl = helper.getChosenFashionItem2().getLargeImageUrl();
				 imageLoader.displayImage(chosenUrl, imageFrame2, options, loadingListener);
			 } else if (requestCode == IMAGE_FRAME3_RESULT) {
				 String chosenUrl = helper.getChosenFashionItem3().getLargeImageUrl();
				 imageLoader.displayImage(chosenUrl, imageFrame3, options, loadingListener);
			 } else if (requestCode == IMAGE_FRAME4_RESULT) {
				 String chosenUrl = helper.getChosenFashionItem4().getLargeImageUrl();
				 imageLoader.displayImage(chosenUrl, imageFrame4, options, loadingListener);
			 }
		} catch (Exception e) {
			Log.i(TAG, "No image was chosen.");
		}		 
    }	
}
