package oldTest;

public class JavaGoto {
	// static Test monitor = new Test();
	public static void main(String[] args) {
		int i = 0;
		outer: for (; true;) {
			inner: for (; i < 10; i++) {
				System.out.println("i = " + i);
				if (i == 2) {
					System.out.println("continue");
					continue;
				}
				if (i == 3) {
					System.out.println("break");
					i++;
					break;
				}
				if (i == 7) {
					System.out.println("continue outer");
					i++;
					continue outer;
				}
				if (i == 8) {
					System.out.println("break outer");
					break outer;
				}

				for (int k = 0; k < 5; k++) {
					if (k == 3) {
						System.out.println("continue inner");
						continue inner;
					}
				}
			}
		}

		int j = 0;
		outer: while (true) {
			System.out.println("Outer while loop");
			while (true) {
				j++;
				System.out.println("j = " + j);
				if (j == 1) {
					System.out.println("continue");
					continue;
				}
				if (j == 3) {
					System.out.println("continue outer");
					continue outer;
				}
				if (j == 5) {
					System.out.println("break");
					break;
				}
				if (j == 7) {
					System.out.println("break outer");
					break outer;
				}
			}
		}
	}
}