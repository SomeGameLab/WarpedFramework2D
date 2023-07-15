package warp.utilities.math.geometry;

import warp.game.entities.celestial.CelestialEntitie;
import warp.game.entities.celestial.CelestialEntities;
import warp.utilities.generalisedEnum.Directions;
import warp.utilities.math.Maths;

public class Orbit<T extends CelestialEntitie> extends EllipseD{

	private T body;
	private CelestialEntitie parentBody;
	private CelestialEntities orbitType;
	private double theta = Maths.random.nextDouble(Math.PI*2);
	public double rotationSpeed = ROTATION_SPEED_CONSTANT;
	private boolean clockwise = true;
	
	private static double SCALE_Y; //orbit step distance i.e. space between each orbit
	private static double SCALE_X = SCALE_Y * 2.0;
	private static final double STELLAR_SCALE_Y = 40000.0;
	private static final double STELLAR_SCALE_X = STELLAR_SCALE_Y * 2;
	private static final double PLANET_SCALE_Y = 3000.0;
	private static final double PLANET_SCALE_X = PLANET_SCALE_Y * 2;
	private static final double SATELLITE_SCALE_Y = 300.0;
	private static final double SATELLITE_SCALE_X = SATELLITE_SCALE_Y * 2;
	private static final double DEFAULT_SCALE = 1000;
	private static final int 	SCALE_D = 6;
	private static final int    ORBIT_SPEED_DECAY = 4;	
	public static double ROTATION_SPEED_CONSTANT = 0.005;
	
	public Orbit(T body) {
		this.body = body;
		parentBody = body.getParent();
		orbitType = body.getID();
		switch(body.getID()){
		case GALAXY_ENTITIE:
			System.err.println("Orbit -> " + body.getID() + "-> no defined instance opperation, using orbit defaults!");
			break;
		case STELLAR_ENTITIE:   
			SCALE_Y = STELLAR_SCALE_Y; SCALE_X = STELLAR_SCALE_X;
			rotationSpeed = ROTATION_SPEED_CONSTANT / 50 ;
			break;
		case PLANET_ENTITIE:	
			SCALE_Y = PLANET_SCALE_Y; SCALE_X = PLANET_SCALE_X;	 
			rotationSpeed = ROTATION_SPEED_CONSTANT / 10 ;
			break;
		case SATELLITE_ENTITIE: 
			SCALE_Y = SATELLITE_SCALE_Y; SCALE_X = SATELLITE_SCALE_X; 
			rotationSpeed = ROTATION_SPEED_CONSTANT;
		break;
		default:
			System.err.println("Orbit -> " + body.getID() + "-> no defined instance opperation, using orbit defaults!");
			SCALE_Y = DEFAULT_SCALE; 	 SCALE_X = DEFAULT_SCALE;
			break;
		
			
		}
		initializeRotationSpeed();
		initializeScale();
	}
	
	public void initializeRotationSpeed() {
		switch(orbitType) {
		case SATELLITE_ENTITIE:rotationSpeed = ROTATION_SPEED_CONSTANT; break;
		case PLANET_ENTITIE:   rotationSpeed = ROTATION_SPEED_CONSTANT / 10; break;
		case STELLAR_ENTITIE:  rotationSpeed = ROTATION_SPEED_CONSTANT / 50; break;
		default: System.err.println("Orbit -> " + body.getID() + "-> initializeRotationSpeed() -> no defined instance opperation, using orbit defaults!");break;
		}
		rotationSpeed = rotationSpeed / parentBody.getChildCount() * ORBIT_SPEED_DECAY;
	}
	
	private void initializeScale() {
		scale.x = parentBody.getChildCount() * SCALE_X; // Set general orbit radius
		scale.y = parentBody.getChildCount() * SCALE_Y;
		
		int rx = Maths.random.nextInt(2); 
		int ry = Maths.random.nextInt(2);
		
		if(rx == 0) scale.x += Maths.random.nextDouble(SCALE_X/SCALE_D); //Randomize the orbit a fraction of the orbit step distance
		else scale.x -= Maths.random.nextDouble(SCALE_X/SCALE_D);
		
		if(ry == 0) scale.y += Maths.random.nextDouble(SCALE_Y/SCALE_D);
		else scale.y -= Maths.random.nextDouble(SCALE_Y/SCALE_D);
		
	}
	
	
	public void setRotationDirection(boolean clockwise) {this.clockwise = clockwise;}
	public void setRotationSpeed(double rotationSpeed) {
		if(rotationSpeed < 0 || rotationSpeed > Math.PI/256) System.err.println("Orbit -> setRotationSpeed -> invalid speed : " + rotationSpeed);
		else this.rotationSpeed = rotationSpeed;
	}
	
	public void update() {
		//set rotation angle		
		if(clockwise) {
			theta += rotationSpeed;
			if(theta > Maths.TWO_PI) theta = 0;
			if(theta > 0 && theta < Maths.PI_ON_TWO) 		      			body.setDirection(Directions.DOWN_LEFT);
			else if(theta > Maths.PI_ON_TWO && theta < Maths.PI)       		body.setDirection(Directions.UP_LEFT);
			else if(theta > Maths.PI && theta < Maths.THREE_PI_ON_TWO) 	   	body.setDirection(Directions.UP_RIGHT);
			else if(theta > Maths.THREE_PI_ON_TWO && theta < Maths.TWO_PI) 	body.setDirection(Directions.DOWN_RIGHT);
		}
		else {
			theta -= rotationSpeed;
			if(theta < 0) theta = Maths.TWO_PI;
			if(theta > 0 && theta < Maths.PI_ON_TWO) 		      			body.setDirection(Directions.UP_RIGHT);
			else if(theta > Maths.PI_ON_TWO && theta < Maths.PI)       		body.setDirection(Directions.DOWN_RIGHT);
			else if(theta > Maths.PI && theta < Maths.THREE_PI_ON_TWO) 	   	body.setDirection(Directions.DOWN_LEFT);
			else if(theta > Maths.THREE_PI_ON_TWO && theta < Maths.TWO_PI)  body.setDirection(Directions.UP_LEFT);
		}
		
		//set body position based on new rotation angle of orbit
		body.setPosition(
				(parentBody.getPosition().x + parentBody.getSizeOffset().x + (scale.x * Math.cos(theta))),  //x pos
				(parentBody.getPosition().y + parentBody.getSizeOffset().y + (scale.y * Math.sin(theta)))); //y pos
		
		//correct error
		
		
	}
}
