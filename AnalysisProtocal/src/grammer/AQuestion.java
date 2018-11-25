package grammer;

/**
 * @author Suzumiya
 * @version Nov 18, 2018 8:25:40 PM
 * 
 */

public class AQuestion {

	//对于基本类型 num ，赋值运算符会直接改变变量的值，原来的值被覆盖掉。
	public static void foo1(int value) {
		value = 100;
	}

	//对于引用类型 str，赋值运算符会改变引用中所保存的地址，原来的地址被覆盖掉。但是原来的对象不会被改变（重要）。
	public static void foo2(String text) {
		text = "windows";
//		text += "windows"; // 这个操作还是相当于一样的构造函数
	}

	public static void foo3(StringBuilder builder) {
		builder.append("4");
	}

	public static void foo4(StringBuilder builder) {
	    builder = new StringBuilder("ipad");
	}

	public static void main(String args[]) {
		int i = 10;
		foo1(i); // 10
		System.out.println(i);

		String str = "Hello Suzumiya";
		foo2(str);
		System.out.println(str);

		StringBuilder sb = new StringBuilder("iphone");
		foo3(sb);
		System.out.println(sb);

		sb = new StringBuilder("iphone");
		foo4(sb);
		System.out.println(sb);
	}

}
