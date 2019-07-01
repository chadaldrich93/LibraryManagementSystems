package lms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class LMSService{
	
	private static ArrayList<Author> authorList = new ArrayList<Author>();
	private static ArrayList<Book> bookList = new ArrayList<Book>();
	private static ArrayList<Publisher> publisherList = new ArrayList<Publisher>();
	
	private final static int OBJECT_NOT_FOUND = -1;
	
	public static ArrayList<Author> getAuthors(){
		return authorList;
	}
	
	public static ArrayList<Book> getBooks(){
		return bookList;
	}
	
	public static ArrayList<Publisher> getPublishers(){
		return publisherList;
	}
	
	public static void loadLists() throws FileNotFoundException, IOException{
		LMSDataAccessObject.readAuthorListFromFile(authorList);
		LMSDataAccessObject.readBookListFromFile(bookList);
		LMSDataAccessObject.readPublisherListFromFile(publisherList);
	}
	
	public static void addAuthorToList(Author data) throws IOException{
		int id = data.getAuthorId();
		if (findAuthorInList(id) != OBJECT_NOT_FOUND){
			System.out.println("Invalid addition, value with id " +
                                id + " already exists in Authors file");
	        return;
        }
		authorList.add(data);
		LMSDataAccessObject.addAuthor(data);
		System.out.println("Author with id " + id + " was added");
	}
	
	public static void addBookToList(Book data) throws IOException{
		int bookId = data.getBookId();
		int authorId = data.getAuthorId();
		int publisherId = data.getPublisherId();
		if (findBookInList(bookId) != OBJECT_NOT_FOUND){
			System.out.println("Invalid addition, book with id " +
                                bookId + " already exists in Books file");
	        return;
        }
		if (findAuthorInList(authorId) == OBJECT_NOT_FOUND){
			System.out.println("Invalid addition, author with id " +
                                authorId + " does not exist in Authors file");
	        return;
        }
		if (findAuthorInList(publisherId) == OBJECT_NOT_FOUND){
			System.out.println("Invalid addition, publisher with id " +
                                publisherId + " does not exist in " + 
					            "Publishers file");
	        return;
        }
		bookList.add(data);
		LMSDataAccessObject.addBook(data);
		System.out.println("Book with id " + bookId + " was added");
	}
	
	public static void addPublisherToList(Publisher data) throws IOException{
		int id = data.getPublisherId();
		if (findPublisherInList(id) != OBJECT_NOT_FOUND){
			System.out.println("Invalid addition, publisher with id " +
                                id + " already exists in Publishers file");
	        return;
        }
		publisherList.add(data);
		LMSDataAccessObject.addPublisher(data);
		System.out.println("Publisher with id " + id + " was added");
	}
	
	public static void removeBookFromList(int bookId) throws IOException{
		int index = findBookInList(bookId);
		if (index == OBJECT_NOT_FOUND) {
			System.out.println("Invalid removal, book with id " + bookId + 
					           " was not found");
			return;
		}
		bookList.remove(index);
		LMSDataAccessObject.updateBooks(bookList);
		System.out.println("Book with id " + bookId + "was removed");
	}
	
	public static void removeAuthorFromList(int authorId) throws IOException{
		int index = findAuthorInList(authorId);
		if (index == OBJECT_NOT_FOUND) {
			System.out.println("Invalid removal, author with id " + authorId + 
					           " was not found");
			return;
		}
		//if author is removed, all books by that author must be removed
		removeBooksByAuthorId(authorId);
		LMSDataAccessObject.updateBooks(bookList);
		authorList.remove(index);
		LMSDataAccessObject.updateAuthors(authorList);
		System.out.println("Author with id " + authorId + " was removed");
	}
	
	public static void removePublisherFromList(int publisherId) 
			                                   throws IOException{
		int index = findPublisherInList(publisherId);
		if (index == OBJECT_NOT_FOUND) {
			System.out.println("Invalid removal, book with id " + publisherId +
					           " was not found");
			return;
		}
		//if publisher is removed, all books by that publisher must be removed
		removeBooksByPublisherId(publisherId);
		LMSDataAccessObject.updateBooks(bookList);
		publisherList.remove(index);
		LMSDataAccessObject.updatePublishers(publisherList);
		System.out.println("Publisher with id " + publisherId + " was removed");
	}
	
	public static void updateAuthorInList(Author newAuthor) throws IOException{
		int id = newAuthor.getAuthorId();
		int index = findAuthorInList(id);
		if (index == OBJECT_NOT_FOUND) {
			System.out.println("Invalid update, author with id " + id +
					           " was not found");
			return;
		}
		authorList.remove(index);
		authorList.add(newAuthor);
		LMSDataAccessObject.updateAuthors(authorList);
		System.out.println("Author with id " + id + " was updated");
	}
	
	public static void updateBookInList(Book newBook) throws IOException{
		int id = newBook.getBookId();
		int index = findBookInList(id);
		if (index == OBJECT_NOT_FOUND) {
			System.out.println("Invalid update, book with id " + id +
					           " was not found");
			return;
		}
		Book oldBook = retrieveBook(id);
		if (newBook.getAuthorId() != oldBook.getAuthorId()) {
			System.out.println("Invalid update, author id can not be " +
		                       " changed from " + oldBook.getAuthorId() +
		                       " to " + newBook.getAuthorId());
			return;
		}
		if (newBook.getPublisherId() != oldBook.getPublisherId()) {
			System.out.println("Invalid update, publisher id can not be " +
		                       " changed from " + oldBook.getPublisherId() +
		                       " to " + newBook.getPublisherId());
			return;
		}
		bookList.remove(index);
		bookList.add(newBook);
		LMSDataAccessObject.updateBooks(bookList);
		System.out.println("Book with id " + id + " was updated");
	}
    
	public static void updatePublisherInList(Publisher newPublisher) 
	                                         throws IOException{
		int id = newPublisher.getPublisherId();
		int index = findPublisherInList(id);
		if (index == OBJECT_NOT_FOUND) {
			System.out.println("Invalid update, author with id " + id +
					           " was not found");
			return;
		}
		publisherList.remove(index);
		publisherList.add(newPublisher);
		LMSDataAccessObject.updatePublishers(publisherList);
		System.out.println("Publisher with id " + id + " was updated");
	}
	
	public static Author retrieveAuthor(int id) {
		int index = findAuthorInList(id);
		if (index == OBJECT_NOT_FOUND) {
			System.out.println("Invalid retrieval, author with id " + id +
					           " was not found.");
			return null;
		}
		System.out.println(authorList.get(index).toString());
		return authorList.get(index);
	}
	
	public static Book retrieveBook(int id) {
		int index = findBookInList(id);
		if (index == OBJECT_NOT_FOUND) {
			System.out.println("Invalid retrieval, book with id " + id +
					           " was not found.");
			return null;
		}
		System.out.println(bookList.get(index).toString());
		return bookList.get(index);
	}
	
	public static Publisher retrievePublisher(int id) {
		int index = findPublisherInList(id);
		if (index == OBJECT_NOT_FOUND) {
			System.out.println("Invalid retrieval, publisher with id " + id +
					           " was not found.");
			return null;
		}
		System.out.println(publisherList.get(index).toString());
		return publisherList.get(index);
	}
	
	//private helper methods 
	//start here
	private static int findAuthorInList(int id) {
		int location = OBJECT_NOT_FOUND;
		int currentIndex = 0;
		for (Author a : authorList) {
			if (a.getAuthorId() == id) {
				location = currentIndex;
			}
			currentIndex++;
		}
		return location;
	}
	
	private static int findBookInList(int id) {
		int location = OBJECT_NOT_FOUND;
		int currentIndex = 0;
		for (Book b : bookList) {
			if (b.getBookId() == id) {
				location = currentIndex;
			}
			currentIndex++;
		}
		return location;
	}
	
	private static int findPublisherInList(int id) {
		int location = OBJECT_NOT_FOUND;
		int currentIndex = 0;
		for (Publisher p : publisherList) {
			if (p.getPublisherId() == id) {
				location = currentIndex;
			}
			currentIndex++;
		}
		return location;
	}
	
	private static void removeBooksByAuthorId(int authorId) {
		ArrayList<Integer> candidates = new ArrayList<Integer>();
		int index = 0;
		for (Book b : bookList) {
			if (b.getAuthorId() == authorId) {
				candidates.add(index);
			}
			index++;
		}
		for (int i: candidates) {
			bookList.remove(i);
		}
	}
	
	private static void removeBooksByPublisherId(int publisherId) {
		ArrayList<Integer> candidates = new ArrayList<Integer>();
		int index = 0;
		for (Book b : bookList) {
			if (b.getPublisherId() == publisherId) {
				candidates.add(index);
			}
			index++;
		}
		for (int i : candidates) {
			bookList.remove(i);
		}
	}
	
}