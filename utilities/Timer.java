package warp.utilities;

public class Timer {

	private double delta  	= 0;     
	private long lastTime 	= System.nanoTime();  
	private double tickSpeed = 1000000000.0 / 120; //Default 120 tick per second
	private boolean tick = false;
	private static final double TICK_INCREMENT = 10000.0;
	private long now;
	
	private int cycles = 0;
	private int ups  = 0;
	
	public Timer() {}
	public Timer(int tickPerSecond) {
		setTickSpeed(tickPerSecond);
	}
	
	
	public boolean update() {
		tick = false;
		now = System.nanoTime();
		delta += (now - lastTime) / tickSpeed;
		lastTime = now;
		if(delta >= 1) {
			tick = true;
			delta = 0;
		}
		if(tick) ups++;
		cycles++;
		return tick;
	}
	public void increaseTickSpeed() {
		if(tickSpeed + TICK_INCREMENT > 1000000000.0) System.err.println("Timer -> increaseTickSpeed -> Reached max tick speed");
		else tickSpeed += TICK_INCREMENT;
	}
	public void decreaseTickSpeed() {
		if(tickSpeed - TICK_INCREMENT < 0) System.err.println("Timer -> decreaseTickSpeed -> Reached min tick speed");
		else tickSpeed -= TICK_INCREMENT; 
		}
	public double getTickSpeed() {return tickSpeed;}
	
	public int cycleCount() {
		int result = cycles;
		cycles = 0;
		return result;
	}
	public int updateCount() {
		int result = ups;
		ups = 0;
		return result;
	}
	
	public void setTickSpeed(int tickPerSecond) {tickSpeed = 1000000000.0 / tickPerSecond;}
	public boolean isUpdate() {return tick;}
}
