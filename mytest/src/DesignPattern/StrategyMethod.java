package DesignPattern;
//策略模式
interface ICalculator{
	public int calculate(String exp);
}

abstract class AbstractCalculator {
	public int[] split(String exp,String opt){
		String array[]=exp.split(opt);
		int arrayInt[]=new int[2];
		arrayInt[0]=Integer.parseInt(array[0]);
		arrayInt[1]=Integer.parseInt(array[1]);
		return arrayInt;
	}
}

class StrategyPlus extends AbstractCalculator implements ICalculator{
	@Override
	public int calculate(String exp) {
		// TODO Auto-generated method stub
		int arrayInt[]=split(exp,"\\+");
		return arrayInt[0]+arrayInt[1];
	}
}

class StrategyMinus extends AbstractCalculator implements ICalculator{
	@Override
	public int calculate(String exp) {
		// TODO Auto-generated method stub
		int arrayInt[]=split(exp,"-");
		return arrayInt[0]-arrayInt[1];
	}
}

class StrategyMultiply extends AbstractCalculator implements ICalculator{
	@Override
	public int calculate(String exp) {
		// TODO Auto-generated method stub
		int arrayInt[]=split(exp,"\\*");
		return arrayInt[0]*arrayInt[1];
	}
}

interface IFight {
	void fight();
}

interface IChat {
	void chat();
}

class FightUseAxe implements IFight {
	@Override
	public void fight() {
		// TODO Auto-generated method stub
		System.out.println("使用斧头战斗");
	}
}

class FightUseBlade implements IFight {
	@Override
	public void fight() {
		// TODO Auto-generated method stub
		System.out.println("使用大剑战斗");
	}
}

class FightUseKnife implements IFight {
	@Override
	public void fight() {
		// TODO Auto-generated method stub
		System.out.println("使用匕首战斗");
	}
}

abstract class Role {
	private IFight weapon;
	public void fight() {
		weapon.fight();
	}
	//这里是策略模式跟工厂模式不同之处
	public void setWeapon(IFight weapon) {
		this.weapon = weapon;
	}
	public abstract void display();
}

class King extends Role implements IChat {
	@Override
	public void chat() {
		// TODO Auto-generated method stub
		System.out.println("跟国王交谈");
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("显示国王的装扮");
	}
}

public class StrategyMethod {
	public static void main(String[] args){
		String exp="2+8";
		ICalculator cal=new StrategyPlus();
		int result=cal.calculate(exp);
		System.out.println(result);
		
		//游戏策略模式
		//Role role = new King();
		//说明父类虽然可以用子类表示，但是不能调用子类函数
		King role = new King();
		role.display();
		role.chat();
		role.setWeapon(new FightUseAxe());
		role.fight();
		role.setWeapon(new FightUseBlade());
		role.fight();
	}
}
