package lms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class LMSTests {

	/*@Test
	void testCsvToAuthor() {
		Author a = LMSDataAccessObject.convertCsvStringToAuthor("Jack,1");
		assertEquals(a.getAuthorName(), "Jack");
		assertEquals(a.getAuthorId(), 1);
	}
	
	@Test
	void testCsvToBook() {
		Book b = LMSDataAccessObject.convertCsvStringToBook("Jack,1,2,3");
		assertEquals(b.getBookName(), "Jack");
		assertEquals(b.getBookId(), 1);
		assertEquals(b.getAuthorId(),2);
		assertEquals(b.getPublisherId(),3);
	}

	@Test
	void testCsvToPublisher() {
		Publisher p = LMSDataAccessObject.convertCsvStringToPublisher("Jack,1");
		assertEquals(p.getPublisherName(), "Jack");
		assertEquals(p.getPublisherId(), 1);
	}
	*/
	
	@Test
	void testAddAuthor() throws IOException{
		Author a = new Author("Jack",1);
		Author b = new Author("Dave",2);
		LMSDataAccessObject.addAuthor(a);
		LMSDataAccessObject.addAuthor(b);
		BufferedReader br = new BufferedReader(new FileReader("src/lms/Authors"));
		String s = br.readLine();
		String t = br.readLine();
		assertEquals(s, "Jack,1");
		assertEquals(t, "Dave,2");
		br.close();
		resetFile("Authors");
	}
	
	@Test
	void testAddBook() throws IOException{
		Book a = new Book("Jack",1,1,2);
		Book b = new Book("Dave",2,1,2);
		LMSDataAccessObject.addBook(a);
		LMSDataAccessObject.addBook(b);
		BufferedReader br = new BufferedReader(new FileReader("src/lms/Books"));
		String s = br.readLine();
		String t = br.readLine();
		assertEquals(s, "Jack,1,1,2");
		assertEquals(t, "Dave,2,1,2");
		br.close();
		resetFile("Books");
	}
	
	@Test
	void testAddPublisher() throws IOException{
		Publisher a = new Publisher("Jack",1);
		Publisher b = new Publisher("Dave",2);
		LMSDataAccessObject.addPublisher(a);
		LMSDataAccessObject.addPublisher(b);
		BufferedReader br = new BufferedReader(new FileReader("src/lms/Publishers"));
		String s = br.readLine();
		String t = br.readLine();
		assertEquals(s, "Jack,1");
		assertEquals(t, "Dave,2");
		br.close();
		resetFile("Publishers");
	}
	
	@Test
	void testUpdateAuthor() throws IOException{
		ArrayList<Author> a = new ArrayList<Author>();
		Author b = new Author("Jeff",1);
		Author c = new Author("Dave",2);
		a.add(b);
		LMSDataAccessObject.updateAuthors(a);
		BufferedReader r = new BufferedReader(new FileReader("src/lms/Authors"));
		assertEquals(r.readLine(), "Jeff,1");
		BufferedReader s = new BufferedReader(new FileReader("src/lms/Authors"));
		a.add(c);
		LMSDataAccessObject.updateAuthors(a);
		assertEquals(s.readLine(), "Jeff,1");
		assertEquals(s.readLine(), "Dave,2");
		BufferedReader t = new BufferedReader(new FileReader("src/lms/Authors"));
		a.clear();
		LMSDataAccessObject.updateAuthors(a);
		assertEquals(t.readLine(), null);
		resetFile("Authors");
	}
	
	@Test
	void testUpdateBook() throws IOException{
		ArrayList<Book> a = new ArrayList<Book>();
		Book b = new Book("Jeff",1,1,1);
		Book c = new Book("Dave",2,1,1);
		a.add(b);
		LMSDataAccessObject.updateBooks(a);
		BufferedReader r = new BufferedReader(new FileReader("src/lms/Books"));
		assertEquals(r.readLine(), "Jeff,1,1,1");
		BufferedReader s = new BufferedReader(new FileReader("src/lms/Books"));
		a.add(c);
		LMSDataAccessObject.updateBooks(a);
		assertEquals(s.readLine(), "Jeff,1,1,1");
		assertEquals(s.readLine(), "Dave,2,1,1");
		BufferedReader t = new BufferedReader(new FileReader("src/lms/Books"));
		a.clear();
		LMSDataAccessObject.updateBooks(a);
		assertEquals(t.readLine(), null);
		resetFile("Books");
	}
	
	@Test
	void testUpdatePublisher() throws IOException{
		ArrayList<Publisher> a = new ArrayList<Publisher>();
		Publisher b = new Publisher("Jeff",1);
		Publisher c = new Publisher("Dave",2);
		a.add(b);
		LMSDataAccessObject.updatePublishers(a);
		BufferedReader r = new BufferedReader(new FileReader("src/lms/Publishers"));
		assertEquals(r.readLine(), "Jeff,1");
		BufferedReader s = new BufferedReader(new FileReader("src/lms/Publishers"));
		a.add(c);
		LMSDataAccessObject.updatePublishers(a);
		assertEquals(s.readLine(), "Jeff,1");
		assertEquals(s.readLine(), "Dave,2");
		BufferedReader t = new BufferedReader(new FileReader("src/lms/Publishers"));
		a.clear();
		LMSDataAccessObject.updatePublishers(a);
		assertEquals(t.readLine(), null);
		resetFile("Publishers");
	}
	
	@Test
	void testReadAuthorListFromFile() throws IOException{
		ArrayList<Author> a = new ArrayList<Author>();
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/lms/Authors"));
		LMSDataAccessObject.readAuthorListFromFile(a);
		assertTrue(a.isEmpty());
		Author b = new Author("Jack",1);
		Author c = new Author("Dave",2);
		LMSDataAccessObject.addAuthor(b);
		LMSDataAccessObject.readAuthorListFromFile(a);
		Author d = a.get(0);
		assertEquals(d.getAuthorName(), b.getAuthorName());
		assertEquals(d.getAuthorId(), b.getAuthorId());
		LMSDataAccessObject.addAuthor(c);
		LMSDataAccessObject.readAuthorListFromFile(a);
		Author e = a.get(1);
		assertEquals(e.getAuthorName(), c.getAuthorName());
		assertEquals(e.getAuthorId(), c.getAuthorId());
		resetFile("Authors");
	}
	
	@Test
	void testReadPublisherListFromFile() throws IOException{
		ArrayList<Publisher> a = new ArrayList<Publisher>();
		LMSDataAccessObject.readPublisherListFromFile(a);
		assertTrue(a.isEmpty());
		Publisher b = new Publisher("Jack",1);
		Publisher c = new Publisher("Dave",2);
		LMSDataAccessObject.addPublisher(b);
		LMSDataAccessObject.readPublisherListFromFile(a);
	    Publisher d = a.get(0);
		assertEquals(d.getPublisherName(), b.getPublisherName());
		assertEquals(d.getPublisherId(), b.getPublisherId());
		LMSDataAccessObject.addPublisher(c);
		LMSDataAccessObject.readPublisherListFromFile(a);
		Publisher e = a.get(1);
		assertEquals(e.getPublisherName(), c.getPublisherName());
		assertEquals(e.getPublisherId(), c.getPublisherId());
		resetFile("Authors");
	}
	
	@Test
	void testReadBookListFromFile() throws IOException{
		ArrayList<Book> a = new ArrayList<Book>();
		LMSDataAccessObject.readBookListFromFile(a);
		assertTrue(a.isEmpty());
		Book b = new Book("Jack",1,1,1);
		Book c = new Book("Dave",2,1,1);
		LMSDataAccessObject.addBook(b);
		LMSDataAccessObject.readBookListFromFile(a);
		Book d = a.get(0);
		assertEquals(d.getBookName(), b.getBookName());
		assertEquals(d.getBookId(), b.getBookId());
		assertEquals(d.getAuthorId(), b.getAuthorId());
		assertEquals(d.getPublisherId(), b.getPublisherId());
		LMSDataAccessObject.addBook(c);
		LMSDataAccessObject.readBookListFromFile(a);
		Book e = a.get(1);
		assertEquals(e.getBookName(), c.getBookName());
		assertEquals(e.getBookId(), c.getBookId());
		assertEquals(e.getAuthorId(), c.getAuthorId());
		assertEquals(e.getPublisherId(), c.getPublisherId());
		resetFile("Authors");
	}
	
	@Test
	void testAuthorServices() throws IOException{
		LMSService.loadLists();
		Author a = new Author("Jack",1);
		Author b = new Author("Dave",2);
		LMSService.addAuthorToList(a);
		ArrayList<Author> al = LMSService.getAuthors();
		Author c = LMSService.retrieveAuthor(1);
		assertEquals(a.getAuthorId(),c.getAuthorId());
		assertEquals(a.getAuthorName(), c.getAuthorName());
		//LMSService.addAuthorToList(b);
		//assertTrue(al.size() == 1);
		//LMSService.retrieveAuthor(2);
		//LMSService.removeAuthorFromList(2);
		//LMSService.updateAuthorInList(b);
		b = new Author("Dave",1);
		LMSService.updateAuthorInList(b);
		Author d = LMSService.retrieveAuthor(1);
		assertEquals(b.getAuthorId(), d.getAuthorId());
		assertEquals(b.getAuthorName(), d.getAuthorName());
		LMSService.removeAuthorFromList(1);
		assertTrue(LMSService.getAuthors().isEmpty());
		resetFile("Authors");
	}
	
	@Test
	void testPublisherServices() throws IOException{
		LMSService.loadLists();
		Publisher a = new Publisher("Jack",1);
		Publisher b = new Publisher("Dave",2);
		LMSService.addPublisherToList(a);
		ArrayList<Publisher> al = LMSService.getPublishers();
		Publisher c = LMSService.retrievePublisher(1);
		assertEquals(a.getPublisherId(),c.getPublisherId());
		assertEquals(a.getPublisherName(), c.getPublisherName());
		//LMSService.addPublisherToList(b);
		//assertTrue(al.size() == 1);
		//LMSService.retrievePublisher(2);
		//LMSService.removePublisherFromList(2);
		//LMSService.updatePublisherInList(b);
		b = new Publisher("Dave",1);
		LMSService.updatePublisherInList(b);
		Publisher d = LMSService.retrievePublisher(1);
		assertEquals(b.getPublisherId(), d.getPublisherId());
		assertEquals(b.getPublisherName(), d.getPublisherName());
		LMSService.removePublisherFromList(1);
		assertTrue(LMSService.getPublishers().isEmpty());
		resetFile("Publishers");
	}
	
	@Test
	void testBookServices() throws IOException{
		LMSService.loadLists();
		//LMSService.retrieveBook(1);
		Book b = new Book("Blue",1,1,1);
		//LMSService.updateBookInList(b);
		//LMSService.removeBookFromList(1);
		//LMSService.addBookToList(b);
		Publisher p = new Publisher("Jack",1);
		LMSService.addPublisherToList(p);
		//LMSService.addBookToList(b);
		Author a = new Author("Dave",1);
		LMSService.addAuthorToList(a);
		LMSService.addBookToList(b);
		Book d = LMSService.retrieveBook(1);
		assertEquals(b.getBookName(), d.getBookName());
		assertEquals(b.getBookId(), d.getBookId());
		assertEquals(b.getPublisherId(), d.getPublisherId());
		assertEquals(b.getAuthorId(), d.getAuthorId());
		LMSService.removeBookFromList(1);
		LMSService.addBookToList(b);
		Book c = new Book("Red",2,1,1);
		Book e = new Book("Purple",1,2,1);
		Book f = new Book("Orange",1,1,2);
		Book g = new Book("Green",1,1,1);
		//LMSService.updateBookInList(c);
		//LMSService.updateBookInList(e);
		//LMSService.updateBookInList(f);
		LMSService.updateBookInList(g);
		Book h = LMSService.retrieveBook(1);
		assertEquals(g.getBookName(), h.getBookName());
		assertEquals(g.getBookId(), h.getBookId());
		assertEquals(g.getAuthorId(), h.getAuthorId());
		assertEquals(g.getPublisherId(), h.getPublisherId());
		//LMSService.removeAuthorFromList(1);
		//LMSService.retrieveBook(1);
		//LMSService.removePublisherFromList(1);
		//LMSService.retrieveBook(1);
		resetFile("Authors");
		resetFile("Publishers");
		resetFile("Books");
	}
	
	private static void resetFile(String path) throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/lms/" + path));
		bw.write("");
		bw.close();
	}
	
	
}
