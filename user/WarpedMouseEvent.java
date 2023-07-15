package warp.user;

import java.awt.Point;
import java.awt.event.MouseEvent;

import warp.graphics.screen.screenComposer.ScreenComposer;
import warp.graphics.screen.screenComposer.viewPort.ViewPortLayer;
import warp.utilities.math.Vec2d;

public class WarpedMouseEvent {

	private MouseEvent e;
	private MouseEvents ID;
	private Point canvasP;
	private Point relativeP;
	
	public WarpedMouseEvent(MouseEvent e, MouseEvents ID) {
		this.e = e;
		this.ID = ID;
		canvasP = e.getPoint();
		relativeP = canvasP;
	}
	
	public MouseEvent getMouseEvent() {return e;}
	public MouseEvents getID() {return ID;}
	public Point getCanvasPoint() {return canvasP;}
	public Point getRelativePoint() {return relativeP;}
	public void setRelativePoint(ScreenComposer composer) {relativeP.setLocation(relativeP.x - composer.getPosition().x, relativeP.y - composer.getPosition().y);}
	public void setRelativePoint(ViewPortLayer vpl) {relativeP.setLocation(relativeP.x - vpl.getPosition().x, relativeP.y - vpl.getPosition().y);}	
	public void setRelativePoint(Vec2d relative) {relativeP.setLocation(relativeP.x - relative.x, relativeP.y - relative.y);}
	
}
