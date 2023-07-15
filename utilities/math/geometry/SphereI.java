package warp.utilities.math.geometry;

import warp.utilities.math.Vec3i;

public class SphereI {

	
	public Vec3i position;
	public int radius;
	
	@SuppressWarnings("unused")
	private int x1, x2, y1, y2, z1, z2;
	
	public SphereI() {
		position = new Vec3i();
		radius = 0;
		x1 = x2 = y1 = y2 = z1 = z2 = 0;
	}
	public SphereI(Vec3i pos, int radius) {
		position = pos;
		this.radius = radius;
		x1 = pos.x - radius;
		x2 = pos.x + radius + 1;
		y1 = pos.y - radius;
		y2 = pos.y + radius;
		z1 = pos.z - radius;
		z2 = pos.z + radius;
	}
	
	
	public boolean contains(Vec3i point) {
		boolean result = false;
		if(Vec3i.doubleDifference(position, point) <= radius) result = true;
		return result;
	}
	
	public static boolean contains(SphereI sphere, Vec3i point) {
		boolean result = false;
		if(Vec3i.doubleDifference(sphere.position, point) <= sphere.radius) result = true;
		return result;
	}
	

	
}
