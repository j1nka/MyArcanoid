package com.myfirstgame.gameobjects;

public class GameLife extends CommonObject {

	private boolean wasted;
	
	public GameLife(float x, float y, float width, float height) {
		super(x, y, width, height);
		wasted = false;
	}
	
	public void wasteLife() {
		wasted = true;
	}
	
	public boolean isWasted() {
		return wasted;
	}

}
