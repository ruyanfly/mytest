package oldTest;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

public class TheTest {

	public static void main(String args[]) {
		int x=1;
		int y=2;
		int z=3;
		int a=2;
		int b=2;
		int c=2;
		int d=2;
		a+=z--/++x;
		b+=z--/x++;
		c+=--z/++x;
		d+=--z/x++;
		System.out.println("a="+a+"; b="+b+"; c="+c+"; d="+d);
		//System.out.println("(y++)/3="+(y++)/3);
		System.out.println("(y++)/3="+(++y)/3);
		
		byte[] seed = new byte[12];
		SecureRandom random = new SecureRandom();
		random.nextBytes(seed);
		String stringSeed;
		try {
			stringSeed = new String(seed, "GB2312");
			System.out.println(random + "---" + stringSeed);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
