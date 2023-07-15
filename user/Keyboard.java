package warp.user;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import warp.user.actions.DefaultActions;
import warp.user.actions.WarpedAction;

public class Keyboard implements KeyListener, DefaultActions {

	private static final int KEY_SIZE = 1028;
	private static ArrayList<KeyEvent> presses = new ArrayList<>();
	private static ArrayList<KeyEvent> releases = new ArrayList<>();
	private static KeyEvent[] pressedKeys = new KeyEvent[KEY_SIZE];
	private static KeyEvent[] releasedKeys = new KeyEvent[KEY_SIZE];
	private static HashMap<Integer, WarpedAction> pressBindings = new HashMap<>();
	private static HashMap<Integer, WarpedAction> releaseBindings = new HashMap<>();
	
	public Keyboard() {setDefaultKeyBindings();}
	private void setDefaultKeyBindings() {
		addKeyReleaseBinding(KeyEvent.VK_F1, toggleDebugDisplay);
		addKeyReleaseBinding(KeyEvent.VK_F2, toggleCapMinSize);
		addKeyPressBinding(KeyEvent.VK_W, panUp);
		addKeyPressBinding(KeyEvent.VK_S, panDown);
		addKeyPressBinding(KeyEvent.VK_A, panLeft);
		addKeyPressBinding(KeyEvent.VK_D, panRight);
		addKeyPressBinding(KeyEvent.VK_SUBTRACT, zoomOut);
		addKeyPressBinding(KeyEvent.VK_ADD,      zoomIn);
		addKeyPressBinding(KeyEvent.VK_PAGE_UP, increaseGameSpeed);
		addKeyPressBinding(KeyEvent.VK_PAGE_DOWN, decreaseGameSpeed);
		addKeyPressBinding(KeyEvent.VK_HOME, rotateClockwise);
		addKeyPressBinding(KeyEvent.VK_END, rotateCounterClockwise);
	}
	
	//Getters
	public ArrayList<KeyEvent> getKeys(){
		ArrayList<KeyEvent> result = new ArrayList<>();
		for(int i = 0; i < presses.size(); i++) result.add(presses.get(i));
		return  result;}
	//Setters
	public static void setKeyPressBindings(HashMap<Integer, WarpedAction> keyBindings) {pressBindings = keyBindings;}
	public static void addKeyPressBinding(Integer keyCode, WarpedAction action) {pressBindings.put(keyCode, action);}
	public static void setKeyReleaseBindings(HashMap<Integer, WarpedAction> keyBindings) {releaseBindings = keyBindings;}
	public static void addKeyReleaseBinding(Integer keyCode, WarpedAction action) {releaseBindings.put(keyCode, action);}
	//Removers
	public static void clearKeyPressBindings() {pressBindings.clear();}
	public static void clearKeyReleaseBindings() {releaseBindings.clear();}
	public static void removeKeyPressBinding(Integer keyCode) {pressBindings.remove(keyCode);}
	public static void removeKeyReleaseBinding(Integer keyCode) {releaseBindings.remove(keyCode);}
	
	public void update() {
		presses.clear();
		for(int i = 0; i < KEY_SIZE; i++) {if(pressedKeys[i] != null)presses.add(pressedKeys[i]);}
		presses.forEach(k ->{pressAction(k.getKeyCode());});
		
		releases.clear();
		for(int i = 0; i < KEY_SIZE; i++) {if(releasedKeys[i] != null) {
			releases.add(releasedKeys[i]);
			releasedKeys[i] = null;
		}}
		releases.forEach(kr -> {releaseAction(kr.getKeyCode());});
	}

	//Key Events
	public void keyPressed(KeyEvent e) {pressedKeys[e.getKeyCode()] = e;}
	public void keyReleased(KeyEvent e) {
		pressedKeys[e.getKeyCode()] = null;
		releasedKeys[e.getKeyCode()] = e;
	}
	public void keyTyped(KeyEvent e) {}/**This does not seem to work? lol*/
	
	private void pressAction(int keyCode) {if(pressBindings.get(keyCode) != null)pressBindings.get(keyCode).action();}
	private void releaseAction(int keyCode) {if(releaseBindings.get(keyCode) != null)releaseBindings.get(keyCode).action();}
	

}
