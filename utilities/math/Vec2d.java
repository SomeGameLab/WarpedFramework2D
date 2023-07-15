package warp.utilities.math;

public class Vec2d { //NOTE -> when used in combination with MatrixD will be considered a ROW VECTOR

	public double x, y;
		
	//Constructors
	public Vec2d() {x = y = 0;}
	public Vec2d(double v) {x = y = v;}
	public Vec2d(double x, double y) {this.x = x; this.y = y;}
	public Vec2d(Vec2d vec) {this.x = vec.x; this.y = vec.y;}
	
	//Getters and Setters
	public static int getM() {return 1;}
	public static int getN() {return 2;}
	public void set(double value) {x = y = value;}
	public void set(Vec2d vec) {x = vec.x;y = vec.y;}
	public void set(double x, double y) {this.x = x;this.y = y;}
	public void zero() {x = y = 0;}
	public double getValue(int index) {
		switch(index) {
		case 0 : return x;		
		case 1 : return y;
		default: System.err.println("Vec2d -> getValue() -> invalid index : " + index); return -0.1;
		}
	}
	
	/////Basic Vector Operations
	public void add(double x, double y) {this.x += x;	this.y += y;}
	public void add(Vec2d vec) {x += vec.x; y += vec.y;}
	public void add(Vec2i vec) {x += vec.x;y += vec.y;}
	public void add(Vec3d vec) {x += vec.x; y += vec.y;}
	public void minus(double x, double y) {this.x -= x; this.y -= y;}
	public void minus(Vec2d vec) {x -= vec.x;y -= vec.y;}
	public void minus(Vec3d vec) {x -= vec.x;y -= vec.y;}
	public void scale(double scale) {x *= scale; y *= scale;}
	public void scale(Vec2d scale) {this.x *= scale.x; this.y *= scale.y;}
	public String getPrintln() {return (" (" + x + ", " + y + ") ");}
	public void println() {System.out.println(" (" + x + ", " + y + ") ");}
	public void copy(Vec2d vec) {x = vec.x; y = vec.y;}
	public void round() {x = Math.round(x); y = Math.round(y);}
	
	public static Vec2d addition(Vec2d vector1, Vec2d vector2) {
		Vec2d result = new Vec2d();
		result.x = vector1.x;
		result.y = vector1.y;
		result.x += vector2.x;
		result.y += vector2.y;
		return result;
	}
	public static Vec2d addition(Vec2d vector1, Vec3d vector2) {
		Vec2d result = new Vec2d();
		result.x = vector1.x;
		result.y = vector1.y;
		result.x += vector2.x;
		result.y += vector2.y;
		return result;
	}
	public static Vec2d subtraction(Vec2d vector1, Vec2d vector2) {
		Vec2d result = new Vec2d();
		result.x = vector1.x;
		result.y = vector1.y;
		result.x -= vector2.x;
		result.y -= vector2.y;
		return result;
	}
	public static Vec2d subtraction(Vec2d vector1, Vec3d vector2) {
		Vec2d result = new Vec2d();
		result.x = vector1.x;
		result.y = vector1.y;
		result.x -= vector2.x;
		result.y -= vector2.y;
		return result;
	}
	public static Vec2d scale(double scalar, Vec2d vec) {
		Vec2d result = new Vec2d();
		result.x = vec.x * scalar;
		result.y = vec.y * scalar;
		return result;
	}
	
	public static Vec2d clone(Vec2d vec) {
		Vec2d result = new Vec2d();
		result.x = vec.x;
		result.y = vec.y;
		return result;
	}
	
	public static double difference(Vec2d vector1, Vec2d vector2) {
		double rx = (vector1.x - vector2.x) * (vector1.x - vector2.x);
		double ry = (vector1.y - vector2.y) * (vector1.y - vector2.y);  
		return Math.sqrt(rx  +  ry);		
	}
	
	public double difference(Vec2d vector) {
		double rx = (x - vector.x) * (x - vector.x);
		double ry = (y - vector.y) * (y - vector.y);  
		return Math.sqrt(rx  +  ry);
	}
	
	public Vec2d unitVector() {
		Vec2d result = new Vec2d();
		double mod = modulus();
		result.x = x / mod;
		result.y = y / mod;
		return result;
	}
	
	
	public double modulus() {return Math.sqrt((x * x) + (y * y));}
	
	public Vec2d vecDifference(Vec2d vector) {
		double rx = (this.x - vector.x) * (this.x - vector.x);
		double ry = (this.y - vector.y) * (this.y - vector.y);
		return new Vec2d(rx, ry);
	}
	
	/////////Vector Generation
	public static Vec2d generateRandomVector(double xBound, double yBound) {
		Vec2d result = new Vec2d();
		result.x = Maths.random.nextDouble(xBound);
		result.y = Maths.random.nextDouble(yBound);
		return result;		
	}
	public static Vec2d generateRandomVector(double xMin,double xMax, double yMin, double yMax) {
		Vec2d result = new Vec2d();
		result.x = Maths.random.nextDouble(xMin,xMax);
		result.y = Maths.random.nextDouble(yMin,yMax);
		return result;		
	}
	public static Vec2d generateRandomBaseVector() {
		Vec2d result = new Vec2d();
		Vec2d r = generateRandomVector(3,3);
		
		if(r.x == 0) 	  result.x = -1;
		else if(r.x == 1) result.x =  1;
		else 			  result.x =  0;
		
		if(r.y == 0)	  result.y = -1;
		else if(r.y == 1) result.y =  1;
		else 			  result.y =  0;
	
		return result;
	}
	
	/////////Vector Conversion
	public static Vec2d convertVec3ToVec2(Vec2d original) {
		Vec2d result = new Vec2d();
		result.x = original.x;
		result.y = original.y;
		return result;
	}

	/////////Logical Checks
	public boolean equals(Vec2d vec) {
		if(vec.x == this.x && vec.y == this.y) return true;
		else return false;
	}
	public boolean lessThan(Vec2d vec) {
		if(x < vec.x && y < vec.y) return true;
		return false;
	}
	public boolean greatThan(Vec2d vec) {
		if(x > vec.x && y > vec.y) return true;
		return false;
	}
	public boolean equals(double value) {
		if(x == value && y == value) return true;
		else return false;
	}
	public boolean lessThan(double value) {
		if(x < value && y < value) return true;
		return false;
	}
	public boolean greatThan(double value) {
		if(x > value && y > value) return true;
		return false;
	}
	public boolean anyLessThan(double value) {
		if(x < value || y < value) return true;
		return false;
	}
	public boolean anyGreatThan(double value) {
		if(x > value || y > value) return true;
		return false;
	}
	
}
