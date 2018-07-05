package by.htp.library.controler;

import java.util.List;

import by.htp.library.dao.BookDao;
import by.htp.library.dao.impl.BookDaoImpl;
import by.htp.library.entity.Book;

public class MainConsoleController {

	static PrintMessage pm = new PrintMessage();
	
	public static void main(String[] args) {
		
		BookDao dao = new BookDaoImpl();
		GetAnswer ga = new GetAnswer();
		List<Book> books = null;
		Book book = null;
		String answer = null;
		int numberOfBook = 0;
		int bookIDupdate = 0;
		int publisherID = 0;
		String newBookTitle = null; 
		
		do {
			printMenu();
			answer = ga.getString();
			switch (answer) {
			case "1":
				// 1) view book catalog - print a list of all books:
				pm.printTopMessage("Printing a list of all books from library:");
				books = dao.listBooks();
				for( int i = 0; i < books.size(); i++ ) {
					System.out.println(books.get(i));
				}
				break;
			case "2":
				// 2) view single book info:
				pm.printTopMessage("Printing full information about one book:");
				numberOfBook = ga.getInt("Enter the book ID: ");
				book = dao.read( numberOfBook );
				System.out.println( book );
				break;
			case "3":
				// 2.1) update book - update book title by ID:
				pm.printTopMessage("Updating the book title by ID:");
				bookIDupdate = ga.getInt("Enter the book ID you want to update:");
				for( int i = 0; i < books.size(); i++ ) {
					if( books.get(i).getId() == bookIDupdate ) {
						book = books.get(i);
					}
				}
				dao.update( book );
				pm.printTopMessage("Book updated!");
				break;
			case "4":
				// 2.2) delete book - delete book by ID
				pm.printTopMessage("DELETING the book by ID:");
				bookIDupdate = ga.getInt("Enter the book ID you want to DELETE:");
				for( int i = 0; i < books.size(); i++ ) {
					if( books.get(i).getId() == bookIDupdate ) {
						bookIDupdate = books.get(i).getId();
					}
				}
				dao.delete( bookIDupdate );
				pm.printTopMessage("Book DELETED from library!");
				break;
			case "5":
				// 3) add new book - enter Publisher ID and book Title:
				pm.printTopMessage("Adding new book by Publisher ID and book Title:");
				publisherID = ga.getInt("Please enter Publisher ID:");
				newBookTitle = ga.getString("Please enter new book Title:");
				Book newBook = new Book( newBookTitle, publisherID );
				dao.create(newBook);
				pm.printTopMessage("New book added in a library!");
				break;
			case "exit":
				break;
			default:
				System.out.println("Wrong entry. Try again.");
				break;
			} // end switch

		} while (!answer.equals("exit"));

		if (answer.equals("exit")) {
			System.out.println("Thank you, and come back!");
		} else {
			System.out.println("Something went wrong. " + "Exiting the application");
		}

		
	} // end main
	
	public static void printMenu() {
		pm.printMenu();
	}
	
}