package warp.graphics.camera.customCameras;

import warp.graphics.camera.WarpedCamera;
import warp.graphics.camera.WarpedCameras;

public class GalacticMapCamera extends WarpedCamera {

	public GalacticMapCamera(WarpedCameras id) {super(id);}

	public void zoomIn()   {
		zoom += zoomSpeed;
		if(zoom > ZOOM_RANGE_MAX) zoom = ZOOM_RANGE_MAX;
		setZoomSpeed();
		setMoveSpeed();
		
	}
	public void zoomOut()  {
		zoom -= zoomSpeed;
		if(zoom < ZOOM_RANGE_MIN) zoom = ZOOM_RANGE_MIN;
		setZoomSpeed();
		setMoveSpeed();
	}
	
	public void update() {
		if(trackTarget) {
			position.x = -target.getPosition().x -target.getSizeOffset().x;
			position.y = -target.getPosition().y -target.getSizeOffset().y;			
			
			/*
			System.out.println("Object Position : " + target.getPosition().getPrintln());
			System.out.println("Camera Position : " + target.c1.getPrintln());
			System.out.println("Camera zoom : " + target.zoom);
			System.out.println("rp1 : cood transform : " + target.rp1.getPrintln());
			Sytem.out.println("rp2 : camera transform : " + target.rp2.getPrintln());
			System.out.println("rp3 : ceter trasorm : " + target.rp3.getPrintln());
			*/
		}
	}
	
	
}
