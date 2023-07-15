package warp.graphics.sprite;

import java.awt.image.BufferedImage;

public class DirectionalSprite extends Sprite {

	BufferedImage[] directionalImage;
	
	public DirectionalSprite(BufferedImage[] directionalImage) {
		if(directionalImage.length > 8 || directionalImage.length < 8) {
			System.err.println("ERROR! -> Directional Sprite -> tried to load Buffered Image of size : " + directionalImage.length);
			System.err.println("ERROR! -> Directional Sprite -> use propper length array of 8 in format up, down, left, right, up_left, up_right, down_left, down_right");
			return;
		} else this.directionalImage = directionalImage;
	}
	
	//public BufferedImage getImage(MoveDirection dir) {return directionalImage[dir.ordinal()];}

	public boolean update() {return true;
		//TODO implement directional sprite update method;
		
	}
	
}
