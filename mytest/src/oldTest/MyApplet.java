package oldTest;
import java.awt.*;
import java.util.*;

@SuppressWarnings({ "serial", "deprecation" })
public class MyApplet extends java.applet.Applet{
	String s;
	int inits=0, starts=0, stops=0;
	public void init(){
		inits++;
		showStatus("now init");
		System.out.println("this is now init");
		pause();
		showStatus("leave init");
	}
	public void start(){
		starts++;
		showStatus("now start");
		System.out.println("this is now start");
		pause();
		showStatus("leave start");
		System.out.println("this is leave start");
		pause();
	}
	public void stop(){
		stops++;
		showStatus("now stop");
		System.out.println("this is now stop");
		pause();
		showStatus("leave stop");
		System.out.println("this is leave stop");
		pause();
	}
	public void paint(Graphics g){
		s="inits: "+inits+"starts: "+starts+"stops: "+stops;  
        g.drawString(s, 10, 10);  
        System.out.println("now paint: "+s);  
        pause(); 
	}
	public void pause() {
        Date d=new Date();  
        long t=d.getTime();  
        while(t+1000>d.getTime()){  
            d=new Date();  
        } 
	}
}
