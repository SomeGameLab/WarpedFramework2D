package warp.utilities.math.geometry;

import warp.utilities.math.Vec2d;
import warp.utilities.math.Vec2f;

public class EllipseF {

	public Vec2f position = new Vec2f();
	public Vec2f scale = new Vec2f();
		
	public EllipseF() {};
	
	public EllipseF(Vec2f pos, Vec2f scale) {
		this.position = pos;
		this.scale = scale;
	}
	
	
	public Vec2d getPointOnEllipse(Double theta) {
		Vec2d result = new Vec2d();
		 result.x = position.x + (scale.x * Math.cos(theta));
		 result.y = position.y + (scale.y * Math.sin(theta));
		return result;
	}
	
	public boolean contains(float x, float y) {
		boolean result = false;
		if(   (Math.pow((x - position.x), 2)/(scale.x*scale.x)) 
			+ (Math.pow((y - position.y), 2)/(scale.y*scale.y)) <= 1)  result = true;
		return result;
	}
	public boolean contains(Vec2f point) {
		boolean result = false;
		if(   (Math.pow((point.x - position.x), 2)/(scale.x*scale.x)) 
			+ (Math.pow((point.y - position.y), 2)/(scale.y*scale.y)) <= 1)  result = true;
		return result;
	}
	public static boolean contains(EllipseD ellipse, Vec2d point) {
		boolean result = false;
		if(   (Math.pow((point.x - ellipse.position.x), 2)/(ellipse.scale.x*ellipse.scale.x)) 
			+ (Math.pow((point.y - ellipse.position.y), 2)/(ellipse.scale.y*ellipse.scale.y)) <= 1)  result = true;
		return result;
	}
	
}
