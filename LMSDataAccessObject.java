package lms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LMSDataAccessObject{
	
	private static final int NAME_INDEX = 0;
	private static final int ID_INDEX = 1;
	//book tuples will hold 3 indices, including the books
	//author and publisher
	private static final int AUTHORID_INDEX = 2;
	private static final int PUBLISHERID_INDEX = 3;
	
	private static final File Authors = new File("src/lms/Authors");
	private static final File Books = new File("src/lms/Books");
	private static final File Publishers = new File("src/lms/Publishers");
	
	public static void addAuthor(Author data) throws FileNotFoundException,
	                                                 IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(Authors,
				                                                  true));
		writer.write(data.toString());
		writer.newLine();
		writer.close();
	}
	
	public static void addBook(Book data) throws FileNotFoundException,
                                                 IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(Books, 
        		                                                  true));
        writer.write(data.toString());
        writer.newLine();
        writer.close();
    }
	
	public static void addPublisher(Publisher data) throws FileNotFoundException,
                                                 IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(Publishers, 
        		                                                  true));
        writer.write(data.toString());
        writer.newLine();
        writer.close();
    }
	
	//whenever a tuple is removed, or updated
	//LMSService class's list will be updated first
	//so the file can be made up to date by simply overwriting the file with
	//the current version of the list
	public static void updateAuthors(ArrayList<Author> authorList) 
			                         throws FileNotFoundException, IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(Authors));
		for (Author a : authorList) {
			writer.write(a.toString());
			writer.newLine();
		}
		writer.close();
	}
	
	public static void updateBooks(ArrayList<Book> bookList) 
                                     throws FileNotFoundException, IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(Books));
        for (Book b : bookList) {
            writer.write(b.toString());
            writer.newLine();
        }
        writer.close();
    }
	
	public static void updatePublishers(ArrayList<Publisher> publisherList) 
                                     throws FileNotFoundException, IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(Publishers));
        for (Publisher p : publisherList) {
            writer.write(p.toString());
            writer.newLine();
        }
        writer.close();
    }
	
	public static void readAuthorListFromFile(ArrayList<Author> authorList) 
			                         throws FileNotFoundException, IOException{
        authorList.clear();
        Author temp;
        BufferedReader reader = new BufferedReader(new FileReader(Authors));
        String currentLine = reader.readLine();
        while (currentLine != null) {
            temp = convertCsvStringToAuthor(currentLine);
            authorList.add(temp);
            currentLine = reader.readLine();
        }
        reader.close();
    }

    public static void readBookListFromFile(ArrayList<Book> bookList) throws 
                                            FileNotFoundException, IOException{
        bookList.clear();
        Book temp;
        BufferedReader reader = new BufferedReader(new FileReader(Books));
        String currentLine = reader.readLine();
        while (currentLine != null) {
            temp = convertCsvStringToBook(currentLine);
            bookList.add(temp);
            currentLine = reader.readLine();
        }
        reader.close();
    }

    public static void readPublisherListFromFile(ArrayList<Publisher> 
                                                 publisherList)
                                     throws FileNotFoundException, IOException{
        publisherList.clear();
        Publisher temp;
        BufferedReader reader = new BufferedReader(new FileReader(Publishers));
        String currentLine = reader.readLine();
        while (currentLine != null) {
            temp = convertCsvStringToPublisher(currentLine);
            publisherList.add(temp);
            currentLine = reader.readLine();
        }
        reader.close();
    }
	
    //private helper methods
    //start here
	private static Author convertCsvStringToAuthor(String csv){
		String[] csvStringAsArray = csv.split(",");
		String authorName = csvStringAsArray[NAME_INDEX];
		int authorId = Integer.parseInt(csvStringAsArray[ID_INDEX]);
		Author dataObject = new Author(authorName, authorId);
		return dataObject;
	}
	
	private static Book convertCsvStringToBook(String csv){
		String[] csvStringAsArray = csv.split(",");
		String bookName = csvStringAsArray[NAME_INDEX];
		int bookId = Integer.parseInt(csvStringAsArray[ID_INDEX]);
		int authorId = Integer.parseInt(csvStringAsArray[AUTHORID_INDEX]);
		int publisherId = Integer.parseInt(
				                         csvStringAsArray[PUBLISHERID_INDEX]);
		Book dataObject = new Book(bookName, bookId, authorId, publisherId);
		return dataObject;
	}
	
	private static Publisher convertCsvStringToPublisher(String csv){
		String[] csvStringAsArray = csv.split(",");
		String publisherName = csvStringAsArray[NAME_INDEX];
		int publisherId = Integer.parseInt(csvStringAsArray[ID_INDEX]);
		Publisher dataObject = new Publisher(publisherName, publisherId);
		return dataObject;
	}
	
}