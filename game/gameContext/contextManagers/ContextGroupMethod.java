package warp.game.gameContext.contextManagers;

import warp.game.GameObject;

@FunctionalInterface
public interface ContextGroupMethod {
	public void action(GameObject object);
}
