package fashion.collage.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;

import fashion.collage.app.R;
import fashion.collage.app.adapter.CollagesAdapter;

public class CollagesFragment extends SherlockFragment {
	private static final String TAG = "CollagesFragment";
	
	// UI
	private ListView list;
	private CollagesAdapter adapter;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_collages, container, false);
		
		list = (ListView)view.findViewById(R.id.fragment_collages_list);
		
		adapter = new CollagesAdapter(getActivity());
		
		list.setAdapter(adapter);
		
		return view;
	}
}
