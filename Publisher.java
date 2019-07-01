package lms;

class Publisher{
	
	private String publisherName;
	private int publisherId;
	
	public Publisher(String publisherName, int publisherId) {
		this.publisherName = publisherName;
		this.publisherId = publisherId;
	}
	
	public String getPublisherName() {
		return this.publisherName;
	}
	
	public int getPublisherId() {
		return this.publisherId;
	}
	
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	
	@Override
	public String toString() {
		return this.publisherName + "," + Integer.toString(publisherId);
	}
}