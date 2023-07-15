package warp;

import warp.audio.AudioController;
import warp.game.WarpedGame;
import warp.graphics.screen.Screen;
import warp.graphics.screen.warpedScreens.WarpTechScreen;
import warp.properties.FrameworkProperties;
import warp.user.UserInput;
import warp.utilities.NameGenerator;
import warp.warpedGames.WarpTech;

public abstract class WarpedFramework2D {
	//The only thing to do in this class is set the target game and screen
	
	public static WarpedGame game; 
	public static AudioController audioController;
	public static UserInput userInput;
	public static Screen screen;
	
	public static Thread gameThread;
	public static Thread audioManagerThread;
	public static Thread userInputThread;
	public static Thread screenThread;
	
	private static boolean running = true;
	private static long then;
	private static long now;
	private static int cycles = 0;

	public static void main(String[] args) {
		then = System.currentTimeMillis();
		NameGenerator.initHashMaps();
		userInput = new UserInput();		
		audioController = new AudioController();
		//////////////////////////////////////////////////////////////////
		game = new WarpTech();    ////////Set target game here////////////
		screen = new WarpTechScreen(); //Set game screen here/////////////
		//////////////////////////////////////////////////////////////////
		
		if(FrameworkProperties.DEBUG) {
			System.out.println();
			System.out.println("<<<Starting Threads>>");
		}
		audioManagerThread = audioController.start();
		userInputThread = userInput.start();
		screenThread = screen.start();
		gameThread = game.start();
		
		while(running) {
			now = System.currentTimeMillis();
			cycles++;				
		
			if(now - then >= 1000) {
				then = now;
				if(FrameworkProperties.DEBUG) {
					Screen.frameworkDisplay.setMainData(cycles);
					Screen.frameworkDisplay.setGameData(WarpedGame.time.cycleCount(), WarpedGame.time.updateCount());
					Screen.frameworkDisplay.setAudioData(audioController.time.cycleCount(), audioController.time.updateCount());
					Screen.frameworkDisplay.setUserInputData(userInput.time.cycleCount(), userInput.time.updateCount());
					Screen.frameworkDisplay.setScreenData(screen.time.cycleCount(), screen.time.updateCount());
				}
				cycles = 0;
			}
		}		
	}
	public static boolean isRunning() { return running;}
	public static void stop() throws InterruptedException {
		if(FrameworkProperties.DEBUG)System.out.println("Stoping WarpedFramework2D");
		
		audioManagerThread.join();
		userInputThread.join();
		screen.stop();
		screenThread.join();
		gameThread.join();
		running = false;
	}
}
