package lms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LMSMenu {
    
	public static void main(String[] args) throws IOException{
		System.out.println("Hello, welcome to Library Management Systems" +
	                       "(LMS)");
		printUsageMessage();
		Scanner input = new Scanner(System.in);
		LMSService.loadLists();
		String command;
		while(true) {
			command = input.next();
			switch (command) {
			    case "A":
			    	parseThenCallAddition(input);
			    	continue;
			    case "R":
			    	parseThenCallRemoval(input);
			    	continue;
			    case "U":
			    	parseThenCallUpdate(input);
			    	continue;
			    case "F":
			    	parseThenCallRetrieval(input);
			    	continue;
			    case "Q":
			    	System.out.println("Library Management Systems is quitting " +
			    			           "execution, goodbye");
			    	return;
			    default:
			    	printUsageMessage();
			        continue;
			}
		}
	}
	
	private static void parseThenCallAddition(Scanner input) 
	                                          throws IOException{
		String objectType= scanObjectType(input);
		Object data;
		switch (objectType) {
		    case "Author":
			    data = validateAuthorData(input);
			    Author authorData = (Author)data;
			    LMSService.addAuthorToList(authorData);
			    break;
		    case "Book":
			    data = validateBookData(input);
			    Book bookData = (Book)data;
			    LMSService.addBookToList(bookData);
			    break;
		    case "Publisher":
			    data = validatePublisherData(input);
			    Publisher publisherData = (Publisher)data;
			    LMSService.addPublisherToList(publisherData);
		        break;
		    default:
		    	System.out.println("Add: A objectType(String) data1 data2 ...");
			    return;
		}
	}
	
	private static void parseThenCallRemoval(Scanner input) throws IOException{
		String objectType = scanObjectType(input);
		int objectId;
		try {
			objectId = input.nextInt();
		}
		catch (NoSuchElementException e) {
			System.out.println("Remove: R objectType(String) tupleId(integer)");
			return;
		}
		if (objectType.equals("Author")) {
			LMSService.removeAuthorFromList(objectId);
		}
		else if (objectType.equals("Book")) {
			LMSService.removeBookFromList(objectId);
		}
		else if (objectType.equals("Publisher")){
		    LMSService.removePublisherFromList(objectId);
		}
		else {
			System.out.println("Remove: R objectType(String) tupleId(integer)");
		}
	}
	
	private static void parseThenCallUpdate(Scanner input) throws IOException{
		String objectType = scanObjectType(input);
		Object newData;
		switch (objectType) {
		    case "Author":
			    newData = validateAuthorData(input);
			    Author newAuthor = (Author)newData;
			    LMSService.updateAuthorInList(newAuthor);
			    break;
		    case "Book":
			    newData = validateBookData(input);
			    Book newBook = (Book)newData;
			    LMSService.updateBookInList(newBook);
			    break;
		    case "Publisher":
			    newData = validatePublisherData(input);
			    Publisher newPublisher = (Publisher)newData;
			    LMSService.updatePublisherInList(newPublisher);
		        break;
		    default:
		    	System.out.println("Update: U objectType(String) " + 
	                               "data1 data2 ...");
			    return;
		}
	}
	
	private static void parseThenCallRetrieval(Scanner input) 
			                                   throws FileNotFoundException,
	                                                  IOException{
		String objectType = scanObjectType(input);
		int objectId;
		try {
			objectId = input.nextInt();
		}
		catch (NoSuchElementException e) {
			System.out.println("Retrieval: F objectType(String) " +
		                       "tupleId(integer)");
			return;
		}
		if (objectType.equals("Author")) {
			LMSService.retrieveAuthor(objectId);
		}
		else if (objectType.equals("Book")) {
			LMSService.retrieveBook(objectId);
		}
		else if (objectType.equals("Publisher")) {
			LMSService.retrievePublisher(objectId);
		}
		else {
			System.out.println("Retrieval: F objectType(String) " +
		                       "tupleId(integer)");
		}
	}
	
	private static void printUsageMessage() {
		System.out.println("Valid functions are Add, Remove, Update, and " +
	                       " Retrieve");
		System.out.println("Proper invocation is as follows");
		System.out.println("Add: A objectType(String) data1 data2 ...");
		System.out.println("Remove: R objectType(String) tupleId(integer)");
		System.out.println("Update: U objectType(String) data1 data2 ..."); 
		System.out.println("Retrieval: F objectType(String) tupleId(integer)");
		System.out.println("Quit: Q");
	}
	
	private static String scanObjectType(Scanner input) {
		String type = "";
		try {
			type = input.next();
		}
		catch (NoSuchElementException e){
			System.out.println("Input does not have objectType");
		}
		return type;
	}
	
	private static Author validateAuthorData(Scanner input){
		Author author = null;
		try {
		    String authorName = input.next();
		    int authorId = input.nextInt();
		    author = new Author(authorName, authorId);
		}
		catch (NoSuchElementException e) {
			System.out.println("Author: authorName(String) authorID(integer)");
			input.nextLine();
		}
		return author;
	}
	
	private static Book validateBookData(Scanner input){
		Book book = null;
		try {
			String bookName = input.next();
		    int bookId = input.nextInt();
		    int authorId = input.nextInt();
		    int publisherId = input.nextInt();
		    book = new Book(bookName, bookId, authorId, publisherId);
		}
		catch (NoSuchElementException n) {
			System.out.println("Book: bookName(String) bookId(integer) " + 
                               "authorId(integer) publisherId(integer)");
			input.nextLine();
		}
		return book;
	}
	
	private static Publisher validatePublisherData(Scanner input){
		Publisher publisher = null;
		try {
			String publisherName = input.next();
		    int publisherId = input.nextInt();
		    publisher = new Publisher(publisherName, publisherId);
		}
		catch (NoSuchElementException n) {
			System.out.println("Publisher: publisherName(String) publisherId(integer)");
			input.nextLine();
		}
		return publisher;
	}
}
