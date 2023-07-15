package warp.graphics.screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JFrame;

import warp.WarpedFramework2D;
import warp.graphics.screen.frameworkDisplay.FrameworkDisplay;
import warp.graphics.screen.screenComposer.ScreenComposer;
import warp.graphics.screen.screenComposer.ScreenComposers;
import warp.properties.FrameworkProperties;
import warp.user.UserInput;
import warp.user.WarpedMouseEvent;
import warp.utilities.Timer;
import warp.utilities.math.Vec2d;

public abstract class Screen extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	// NEVER USE SCREEN.WIDTH/HEIGHT that is Canvas width / height 
	public Timer time = new Timer();  
	private JFrame frame;
	private static Graphics g;
	private static int scaleWidth;
	private static int scaleHeight;
	private static HashMap<ScreenComposers, Thread> composerThreads = new HashMap<>(){private static final long serialVersionUID = 1L;};
	private static HashMap<ScreenComposers, ScreenComposer> composers = new HashMap<>(){private static final long serialVersionUID = 1L;};
	private int composerCount = 0;
	
	public static FrameworkDisplay frameworkDisplay = new FrameworkDisplay();    
	private static Color clearColour = new Color(0,0,0, 0);
	
	public static ScreenComposer getComposer(ScreenComposers composer) {return composers.get(composer);}
	public static HashMap<ScreenComposers, ScreenComposer> getComposers(){return composers;}

	public Screen() {
		time.setTickSpeed(FrameworkProperties.SCREEN_REFRESH_RATE);
		if(FrameworkProperties.DEBUG) {
			System.out.println("Screen -> Creating JFrame with Settings : ");
			System.out.println("Screen -> Name  : " + FrameworkProperties.WINDOW_NAME);
			System.out.println("Screen -> Scale : " + FrameworkProperties.WINDOW_SCALE);
			System.out.println("Screen -> Aspect Ratio : " + FrameworkProperties.WINDOW_ASPECT_RATIO);
			System.out.println("Screen -> Width  : " + FrameworkProperties.SCALE_WINDOW_WIDTH);
			System.out.println("Screen -> Height : " + FrameworkProperties.SCALE_WINDOW_HEIGHT);
		}
		Dimension size = new Dimension(FrameworkProperties.SCALE_WINDOW_WIDTH, FrameworkProperties.SCALE_WINDOW_HEIGHT);
		setPreferredSize(size);
		scaleWidth = size.width;
		scaleHeight = size.height;
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle(FrameworkProperties.WINDOW_NAME);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		Image icon = Toolkit.getDefaultToolkit().getImage("assets/framework/frameicon.png");
		frame.setIconImage(icon);
		frame.setVisible(true);	
		
		addKeyListener(UserInput.keyboard);
		addMouseListener(UserInput.mouse);
		addMouseMotionListener(UserInput.mouse);
		addMouseWheelListener(UserInput.mouse);
		
		addComposers();
	}
	public abstract void addComposers();
	
	public void render() {
		//draw Screen composers onto canvas
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		composers.forEach((s, c) -> {
			if(c.isVisible())
				//c.lock.readLock().lock();
				g.drawImage(c.raster(), c.getPosition().x, c.getPosition().y, c.getSize().x, c.getSize().y, null);
				//c.lock.readLock().unlock();
		});
		frameworkDisplay.render(g);
		bs.show();
		clear();	
	}
	
	
	/* ---------------------- Screen Composers ---------------------- */
	public void addScreenComposer(ScreenComposer composer) { 	 	
		if(composerCount >= FrameworkProperties.SCREEN_COMPOSERS_MAX) {
			if(FrameworkProperties.DEBUG) System.err.println("Screen -> tried to add more than " + FrameworkProperties.SCREEN_COMPOSERS_MAX + "screen composers");
			return;
		} else {
			if(FrameworkProperties.DEBUG) System.out.println("Screen -> added ScreenComposer : " + composer.getType());
			composers.put(composer.getType(), composer);
			composerThreads.put(composer.getType(), composer.start());
			composerCount++;
		}
	}
	public HashMap<ScreenComposers, ScreenComposer> getScreenComposers(){return composers;}
	public void removeScreenComposer(ScreenComposers type) {
		if(!composers.containsKey(type)) {
			if(FrameworkProperties.DEBUG) System.err.println("Screen -> removeScreenComposer() -> tried to remove non-existant sceen composer : " + type);
			return;
		} else {
			if(FrameworkProperties.DEBUG) System.out.println("Screen -> removing composer : " + type);
			composers.remove(type);
			try {
				composerThreads.get(type).join();
			} catch (InterruptedException e) {
				if(FrameworkProperties.DEBUG) System.err.println("Screen -> removeScreenComposer() -> failed to join thread : " + type);
				e.printStackTrace();
			}
			composerCount--;
		}
	}
	
	/* ---------------------- Thread ---------------------- */
	public synchronized Thread start() {
		if(FrameworkProperties.DEBUG) System.out.println("Screen -> Starting Screen Thread");
		Thread result = new Thread(this, "Screen");
		result.start();
		return result;
	}
	public void run() {		
		while(WarpedFramework2D.isRunning()) {			
			if(time.update()) render(); 
		}
	}
	public synchronized void stop() {
		composerThreads.forEach((s, t) -> {try {
			t.join();
		} catch (InterruptedException e) {
			System.err.println("Screen -> stop() -> failed to join screen composer thread");
			e.printStackTrace();
		}});
	}
	
	/* ---------------------- Mouse ---------------------- */
	public static void MouseEvent(WarpedMouseEvent mouseEvent) {
		composers.forEach((e, c) -> {
			if(c.isHit(mouseEvent.getRelativePoint())) {
				c.MouseEvent(mouseEvent);
			}
		});  
	}
	
	
	
	////////////////PRIMITIVE RENDER METHODS//////////////// - OBSOLETE DO NOT USE THESE
	//public static void renderEntitie(Entitie entitie) {g.drawImage(entitie.raster(),(int)entitie.getPosition().x,(int)entitie.getPosition().y, entitie.getSize().x, entitie.getSize().y , null); }
	public static void renderBufferedImage(BufferedImage img, Vec2d position) {	g.drawImage(img,(int)position.x,(int)position.y, img.getWidth(), img.getHeight(), null);}
	public static void renderBufferedImage(BufferedImage img, Vec2d position, Vec2d size) {	g.drawImage(img,(int)position.x,(int)position.y,(int)size.x,(int)size.y, null);}
	public static void renderString(String string, Vec2d position) {	g.drawString(string,(int)position.x,(int)position.y);}
	public static void renderString(String string, Vec2d position, Font font) {	g.setFont(font);g.drawString(string,(int)position.x,(int)position.y);}
	public static void renderString(String string, Vec2d position, Font font, Color color) {g.setColor(color);g.setFont(font);g.drawString(string,(int)position.x,(int)position.y);}
	public static void renderRect(Rectangle rect, Color color) {g.setColor(color);g.fillRect(rect.x, rect.y, rect.width, rect.height);}
	public static void renderRect(Vec2d position, Vec2d size, Color color) {g.setColor(color);g.fillRect((int)position.x,(int)position.y,(int)size.x,(int)size.y);}
	public static void clear() {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, scaleWidth, scaleWidth);
		g.dispose();
	}
	
	

}
