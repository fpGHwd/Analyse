package calculate;

import org.junit.Test;

import excptions.LengthException;
import junit.framework.Assert;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 11:35:43 AM
 * 
 */

public class ByteArray {

	@Test
	public void test() {
		byte[] a = new byte[] { 0x01, 0x00 };
		int result = byteArraytoInt(a);
		Assert.assertEquals(result, 256);
	}

	public static int byteArraytoInt(byte[] b) {
		int result = 0;
		for (int i = 0; i < b.length; i++) {
			result *= 0x100;
			result += b[i];
		}
		return result;
	}

	public static byte[] stringToBytes(String s) {
		byte[] buf = new byte[s.length() / 2];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = (byte) Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
		}
		return buf;
	}
	
	public static byte[] subBytes(byte[] src, int begin, int count) {
		byte[] bs = new byte[count];
		System.arraycopy(src, begin, bs, 0, count);
		return bs;
	}

	/*
	 * public static byte[] stringtoByteArray(String in) throws LengthException
	 * { int templen = in.length(); if (templen % 2 != 0) throw new
	 * LengthException("stringtoByteArray");
	 * 
	 * int temp = templen / 2, high = 0, low =0; byte[] result = new byte[temp];
	 * for (int i = 0; i < temp; i++) { high = in.charAt(2*i); } return null;
	 * 
	 * }
	 */
}
