package warp.properties;

import java.awt.image.BufferedImage;

public class FrameworkProperties {

	/* ---------------------- Window ---------------------- */
	public static final String WINDOW_NAME = "My Game";
	public static final double WINDOW_ASPECT_RATIO = (16.0 / 9.0);
	public static final int WINDOW_SCALE  = 1;
	public static final int WINDOW_WIDTH  = 1920;
	public static final int WINDOW_HEIGHT = (int)(WINDOW_WIDTH / WINDOW_ASPECT_RATIO);
	public static final int SCALE_WINDOW_WIDTH  = WINDOW_WIDTH * WINDOW_SCALE;
	public static final int SCALE_WINDOW_HEIGHT = WINDOW_HEIGHT * WINDOW_SCALE;
	
	public static final int BUFFERED_IMAGE_TYPE = BufferedImage.TYPE_INT_ARGB;

	/* ---------------------- Screen ---------------------- */
	public static final int SCREEN_COMPOSERS_MAX 		  = 10;
	public static final int MODULUS 					  = 64;		//Tile Size
	/* ---------------------- Camera ---------------------- */
	public static final double DEFAULT_CAMERA_MOVE_SPEED  = 1.0;
	public static final double DEFAULT_CAMERA_ZOOM_D  	  = 100.0;
	public static boolean CAP_MIN_SIZE 					  = true;

	/* ---------------------- Debug ---------------------- */
	public static final boolean DEBUG 					  = true;
	public static boolean DEBUG_DISPLAY			 		  = true;
	
	/* ------------------ Thread Clocks ------------------ */
	public static final boolean CAP_FRAMES 				  = true;					
	public static final int WARPED_GAME_REFRESH_RATE 	  = 120; 	//Controls game speed, higher values game runs faster; lower slower
	public static final int SCREEN_REFRESH_RATE 		  = 500;	//Controls frame rate of screen + all screen composers
	public static final int AUDIO_CONTROLLER_REFRESH_RATE = 60;		
	public static final int USER_INPUT_REFRESH_RATE 	  = 120;
	
	

}
