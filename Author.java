package lms;

public class Author{
	
	private String authorName;
	private int authorId;
	
	public Author(String authorName, int authorId) {
		this.authorName = authorName;
		this.authorId = authorId;
	}
	
	public int getAuthorId() {
		return this.authorId;
	}
	
	public String getAuthorName() {
		return this.authorName;
	}
	
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	@Override
	public String toString() {
		return authorName + "," + Integer.toString(this.authorId);
	}
}