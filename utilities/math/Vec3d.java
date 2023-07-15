package warp.utilities.math;

public class Vec3d { //NOTE -> when used in combination with MatrixD will be considered a ROW VECTOR

	public double x, y, z;
	
	//Constructors
	public Vec3d() {x = y = z = 0;}
	public Vec3d(double v) {x = y = z = v;}
	public Vec3d(double x, double y, double z) {this.x = x;this.y = y;this.z = z;}
	public Vec3d(Vec3d vec) {this.x = vec.x; this.y = vec.y; this.z = vec.z;}
	
	//Getters and Setters
	public static int getM() {return 1;}
	public static int getN() {return 3;}
	public void set(int v) {x = y = z = v;}
	public void set(double x, double y, double z) {this.x = x;this.y = y;this.z = z;}
	public void zero() {x = y = z = 0;}
	public double getValue(int index) {
		switch(index) {
		case 0: return x;
		case 1: return y;
		case 2: return z;
		default: System.err.println("Vec3d -> getValue() -> invalid index : " + index); return -0.1;
		}
	}
	
	//Instanced operations
	//WILL change the instance that calls these functions
	////////Basic Vector Operations
	public void scale(int scalar) {x *= scalar; y*= scalar; z *= scalar;}
	public void scale(Vec3d scalar) { x *= scalar.x; y *= scalar.y; z *= scalar.z;}
	public void add(double x, double y, double z) {this.x += x;	this.y += y; this.z += z;}
	public void add(Vec3d vec) {x += vec.x;y += vec.y; z += vec.z;};
	public void minus(Vec3d vec) {x -= vec.x;y -= vec.y;z -= vec.z;}
	public void minus(double x, double y, double z) {this.x -= x; this.y -= y; this.z -= z;}
	public String getPrintln() {return(" (" + x + ", " + y + ", "+ z + ") ");}
	public void println() {System.out.println(" (" + x + ", " + y + ", "+ z + ") ");}
	public void copy(Vec3d vec) {x = vec.x; y = vec.y; z = vec.z;}
	
	//Instance operations 
	//Will not change the instance that calls these functions but will use them for calculation
	public double dotProduct(Vec3d vec) {return ((x * vec.x) + (y * vec.y) + (z * vec.z));}
	
	public Vec3d crossProduct(Vec3d vec) {
		Vec3d result = new Vec3d();
		//TODO implement crossProduct function
		return result;
	}
	
	public double difference(Vec3d vector) { //FIXME is this correct?
		double rx = (this.x - vector.x) * (this.x - vector.x);
		double ry = (this.y - vector.y) * (this.y - vector.y);  
		double rz = (this.z - vector.z) * (this.z - vector.z); 
		return Math.sqrt(rx  +  ry + rz);
	}	
	
	public Vec3d vecDifference(Vec3d vector) {
		Vec3d result = new Vec3d();
		result.x = (this.x - vector.x) * (this.x - vector.x);
		result.y = (this.y - vector.y) * (this.y - vector.y);
		result.z = (this.z - vector.z) * (this.z - vector.z);
		return result;
	}
	
	public Vec3d unitVector() {
		Vec3d result = new Vec3d();
		double mod = modulus();
		result.x = x / mod;
		result.y = y / mod;
		result.z = z / mod;
		return result;
	}
	
	public double modulus() {return Math.sqrt((x * x) + (y * y) + (z * z));}
	
	//Static operations,
	//Return a new result -> Does NOT change the inputs
	public static Vec3d clone(Vec3d vec) {
		Vec3d result = new Vec3d();
		result.x = vec.x;
		result.y = vec.y;
		result.z = vec.z;
		return result; 
	}
	
	public static double dotProduct(Vec3d vec1, Vec3d vec2) {
		return ((vec1.x * vec2.x) + (vec1.y * vec2.y) + (vec1.z * vec2.z));
	}
	
	public static Vec3d scale(double scalar, Vec3d vec) {
		Vec3d result = new Vec3d();
		result.x = vec.x * scalar;
		result.y = vec.y * scalar;
		result.z = vec.z * scalar;
		return result;
	}
	
	public static Vec3d scaleVector(Vec3d vec1, Vec3d vec2) {
		Vec3d result = new Vec3d();
		result.x = vec1.x * vec2.x;
		result.y = vec1.y * vec2.y;
		result.z = vec1.z * vec2.z;
		return result;
	}
	public static Vec3d crossProduct(Vec3d vec1, Vec3d vec2) {
		Vec3d result = new Vec3d();
		result.x =   (vec1.y * vec2.z) - (vec1.z * vec2.y); 
		result.y = -((vec1.x * vec2.z) - (vec1.z * vec2.x));
		result.z =   (vec1.x * vec2.y) - (vec1.y * vec2.x);
		return result;
	}
	
	public static Vec3d addVectors(Vec3d vector1, Vec3d vector2) {
		Vec3d result = new Vec3d();
		result.x = vector1.x;
		result.y = vector1.y;
		result.z = vector1.z;
		result.x += vector2.x;
		result.y += vector2.y;
		result.z += vector2.z;
		return result;
	}
	public static Vec3d subtractVectors(Vec3d vec1, Vec3d vec2) {
		Vec3d result = new Vec3d();
		result.x = vec1.x;
		result.y = vec1.y;
		result.z = vec1.z;
		result.x -= vec2.x;
		result.y -= vec2.y;
		result.z -= vec2.z;
		return result;
	}
	
	public static double difference(Vec3d vector1, Vec3d vector2) {
		double rx = (vector1.x - vector2.x) * (vector1.x - vector2.x);
		double ry = (vector1.y - vector2.y) * (vector1.y - vector2.y);  
		double rz = (vector1.z - vector2.z) * (vector1.z - vector2.z);  
		return Math.sqrt(rx  +  ry + rz);		
	}
	
	
	////////////////VECTOR GENERATION
	public static Vec3d generateRandomVector(double xBound, double yBound, double zBound) {
		Vec3d result = new Vec3d();
		result.x = Maths.random.nextDouble(xBound);
		result.y = Maths.random.nextDouble(yBound);
		result.z = Maths.random.nextDouble(zBound);
		return result;		
	}
	public static Vec3d generateRandomVector(double xMin,double xMax, double yMin, double yMax, double zMin, double zMax) {
		Vec3d result = new Vec3d();
		result.x = Maths.random.nextDouble(xMin,xMax);
		result.y = Maths.random.nextDouble(yMin,yMax);
		result.z = Maths.random.nextDouble(zMin,zMax);
		return result;		
	}
	public static Vec3d generateRandomBaseVector() {
		Vec3d result = new Vec3d();
		Vec3d r = generateRandomVector(3,3,3);
		
		if(r.x == 0) 	  result.x = -1;
		else if(r.x == 1) result.x =  1;
		else 			  result.x =  0;
		
		if(r.y == 0)	  result.y = -1;
		else if(r.y == 1) result.y =  1;
		else 			  result.y =  0;
		
		if(r.z == 0)	  result.z = -1;
		else if(r.z == 1) result.z =  1;
		else 			  result.z =  0;
	
		return result;
	}
	
	///////////////////////CONVERSION
	public static Vec2d convertVec3ToVec2(Vec3d original) {
		Vec2d result = new Vec2d();
		result.x = original.x;
		result.y = original.y;
		return result;
	}
		
	
	//LOGICAL CHECKS
	public boolean equals(Vec3d vec) {if(vec.x == this.x && vec.y == this.y && vec.z == this.z) return true; return false;}
	public boolean lessThan(Vec3d vec) {if(x < vec.x && y < vec.y && z < vec.z)return true;	return false;}
	public boolean greaterThan(Vec3d vec) { if(x > vec.x && y > vec.y && z > vec.z)return true;	return false;}
	
	public static boolean equals(Vec3d vec1, Vec3d vec2) {if(vec1.x == vec2.x && vec1.y == vec2.y && vec1.z == vec2.z)return true;return false;}
	public static boolean lessThan(Vec3d vec1, Vec3d vec2) {if(vec1.x < vec2.x && vec1.y < vec2.y && vec1.z < vec2.z) return true; return false;}
	public static boolean greaterThan(Vec3d vec1, Vec3d vec2) { if(vec1.x > vec2.x && vec1.y > vec2.y && vec1.z > vec2.z) return true; return false;}
}
