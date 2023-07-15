package warp.graphics.sprite.spriteSheets;

import java.awt.image.BufferedImage;

import warp.graphics.sprite.PrimitiveSprite;
import warp.properties.FrameworkProperties;
import warp.utilities.ImageUtilities;
import warp.utilities.math.Maths;

public class SpriteSheet {

	//Test Sprite Sheets
	public static final SpriteSheet testSprites = new SpriteSheet("assets/test/testspritesheet.png", 64, 64);
	
	private BufferedImage sheet; /** image containing the pixel data for the sprites */
	private int sheetWidth; /** pixel width of the sheet */
	private int sheetHeight; /** pixel height of the sheet */
	private boolean isSheetSquare = false;
	
	private BufferedImage[] rawSpriteImages; /** array of sub images derived from the sheet */
	public int spriteWidth;	/**pixel width of a single sprite */
	public int spriteHeight;/** pixel height of a single sprite */ 
	private boolean isSpriteSquare = false;
		
	private int spriteXCount; /** number of sprites across the sheet   */
	private int spriteYCount; /** number of sprites from top to bottom */
	private int spriteCount;  /** total number of sprites on the sheet */
	
	
	public boolean isSpriteSquare() {if(isSpriteSquare) return true; return false;}
	public boolean isSheetSquare() {if(isSheetSquare) return true; return false;}
	public int getSpriteCount() {int result = spriteCount; return result;}
	public int getXcount() {int result = spriteXCount; return result;}
	public int getYcount() {int result = spriteYCount; return result;}
	
	/**SpriteSheet contains sprites of ONLY ONE size
	 * to create sprites of a different size create a new sprite sheet with that size
	 * Create all sprite sheets as static objects, no need to have multiples of the same image data
	 * @param string - directory location of the sprite sheet file
	 * 		  int    - width of each sprite on the sheet	
	 * 		  int    - height of each sprite on the sheet
	 * 		  */
	public SpriteSheet(String path, int spriteWidth, int spriteHeight) {
		if(FrameworkProperties.DEBUG)System.out.println("SpriteSheet -> Attempting to load sprite sheet from : " + path);
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		if(spriteWidth == spriteHeight) isSpriteSquare = true;
		sheet = ImageUtilities.loadBufferedImage(path);
		sheetWidth = sheet.getWidth();
		sheetHeight = sheet.getHeight();
		if(sheetWidth == sheetHeight) isSheetSquare = true;
		if(spriteWidth > sheetWidth || spriteHeight > sheetHeight) {
			System.err.println("SpriteSheet -> spriteWidth or height is larger than sheet size -> sprite/sheet size : " + "( " + spriteWidth + ", " + spriteHeight + ") (" + sheetWidth + ", " + sheetHeight + ")");
		}
		spriteXCount = sheetWidth / spriteWidth;
		spriteYCount = sheetHeight / spriteHeight;
		spriteCount = spriteXCount * spriteYCount;
		rawSpriteImages = new BufferedImage[spriteCount];
		int i = 0;
		for(int y = 0; y < spriteYCount; y++) {
			for(int x = 0; x < spriteXCount; x++) {
				rawSpriteImages[i] = sheet.getSubimage(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight);
				i++;
			}
		}
		if(FrameworkProperties.DEBUG) {			
			System.out.println("SpriteSheet -> Number of sprites on sheet : " + spriteCount);
			System.out.println("SpriteSheet -> Sprite (Width, Height) : ("+spriteWidth+", "+spriteHeight+")");
			System.out.println("SpriteSheet -> Number of subSprites created : " + i);
		}
	}
	
	/* ---------------------- Sprite Generation ---------------------- */
	public PrimitiveSprite generatePrimitiveSprite(int x, int y) {return new PrimitiveSprite(getBufferedImage(x,y));}
	public PrimitiveSprite generateRandomPrimitiveSprite() {return new PrimitiveSprite(getRandomBufferedImage());}
	
	/* ---------------------- Raw Sprite data Generation ---------------------- */
	/**@return returns the raw data of a primitive sprite*/
	private BufferedImage getBufferedImage(int x, int y) {
		if(x >= spriteXCount || y >= spriteYCount) {
			System.err.println("SpriteSheet -> getSprite() -> invalid sheet coordinates : (" + x + ", " + y +")");
			return null;
		}
		else return rawSpriteImages[x + y * spriteXCount];
	}
	private BufferedImage getRandomBufferedImage() {
		int rand = Maths.random.nextInt(spriteCount);
		int i = 0;
		for(int y = 0; y < spriteYCount; y++) {
			for(int x = 0; x < spriteXCount; x++) {
				if(rand == i) return getBufferedImage(x,y);
				i++;
			}
		}
		System.err.println("SpriteSheet -> getRandomSprite() -> failed to find random sprite");
		return null;
	}

	

	
}
