package DesignPattern;

//import java.util.ArrayList;
//import java.util.List;

//这里不能申明一个类为car,Car因为其他类中已经有了，且不区分大小写
//建造者模式可以理解为把不相关的几个部分组合成一个个体
//建造者最形象的比喻就是肯德基麦当劳的套餐，由不同的产品组合合成
class autoCar{
	private String wheel;
	private String skeleton;
	private String engine;
	public String getWheel() {
		return this.wheel;
	}
	public void setWheel(String wheel) {
		this.wheel = wheel;
	}
	public String getSkeleton() {
		return this.skeleton;
	}
	public void setSkeleton(String skeleton) {
		this.skeleton = skeleton;
	}
	public String getEngine() {
		return this.engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
}
//个体建造接口
interface CarBuilder{
	public void buildWheel();
	public void buildSkeleton();
	public void buildEngine();
	autoCar buildCar(); 
}
//这里很明显应该用工厂模式，甚至应该用抽象工厂模式
//描述具体个体建造过程方法
class FerrariBuilder implements CarBuilder{
	autoCar FerrariCar;
	public FerrariBuilder() {
		FerrariCar = new autoCar();
	}
	@Override
	public void buildWheel() {
		// TODO Auto-generated method stub
		FerrariCar.setWheel("法拉利专用轮子");
	}
	@Override
	public void buildSkeleton() {
		// TODO Auto-generated method stub
		FerrariCar.setSkeleton("法拉利车身结构");
	}
	@Override
	public void buildEngine() {
		// TODO Auto-generated method stub
		FerrariCar.setEngine("法拉利自研发动机");
	}
	@Override
	public autoCar buildCar() {
		// TODO Auto-generated method stub
		return this.FerrariCar;
	}
}
//建造者类
//这里用到一个知识点是父类可以用子类来表示
class CarDirector {
	public autoCar constructCar (CarBuilder builder) {
		builder.buildWheel();
		builder.buildSkeleton();
		builder.buildEngine();
		return builder.buildCar();
	}
}

//class Builder{
//	private List<Sender> list=new ArrayList<Sender>();
//	public void produceMailSender(int count){
//		for(int i=0;i<count;i++){
//			list.add(new MailSender());
//		}
//		System.out.println("this is mail sender!");
//	}
//	public void produceSmsSender(int count){
//		for(int i=0;i<count;i++){
//			list.add(new SmsSender());
//		}
//		System.out.print("this is sms sender!");
//	}
//}

public class BuilderMethod {
	public static void main(String[] args){
//		Builder builder=new Builder();
//		builder.produceMailSender(10);
		
        CarDirector director = new CarDirector();
        autoCar car = director.constructCar(new FerrariBuilder());
        System.out.println(car.getWheel());
        System.out.println(car.getEngine());
        System.out.println(car.getSkeleton());
	}
}
