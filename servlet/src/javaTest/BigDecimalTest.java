package javaTest;

import java.math.BigDecimal;

/**
 * 这个是用来学习BigDecimal处理Java计算精度问题
 * @author yanru
 *
 */
//二进制不能准确的表示一个小数，如0.1

public class BigDecimalTest {
	
	private BigDecimalTest() {
		
	}
	
	public static BigDecimal add(double v1, double v2) {
		BigDecimal b1=new BigDecimal(Double.toString(v1));
		BigDecimal b2=new BigDecimal(Double.toString(v2));
		return b1.add(b2);
	}
	
	public static BigDecimal sub(double v1, double v2) {
		BigDecimal b1=new BigDecimal(Double.toString(v1));
		BigDecimal b2=new BigDecimal(Double.toString(v2));
		return b1.subtract(b2);
	}
	
	public static BigDecimal mul(double v1, double v2) {
		BigDecimal b1=new BigDecimal(Double.toString(v1));
		BigDecimal b2=new BigDecimal(Double.toString(v2));
		return b1.multiply(b2);
	}
	
	public static BigDecimal div(double v1, double v2) {
		BigDecimal b1=new BigDecimal(Double.toString(v1));
		BigDecimal b2=new BigDecimal(Double.toString(v2));
		return b1.divide(b2,2,BigDecimal.ROUND_HALF_UP);//四舍五入，保留两位小数
	}
	
	public void test1() {
		System.out.println(0.05+0.01);
		System.out.println(1.0-0.42);
		System.out.println(4.015*100);
		System.out.println(123.3/100);
		System.out.println(0.5*3);
		System.out.println(0.1*3);
	}
	
	//使用String构造
	//BigDecimal b1 = new BigDecimal("0.1");
	//或者是：
	//BigDecimal b1 = BigDecimal.valueOf(0.1);
	//valueOf源码
	//return new BigDecimal(Double.toString(val));
	//BigDecimal都是不可变的，每一步的运算时，都会产生一个新的对象。所以在做加减乘除后千万要保存操作后的值。
	
	public void test2() {
		BigDecimal b1=new BigDecimal("0.05");
		BigDecimal b2=new BigDecimal("0.01");
		System.out.println(b1.add(b2));
		BigDecimal a = new BigDecimal("1.5");
		BigDecimal a1 = new BigDecimal("329.530");
		System.out.println(a.multiply(a1).setScale(2, BigDecimal.ROUND_HALF_UP));
	}
	
	public void test3() {
		BigDecimal b1=new BigDecimal(0.05);
		BigDecimal b2=new BigDecimal(0.01);
		System.out.println(b1.add(b2));
		BigDecimal a = new BigDecimal(1.5);
        BigDecimal a1 = new BigDecimal(329.530);
        System.out.println(a.multiply(a1).setScale(2, BigDecimal.ROUND_HALF_UP));
	}
	
	public static void main(String[] args) {
		BigDecimalTest t = new BigDecimalTest();
		t.test1();
		t.test2();
		t.test3();
	}

}
