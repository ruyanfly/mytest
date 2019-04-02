package oldTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Pipeline{
	List<Valve> valves=new ArrayList<Valve>();
	private Valve basic;
	public Valve getBasic(){
		return basic;
	}
	public void setBasic(Valve basic){
		this.basic=basic;
	}
	public Pipeline(Valve basic){
		super();
		this.basic=basic;
	}
	public void addValve(Valve v){
		valves.add(v);
	}
	public void invoke(){
		Context context=new Context(valves.iterator(),this);
		context.invokeNext();
	}
}

class Context{
	private Iterator<Valve> valveIterator;
	private Pipeline pipeline;
	public Context(Iterator<Valve> valveIterator, Pipeline pipeline){
		super();
		this.valveIterator=valveIterator;
		this.pipeline=pipeline;
	}
	public void invokeNext(){
		if(valveIterator.hasNext()){
			Valve valve=valveIterator.next();
			valve.invoke(this);
		}else{
			pipeline.getBasic().invoke(this);
		}
	}
}

interface Valve{
	public String getName();
	public void invoke(Context context);
}

class BasicValve implements Valve{

	private String name;
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public BasicValve(String name){
		super();
		this.name=name;
	}
	public void invoke(Context context) {
		// TODO Auto-generated method stub
		System.out.println(name + " basic is OK! ");
	}
	
}

class CommValve implements Valve{
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public CommValve(String name){
		super();
		this.name=name;
	}
	public void invoke(Context context){
		System.out.println(name + " commvalve is ok! ");
		context.invokeNext();
	}
}

public class ThePipeline {
	public static void main(String[] agrs){
		Pipeline pline=new Pipeline(new BasicValve("Basic"));
		pline.addValve(new CommValve( "A" ));
		pline.addValve(new CommValve( "B" ));
		pline.addValve(new CommValve( "C" ));
		pline.addValve(new CommValve( "D" ));
		pline.invoke();
	}
}
