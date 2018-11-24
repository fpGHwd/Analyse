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

		String[] messages = new String[] {
				// 链路维持报
				"7E7E00001234567812342F00080200071401211457130395E5",

				// 测试报
				"7E7E000012345678123430002B020007140121145707F1F1001234567848"
						+ "F0F01401211457201900000526190000053923000002393812115303B0" + "78",

				// 均匀时段水文信息报
				"7E7E62004455667704D231004E02007E140121170015F1F1004455667748"
						+ "F0F0140121160504180000003923000020010000211800002140000021"
						+ "3900002144000021680000220300002213000022330000223600002262" + "0000227103A3BE",

				// 定时报
				"7E7E62004455667704D232005A02007C140121170005F1F1004455667748"
						+ "F0F01401211605F4600A05050A000F050A0A0A0A0AF5C00064006F0072"
						+ "00710072007400780079007B007B007E007FF0F0140121170020190001"
						+ "1026190001103923000022743812115303779B",
				// 加报报
				"7E7E62004455667704D233002B020076140121165723F1F1004455667748"
						+ "F0F0140121165720190001103923000022602619000110381211530368" + "BE",
				// 小时报
				"7E7E62004455667704D234006802007D140121170010F1F1004455667748"
						+ "F0F01401211605F4600A05050A000F050A0A0A0A0AF0F0140121170026"
						+ "19000110F0F01401211605F5C00064006F007200710072007400780079"
						+ "007B007B007E007FF0F014012117002019000110392300002271381211" + "53030111", };

		for (int i = 0; i < messages.length; i++) {
			byte[] temp = ByteArray.stringToBytes(messages[i]);
			byte[] temp_message_text = ByteArray.subBytes(temp, 14, temp.length - 3 - 14);
			System.out.println("Message " + i + " process:");
			Header header = new Header();
			header.function_code = temp[10];
			List<Factor> result = analyseMessageText(header, temp_message_text);
			if (result == null) {
				System.out.println(" return null");
			} else {
				System.out.println(" return:");
				for (int ri = 0; ri < result.size(); ri++) {
					Factor temp_fac = result.get(ri);
					System.out.println(temp_fac.getCenter_station() + " " + temp_fac.getTelementry_station() + " "
							+ temp_fac.getFunction_code() + " " + temp_fac.getSend_time() + " "
							+ temp_fac.getStation_type() + " " + temp_fac.getF0F0() + " " + temp_fac.getIdentifier()
							+ " " + temp_fac.getIdentifier_num() + " " + temp_fac.getReceive_time());
				}
			}
			System.out.println();
		}
	}

	public static List<Factor> analyseMessageText(Header header, byte[] text) {
		List<Factor> listobj = new ArrayList<Factor>();
		Date ot_date;
		String temp_str, ot;

		// 中心站地址 // 1 // 来自header目前全部是01，也不会用到
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
		String mt = DateUtils.hexBytesToStringDate(ByteArray.subBytes(text, pos, 6));
		pos += 6;

		// 测试报
		if (pos == len)
			return null;

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
		if (text[pos] != (byte) 0xf0 && text[pos + 1] != (byte) 0xf0) // 无效
			return null;
		pos += 2;
		ot_date = DateUtils.hexBytesToDate(ByteArray.subBytes(text, pos, 5));
		ot = DateUtils.dateToString(ot_date);
		pos += 5;

		// 要素
		int dataLength, floatLength;
		byte idCode, dataCode;
		long value = 0;

		while (pos < text.length) {

			// 穿插的有多的观测时间的F0F0比如定时报里面
			if ((text[pos] == ((byte) 0xF0)) && (text[pos + 1] == ((byte) 0xf0))) {
				pos += 2;

				ot_date = DateUtils.hexBytesToDate(ByteArray.subBytes(text, pos, 5));
				ot = DateUtils.dateToString(ot_date);
				// 步长时间函数
				// 观察时间后面必然会跟着相应的数据，无需标识
				pos += 5;
			} else {

				int time_step = 0, step_type = 0;
				String id = null;

				// 数据格式区
				{
					idCode = text[pos];
					dataCode = text[pos + 1];
					id = ByteArray.byteToString(idCode);
					pos += 2;

					dataLength = ((dataCode & 0x000000ff) >> 3);
					floatLength = ((dataCode & 0x000000ff) % (1 << 3));
				}

				// 数据值区
				{
					// 0x32前面有步长信息完全由实际报文结构决定，如果没有，数据会有误。// 没有判断
					// 0x31的0418后面的三个字节表示的time_step(均匀时段水文信息报)
					if ((idCode == (byte) 0xf4) || (idCode == (byte) 0xf5)
							|| ((idCode == (byte) 0x39) && (header.function_code == (byte) 0x31))) { // 1小时每5分钟的
						if ((idCode == (byte) 0xf4) || (idCode == (byte) 0xf5)) {
							time_step = 5;
							dataLength /= 12;
						}
						// hex表示
						for (int i = 0; i < 12; i++) {
							value = ByteArray.byteArraytoInt(ByteArray.subBytes(text, pos, dataLength));
							ot_date = DateUtils.dateStringByStep(ot_date, step_type, time_step); // i+1?
							ot = DateUtils.dateToStringDate(ot_date);
							pos += dataLength;
							temp_str = StringUtils.formFloatString(value, floatLength);
							Factor f = new Factor(cs, rsa, fc, mt, scid, ot, id, temp_str, new Date());
							listobj.add(f);
						}
					} else if (idCode == (byte) 0x04) {
						// 逻辑上这里的浮点位数一定是0，只取整数位 //不做处理
						dataLength = (dataCode >>> 3); // 获得单个数据的长度，实际上只是000000三位
						if (text[pos] != (byte) 0x00) {
							step_type = 1;
							time_step = (text[pos] & 0x000000ff);
						} else if (text[pos + 1] != (byte) 0x00) {
							step_type = 2;
							time_step = (text[pos + 1] & 0x000000ff);
						} else if (text[pos + 2] != (byte) 0x00) {
							step_type = 3;
							time_step = (text[pos + 2] & 0x000000ff);
						}
						pos += dataLength;
					} else { // 单组其他没有多组数据的要素
						value = BCD.BCDToDecimal(ByteArray.subBytes(text, pos, dataLength));
						pos += dataLength;
						temp_str = StringUtils.formFloatString(value, floatLength);
						Factor f = new Factor(cs, rsa, fc, mt, scid, ot, id, temp_str, new Date());
						listobj.add(f);
					}
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
