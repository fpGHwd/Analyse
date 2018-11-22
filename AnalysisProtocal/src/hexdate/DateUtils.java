package hexdate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Suzumiya
 * @version Nov 22, 2018 12:45:52 PM
 * 
 */

public class DateUtils {
	public static String dateToString(Date date) {
		SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format0.format(date.getTime());
		return time;
	}

	public static String hexBytesToStringDate(byte[] hexdate) {
		return dateToString(hexBytesToDate(hexdate));
	}

	public static Date hexBytesToDate(byte[] hexdate) {
		if (hexdate.length < 5)
			return null;
		int pos = 0;
		int year = BCD.BCDBytetoInt(hexdate[pos]);
		int month = BCD.BCDBytetoInt(hexdate[pos + 1]);
		int day = BCD.BCDBytetoInt(hexdate[pos + 2]);
		int hour = BCD.BCDBytetoInt(hexdate[pos + 3]);
		int min = BCD.BCDBytetoInt(hexdate[pos + 4]);
		int second;
		if (hexdate.length == 5) {
			second = 0;
		} else
			second = BCD.BCDBytetoInt(hexdate[pos + 5]);

		return new Date(year + 100, month - 1, day, hour, min, second);
	}

	public static String dateToStringDate(Date date) {
		return dateToString(date);
	}

	public static String dateStringByStep(Date date, int type, int step) {
		Date result = null;
		switch (type) {
		case 0:
			result = new Date(date.getYear(), date.getMonth(), date.getDay(), date.getHours(), date.getMinutes(),
					date.getSeconds());
		case 1: // day step
			result = new Date(date.getYear(), date.getMonth(), date.getDay() + step, date.getHours(), date.getMinutes(),
					date.getSeconds());
			break;
		case 2: // hours step
			result = new Date(date.getYear(), date.getMonth(), date.getDay(), date.getHours()+step, date.getMinutes(),
					date.getSeconds());
			break;
		case 3:
			result = new Date(date.getYear(), date.getMonth(), date.getDay(), date.getHours(), date.getMinutes() + step,
					date.getSeconds());
			break;
		}
		return dateToString(result);
	}
}
