package fashion.collage.app.data;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import fashion.collage.app.fragment.CreateFragment;
import fashion.collage.app.object.FashionItem;

public class FashionObjectsHelper {
	private static final String TAG = "FashionObjectsHelper";
	
	private static ArrayList<FashionItem> shoes_al = new ArrayList<FashionItem>();
	private static ArrayList<FashionItem> tops_al = new ArrayList<FashionItem>();
	private static ArrayList<FashionItem> bottoms_al = new ArrayList<FashionItem>();
	private static ArrayList<FashionItem> accessories_al = new ArrayList<FashionItem>();
	
	// types
	public static final int TOPS = 111;
	public static final int BOTTOMS = 112;
	public static final int SHOES = 113;
	public static final int ACCESSORIES = 114;
	
	// temp saved data
	private static FashionItem chosenFashionItem1;
	private static FashionItem chosenFashionItem2;
	private static FashionItem chosenFashionItem3;
	private static FashionItem chosenFashionItem4;
	
	// constructor
	public FashionObjectsHelper() {
		super();
	}

	/** Get the shoes ArrayList.
	 * 
	 * @return the shoes
	 */
	public static ArrayList<FashionItem> getShoesArrayList() {
		return shoes_al;
	}

	/** Set the shoes ArrayList.
	 * 
	 * @param shoes the shoes to set
	 */
	public static void setShoesArrayList(ArrayList<FashionItem> shoes) {
		FashionObjectsHelper.shoes_al = shoes;
	}

	public void parseShopStyleData(String response, int type) {
		// TODO
		if (type == SHOES) {
			shoes_al.clear();
			
			shoes_al.add(new FashionItem("234234", "http://profile.ak.fbcdn.net/hprofile-ak-prn1/41565_116907475022811_3894_n.jpg", "http://profile.ak.fbcdn.net/hprofile-ak-prn1/41565_116907475022811_3894_n.jpg", "http://profile.ak.fbcdn.net/hprofile-ak-prn1/41565_116907475022811_3894_n.jpg"));
			shoes_al.add(new FashionItem("12312", "http://cdn2.bigcommerce.com/server3100/722d4/products/15890/images/24478/gp2__35470.1340175565.200.200.jpg", "http://cdn2.bigcommerce.com/server3100/722d4/products/15890/images/24478/gp2__35470.1340175565.200.200.jpg", "http://cdn2.bigcommerce.com/server3100/722d4/products/15890/images/24478/gp2__35470.1340175565.200.200.jpg"));
			shoes_al.add(new FashionItem("553", "http://www.salesredwing.org/image/cache/data/Nike-Free-Run-2-Women-Shoes-108-200x200.jpg", "http://www.salesredwing.org/image/cache/data/Nike-Free-Run-2-Women-Shoes-108-200x200.jpg", "http://www.salesredwing.org/image/cache/data/Nike-Free-Run-2-Women-Shoes-108-200x200.jpg"));
			
		} else if (type == TOPS) {
			tops_al.clear();
			
			tops_al.add(new FashionItem("234234", "http://c-product.images.dreamsretail.com/52-97/52-97011-J.jpg", "http://c-product.images.dreamsretail.com/52-97/52-97011-J.jpg", "http://c-product.images.dreamsretail.com/52-97/52-97011-J.jpg"));
			tops_al.add(new FashionItem("12312", "http://c-product.images.dreamsretail.com/52-96/52-96979-J.jpg", "http://c-product.images.dreamsretail.com/52-96/52-96979-J.jpg", "http://c-product.images.dreamsretail.com/52-96/52-96979-J.jpg"));
			tops_al.add(new FashionItem("553", "http://parts.ncbmwmotorcycles.com/phpengine/site/motorcycle.com/images/ApparelInfo/Reflection-T-shirt-womens-white-small.jpg", "http://parts.ncbmwmotorcycles.com/phpengine/site/motorcycle.com/images/ApparelInfo/Reflection-T-shirt-womens-white-small.jpg", "http://parts.ncbmwmotorcycles.com/phpengine/site/motorcycle.com/images/ApparelInfo/Reflection-T-shirt-womens-white-small.jpg"));			
			
		} else if (type == BOTTOMS) {
			bottoms_al.clear();
			
			bottoms_al.add(new FashionItem("234234", "http://museskis.com/photos/The-North-Face-Freedom-LRBC-Insulated-Pants-Womens-Short-Sizes.jpg", "http://museskis.com/photos/The-North-Face-Freedom-LRBC-Insulated-Pants-Womens-Short-Sizes.jpg", "http://museskis.com/photos/The-North-Face-Freedom-LRBC-Insulated-Pants-Womens-Short-Sizes.jpg"));
			bottoms_al.add(new FashionItem("234234", "http://c-product.images.dreamsretail.com/74-03/74-03382-J.jpg", "http://c-product.images.dreamsretail.com/74-03/74-03382-J.jpg", "http://c-product.images.dreamsretail.com/74-03/74-03382-J.jpg"));
			bottoms_al.add(new FashionItem("234234", "http://www.shopthebills.com/images/products/thumb/BUF_BIL_APARREL_WOMENS_GREY_SWEAT_PANTS_F.jpg", "http://www.shopthebills.com/images/products/thumb/BUF_BIL_APARREL_WOMENS_GREY_SWEAT_PANTS_F.jpg", "http://www.shopthebills.com/images/products/thumb/BUF_BIL_APARREL_WOMENS_GREY_SWEAT_PANTS_F.jpg"));
			
		} else if (type == ACCESSORIES) {
			accessories_al.clear();
			
			accessories_al.add(new FashionItem("234234", "http://s10.thisnext.com/media/largest_dimension/7A415963.jpg", "http://s10.thisnext.com/media/largest_dimension/7A415963.jpg", "http://s10.thisnext.com/media/largest_dimension/7A415963.jpg"));
			accessories_al.add(new FashionItem("234234", "http://www.bayareabags.com/wp-content/uploads/2009/07/gucci-glasses.jpg", "http://www.bayareabags.com/wp-content/uploads/2009/07/gucci-glasses.jpg", "http://www.bayareabags.com/wp-content/uploads/2009/07/gucci-glasses.jpg"));
			accessories_al.add(new FashionItem("234234", "http://www.foothills.uk.com/ekmps/shops/foothills/images/rohan-shivling-women-s-fleece-scarf-849-p.jpg", "http://www.foothills.uk.com/ekmps/shops/foothills/images/rohan-shivling-women-s-fleece-scarf-849-p.jpg", "http://www.foothills.uk.com/ekmps/shops/foothills/images/rohan-shivling-women-s-fleece-scarf-849-p.jpg"));
	
		}
		
		Log.i(TAG, "starting to parse data");
		JSONObject jObject1;
		try {
			jObject1 = new JSONObject(response);
			JSONArray jArray1 = jObject1.getJSONArray("products");
			for (int x = 0; x < jArray1.length(); x++) {
				JSONObject jObject2 = jArray1.getJSONObject(x);
				String ID = jObject2.getString("id");
				JSONArray jArray2 = jObject2.getJSONArray("images");
				JSONObject jObjectLargeImage = jArray2.getJSONObject(2);
				String largeImgUrl = jObjectLargeImage.getString("url");
				
				JSONObject jObjectMediumImage = jArray2.getJSONObject(1);
				String mediumImageUrl = jObjectMediumImage.getString("url");
				
				JSONObject jObjectSmallImage = jArray2.getJSONObject(0);
				String smallImageUrl = jObjectSmallImage.getString("url");
				
				Log.i(TAG, "id: "+ID+", url: "+largeImgUrl);
				
				if (type == SHOES) {
					shoes_al.add(new FashionItem(ID, largeImgUrl, smallImageUrl, mediumImageUrl));
				} else if (type == TOPS) {
					tops_al.add(new FashionItem(ID, largeImgUrl, smallImageUrl, mediumImageUrl));
				} else if (type == BOTTOMS) {
					bottoms_al.add(new FashionItem(ID, largeImgUrl, smallImageUrl, mediumImageUrl));
				} else if (type == ACCESSORIES) {
					accessories_al.add(new FashionItem(ID, largeImgUrl, smallImageUrl, mediumImageUrl));
				}
			}		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the tops
	 */
	public static ArrayList<FashionItem> getTops() {
		return tops_al;
	}

	/**
	 * @param tops the tops to set
	 */
	public static void setTops(ArrayList<FashionItem> tops) {
		FashionObjectsHelper.tops_al = tops;
	}

	/**
	 * @return the bottoms
	 */
	public static ArrayList<FashionItem> getBottoms() {
		return bottoms_al;
	}

	/**
	 * @param bottoms the bottoms to set
	 */
	public static void setBottoms(ArrayList<FashionItem> bottoms) {
		FashionObjectsHelper.bottoms_al = bottoms;
	}

	/**
	 * @return the accessories
	 */
	public static ArrayList<FashionItem> getAccessories() {
		return accessories_al;
	}

	/**
	 * @param accessories the accessories to set
	 */
	public static void setAccessories(ArrayList<FashionItem> accessories) {
		FashionObjectsHelper.accessories_al = accessories;
	}

	/**
	 * @return the chosenFashionItem
	 */
	public static FashionItem getChosenFashionItem1() {
		return chosenFashionItem1;
	}

	/**
	 * @param chosenFashionItem the chosenFashionItem to set
	 */
	public static void setChosenFashionItem(FashionItem chosenFashionItem, int requestCode) {
		if (requestCode == CreateFragment.IMAGE_FRAME1_RESULT) {
			FashionObjectsHelper.chosenFashionItem1 = chosenFashionItem;
		} else if (requestCode == CreateFragment.IMAGE_FRAME2_RESULT) {
			FashionObjectsHelper.chosenFashionItem2 = chosenFashionItem;
		} else if (requestCode == CreateFragment.IMAGE_FRAME3_RESULT) {
			FashionObjectsHelper.chosenFashionItem3 = chosenFashionItem;
		} else if (requestCode == CreateFragment.IMAGE_FRAME4_RESULT) {
			FashionObjectsHelper.chosenFashionItem4 = chosenFashionItem;
		}
	}

	/**
	 * @return the chosenFashionItem2
	 */
	public static FashionItem getChosenFashionItem2() {
		return chosenFashionItem2;
	}

	/**
	 * @return the chosenFashionItem3
	 */
	public static FashionItem getChosenFashionItem3() {
		return chosenFashionItem3;
	}

	/**
	 * @return the chosenFashionItem4
	 */
	public static FashionItem getChosenFashionItem4() {
		return chosenFashionItem4;
	}
}
