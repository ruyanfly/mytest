//设计原则中的开闭合原则
package myTest;

import java.util.ArrayList;

interface Book {
	public String getName();
	public String getPrice();
	public String getAuthor();
}

class NovelBook implements Book {
	private String name;
	private String price;
	private String author;
	public NovelBook(String name, String price, String author) {
		this.name = name;
		this.price = price;
		this.author = author;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	@Override
	public String getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}
	@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return this.author;
	} 
}
//设计模式六大原则之开闭原则
public class OpenClosePrinciple {
	private final static ArrayList<Book> bookList = new ArrayList<Book>();
	//静态模块初始化,项目中一般是从持久层初始化产生,在main函数之前就执行静态模块
	static {
		bookList.add(new NovelBook("天龙八部","32","金庸"));
		bookList.add(new NovelBook("巴黎圣母院","56","雨果"));
		bookList.add(new NovelBook("悲惨世界","38","雨果"));
		bookList.add(new NovelBook("金瓶梅","43","兰陵笑笑生")); 
	}
	public static void main(String[] args) {
		System.out.println("书店买出去的书籍记录如下：");
		for(Book book:bookList){
			System.out.println("书籍名称：" + book.getName()+"\t书籍作者：" + book.getAuthor()+  "\t书籍价格：" + book.getPrice() + "元"); 
        }
	}
}
