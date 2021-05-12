package pratice.libraryPgrm.entity;

/**
 * 
 * Book Entity
 * @author SBLEE
 * @date 2021. 5. 10.
 * 
 */

public class Book {
	
	private String bookName;//PK
	private String writer;//글쓴이
	private boolean rentOk;//대여가능여부
	private boolean reservateOk;//예약가능여부
	private String collectDate;//반납예정일
	private String renter;//대여자
	private String reservater;//예약자
	private int delayDate;//연체일수
	
	public Book() {
	}
	
	public Book(String bookName, String writer, boolean rentOk, boolean ReservatOk, 
			String collectDate, String renter, String reservater, int delayDate) {
		
		this.bookName = bookName;
		this.writer = writer;
		this.rentOk = rentOk;
		this.reservateOk = ReservatOk;
		this.reservater = reservater;
		this.collectDate = collectDate;
		this.renter = renter;
		this.delayDate = delayDate;
		
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public boolean isRentOk() {
		return rentOk;
	}

	public void setRentOk(boolean rentOk) {
		this.rentOk = rentOk;
	}

	public boolean isReservateOk() {
		return reservateOk;
	}

	public void setReservateOk(boolean reservatOk) {
		this.reservateOk = reservatOk;
	}

	public String getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(String collectDate) {
		this.collectDate = collectDate;
	}

	public String getRenter() {
		return renter;
	}

	public void setRenter(String renter) {
		this.renter = renter;
	}

	public String getReservater() {
		return reservater;
	}

	public void setReservater(String reservater) {
		this.reservater = reservater;
	}

	public int getDelayDate() {
		return delayDate;
	}

	public void setDelayDate(int delayDate) {
		this.delayDate = delayDate;
	}

	
	
}
