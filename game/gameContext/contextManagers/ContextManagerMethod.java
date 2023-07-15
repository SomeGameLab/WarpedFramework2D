package warp.game.gameContext.contextManagers;

import warp.game.GameObject;

public interface ContextManagerMethod {
	public void action(ContextManager<? extends GameObject> manager);
	
}
