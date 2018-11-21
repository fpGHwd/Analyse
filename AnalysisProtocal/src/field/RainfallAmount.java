package field;

import java.io.Serializable;

/**
 * @author Suzumiya
 * @version Nov 21, 2018 12:04:53 AM
 * 
 */

public class RainfallAmount implements Fields, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final byte current_rainfall_code=0x00;
	private static final byte overall_rainfall_code=0x00;
	private static final int LENGTH = 0;
	private static final byte instant_waterlevel_code = 0x00;
	private static final byte voltage_code =0x00;
	
	private byte[] content = new byte[LENGTH];
	
	private double currentRainfall = 0.0;
	private double overallRainfall = 0.0;
	private double currentWaterLevel = 0.0;
	private double voltage = 0.0; // TODO to apart

	@Override
	public int getFieldsLength() {
		// TODO Auto-generated method stub
		return 0;
	}

}
