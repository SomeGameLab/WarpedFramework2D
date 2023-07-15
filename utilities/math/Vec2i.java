package warp.utilities.math
;

public class Vec2i { //NOTE -> when used in combination with MatrixI will be considered a ROW VECTOR

	public int x, y;
	
	//Constructors
	public Vec2i() {x = y = 0;}
	public Vec2i(int v) {x = y = v;}
	public Vec2i(int x, int y) {this.x = x;	this.y = y;}
	public Vec2i(Vec2i vec) {this.x = vec.x; this.y = vec.y;}
	
	//Getters and setters
	public static int getM() {return 1;}
	public static int getN() {return 2;}
	public void set(Vec2i vec) {x = vec.x;y = vec.y;}
	public void set(int x, int y) {this.x = x; this.y = y;}
	public void zero() {x = y = 0;}
	public void set(int value) {x = y = value;}
	public int getValue(int index) {
		switch(index) {
		case 0: return x;
		case 1: return y;
		default: System.err.println("Vec2i -> getValue() -> tried to access non existant index : " + index); return -1;
		}		
	}
	
	//Instance specific -> will change the value of the object that calls it
	//Basic Vector Operations
	public void println() {System.out.println(" ( " + x + ", " + y + " )");}
	public String getPrintln() {return (" ( " + x + ", " + y + " )");}
	public void add(Vec2i vec) {x += vec.x; y += vec.y;}
	public void add(int x, int y) {this.x += x; this.y += y;}
	public void minus(Vec2i vec) {x -= vec.x; y -= vec.y;}
	public void minus(int x, int y) {this.x -= x; this.y -= y;}
	public void scale(double scale) {x *= scale; y *= scale;}
	public void scale(Vec2i scale) {x *= scale.x; y *= scale.y;}
	public void copy(Vec2i vec) {this.x = vec.x; this.y = vec.y;}
	
	
	public Vec2i vecDifference(Vec2i vector) {
		int rx = (this.x - vector.x) * (this.x - vector.x);
		int ry = (this.y - vector.y) * (this.y - vector.y);
		return new Vec2i(rx, ry);
	}
	
	public double intDifference(Vec2i vector) {
		int rx = (this.x - vector.x) * (this.x - vector.x);
		int ry = (this.y - vector.y) * (this.y - vector.y); 
		return (double) Math.sqrt(rx + ry);
	}
	
	//Vector Generation
	public static Vec2i generateRandomVector(int xBound, int yBound) {
		Vec2i result = new Vec2i();
		result.x = Maths.random.nextInt(xBound);
		result.y = Maths.random.nextInt(yBound);
		return result;		
	}
	
	public static Vec2i generateRandomVector(int xMin,int xMax, int yMin, int yMax) {
		Vec2i result = new Vec2i();
		result.x = Maths.random.nextInt(xMin, xMax);
		result.y = Maths.random.nextInt(yMin, yMax);
		return result;		
	}
	
	public static Vec2i generateRandomBaseVector() {
		Vec2i result = new Vec2i();
		Vec2i r = generateRandomVector(3,3);
		
		if(r.x == 0) 	  result.x = -1;
		else if(r.x == 1) result.x =  1;
		else 			  result.x =  0;
		
		if(r.y == 0)	  result.y = -1;
		else if(r.y == 1) result.y =  1;
		else 			  result.y =  0;
	
		return result;
	}
	
	//Vector conversion
	public static Vec2i convertVec3ToVec2(Vec3i original) {
		Vec2i result = new Vec2i();
		result.x = original.x;
		result.y = original.y;
		return result;
	}
	
	public static Vec2d convert2iTo2d(Vec2i original) {
		return new Vec2d((double)original.x, (double)original.y);
	}
	
	//Static vector operations
	//Returns a new result vector without changing the input vectors
	public static Vec2i addVectors(Vec2i vector1, Vec2i vector2) {
		Vec2i result = new Vec2i();
		result.x = vector1.x;
		result.y = vector1.y;
		result.x += vector2.x;
		result.y += vector2.y;
		return result;
	}
	

	public double difference(Vec2i vector) {
		int rx = (this.x - vector.x) * (this.x - vector.x);
		int ry = (this.y - vector.y) * (this.y - vector.y);  
		return Math.sqrt(rx  +  ry);
	}
	
	public static double difference(Vec2i vector1, Vec2i vector2) {
		int rx = (vector1.x - vector2.x) * (vector1.x - vector2.x);
		int ry = (vector1.y - vector2.y) * (vector1.y - vector2.y);  
		return Math.sqrt(rx  +  ry);		
	}
	
	public boolean equals(Vec2i vec) {if(x == vec.x && y == vec.y) return true; return false;}
	
	
	
		
	
}
