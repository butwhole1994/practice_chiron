package pratice.libraryPgrm.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 현재날짜 출력 메소드
	 * @author SBLEE
	 * @date 2021. 5. 11.
	 */
	public static String Today() {
		
		Date date = new Date();
		SimpleDateFormat today_ = new SimpleDateFormat("YYYY-MM-dd");
		String today = today_.format(date);
		
		return today;
	}
	
	/**
	 * 현재날짜에 date+ 메소드
	 * @param date
	 * @return 날짜 포맷
	 * @date 2021. 5. 11.
	 */
	public static String CollectDate(int date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		SimpleDateFormat today = new SimpleDateFormat("YYYY-MM-dd");
		cal.add(Calendar.DATE, +date);
		String collectDate = today.format(cal.getTime());
		return collectDate;
	}
}
