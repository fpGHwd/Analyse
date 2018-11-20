package field;

import java.io.Serializable;
import java.util.Date;

import org.junit.Test;

import excptions.LengthException;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 1:51:03 PM
 * 
 */

public class SendMessageTime implements Serializable,Fields {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int LENGTH = 6;

	private Date date;

	@SuppressWarnings("deprecation")
	public SendMessageTime(byte[] in) throws LengthException {
		if (in.length == LENGTH) {
			date = new Date(calculate.BCD.BCDBytetoInt(in[0]) + 100, calculate.BCD.BCDBytetoInt(in[1])-1,
					calculate.BCD.BCDBytetoInt(in[2]), calculate.BCD.BCDBytetoInt(in[3]),
					calculate.BCD.BCDBytetoInt(in[4]), calculate.BCD.BCDBytetoInt(in[5]));
		} else {
			throw new LengthException(this.toString());
		}
	}

	public static void main(String args[]) throws LengthException{
		byte[] ta_date = {0x18,0x11,0x20,0x14,0x16,0x40};
		SendMessageTime stt = new SendMessageTime(ta_date);
		System.out.println(stt.date);
	}

	@Override
	public int getLength() {
		return LENGTH;
	}

}
