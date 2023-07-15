package warp.game.gameContext.managers;

import java.util.ArrayList;

import warp.game.GameObject;

public class SelectionManager {
	
	
	private static ArrayList<GameObject> selection;
	
	
	public static void selectObject(GameObject selection) {
		SelectionManager.selection.add(selection);
	}
	public static void setSelectionPosition() {};

}
