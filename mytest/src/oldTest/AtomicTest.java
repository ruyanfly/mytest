package oldTest;

import java.util.concurrent.atomic.*;

public class AtomicTest {
	
	static AtomicInteger ai = new AtomicInteger(1);
	
	public static void main(String[] args) {
		System.out.println(ai.getAndIncrement());
		System.out.println(ai.get());
	}

}
