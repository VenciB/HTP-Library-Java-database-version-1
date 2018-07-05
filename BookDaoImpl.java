package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import by.htp.library.controler.GetAnswer;
import by.htp.library.dao.BookDao;
import by.htp.library.entity.Book;

public class BookDaoImpl implements BookDao {
	
	private static final String SQL_SELECT_BOOK = "SELECT * FROM book";
	private static final String SQL_SPECIFY_BOOKID = " WHERE ID_book = ?";
	private static final String SQL_UPDATE_ONE_BOOK = "UPDATE book SET name = \"";
	private static final String SQL_END_OF_UPDATE_NAME = "\"";
	private static final String SQL_SPECIFY_BOOKONLY = " WHERE ID_book = ";
	private static final String SQL_CREATE_BOOK_1 = "INSERT INTO book( ID_publisher, name ) VALUES ( "; 
	private static final String SQL_CREATE_BOOK_2 = ", \"";
	private static final String SQL_CREATE_BOOK_3 = "\" )";
	private static final String SQL_DELETE_BOOK = "DELETE FROM book WHERE ID_book = "; 
	
	GetAnswer ga = new GetAnswer();
	
	private Connection connect() {

		ResourceBundle rb = ResourceBundle.getBundle("db_config");
		String driver = rb.getString("db.driver");
		String url = rb.getString("db.url");
		String login = rb.getString("db.login");
		String pass = rb.getString("db.pass");

		Connection conn = null;			
		
		try {
			Class.forName( driver );			
			conn = DriverManager.getConnection( url, login, pass );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	} // end method connect
	
	
	public Book read(int id) {
		Connection conn = connect();
		Book book = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement( SQL_SELECT_BOOK + SQL_SPECIFY_BOOKID );
			ps.setInt( 1, id );
			ResultSet rs = ps.executeQuery();
			
			if( rs.next() ) {
				book = new Book( );
				book.setId( rs.getInt( "ID_book" ) );
				book.setId_publisher( rs.getInt( "ID_publisher" ) );
				book.setTitle( rs.getString("name") );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection( conn );
		}
		//Statement st = conn.createStatement();
		
		return book;
	} // end method read
	
	
	private void closeConnection( Connection conn ) {
		if( conn != null ) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	public List<Book> listBooks() {
		
		Connection conn = connect();
		List<Book> listOfBooks = null;
		Book book = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement( SQL_SELECT_BOOK );
			ResultSet rs = ps.executeQuery();

			listOfBooks = new ArrayList<>();
			
			while( rs.next() ) {
				book = new Book();
				book.setId( rs.getInt( "ID_book" ) );
				book.setId_publisher( rs.getInt( "ID_publisher" ) );
				book.setTitle( rs.getString("name") );
				listOfBooks.add(book);
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection( conn );
		}
		//Statement st = conn.createStatement();
		
		return listOfBooks;
	} // end method listBooks

	String newBookName = null;
	public void update(Book book) {
		
		int book_id = book.getId();
		newBookName = ga.getString("Please enter new book title:");
		Connection conn = connect();
		try {
			Statement st = conn.createStatement();
			st.executeUpdate( SQL_UPDATE_ONE_BOOK 
							+ newBookName 
							+ SQL_END_OF_UPDATE_NAME 
							+ SQL_SPECIFY_BOOKONLY
							+ book_id );
		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // end method update

	
	public void create(Book book) {
		Connection conn = connect();
		int id_publisher = book.getId_publisher();
		String newBookName = book.getTitle();
		
		try {
			Statement st = conn.createStatement();
			st.executeUpdate( SQL_CREATE_BOOK_1
							+ id_publisher
							+ SQL_CREATE_BOOK_2
							+ newBookName
							+ SQL_CREATE_BOOK_3 );
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}  // end method create
	

	public void delete(int id) {
		Connection conn = connect();
		
		try {
			Statement st = conn.createStatement();
			st.executeUpdate( SQL_DELETE_BOOK
							+ id );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // end method delete


}