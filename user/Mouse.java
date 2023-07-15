package warp.user;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import warp.graphics.screen.Screen;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

	private static boolean inWindow = true;
	private static boolean moving = false;
	//private Point nowPoint;
	//private Point lastPoint;
	
	public static boolean isMoving() {if(moving)return true; return false;}
	public static boolean isInWindow() {return inWindow;}

	public void update() {
		/*
		if(moving) {
			System.out.println("Mouse -> mouse in motion");
			if(lastPoint == nowPoint) moving = false;
			else lastPoint = nowPoint;
		}
		*/
	}
	public void mouseWheelMoved(MouseWheelEvent e) {Screen.MouseEvent(new WarpedMouseEvent(e, MouseEvents.WHEEL_ROTATION));}
	public void mouseDragged(MouseEvent e) {Screen.MouseEvent(new WarpedMouseEvent(e, MouseEvents.DRAG));}
	public void mouseMoved(MouseEvent e) {Screen.MouseEvent(new WarpedMouseEvent(e, MouseEvents.MOVE));}
	public void mousePressed(MouseEvent e) {Screen.MouseEvent(new WarpedMouseEvent(e, MouseEvents.BUTTON_PRESS));}
	public void mouseReleased(MouseEvent e) {Screen.MouseEvent(new WarpedMouseEvent(e, MouseEvents.BUTTON_RELEASE));}
	public void mouseEntered(MouseEvent e) {inWindow = true;}
	public void mouseExited(MouseEvent e) {inWindow = false;}
	
	/**Don't use -> is invalidated by mouse moving while clicking*/
	public void mouseClicked(MouseEvent e) {}//
	
	
}
