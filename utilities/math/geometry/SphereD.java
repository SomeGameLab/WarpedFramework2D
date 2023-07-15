package warp.utilities.math.geometry;

import warp.utilities.math.Vec3d;

public class SphereD {

	
	public Vec3d position;
	public double radius;
	@SuppressWarnings("unused")
	private double x1, x2, y1, y2, z1, z2;
	
	public SphereD() {
		position = new Vec3d();
		radius = 0;
		x1 = x2 = y1 = y2 = z1 = z2 = 0;
	}
	public SphereD(Vec3d pos, double radius) {
		position = pos;
		this.radius = radius;
		x1 = pos.x - radius;
		x2 = pos.x + radius + 1;
		y1 = pos.y - radius;
		y2 = pos.y + radius;
		z1 = pos.z - radius;
		z2 = pos.z + radius;
	}
	
	
	public boolean contains(Vec3d point) {
		boolean result = false;
		//FIXME fix contains method
		//if(Vec3d.doubleDifference(position, point) <= radius) result = true;
		return result;
	}
	
	public static boolean contains(SphereI sphere, Vec3d point) {
		boolean result = false;
		//FIXME fix contains method
		//if(Vec3i.doubleDifference(sphere.position, point) <= sphere.radius) result = true;
		return result;
	}
	

}
