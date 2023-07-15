package warp.game.gui;

import warp.audio.AudioController;
import warp.audio.SoundEffects;
import warp.graphics.sprite.PrimitiveSprite;
import warp.graphics.sprite.Sprite;
import warp.user.WarpedMouseEvent;
import warp.user.actions.WarpedAction;
import warp.utilities.ImageUtilities;
import warp.utilities.generalisedEnum.Colours;
import warp.utilities.math.Vec3d;

public class GUIButton extends GUIElement {

	private Sprite buttonSprite;
	private Sprite hoveredSprite;
	private Sprite pressedSprite;
	private Sprite releasedSprite;
	
	private SoundEffects enteredSFX  = SoundEffects.DEFAULT_BUTTON_ENTERED;
	private SoundEffects exitedSFX   = SoundEffects.DEFAULT_BUTTON_EXITED;
	private SoundEffects pressedSFX  = SoundEffects.DEFAULT_BUTTON_PRESSED;
	private SoundEffects releasedSFX = SoundEffects.DEFAULT_BUTTON_RELEASED;
	
	private WarpedAction pressedAction;                                                                 
	private WarpedAction releasedAction;
	private WarpedAction enteredAction;
	private WarpedAction exitedAction;
	
	private static final int BRIGHTNESS_OFFSET = -20;
	private static final int TINT_OFFSET = 20;
	
	public GUIButton(Sprite buttonSprite, Vec3d position) {
		this.buttonSprite = buttonSprite;
		this.position = position;
		setRaster(buttonSprite.raster());
		hoveredSprite  = new PrimitiveSprite(ImageUtilities.changeBrightness(buttonSprite.raster(), BRIGHTNESS_OFFSET));
		pressedSprite  = new PrimitiveSprite(ImageUtilities.changeBrightness(buttonSprite.raster(), BRIGHTNESS_OFFSET * 2));
		releasedSprite = new PrimitiveSprite(ImageUtilities.tint(buttonSprite.raster(), TINT_OFFSET, Colours.RED));
	}
	public GUIButton(Sprite buttonSprite, Sprite hoveredSprite, Sprite pressedSprite, Sprite releasedSprite, Vec3d position) {		
		this.buttonSprite   = buttonSprite;
		this.position = position;
		setRaster(buttonSprite.raster());
		this.hoveredSprite  = hoveredSprite;
		this.pressedSprite  = pressedSprite;
		this.releasedSprite = releasedSprite;
	}
	
	/* ---------------------- Setters ---------------------- */
	public void setEnteredSFX(SoundEffects enteredSFX) {this.enteredSFX = enteredSFX;}
	public void setExitedSFX(SoundEffects exitedSFX) {this.exitedSFX = exitedSFX;}
	public void setPressedSFX(SoundEffects pressedSFX) {this.pressedSFX = pressedSFX;}
	public void setReleasedSFX(SoundEffects releasedSFX) {this.releasedSFX = releasedSFX;}
	public void setButtonSprite(Sprite buttonSprite) {this.buttonSprite = buttonSprite;}
	public void setHoveredSprite(Sprite hoveredSprite) {this.hoveredSprite = hoveredSprite;}
	public void setPressedSprite(Sprite pressedSprite) {this.pressedSprite = pressedSprite;}
	public void setReleasedSprite(Sprite releasedSprite) {this.releasedSprite = releasedSprite;}
	public void setPressedAction(WarpedAction pressedAction) {this.pressedAction = pressedAction;}
	public void setReleasedAction(WarpedAction releasedAction) {this.releasedAction = releasedAction;}
	public void setEnteredAction(WarpedAction enteredAction) {this.enteredAction = enteredAction;}
	public void setExitedAction(WarpedAction exitedAction) {this.exitedAction = exitedAction;}
	
	protected void updateRaster()  {return;}
	protected void updatePosition(){return;}
	protected void updateObject()  {return;}

	protected void mouseEntered() {
		setRaster(hoveredSprite.raster());
		AudioController.playSoundEffect(enteredSFX);
		if(enteredAction != null) enteredAction.action();
	}
	protected void mouseExited() {
		setRaster(buttonSprite.raster());
		AudioController.playSoundEffect(exitedSFX);
		if(exitedAction != null) exitedAction.action();
	}
	protected void mousePressed(WarpedMouseEvent mouseEvent) {
		setRaster(pressedSprite.raster());
		AudioController.playSoundEffect(pressedSFX);		
		if(pressedAction!= null) pressedAction.action();
	} 
	protected void mouseReleased(WarpedMouseEvent mouseEvent) {
		setRaster(releasedSprite.raster());
		AudioController.playSoundEffect(releasedSFX);
		if(releasedAction != null) releasedAction.action();
	}	
	
	protected void mouseMoved(WarpedMouseEvent mouseEvent) {return;}
	protected void mouseDragged(WarpedMouseEvent mouseEvent) {return;}
	protected void mouseRotation(WarpedMouseEvent mouseEvent) {return;}

}
