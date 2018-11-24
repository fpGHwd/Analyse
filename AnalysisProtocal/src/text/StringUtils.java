package text;

/**
 * @author Suzumiya
 * @version Nov 22, 2018 11:30:05 AM
 * 
 */

public class StringUtils {

	public static void main(String args[]) {
		System.out.print(formFloatString(0, 0));
		System.out.print(formFloatString(0, 3));
		System.out.print(formFloatString(34, 1));
		System.out.print(formFloatString(34, 2));
		System.out.print(formFloatString(34, 3));
	}

	public static String formFloatString(long value, int floatLength) {
		String val = String.valueOf(value);
		String value_string;
		if (floatLength != 0) {
			if (val.length() > floatLength)
				value_string = val.substring(0, val.length() - floatLength) + "."
						+ val.substring(val.length() - floatLength, val.length());
			else if (val.length() == floatLength) {
				value_string = "0." + val;
			} else {
				String temp_str = "";
				for (int i = val.length(); i < floatLength; i++) {
					temp_str += "0";
				}
				value_string = "0." + temp_str + val;
			}
		} else
			value_string = val;
		return value_string;

	}
}
