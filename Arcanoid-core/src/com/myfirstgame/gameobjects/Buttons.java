package com.myfirstgame.gameobjects;

public class Buttons extends CommonObject {

	private boolean isPressed;
	
	public Buttons(float x, float y, float width, float height) {
		super(x, y, width, height);
		isPressed = false;
	}
	
	public void press(boolean pressMe) {
		isPressed = pressMe;
	}
	
	public boolean isButtonPressed() {
		return isPressed;
	}
	
	

}
