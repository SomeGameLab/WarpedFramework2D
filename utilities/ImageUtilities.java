package warp.utilities;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import warp.properties.FrameworkProperties;
import warp.utilities.generalisedEnum.Colours;
import warp.utilities.math.Maths;

public class ImageUtilities {

	
	/* ---------------------- Loading ---------------------- */
	public static BufferedImage loadBufferedImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.err.println("ImageUtilities -> failed to read file at : " + path);
			e.printStackTrace();
		}
		
		if(image == null)System.err.println("ImageUtilities -> failed to load buffered image");
		else if(FrameworkProperties.DEBUG)System.out.println("ImageUtilities -> file was loaded to Buffered Image from : " + path);
		return image;
	}

	/* ---------------------- Tint ---------------------- */
	/**Avoid using during runtime this is a comprehensive and slow operation
	 * Instead create a tinted version during compilation and hot swap as needed */
	public static BufferedImage tint(BufferedImage original, int amount, Colours colour) {
		BufferedImage result = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    result.getGraphics().drawImage(original, 0, 0, null);
        int[] resultPixels = ((DataBufferInt)result.getRaster().getDataBuffer()).getData();
        for (int y = 0; y < result.getHeight(); y++) {
            for (int x = 0; x < result.getWidth(); x++) {

                int argb  = resultPixels[x + y * original.getWidth()]; 
                int alpha = 0xFF & (argb >> 24);
                int red   = 0xFF & (argb >> 16);
                int green = 0xFF & (argb >>  8);
                int blue  = 0xFF & (argb >>  0);

                int mr, mg, mb; mr = mg = mb = 1;
                switch(colour) {
                case RED:     mr =  1; mg = -1; mb = -1; break;
                case GREEN:	  mr = -1; mg =  1; mb = -1; break;
                case BLUE:	  mr = -1; mg = -1; mb =  1; break;
				case MAGENTA: mr =  1; mg = -1;	mb =  1; break;
				case CYAN:    mr = -1; mg =  1;	mb =  1; break;
				case YELLOW:  mr =  1; mg =  1; mb = -1; break;
				default: System.err.println("ImageUtilities -> tint"); break;
                }
                red   = Maths.clampInt(red   + (amount * mr), 0, 255);
                green = Maths.clampInt(green + (amount * mg), 0, 255);
                blue  = Maths.clampInt(blue  + (amount * mb), 0, 255); 
                resultPixels[x + y * result.getWidth()] = (alpha << 24) | (red << 16 ) | (green<<8) | blue;
            }
        }
        return result;
	}
	
	
	/* ---------------------- Brightness ---------------------- */
	public static BufferedImage changeBrightness(BufferedImage original, int amount) {
		BufferedImage result = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    result.getGraphics().drawImage(original, 0, 0, null);
        int[] resultPixels = ((DataBufferInt)result.getRaster().getDataBuffer()).getData();
        for (int y = 0; y < result.getHeight(); y++) {
            for (int x = 0; x < result.getWidth(); x++) {

                int argb  = resultPixels[x + y * original.getWidth()]; 
                int alpha = 0xFF & (argb >> 24);
                int red   = 0xFF & (argb >> 16);
                int green = 0xFF & (argb >>  8);
                int blue  = 0xFF & (argb >>  0);

                
                red   = Maths.clampInt(red   + amount, 0, 255); // Can be called like this thanks to import
                green = Maths.clampInt(green + amount, 0, 255);
                blue  = Maths.clampInt(blue  + amount, 0, 255);

                resultPixels[x + y * result.getWidth()] = (alpha << 24) | (red << 16 ) | (green<<8) | blue;
            }
        }
        return result;
	}
	
	/* ---------------------- Rotation ---------------------- */
	public static BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
	    double rads = Math.toRadians(angle);
	    double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
	    int w = img.getWidth();
	    int h = img.getHeight();
	    int newWidth = (int) Math.floor(w * cos + h * sin);
	    int newHeight = (int) Math.floor(h * cos + w * sin);

	    BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = rotated.createGraphics();
	    AffineTransform at = new AffineTransform();
	    at.translate((newWidth - w) / 2, (newHeight - h) / 2);

	    int x = w / 2;
	    int y = h / 2;

	    at.rotate(rads, x, y);
	    g2d.setTransform(at);
	    g2d.drawImage(img, 0, 0, null);
	    g2d.dispose();

	    return rotated;
	}
	public static BufferedImage rotateImageByRads(BufferedImage img, double angle) {
	    double rads = angle;
	    double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
	    int w = img.getWidth();
	    int h = img.getHeight();
	    int newWidth = (int) Math.floor(w * cos + h * sin);
	    int newHeight = (int) Math.floor(h * cos + w * sin);

	    BufferedImage result = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = result.createGraphics();
	    AffineTransform at = new AffineTransform();
	    at.translate((newWidth - w) / 2, (newHeight - h) / 2);

	    int x = w / 2;
	    int y = h / 2;

	    at.rotate(rads, x, y);
	    g2d.setTransform(at);
	    g2d.drawImage(img, 0, 0, null);
	    g2d.dispose();

	    return result;
	}
	
}
