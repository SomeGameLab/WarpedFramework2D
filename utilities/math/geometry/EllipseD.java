package warp.utilities.math.geometry;

import warp.utilities.math.Vec2d;

public class EllipseD {

	public Vec2d position = new Vec2d();
	public Vec2d scale = new Vec2d();
	
	public Vec2d focus1, focus2;
	
	public EllipseD() {};
	
	public EllipseD(Vec2d pos, Vec2d scale) {
		this.position = pos;
		this.scale = scale;
		if(scale.x > scale.y) {
			double c = Math.sqrt((scale.x * scale.x) - (scale.y * scale.y));
			focus1 = new Vec2d( c, 0);
			focus2 = new Vec2d(-c, 0);
		}
	}
	
	
	public Vec2d getPointOnEllipse(Double theta) {
		Vec2d result = new Vec2d();
		 result.x = position.x + (scale.x * Math.cos(theta));
		 result.y = position.y + (scale.y * Math.sin(theta));
		return result;
	}
	
	public boolean contains(double x, double y) {
		boolean result = false;
		if(   (Math.pow((x - position.x), 2)/(scale.x*scale.x)) 
			+ (Math.pow((y - position.y), 2)/(scale.y*scale.y)) <= 1)  result = true;
		return result;
	}
	public boolean contains(Vec2d point) {
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
