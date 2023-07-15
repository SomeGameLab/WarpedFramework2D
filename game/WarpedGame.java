package warp.game;

import warp.WarpedFramework2D;
import warp.game.gameContext.GameContext;
import warp.properties.FrameworkProperties;
import warp.utilities.Timer;

public abstract class WarpedGame implements Runnable {
		
	public static Timer time = new Timer(120);
	
	//FIXME setup synchronized access to game context. Data could/will become corrupted otherwise  
	//Warped game should control the monitor during update cycle. 
	//When cycle completes other threads (i.e. screenComposers) should be notified that they can access the context to get game data for rendering 
	public static GameContext gameContext = new GameContext();
	/////////////////////////////////////////////////////////////
	
	
	public WarpedGame () {
		if(FrameworkProperties.DEBUG) {
			System.out.println("");
			System.out.println("WarpedGame -> Constructing Game : " + this.getClass().getSimpleName());
		}
		time.setTickSpeed(FrameworkProperties.WARPED_GAME_REFRESH_RATE);
		if(FrameworkProperties.DEBUG) System.out.println("WarpTech -> Defining Game");	
		defineGame();
		if(initializationChecks()) return;
		else try {
			WarpedFramework2D.stop();
		} catch (InterruptedException e) {
			System.err.println("WarpedGame -> failed to stop!");
			e.printStackTrace();
		}
	}
	
	protected abstract void defineGame();
	public static void increaseGameSpeed() {time.decreaseTickSpeed();}
	public static void decreaseGameSpeed() {time.increaseTickSpeed();}
	
	public synchronized void run() {
		while(WarpedFramework2D.isRunning()) {		
			if(time.update()) update();
		}
	}
	
	public synchronized Thread start() {
		if(FrameworkProperties.DEBUG) System.out.println("WarpedGame -> Starting Game Thread");
		Thread result = new Thread(this, "WarpedGame");
		result.start();
		return result;
	}
	
	private void update() {
		GameContext.update();
	}	
	
	/**Obsolete*/
	private boolean initializationChecks() {	//Must run at the end of constructor
		if(!FrameworkProperties.DEBUG) {
			return true;
		}
		System.out.println();
		System.out.println("WarpedGame -> running initilization checks");
		if(gameContext == null) {
			System.err.println("WarpedGame -> Initialization check fail -> gameContext is null!");
			return false;
		}
		System.out.println("WarpedGame -> gameContext exists Check");
		if(time == null) {
			System.err.println("WarpedGame -> Initialization check fail -> gameTime is null!");
			return false;
		}
		return true;
		
	}
	
	
	

}
