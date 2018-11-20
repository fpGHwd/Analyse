package common;

import java.io.Serializable;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 10:37:29 AM
 * 
 */

public abstract class ControlCode implements Serializable {
	private static final long serialVersionUID = 1L;

	// STARTING CODE
	private static final byte[] SOH = { 0x7e, 0x7e };
	// STARTING TEXT CODE
	protected static final byte STX = 0x02;
	// MULTIPLE PACKAGES STARTING CODE
	protected static final byte SYN = 0x16;
	// END TEXT
	protected static final byte ETX = 0x03;
	// END FRAME BUT NOT TEXT
	protected static final byte ETB = 0x17;
	// DOWNWARDS ENQUERY OR CONTROLLING END CODE
	protected static final byte ENQ = 0x05;
	// END OF TRANSPORTAION
	protected static final byte EOT = 0x04;
	// ACKNOWLEDGE FOR FOLLOWING FRAMES
	protected static final byte ACK = 0X06;
	// NOT ACKKNOWLEDGE
	protected static final byte NAK = 0X15;
	// END IN TRANSPORATION DOWNWARDS FRAME FOR RETAINNING ONLINE
	protected static final byte ESC = 0x1B;
}
