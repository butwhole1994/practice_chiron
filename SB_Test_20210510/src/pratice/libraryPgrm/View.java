package pratice.libraryPgrm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pratice.libraryPgrm.entity.Book;
import pratice.libraryPgrm.entity.User;
import pratice.libraryPgrm.util.DateUtil;

/**
 * View 클래스
 * @author SBLEE
 * @date 2021. 5. 11.
 */
public class View {
	/**
	 * 메뉴 화면
	 * @param currentUser
	 * @return
	 * @date 2021. 5. 11.
	 */
	protected String doMenu(User currentUser) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("┌──────────────────────────────────┐");
		System.out.println("│        회원제 도서관리 프로그램        │");
		System.out.println("└──────────────────────────────────┘");
		
		System.out.printf("%s\t\t",currentUser.getId());//현재유저 아이디
		String today = DateUtil.Today();
		System.out.printf("%s\n", today);//현재 날짜
		System.out.println("\t1.도서목록 조회");
		System.out.println("\t2.대여목록 조회");
		System.out.println("\t3.연체목록 조회");
		System.out.println("\t4.사용자 선택");
		System.out.println("\t5.종료");
		System.out.print("\t메뉴번호를 선택하세요>");
		String menu = sc.nextLine();
		
		return menu;
	}
	
	/**
	 * 모든 도서목록 화면
	 * @param books
	 * @param currentUser
	 * @date 2021. 5. 11.
	 */
	protected void listAll(List<Book> books, User currentUser) {
		Scanner sc = new Scanner(System.in);
		String menu = "";
		System.out.println("┌──────────────────────────────────┐");
		System.out.println("│           모든 도서목록             │");
		System.out.println("└──────────────────────────────────┘");
		System.out.printf("%s\t\t",currentUser.getId());
		String today = DateUtil.Today();
		System.out.printf("%s\n", today);
		
		for (int i=0; i<books.size(); ++i) {
			Book book = books.get(i);
			System.out.println("===============================");
			System.out.printf("\t도서명 : %s\n", book.getBookName());
			System.out.printf("\t글쓴이 : %s\n", book.getWriter());
			System.out.printf("\t대여여부 : %s\n", book.isRentOk() ? "대여가능":"대여불가능");
			System.out.printf("\t예약여부 : %s\n", book.isReservateOk() ? "예약가능" : "예약불가능");
			System.out.printf("\t반납예정일 : %s\n", book.getCollectDate());
			System.out.printf("\t대여자 : %s\n", book.getRenter());
			System.out.printf("\t예약자 : %s\n", book.getReservater());
			System.out.printf("\t연체일수 : %d\n", book.getDelayDate());
			System.out.println("===============================");
		}
		
		do {
			System.out.println("\t1.도서 대여");
			System.out.println("\t2.도서 예약");
			System.out.println("\t3.상위 메뉴로");
			System.out.print("\t메뉴번호를 입력하세요>");
			menu = sc.nextLine();
			
			switch(menu) {
			case "1" :
				if (currentUser.getId().equals("")) {//사용자가 없을 경우 예외처리
					System.out.println("===============================");
					System.out.println("!!!!사용자를 선택해주세요!!!!");
					System.out.println("===============================");
					break;
				} else {//도서대여 화면으로
					rentView(books,currentUser);
				}
				return;
			case "2" :
				if (currentUser.getId().equals("")) {//사용자가 없을 경우 예외처리
					System.out.println("===============================");
					System.out.println("!!!!사용자를 선택해주세요!!!!");
					System.out.println("===============================");
					break;
				} else {//도서예약 화면으로
					reservateView(books,currentUser);
				}
				return;
			case "3" :
				return;
			default :
				System.out.println("===============================");
				System.out.println("!!!!!올바른 메뉴가 아닙니다!!!!!");
				System.out.println("===============================");
				break;
			}
		} while(!menu.equals("3"));
		
	}
	
	/**
	 * 도서대여 화면
	 * @param books
	 * @param currentUser
	 * @date 2021. 5. 11.
	 */
	private void rentView(List<Book> books, User currentUser) {
		Scanner sc = new Scanner(System.in);
		Service service = new Service();
		
		
		System.out.println("┌──────────────────────────────────┐");
		System.out.println("│          대여 가능한 도서            │");
		System.out.println("└──────────────────────────────────┘");
		System.out.printf("%s\t\t",currentUser.getId());
		String today = DateUtil.Today();
		System.out.printf("%s\n", today);
		
		//선택 가능한 도서의 index값을 바인딩 할 List변수 선언
		List<Integer> canSelect = new ArrayList<Integer>();
		
		for (int i=0; i<books.size(); ++i) {
			boolean rentIsOk = books.get(i).isRentOk();
			
			if (rentIsOk == true) {
				System.out.println("===============================");
				System.out.printf("\t%d.도서명 : %s\n",i+1,books.get(i).getBookName());
				System.out.printf("\t  글쓴이 : %s\n",books.get(i).getWriter());
				System.out.println("===============================");
				canSelect.add(i+1);
			}
			
		}
		System.out.printf("\t%d. 상위메뉴로\n",0);
		System.out.print("\t대여할 도서번호를 입력하세요>");
		int menu;
		
		if (sc.hasNextInt()) {
			menu= sc.nextInt();
			System.out.println();
			if (canSelect.contains(menu)) {//선택가능한 메뉴인지 유효성검사 
				Book selectedBook = books.get(menu-1);
				
				service.rent(selectedBook, currentUser);//대여처리
				System.out.println("===============================");
				System.out.printf("반납일자는 %s입니다.\n",selectedBook.getCollectDate());
				System.out.println("===============================");
				
				listAll(books, currentUser);//모든 도서목록 화면으로
			} else if (menu == 0) {//상위메뉴로
				listAll(books, currentUser);
			} else {
				System.out.println("===============================");
				System.out.println("!!!!대여가능한 도서번호가 아닙니다!!!!");
				System.out.println("===============================");
				rentView(books, currentUser);
			}
		} else {
			System.out.println("===============================");
			System.out.println("!!!!대여가능한 도서번호가 아닙니다!!!!");
			System.out.println("===============================");
			rentView(books, currentUser);
		}
	}
	
	/**
	 * 도서예약 화면
	 * @param books
	 * @param currentUser
	 * @date 2021. 5. 11.
	 */
	private void reservateView(List<Book> books, User currentUser) {
		
		Service service = new Service();
		String today = DateUtil.Today();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("┌──────────────────────────────────┐");
		System.out.println("│          예약 가능한 도서            │");
		System.out.println("└──────────────────────────────────┘");
		System.out.printf("%s\t\t",currentUser.getId());
		System.out.printf("%s\n", today);
		
		//선택 가능한 도서의 index값을 바인딩 할 List변수 선언
		List<Integer> canSelect = new ArrayList<Integer>();
		
		for (int i=0; i<books.size(); ++i) {
			boolean isReservOk = books.get(i).isReservateOk();
			
			if (isReservOk == true) {
				System.out.println("===============================");
				System.out.printf("\t%d.도서명 : %s\n",i+1,books.get(i).getBookName());
				System.out.printf("\t  글쓴이 : %s\n",books.get(i).getWriter());
				System.out.println("===============================");
				canSelect.add(i+1);
			}
		}
		System.out.printf("\t%d. 상위메뉴로\n",0);
		System.out.print("예약할 도서번호를 입력하세요>");
		
		int menu;
		
		if (sc.hasNextInt()) {
			menu= sc.nextInt();
			System.out.println();
			if (canSelect.contains(menu)) {
				Book book = books.get(menu-1);
				
				service.reservate(book, currentUser);//예약처리
				listAll(books, currentUser);//모든 도서목록 화면으로
				
			} else if (menu == 0) {
				listAll(books, currentUser);
			} else {
				System.out.println("===============================");
				System.out.println("!!!!예약가능한 도서번호가 아닙니다!!!!");
				System.out.println("===============================");
				
				reservateView(books, currentUser);//도서예약 화면으로
			}
		} else {
			System.out.println("===============================");
			System.out.println("!!!!예약가능한 도서번호가 아닙니다!!!!");
			System.out.println("===============================");
			
			reservateView(books, currentUser);//도서예약 화면으로
		}
	}
	
	
	
	/**
	 *모든 대여목록 화면
	 * @param books
	 * @param currentUser
	 * @date 2021. 5. 11.
	 */
	public void listRentedAll(List<Book> books, User currentUser) {
		String today = DateUtil.Today();
		Scanner sc = new Scanner(System.in);
		String menu = "";
		
		if (currentUser.getId().equals("")) {//사용자가 없을 경우 예외처리
			System.out.println("===============================");
			System.out.println("!!!!사용자를 선택해주세요!!!!");
			System.out.println("===============================");
			return;
		}
		
		System.out.println("┌──────────────────────────────────┐");
		System.out.println("│         대여하고 있는 도서           │");
		System.out.println("└──────────────────────────────────┘");
		System.out.printf("%s\t\t",currentUser.getId());
		System.out.printf("%s\n", today);
		
		//currentUser의 rentBooks(대여중인 도서명) 속성을 바인딩할 변수 선언
		List<String> rentBooks = currentUser.getRentBooks();
		List<Integer> booksIndex = new ArrayList<Integer>();
		
		//rentBooks리스트의 도서명이 books상의 어느index에 위치하는지 탐색
		//해당 index를 booksIndex에 add()
		//SELECT a.index FROM a.BOOKS, b.RENTBOOKS  WHERE a.bookname = b.bookname;
		for (int i=0; i<rentBooks.size(); i++) {
			System.out.println("===============================");
			System.out.printf("\t 도서명 : %s\n",rentBooks.get(i));
			for(int j=0; j<books.size(); j++) {
				String bookName = books.get(j).getBookName();
				if(rentBooks.get(i).equals(bookName)) {
					System.out.printf("\t 글쓴이 : %s\n",books.get(j).getWriter());
					booksIndex.add(j);
					System.out.println("===============================");
				}
			}
		}
		exit :
			do {
				System.out.println("\t1.도서 반납");
				System.out.println("\t2.상위메뉴로");
				System.out.print("메뉴번호를 입력하세요>");
				menu = sc.nextLine();
				
				switch(menu) {
				case "1" ://반납 화면으로
					collectView(books,currentUser,booksIndex);
					break exit;
				case "2" ://상위메뉴로
					break exit;
				default :
					System.out.println("===============================");
					System.out.println("!!!!!올바른 메뉴가 아닙니다!!!!!");
					System.out.println("===============================");
					break;
				}
			} while (!menu.equals("2"));
	}
	
	/**
	 * 반납목록(대여)화면
	 * @param books
	 * @param currentUser
	 * @param booksIndex
	 * @date 2021. 5. 11.
	 */
	private void collectView(List<Book> books, User currentUser, List<Integer> booksIndex) {
		
		if (booksIndex.size()==0) {
			System.out.println("===============================");
			System.out.println("!!!!반납할 도서가 없습니다.!!!!");
			System.out.println("===============================");
			
			return;
		}
		
		Service service = new Service();
		String today = DateUtil.Today();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("┌──────────────────────────────────┐");
		System.out.println("│             반납할 도서             │");
		System.out.println("└──────────────────────────────────┘");
		System.out.printf("%s\t\t",currentUser.getId());
		System.out.printf("%s\n", today);
		for (int index : booksIndex) {
			System.out.println("===============================");
			System.out.printf("\t%s 도서명 : %s\n",index,books.get(index).getBookName());
			System.out.printf("\t  글쓴이 : %s\n",books.get(index).getWriter());
			System.out.println("===============================");
		}
		
		System.out.print("반납할 도서를 선택해주세요.>");
		
		int menu;
		
		if (sc.hasNextInt()) {
			menu= sc.nextInt();
			System.out.println();
			if (booksIndex.contains(menu)) {
				Book selectedBook = books.get(menu);
				service.collect(selectedBook, currentUser);
				
				listRentedAll(books, currentUser);
			} else {
				System.out.println("===============================");
				System.out.println("!!!!반납 가능한 도서번호가 아닙니다!!!!");
				System.out.println("===============================");
				
				collectView(books, currentUser, booksIndex);
			}
		} else {
			System.out.println("===============================");
			System.out.println("!!!!반납 가능한 도서번호가 아닙니다!!!!");
			System.out.println("===============================");
			
			collectView(books, currentUser, booksIndex);
		}
		
	}

	
	
	/**
	 * 연체목록 화면
	 * @param books
	 * @param currentUser
	 * @date 2021. .5 11.
	 */

	public void listDelayed(List<Book> books, User currentUser) {
		String today = DateUtil.Today();
		Scanner sc = new Scanner(System.in);
		String menu = "";
		
		
		if (currentUser.getId().equals("")) {//사용자 없을 시 예외처리
			System.out.println("===============================");
			System.out.println("!!!!사용자를 선택해주세요!!!!");
			System.out.println("===============================");
			return;
		}
		
		System.out.println("┌──────────────────────────────────┐");
		System.out.println("│         연체하고 있는 도서           │");
		System.out.println("└──────────────────────────────────┘");
		System.out.printf("%s\t\t",currentUser.getId());
		System.out.printf("%s\n", today);
		
		//currentUser의 rentBooks(대여중인 도서명) 속성을 바인딩할 변수 선언
		List<String> rentBooks = currentUser.getRentBooks();
		List<Integer> booksIndex = new ArrayList<Integer>();
		
		//rentBooks리스트의 도서명이 books상의 어느index에 위치하는지 탐색
		//해당 index를 booksIndex에 add()
		for (int i=0; i<rentBooks.size(); i++) {
			for (int j=0; j<books.size(); j++) {
				if(rentBooks.get(i).equals(books.get(j).getBookName())) {
					booksIndex.add(j);
				}
			}
		}
		List<Integer> delayIndex = new ArrayList<Integer>();
		//대여중인 도서가 연체일수가 있는지 확인
		//SELECT a.index FROM a.BOOKS, b.RENTBOOKS  WHERE a.bookname = b.bookname AND a.delaydate > 0;
		for (int index : booksIndex) {
			if (books.get(index).getDelayDate() > 0) {
				System.out.println("===============================");
				System.out.printf("\t 도서명 : %s\n",books.get(index).getBookName());
				System.out.printf("\t 글쓴이 : %s\n",books.get(index).getWriter());
				System.out.println("===============================");
				delayIndex.add(index);
			}
		}
		
		exit :
			do {
				System.out.println("\t1.도서 반납");
				System.out.println("\t2.상위메뉴로");
				System.out.print("메뉴번호를 입력하세요>");
				menu = sc.nextLine();
				
				switch(menu) {
				case "1" ://도서반납화면
					delayCollectView(books,currentUser,delayIndex);
					break exit;
				case "2" ://상위메뉴
					return;
				default :
					System.out.println("===============================");
					System.out.println("!!!!!올바른 메뉴가 아닙니다!!!!!");
					System.out.println("===============================");
					break;
				}
			} while (!menu.equals(2));
	}
	
	/**
	 * 반납목록(연체) 화면
	 * @param books
	 * @param currentUser
	 * @param booksIndex
	 * @date 2021. 5. 11.
	 */
	private void delayCollectView(List<Book> books, User currentUser, List<Integer> delayIndex) {
		
		if (delayIndex.size()==0) {
			System.out.println("===============================");
			System.out.println("!!!!반납할 도서가 없습니다.!!!!");
			System.out.println("===============================");
			
			return;
		}
		
		Service service = new Service();
		String today = DateUtil.Today();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("┌──────────────────────────────────┐");
		System.out.println("│             반납할 도서             │");
		System.out.println("└──────────────────────────────────┘");
		
		System.out.printf("%s\t\t",currentUser.getId());
		System.out.printf("%s\n", today);
		
		for (int index : delayIndex) {
			System.out.println("===============================");
			System.out.printf("\t%s 도서명 : %s\n",index,books.get(index).getBookName());
			System.out.printf("\t  글쓴이 : %s\n",books.get(index).getWriter());
			System.out.println("===============================");
		}
		System.out.print("반납할 도서를 선택해주세요.>");
		
		int menu;
		
		if (sc.hasNextInt()) {
			menu= sc.nextInt();
			System.out.println();
			if (delayIndex.contains(menu)) {
				Book selectedBook = books.get(menu);
				service.delayCollect(selectedBook, currentUser);
				
				listDelayed(books, currentUser);//연체목록 화면으로
			} else {
				System.out.println("===============================");
				System.out.println("!!!!반납 가능한 도서번호가 아닙니다!!!!");
				System.out.println("===============================");
				
				collectView(books, currentUser, delayIndex);
			}
		} else {
			System.out.println("===============================");
			System.out.println("!!!!반납 가능한 도서번호가 아닙니다!!!!");
			System.out.println("===============================");
			
			collectView(books, currentUser, delayIndex);
		}
	}

	
	
	/**
	 * 유저선택 화면
	 * @param users
	 * @return
	 * @date 2021. 5. 11.
	 */
	public User selectUser(List<User> users) {
		String today = DateUtil.Today();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("┌──────────────────────────────────┐");
		System.out.println("│             유저 선택              │");
		System.out.println("└──────────────────────────────────┘");
		System.out.printf("%s\n", today);
		System.out.println("===============================");
		
		for (int i=1; i<users.size(); ++i) {
			System.out.printf("\t%d.%s\n",i,users.get(i).getId());
		}
		System.out.println("===============================");
		System.out.print("\t0. 선택하지 않음\n");
		System.out.print("선택할 유저번호 입력>");
		
		int selectedUser;
		
		if (sc.hasNextInt()) {
			selectedUser = sc.nextInt();
			System.out.println();
			if (selectedUser == 0) {
				return users.get(0);
			} else if(selectedUser<0 || selectedUser>users.size()){
				System.out.println("===============================");
				System.out.println("!!!!잘못된 유저번호 입니다!!!!");
				System.out.println("===============================");
				return users.get(0);
			} else {
				User currentUser = users.get(selectedUser);
				return currentUser;
			}
		} else {
			System.out.println("===============================");
			System.out.println("!!!!잘못된 유저번호 입니다!!!!");
			System.out.println("===============================");
			return users.get(0);
		}
	}
}
