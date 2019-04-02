package oldTest;

import java.util.ArrayList;
import java.util.List;

public class Queens {
	
	private static void getResult(int number) {
		System.out.println(number + " queens is : " + recurrence(number));
	}
	
	@SuppressWarnings("unused")
	private static int nonRecurrence(int number) {
		List<Integer> coordinate = new ArrayList<Integer>();
		int result = 0;
		int p = 0;
		int x = 1;
		int y = 0;
		int isOk = 0;
		if (1 == number)
			result = 1;
		while (p < number) {
			coordinate.add(0, p);
			while (x <= number) {
				if(x == number)
					break;
				while (y <= number) {
					if(y == number)
						break;
					for (int c = 0; c < coordinate.size(); c++ ) {
						if(x == c || y == coordinate.get(c) || Math.abs(x - c) == Math.abs(y - coordinate.get(c)))
							break;
						isOk++;
							
					}
					if(coordinate.size() == isOk) {
						coordinate.add(x, y);
						break;
					}else {
						isOk = 0;
					}
					y++;
				}
				if(0 == isOk) {
					if(1 == x && number == y) {
						y = 0;
						break;
					}
					x--;
					y = coordinate.get(x) + 1;
					coordinate.remove(x);
				}else if (number  == isOk + 1){
					y = coordinate.get(x) + 1;
					coordinate.remove(x);
					isOk = 0;
					result++;
				}else {
					x++;
					isOk = 0;
					y = 0;
				}
			}
			p++;
			coordinate.remove(0);
		}
		return result;
	}
	
	@SuppressWarnings("unused")
	private static int recurrence(int number) {
		int [] array = new int [number];
		array[0] = 0;
		return setQueens(0, array, 0, 0, 0, number, 0);
	}
	
	private static int setQueens(int p, int [] a, int n, int m, int c, int number, int count) {
		if(number == p)
			return count;
		else {
			if(number == n) {
				return setQueens(p++, a, 0, 0, 0, number, count);
			}else {
				for (int i = 0; i < n; i++) {
					if (a[i] != m && Math.abs(a[i] - m) != Math.abs(i - n)) {
						c++;
					}
				}
				if(n - 1 == c) {
					a[n] = m;
					return setQueens(p, a, n + 1, 0, 0, number, count++);
				}else
					return setQueens(p, a, n, m++, 0, number, count++);
			}
		}
	}
	
	public static void main(String [] args) {
		getResult(8);
	}
}
