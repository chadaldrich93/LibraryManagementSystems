package lms;

class Book{
	
	private String bookName;
	private int bookId;
	private int authorId;
	private int publisherId;
	
	public Book(String bookName, int bookId, int authorId, int publisherId) {
		this.bookName = bookName;
		this.bookId = bookId;
		this.authorId = authorId;
		this.publisherId = publisherId;
	}
	
	public String getBookName() {
		return this.bookName;
	}
	
	public int getBookId() {
		return this.bookId;
	}
	
	public int getAuthorId() {
		return this.authorId;
	}
	
	public int getPublisherId() {
		return this.publisherId;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	
	@Override
	public String toString() {
		return this.bookName + "," + Integer.toString(bookId) + "," +
	           Integer.toString(this.authorId) + "," +
			   Integer.toString(this.publisherId);
	}
}