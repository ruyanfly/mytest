package javaTest;

public class Main {
	
	public static void main(String[] args) {
		int i = 2147483647;//2的32次方-1；
		int j = -2147483648;//-2的31次方
		//最大值+1变成最小值，最小值-1变成最大值，说明没有符号位
		System.out.println("max : "+(i+1)+" ; min : "+(j-1)+" ; and : "+(i+j)+" ; -j : "+(0-j));
		
	}
	
}
