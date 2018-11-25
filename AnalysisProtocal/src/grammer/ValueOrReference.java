package grammer;

/**
 * @author Suzumiya
 * @version Nov 18, 2018 8:23:41 PM
 * 
 */

public class ValueOrReference {
	public static void main(String args[]){
		int[] a = {1, 2};
		int[] b = a;
		b[1] = 5;
		System.out.println(a[1]);
	}
}
