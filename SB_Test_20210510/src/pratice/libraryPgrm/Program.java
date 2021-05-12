package pratice.libraryPgrm;

import java.util.ArrayList;
import java.util.List;

import pratice.libraryPgrm.entity.Book;
import pratice.libraryPgrm.entity.User;

/**
 * Program
 * @author SBLEE
 * @date 2021. 5. 11
*/

public class Program {

	public static void main(String[] args) {
		
		View view = new View();
		
		//book 초기값 하드코딩
		List<Book> books = new ArrayList<>();
		
		Book book1 = new Book("자바의 정석", "남궁성", true, true, "","","", 0);
		Book book2 = new Book("클라우드 컴퓨팅", "톰 화이트" , true,true, "","","", 0);
		Book book3 = new Book("클린코드", "로버트 마틴", true, true, "", "","",  0);
		Book book4 = new Book("토비의 스프링", "이일민", true, true, "", "", "",0);
		Book book5 = new Book("자바프로그래밍","박용우", false, true, "2021-05-13", "박성현", "",0);//박성현이 대여중
		Book book6 = new Book("JSP&Servlet", "뉴렉처", false, true, "2021-05-14", "박성현", "",0);//박성현이 대여중
		Book book7 = new Book("MySQL", "일월", false, false, "", "", "주영우",0);//주영우가 예약중
		Book book8 = new Book("HTML", "이목", false, false, "", "", "주영우",0);//주영우가 예약중
		Book book9 = new Book("CSS", "삼덕", false, true, "2021-05-09", "강민제", "",3);//강민제가 연체중
		Book book10 = new Book("ES6", "사금", false, true, "2021-05-08", "강민제", "",4);//강민제가 연체중
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);
		books.add(book5);
		books.add(book6);
		books.add(book7);
		books.add(book8);
		books.add(book9);
		books.add(book10);
		
		
		//user 초기값 하드코딩
		List<User> users = new ArrayList<>();
		List<String> initList = new ArrayList<String>();
		List<String> initList1 = new ArrayList<String>();
		initList1.add("자바프로그래밍");
		initList1.add("JSP&Servlet");
		List<String> initList2 = new ArrayList<String>();
		List<String> initList3 = new ArrayList<String>();
		initList3.add("CSS");
		initList3.add("ES6");
		User fakeUser = new User("", initList, "");
		
		User user1 = new User("박성현", initList1, "");
		User user2 = new User("주영우", initList2, "");
		User user3 = new User("강민제", initList3, "");
		users.add(fakeUser);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		
		User currentUser = fakeUser;
		
		exit :
		while (true) {
			String menu = view.doMenu(currentUser);
			
			switch(menu) {
			case "1" ://모든 도서목록 출력
				view.listAll(books, currentUser);
				break;
			case "2" ://모든 대여목록 출력
				view.listRentedAll(books, currentUser);
				break;
			case "3" ://모든 연체목록 출력
				view.listDelayed(books, currentUser);
				break;
			case "4" ://사용자 선택
				currentUser = view.selectUser(users);
				break;
			case "5" ://시스템 종료
				break exit;
			default :
				System.out.println("===============================");
				System.out.println("!!!!올바른 메뉴번호가 아닙니다!!!!");
				System.out.println("===============================");
			}
			
		}
		System.out.println("프로그램을 종료합니다.");
	}

}
