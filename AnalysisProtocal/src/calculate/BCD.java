package calculate;

import org.junit.Test;

import junit.framework.Assert;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 12:16:14 PM
 * 
 */

public class BCD {

	public static long BCDToDecimal(byte[] bcd) {
		return Long.valueOf(BCD.BCDtoString(bcd));
	}

	public static String BCDtoString(byte[] bcd) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < bcd.length; i++) {
			sb.append(BCDtoString(bcd[i]));
		}

		return sb.toString();
	}

	public static String BCDtoString(byte bcd) {
		StringBuffer sb = new StringBuffer();

		byte high = (byte) (bcd & 0xf0);
		high >>>= (byte) 4;
		high = (byte) (high & 0x0f);
		byte low = (byte) (bcd & 0x0f);

		sb.append(high);
		sb.append(low);

		return sb.toString();
	}

	public static int BCDBytetoInt(byte bcd) {
		byte high = (byte) (bcd & 0xf0);
		high >>= (byte) 4;
		byte low = (byte) (bcd & 0x0f);
		return (10 * high + low);
	}

	public static void main(String args[]) {
		System.out.println(BCDBytetoInt((byte)0x15));
	}

	@Test
	public void test() {
		byte[] bcd = new byte[] { 0x23, 0x44, 0x64 };
		long result = BCDToDecimal(bcd);
		System.out.println("lnvalid");
		Assert.assertEquals(234464L, result);
	}

}
