package text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import excptions.LengthException;

/**
 * @author Suzumiya
 * @version Nov 21, 2018 12:32:13 PM
 * 
 */

public class AnalyseMessageText {

	public static void main(String args[]) {
		int a = 0;
		String test_frame = "0007140121145707F1F1001234567848F0F"
				+ "014012114572019000005261900000539230000023938121153";
		byte[] text = { /*
						 * 0x7E, 0x7E, 0x00,0x00, 0x12,
						 * 0x34,0x56,0x78,0x12,0x34,0x30,0x00 ,0x2B, 0x02,
						 */ 0x00, 0x07, 0x14, 0x01, 0x21, 0x14, 0x57, 0x07, (byte) 0xF1, (byte) 0xF1, 0x00, 0x12, 0x34,
				0x56, 0x78, 0x48, (byte) 0xF0, (byte) 0xF0, 0x14, 0x01, 0x21, 0x14, 0x57, 0x20, 0x19, 0x00, 0x00, 0x05,
				0x26, 0x19, 0x00, 0x00, 0x05, 0x39, 0x23, 0x00, 0x00, 0x02, 0x39, 0x38, 0x12, 0x11,
				0x53/* ,0x03,(byte) 0xB0,0x78 */ };
		// byte[] text = ByteArray.stringToBytes(test_frame);

		String message_fix = "7E7E62004455667704D232005A02007C140121170005F1F1004455667748F0"
				+ "F01401211605F4600A05050A0F0F050A0A0A0A0AF5C00064006F007200710072007400"
				+ "780079007B007B007E007FF0F014012117002019000110261900011039230000227438" + "12115303779B";
		// byte[] message_bytes_fix = ByteUtil.toBytes(message_fix);
		
		String message_fix2 = "7E7E62004455667704D232005A02007C140121170005F1F1004455667748F0F01401211605F4600A05050A000F050A0A0A0A0AF5C00064006F007200710072007400780079007B007B007E007FF0F01401211700201900011026190001103923000022743812115303779B";
		byte[] message_bytes_fix = ByteArray.stringToBytes(message_fix2);
		byte[] text_bytes_fix = ByteArray.subBytes(message_bytes_fix, 14, message_bytes_fix.length - 3 - 14);
		List<Factor> result = analyseMessageText(new Header(), text_bytes_fix);

	}

	public static List<Factor> analyseMessageText(Header header, byte[] text) {
		List<Factor> listobj = new ArrayList<Factor>();

		// 中心站地址1
		String cs = "01";
		// 3 // 功能码
		String fc = ByteArray.byteToString(header.function_code);

		// 流水号
		int pos = 0, len = text.length;
		if (pos >= len - 2)
			return null;
		int sn = (int) BCD.BCDToDecimal(ByteArray.subBytes(text, pos, 2));
		pos += 2;
		// 发报时间 // 4
		int year = BCD.BCDBytetoInt(text[pos]), month = BCD.BCDBytetoInt(text[pos + 1]),
				day = BCD.BCDBytetoInt(text[pos + 2]), hour = BCD.BCDBytetoInt(text[pos + 3]),
				min = BCD.BCDBytetoInt(text[pos + 4]), second = BCD.BCDBytetoInt(text[pos + 5]);
		Date mt = new Date(year + 100, month - 1, day, hour, min, second);
		pos += 6;

		// 遥测站地址 // 2
		if (text[pos] != (byte) 0xf1 && text[pos + 1] != (byte) 0xf1)
			return null;
		pos += 2;
		String rsa = BCD.BCDtoString(ByteArray.subBytes(text, pos, 5));
		pos += 5;

		// 遥测站类型 // 5
		String scid = BCD.BCDtoString(text[pos]);
		pos += 1; // 5

		// 观测时间 // 6
		if (text[pos] != (byte) 0xf0 && text[pos + 1] != (byte) 0xf0)
			return null;
		pos += 2;
		year = BCD.BCDBytetoInt(text[pos]);
		month = BCD.BCDBytetoInt(text[pos + 1]);
		day = BCD.BCDBytetoInt(text[pos + 2]);
		hour = BCD.BCDBytetoInt(text[pos + 3]);
		min = BCD.BCDBytetoInt(text[pos + 4]);
		Date ot = new Date(year + 100, month - 1, day, hour, min, 0);
		pos += 5;

		// 要素
		int dataLength, floatLength, bitPos;
		byte idCode, dataCode;
		double value = 0;
		while (pos < text.length) {
			if ((text[pos] == ((byte) 0xF0)) && (text[pos + 1] == ((byte) 0xf0))) {
				pos += 2;
				year = BCD.BCDBytetoInt(text[pos]);
				month = BCD.BCDBytetoInt(text[pos + 1]);
				day = BCD.BCDBytetoInt(text[pos + 2]);
				hour = BCD.BCDBytetoInt(text[pos + 3]);
				min = BCD.BCDBytetoInt(text[pos + 4]);
				ot = new Date(year + 100, month - 1, day, hour, min, 0);
				pos += 5;
			} else {
				idCode = text[pos];
				dataCode = text[pos + 1];
				String id = ByteArray.byteToString(idCode);
				pos += 2;

				dataLength = ((((int)dataCode)& 0xff) >> 3);
				floatLength = ((((int)dataCode) &0xff) % (1 << 3));

				if (idCode == (byte) 0xf4 || idCode == (byte) 0xf5) { // 12组数据

					int step = 0, steps = 12;
					while (step < steps) {
						ot = new Date(year + 100, month - 1, day, hour, min + step * 5, 0);
						value = (double) ByteArray.byteArrayToInt(ByteArray.subBytes(text, pos, dataLength / steps));
						pos += dataLength / steps;
						int i = 0;
						while (i < floatLength) {
							value /= 10;
							i++;
						}
						Factor f = new Factor(cs, rsa, fc, mt, scid, ot, id, String.valueOf(value), new Date());
						listobj.add(f);
						step++;
					}
				} else { // 非12组
					value = (double) BCD.BCDToDecimal(ByteArray.subBytes(text, pos, dataLength));
					int i = 0;
					while (i < floatLength) {
						value /= 10;
						i++;
					}
					pos += dataLength;
					Factor f = new Factor(cs, rsa, fc, mt, scid, ot, id, String.valueOf(value), new Date());
					listobj.add(f);
				}
			}
		}
		if (pos != text.length)
			return null;
		/*
		 * System.out.println(mt); System.out.println(ot);
		 * System.out.println(receive_time);
		 */
		return listobj;
	}
}
