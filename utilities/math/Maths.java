package warp.utilities.math;

import java.util.Random;

public class Maths {
	
	public static Random random = new Random();
	public static final double PI_ON_TWO 	   = Math.PI /2;		// Quarter rotation
	public static final double PI 			   = Math.PI;			// Half Rotation
	public static final double THREE_PI_ON_TWO = Math.PI * 3 / 2; 	// Three Quarter Rotation
	public static final double TWO_PI  		   = Math.PI * 2; 		 // Full rotation
	
	//CLAMP functions
	public static Double clampDouble(double value, double min, double max) {
		if(value > max) return max;
		if(value < min) return min;
		return value;
	}
	public static Double clampDoubleMax(double value, double max) {
		if(value > max) return max;
		return value;
	}
	public static Double clampDoubleMin(double value, double min) {
		if(value < min) return min;
		return value;
	}
	public static int clampInt(int value, int min, int max) {
		if(value > max) return max;
		if(value < min) return min;
		return value;
	}
	public static int clampIntMax(int value, int max) {
		if(value > max) return max;
		return value;
	}
	public static int clampIntMin(int value, int min) {
		if(value < min) return min;
		return value;
	}
	public static float clampFloat(float value, float min, float max) {
		if(value > max) return max;
		if(value < min) return min;
		return value;
	}
	public static float clampFloatMax(float value, float max) {
		if(value > max) return max;
		return value;
	}
	public static float clampFloatMin(float value, float min) {
		if(value < min) return min;
		return value;
	}
	public static Integer clampInteger(Integer value, Integer min, Integer max) {
		if(value > max) return max;
		if(value < min) return min;
		return value;
	}
	public static Integer clampIntegerMax(Integer value, Integer max) {
		if(value > max) return max;
		return value;
	}
	public static Integer clampIntegerMin(Integer value, Integer min) {
		if(value < min) return min;
		return value;
	}
	
	
	
	

}
