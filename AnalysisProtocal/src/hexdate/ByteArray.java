package hexdate;

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

	public static void main1(String args[]) {
		byte test = 0x48;
		String str = byteToString(test);
		System.out.println(str);
		System.out.println(String.valueOf('C'));
	}

	public static String byteToString1(byte b) {
		int low, high;
		high = b / 0x10;
		low = b & 0x0F;
		StringBuilder a = new StringBuilder();
		a.append(String.valueOf((char) ((high >= 10) ? (high - 10 + 'A') : (high + '0'))));
		a.append(String.valueOf((char) ((low >= 10) ? (low - 10 + 'A') : (low + '0'))));

		return a.toString();
	}
	
	
	public static void main(String args[]){
		byte b = (byte)0xF6;
		System.out.println(byteToString(b));
	}
	
	public static final char[] HEX_CHAR = {'0','1', '2', '3', '4', '5', '6','7', '8','9','A', 'B', 'C', 'D', 'E', 'F'};
	
	public static String byteToString(byte b) {
		char[] buf = new char[2];
		buf[0] = HEX_CHAR[ (b >>> 4) & 0x0f]; // 无符号右移
		buf[1] = HEX_CHAR[ b & 0x0f];

		return new String(buf);
	}
	
	public static String bytesToHexString(byte[] bytes){
		
		char[] buf = new char[bytes.length * 2];
        int index = 0;
        for(byte b : bytes) { // 利用位运算进行转换，可以看作方法一的变种
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }
        return new String(buf);
	}

	// 字符流转换成字符串
	public static String bytesToString(byte[] bytes) {
		final char[] hexArray = "0123456789ABCDEF".toCharArray();
		char[] hexChars = new char[bytes.length * 2];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			int v = bytes[i] & 0xFF;
			hexChars[i * 2] = hexArray[v >>> 4];
			hexChars[i * 2 + 1] = hexArray[v & 0x0F];

			sb.append(hexChars[i * 2]);
			sb.append(hexChars[i * 2 + 1]);
			sb.append(' ');
		}
		return sb.toString();
	}
	
	public static int byteArrayToInt(byte[] b) 
	{
	    int value = 0;
	    for (int i = 0; i < b.length; i++) {
/*	        int shift = (4 - 1 - i) * 8;
	        value += (b[i] & 0x000000FF) << shift;*/
	    	value <<= 8;
	    	value += (((b[i] & 0xf0)>>4)<<4) + (b[i] & 0x0f);
	    	
	    }
	    return value;
	}

}
