package frames;
import java.io.Serializable;

import common.ControlCode;
import field.CRCCode;
import field.CenterStationAddress;
import field.FrameOrientationLength;
import field.FrameStartCode;
import field.FunctionCode;
import field.MessagePackageSumNumber;
import field.MessageText;
import field.Password;
import field.RemoteStationAddress;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 11:06:43 AM
 * 
 */

public class DownFrame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FrameStartCode fsc;
	private RemoteStationAddress rsa;
	private CenterStationAddress csa;
	private Password psw;
	private FunctionCode fc;
	private FrameOrientationLength fo;
	private MessageText mt;
	private CRCCode cc;
}
