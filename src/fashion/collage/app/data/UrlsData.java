package fashion.collage.app.data;

public class UrlsData {
	private static final String PARTNER_ID = "uid1249-8197057-90";
	
	private static final String SHOPSTYLE_SHOES_IMAGES = "http://api.shopstyle.com/action/apiSearch?pid="+PARTNER_ID+"&cat=womens-shoes&min=0&count=12&format=json";
	private static final String SHOPSTYLE_TOPS_IMAGES = "http://api.shopstyle.com/action/apiSearch?pid="+PARTNER_ID+"&cat=sweatshirts&min=0&count=12&format=json";
	private static final String SHOPSTYLE_BOTTOMS_IMAGES = "http://api.shopstyle.com/action/apiSearch?pid="+PARTNER_ID+"&cat=pants-shorts&min=0&count=12&format=json";
	private static final String SHOPSTYLE_ACCESSORIES_IMAGES = "http://api.shopstyle.com/action/apiSearch?pid="+PARTNER_ID+"&cat=womens-accessories&min=0&count=12&format=json";

	/**
	 * @return the shopstyleShoesImages
	 */
	public static String getShopstyleShoesImages() {
		return SHOPSTYLE_SHOES_IMAGES;
	}

	/**
	 * @return the shopstyleTopsImages
	 */
	public static String getShopstyleTopsImages() {
		return SHOPSTYLE_TOPS_IMAGES;
	}

	/**
	 * @return the shopstyleBottomsImages
	 */
	public static String getShopstyleBottomsImages() {
		return SHOPSTYLE_BOTTOMS_IMAGES;
	}

	/**
	 * @return the shopstyleAccessoriesImages
	 */
	public static String getShopstyleAccessoriesImages() {
		return SHOPSTYLE_ACCESSORIES_IMAGES;
	}
}
