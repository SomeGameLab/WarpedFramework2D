package warp.graphics.screen.screenComposer.viewPort;

import java.awt.Graphics;

import warp.game.gameContext.GameContext;

public interface VPLRender {

	public ViewPortRender renderTargetsActiveGroups =
			(vpl) -> {
				vpl.clear();
				Graphics g = vpl.getGraphics();
				vpl.getTarget().forEachActiveGroup((obj) -> {
					vpl.renderObject = obj;
					if(obj.isVisible()) {						
						vpl.sz = vpl.camera.sizeTransformation(obj.getSize()); //scaled size of object based on the zoom value
						vpl.rp = GameContext.coordinateSystem.getPositionTransformation(obj.getPosition()); //2D screen position for the objects 3D world position
						vpl.camera.positionTransformation(vpl.rp, vpl.sz); //Offset the screen position for the view ports camera p
						GameContext.coordinateSystem.Center(vpl.rp); // offset the origin to the center of the screen
						g.drawImage(obj.raster(), (int)(vpl.rp.x),(int)(vpl.rp.y),(int)(vpl.sz.x),(int)(vpl.sz.y), null); // draw the object
						vpl.handleMouse();
					}
				});
				g.dispose();
			};
	
	public ViewPortRender renderGalacticMap =
			(vpl) -> {
				vpl.clear();
				Graphics g = vpl.getGraphics();
					vpl.getTarget().forEachActiveGroup((obj) -> {
					vpl.renderObject = obj;
					if(obj.isVisible()) {						
						vpl.sz = vpl.camera.sizeTransformation(obj.getSize()); //scaled size of object based on the zoom value
						vpl.rp = GameContext.coordinateSystem.getPositionTransformation(obj.getPosition()); //2D screen position for the objects 3D world position
						vpl.camera.positionTransformation(vpl.rp, vpl.sz); //Offset the screen position for the view ports camera p
						GameContext.coordinateSystem.Center(vpl.rp); // offset the origin to the center of the screen
						g.drawImage(obj.raster(), (int)(vpl.rp.x),(int)(vpl.rp.y),(int)(vpl.sz.x),(int)(vpl.sz.y), null); // draw the object
						vpl.handleMouse();
					}
				});
				g.dispose();
			};
}

