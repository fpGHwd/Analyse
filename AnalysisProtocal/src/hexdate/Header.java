package hexdate;

/**
 * @author Suzumiya
 * @version Nov 21, 2018 2:42:59 PM
 * 
 */

public class Header {
	public byte center_station = 0x01;
	public byte[] telementry_station;
	public byte[] password;
	public byte function_code;
	public byte[] identity_length;
	public byte message_start_symbol;
}
