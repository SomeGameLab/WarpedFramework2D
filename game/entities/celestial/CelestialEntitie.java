package warp.game.entities.celestial;

import java.awt.event.MouseEvent;

import warp.game.entities.Entitie;
import warp.game.gameContext.GameContext;
import warp.graphics.camera.WarpedCameras;
import warp.properties.WarpTechProperties;
import warp.user.WarpedMouseEvent;
import warp.utilities.math.Maths;

public abstract class CelestialEntitie extends Entitie {

	protected CelestialEntities id;
	protected CelestialEntitie parent;
	protected int childCount = 0;   // The actual number of children
	protected double fertility = 0; // Determines the number of children that spawn

	
	
	public CelestialEntities getID() {return id;}
	public int getChildCount() {return childCount;}
	public CelestialEntitie  getParent() {return parent;}

	protected void setFertility(double fertility) {
		double f_max = fertility * WarpTechProperties.FERTILITY_MULTIPLYER;
		double f_min = (fertility * WarpTechProperties.FERTILITY_MULTIPLYER) / 2;
		this.fertility = Maths.random.nextDouble(f_min, f_max);}
	
	public double getFertility() {return fertility;}

	public void mouseEvent(MouseEvent mouseEvent) {System.out.println("GameObject : " + this.getClass() + " Has a mouse Event" );
	GameContext.cameraManager.getCamera(WarpedCameras.GALACTIC_MAP_CAMERA).setTarget(this);;
	;}
	
	
	protected void mouseDragged() {};
	protected void mousePressed(WarpedMouseEvent mouseEvent) {
		GameContext.cameraManager.getCamera(WarpedCameras.GALACTIC_MAP_CAMERA).setTarget(this);
	};
	protected void mouseReleased(WarpedMouseEvent mouseEvent) {};
	protected void mouseRotation(WarpedMouseEvent mouseEvent) {};
	protected void mouseEntered() {
		System.out.println("CelestialEntitie : mouse entered " + this.getClass().getSimpleName());
	}
	protected void mouseExited() {
		System.out.println("CelestialEntitie : mouse exited " + this.getClass().getSimpleName());
	}

	protected void mouseMoved(WarpedMouseEvent mouseEvent) {
		
		
	}



	protected void mouseDragged(WarpedMouseEvent mouseEvent) {
		
		
	}
	
	
	
	
}
