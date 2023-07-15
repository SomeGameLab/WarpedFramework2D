package warp.graphics.screen.warpedScreens;

import warp.game.gameContext.GameContext;
import warp.graphics.camera.WarpedCameras;
import warp.graphics.screen.Screen;
import warp.graphics.screen.screenComposer.ScreenComposer;
import warp.graphics.screen.screenComposer.ScreenComposers;
import warp.graphics.screen.screenComposer.viewPort.VPLRender;
import warp.graphics.screen.screenComposer.viewPort.ViewPortLayer;
import warp.graphics.screen.screenComposer.viewPort.ViewPortLayers;
import warp.properties.FrameworkProperties;
import warp.utilities.math.Vec2i;

public class WarpTechScreen extends Screen {
	private static final long serialVersionUID = 1L;

	public void addComposers() {
		
		ScreenComposer warpTechComposer = new ScreenComposer(ScreenComposers.WARP_TECH_COMPOSER, new Vec2i(FrameworkProperties.SCALE_WINDOW_WIDTH, FrameworkProperties.SCALE_WINDOW_HEIGHT), new Vec2i());
		
		ViewPortLayer galaxyVPL = new ViewPortLayer(ViewPortLayers.WARP_TECH_GALAXY, GameContext.entitieManager, new Vec2i(FrameworkProperties.SCALE_WINDOW_WIDTH, FrameworkProperties.SCALE_WINDOW_HEIGHT), new Vec2i(), GameContext.cameraManager.getCamera(WarpedCameras.GALACTIC_MAP_CAMERA));
		galaxyVPL.setRenderMethod(VPLRender.renderGalacticMap);
		//warpTechComposer.addViewPortLayer(galaxyVPL);
		
		ViewPortLayer guiVPL = new ViewPortLayer(ViewPortLayers.WARP_TECH_GUI, GameContext.guiManager, new Vec2i(FrameworkProperties.SCALE_WINDOW_WIDTH, FrameworkProperties.SCALE_WINDOW_HEIGHT), new Vec2i(), GameContext.cameraManager.getCamera(WarpedCameras.DEFAULT));
		guiVPL.setRenderMethod(VPLRender.renderTargetsActiveGroups);
		warpTechComposer.addViewPortLayer(guiVPL);
		
		addScreenComposer(warpTechComposer);
		
	}

}
