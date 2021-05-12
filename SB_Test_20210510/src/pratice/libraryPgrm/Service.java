package pratice.libraryPgrm;

import java.util.List;
import java.util.Scanner;

import pratice.libraryPgrm.entity.Book;
import pratice.libraryPgrm.entity.User;
import pratice.libraryPgrm.util.DateUtil;
/**
 * Service 클래스
 * @author SBLEE
 * @date 2021. 5. 11.
 */
public class Service {

	
	/**
	 * 대여 프로세스
	 * @param book
	 * @param user
	 * @date 2021. 5. 11.
	 */
	public void rent(Book selectedBook, User user) {
		String bookName = selectedBook.getBookName();//반납 선택한 도서명
		String collectDate = DateUtil.CollectDate(3);//반납예정일
		
		//book 튜플 update
		selectedBook.setRentOk(false);
		selectedBook.setCollectDate(collectDate);
		selectedBook.setRenter(user.getId());
		//user 튜플 update
		user.getRentBooks().add(bookName);
		
	}
	
	
	/**
	 * 예약 프로세스
	 * @param book
	 * @param currentUser
	 * @date 2021. 5. 11.
	 */
	public void reservate(Book selectedBook, User currentUser) {
		String bookName = selectedBook.getBookName();//예약 선택한 도서명
		
		//book 튜플 update
		selectedBook.setRentOk(false);
		selectedBook.setReservateOk(false);
		selectedBook.setReservater(currentUser.getId());
		//user 튜플 update
		currentUser.setReservation(bookName);
	}
	
	
	/**
	 * 반납(대여) 프로세스
	 * @param books
	 * @param currentUser
	 * @param selected
	 * @date 2021. 5. 11.
	 */
	public void collect(Book selectedBook, User currentUser) {
		
		//book 튜플 업데이트
		selectedBook.setRentOk(true);
		selectedBook.setCollectDate("");
		selectedBook.setRenter("");
		
		//currentUser가 대여중인 목록(rentBooks)list를 대조탐색해서
		//반납선택한 책만 remove()
		String bookName = selectedBook.getBookName();
		
		//user튜플 업데이트
		for (int j=0; j<currentUser.getRentBooks().size(); j++) {
			if (bookName == currentUser.getRentBooks().get(j)) {
				currentUser.getRentBooks().remove(j);
			}
		}
		
	}
	
	
	/**
	 * 반납(연체) 프로세스
	 * @param books
	 * @param currentUser
	 * @param selected
	 * @date 2021. 5. 11.
	 */
	public void delayCollect(Book selectedBook, User currentUser) {
		//book 튜플 업데이트
		selectedBook.setRentOk(true);
		selectedBook.setReservateOk(true);;
		selectedBook.setCollectDate("");
		selectedBook.setRenter("");
		selectedBook.setDelayDate(0);
		
		//currentUser가 대여중인 목록(rentBooks)list를 대조탐색해서
		//반납선택한 책만 remove()
		String bookName = selectedBook.getBookName();
		
		//user튜플 업데이트
		for (int j=0; j<currentUser.getRentBooks().size(); j++) {
			if (bookName == currentUser.getRentBooks().get(j)) {
				currentUser.getRentBooks().remove(j);
			}
		}
		
	}
	
	/**
	 * 사용자 선택 프로세스
	 * @param users
	 * @return 사용할 유저 객체
	 * @date 2021. 5. 11.
	 */
	public User selectUser(List<User> users) {
		Scanner sc = new Scanner(System.in);
		
		for (int i=0; i<users.size(); ++i) {
			String userId = users.get(i).getId();
			System.out.printf("\t%d.%s\n",i+1,userId);
		}
		int user = sc.nextInt();
		User currentUser = users.get(user-1);
		
		return currentUser;
	}

	
}

