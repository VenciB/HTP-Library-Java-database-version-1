package by.htp.library.controler;

public class PrintMessage {

	public void printTopMessage(String message) {
		printLongLine();
		System.out.println("\n" + message);
		printLongLine();
	}

	public void printLongLine() {
		System.out.println("==========================================================");
	}

	public void printShortLine() {
		System.out.println("============================");
	}

	public void printMenu() {
		printShortLine();
		printLongLine();
		System.out.println("Menu list, please choose one option by selecting "
				+ "\nthe number of option, or enter \"exit\" to exit.");
		printLongLine();
		System.out.println("1 - Print a list of all books from library");
		System.out.println("2 - Print full information about one book");
		System.out.println("3 - Update book title by ID");
		System.out.println("4 - Delete book by ID");
		System.out.println("5 - Add new book in library");
		printLongLine();
		printShortLine();
	}

}
