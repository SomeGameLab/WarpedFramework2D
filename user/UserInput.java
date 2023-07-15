package warp.user;

import warp.WarpedFramework2D;
import warp.properties.FrameworkProperties;
import warp.utilities.Timer;

public class UserInput implements Runnable {

	public Timer time = new Timer();  
	
	public static Keyboard keyboard = new Keyboard();
	public static Mouse mouse = new Mouse();
	
	public UserInput() {
		time.setTickSpeed(FrameworkProperties.USER_INPUT_REFRESH_RATE);
	}

	
	public void run() {
		while(WarpedFramework2D.isRunning()) {			
			if(time.update()) update();	
		}
		
	}
	
	public void update() {
		mouse.update();
		keyboard.update();
	}
	
	public synchronized Thread start() {
		if(FrameworkProperties.DEBUG) System.out.println("UserInput -> Starting UserInput Thread");
		Thread result = new Thread(this, "UserInput");
		result.start();
		return result;
	}
	
}
