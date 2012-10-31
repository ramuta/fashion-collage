package fashion.collage.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import fashion.collage.app.R;
import fashion.collage.app.adapter.ItemAdapter;
import fashion.collage.app.data.FashionObjectsHelper;

public class ChooseItemBox extends SherlockFragmentActivity {
	private static final String TAG = "ChooseItemBox";
	
	private ActionBar actionBar;
	
	//data
	private FashionObjectsHelper helper = new FashionObjectsHelper();
	private static int CURRENT_TAB = 1; // 1-tops, 2-bottoms, 3-shoes, 4-accessories
	private int reqCode;
	
	// UI
	private GridView grid;
	private ItemAdapter itemAdapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_item_box);
        
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        Intent intent = getIntent();
        reqCode = intent.getIntExtra("requestCode", 0000);
        Log.i(TAG, "reqCode: "+reqCode);
        
        grid = (GridView)findViewById(R.id.chooseitembox_grid);

        // set Tops tab in the action bar
        ActionBar.Tab tops = getSupportActionBar().newTab();
        tops.setText(R.string.chooseitembox_tab_tops);
        tops.setTabListener(new ActionBar.TabListener() {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				CURRENT_TAB = 1;
				itemAdapter = new ItemAdapter(ChooseItemBox.this, helper.getTops());
				grid.setAdapter(itemAdapter);
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {}
		});
        
        // set Tops tab in the action bar
        ActionBar.Tab bottoms = getSupportActionBar().newTab();
        bottoms.setText(R.string.chooseitembox_tab_bottoms);
        bottoms.setTabListener(new ActionBar.TabListener() {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				CURRENT_TAB = 2;
				itemAdapter = new ItemAdapter(ChooseItemBox.this, helper.getBottoms());
				grid.setAdapter(itemAdapter);
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {}
		});
        
        // set Tops tab in the action bar
        ActionBar.Tab shoes = getSupportActionBar().newTab();
        shoes.setText(R.string.chooseitembox_tab_shoes);
        shoes.setTabListener(new ActionBar.TabListener() {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				/*
				itemFragment = new ItemFragment();
				getSupportFragmentManager().beginTransaction().replace(R.id.choseitembox_container, shoesFragment).commit();
				*/
				CURRENT_TAB = 3;
				
				itemAdapter = new ItemAdapter(ChooseItemBox.this, helper.getShoesArrayList());
				grid.setAdapter(itemAdapter);
				
				Log.i(TAG, "SHOES");
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {}
		});
        
        // set Tops tab in the action bar
        ActionBar.Tab accessories = getSupportActionBar().newTab();
        accessories.setText(R.string.chooseitembox_tab_accessories);
        accessories.setTabListener(new ActionBar.TabListener() {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				CURRENT_TAB = 4;
				itemAdapter = new ItemAdapter(ChooseItemBox.this, helper.getAccessories());
				grid.setAdapter(itemAdapter);
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {}
		});
        
        getSupportActionBar().addTab(tops);
        getSupportActionBar().addTab(bottoms);
        getSupportActionBar().addTab(shoes);
        getSupportActionBar().addTab(accessories);
        
        grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				// TODO Auto-generated method stub
				switch (CURRENT_TAB) {
				case 1:
					helper.setChosenFashionItem(helper.getTops().get(position), reqCode);
					break;
				case 2:
					helper.setChosenFashionItem(helper.getBottoms().get(position), reqCode);
					break;
				case 3:
					helper.setChosenFashionItem(helper.getShoesArrayList().get(position), reqCode);
					break;
				case 4:
					helper.setChosenFashionItem(helper.getAccessories().get(position), reqCode);
					break;
				}
				ChooseItemBox.this.finish();
			}
		});
	}
}
