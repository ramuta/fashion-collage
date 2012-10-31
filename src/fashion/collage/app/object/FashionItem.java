package fashion.collage.app.object;

public class FashionItem {
	private String ID;
	private String largeImageUrl;
	private String mediumImageUrl;
	private String smallImageUrl;

	public FashionItem() {
		super();
	}
	
	public FashionItem(String ID, String largeImageUrl, String smallImageUrl, String mediumImageUrl) {
		super();
		this.setID(ID);
		this.setLargeImageUrl(largeImageUrl);
		this.setSmallImageUrl(smallImageUrl);
		this.setMediumImageUrl(mediumImageUrl);
	}

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(String ID) {
		this.ID = ID;
	}

	/**
	 * @return the largeImageUrl
	 */
	public String getLargeImageUrl() {
		return largeImageUrl;
	}

	/**
	 * @param largeImageUrl the largeImageUrl to set
	 */
	public void setLargeImageUrl(String largeImageUrl) {
		this.largeImageUrl = largeImageUrl;
	}

	/**
	 * @return the smallImageUrl
	 */
	public String getSmallImageUrl() {
		return smallImageUrl;
	}

	/**
	 * @param smallImageUrl the smallImageUrl to set
	 */
	public void setSmallImageUrl(String smallImageUrl) {
		this.smallImageUrl = smallImageUrl;
	}

	/**
	 * @return the mediumImageUrl
	 */
	public String getMediumImageUrl() {
		return mediumImageUrl;
	}

	/**
	 * @param mediumImageUrl the mediumImageUrl to set
	 */
	public void setMediumImageUrl(String mediumImageUrl) {
		this.mediumImageUrl = mediumImageUrl;
	}
}
