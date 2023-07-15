package warp.game.gameContext.contextCoordinates;

import warp.properties.FrameworkProperties;
import warp.utilities.math.MatrixD;
import warp.utilities.math.Vec2d;
import warp.utilities.math.Vec3d;

public class ContextCoordinate {

	private static ContextCoordinates id;	
	@SuppressWarnings("unused")
	private static int modulus = FrameworkProperties.MODULUS; /**Will determine the size of tiles  */

	private static final int CONVERSION_MATRIX_M = 3; //Dimensions of the conversion matrix
	private static final int CONVERSION_MATRIX_N = 2;
	
	private static double theta = 0; // angle of deviation between screen X axis and theoretical X axis (clockwise rotation)
	private static double omega  = Math.PI/2; // angle of deviation between screen Y axis and theoretical Y axis (clockwise rotation)
	
	private static MatrixD conversionMatrix = new MatrixD(CONVERSION_MATRIX_M,CONVERSION_MATRIX_N);
	private static Vec3d scalar = new Vec3d();
	
	private static Vec2d screenOffset = new Vec2d(FrameworkProperties.WINDOW_WIDTH/2, FrameworkProperties.WINDOW_HEIGHT/2);
	
	private static Vec2d screenPosition = new Vec2d();
	private static MatrixD screenMatrix;
	
	public ContextCoordinate(ContextCoordinates id) {
		ContextCoordinate.id = id;		
		initializeConversionMatrix(id);
	}
	
	public Vec2d getPositionTransformation(Vec3d vec) {
		screenMatrix = MatrixD.multiplication(vec, conversionMatrix);
		screenPosition.set(screenMatrix.getValue(0, 0), screenMatrix.getValue(0, 1));
		return screenPosition;
	}
	public void Center(Vec2d position) {position.add(screenOffset);}
	
	public ContextCoordinates getID() {return id;}
	
	private void initializeConversionMatrix(ContextCoordinates id) {
		switch(id) {
		case PLAN:
			theta = 0.0;
			omega = Math.PI/2;
			scalar.set(1.0, 1.0, 0.0);
			conversionMatrix.setMatrix(new double[][]  
					{{1.0 * scalar.x, 0.0 * scalar.x},
					 {0.0 * scalar.y, 1.0 * scalar.y}, 
					 {0.0 * scalar.z, 0.0 * scalar.z}});break;
		case ISOMETRIC:
			theta = 30.0;
			omega  = 30.0;
			scalar.set(1.0, 1.0, 1.0); break;
		case DIMETRIC:
			theta = 30;
			omega  = 30;
			scalar.set(1.0, 1.0, 0.5); break;	
		case TRIMETRIC:
			theta = 20;
			omega  = 40;
			scalar.set(1.0, 1.0, 0.75); break;
		case CABINET:
			theta = 0;
			omega  = 45;
			scalar.set(1.0, 0.5, 1.0); break;			
		case CAVALIER:
			theta = 0;
			omega  = 45;
			scalar.set(1.0, 1.0, 1.0); break;			
		case MILITARY:
			theta = 67;
			omega  = 68;
			scalar.set(1.0, 1.0, 1.0); break;
		default:
			System.err.println("ContextCoordinate -> invalid switch id : " + id); break;
		}
		if(id != ContextCoordinates.PLAN) {			
			conversionMatrix.setMatrix(new double[][]  
				{{Math.cos(theta) * scalar.x, Math.sin(theta) * scalar.x},
				 {Math.cos(omega) * scalar.y, Math.sin(omega) * scalar.y}, 
				 {0.0			  * scalar.z, 1.0		      * scalar.z}});
		}
		
		
	}
	
	

}