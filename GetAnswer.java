package by.htp.library.controler;

import java.util.Scanner;

public class GetAnswer {

	Scanner in;

	public GetAnswer() {
		in = new Scanner(System.in);
	}

	public String getString(String message) {
		System.out.println(message);
		System.out.println("Enter your option: ");
		return in.nextLine();
	} // end method getString

	public String getString() {
		System.out.println("Enter your option: ");
		return in.nextLine();
	} // end method getString

	public int getInt(String message) {
		System.out.println(message);
		System.out.println("Enter your option: ");
		return Integer.parseInt(in.nextLine());
	}

} // end class GetAnswer
