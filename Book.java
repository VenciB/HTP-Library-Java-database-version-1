package by.htp.library.entity;

public class Book {
	
	private int id;
	private int id_publisher;
	private String title;
	
	public Book( ) {
	}
	
	public Book( int id ) {
		this.id = id;
	}
	
	public Book(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public Book( String title, int id_publisher ) {
		this.title = title;
		this.id_publisher = id_publisher;
	}
	
	public Book(int id, int id_publisher, String title) {
		this.id = id;
		this.id_publisher = id_publisher;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId_publisher() {
		return id_publisher;
	}

	public void setId_publisher(int id_publisher) {
		this.id_publisher = id_publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + id_publisher;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		if (id_publisher != other.id_publisher)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", id_publisher=" + id_publisher + ", title=" + title + "]";
	}	

}
