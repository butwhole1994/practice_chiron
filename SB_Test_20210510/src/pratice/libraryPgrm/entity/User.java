package pratice.libraryPgrm.entity;

import java.util.List;

/**
 * 
 * User Entity
 * @author SBLEE
 * @date 2021. 5. 10.
 * 
 */

public class User {
	
	private String id; //이름이자 ID PK
	private List<String> rentBooks;//대여한 책 리스트
	private String reservation;//예약한 책
	
	public User() {
	}
	
	public User(String id, List<String> rentBooks, String reservation) {
		
		this.id = id;
		this.rentBooks = rentBooks;
		this.reservation = reservation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getRentBooks() {
		return rentBooks;
	}

	public void setRentBooks(List<String> rentBooks) {
		this.rentBooks = rentBooks;
	}

	public String getReservation() {
		return reservation;
	}

	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	
}
