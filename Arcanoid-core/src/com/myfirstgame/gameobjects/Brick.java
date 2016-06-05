package com.myfirstgame.gameobjects;

import com.badlogic.gdx.math.Rectangle;

public class Brick extends CommonObject {

	private boolean deleted;
	private Rectangle boundingRect;
	
	public Brick(float x, float y, float width, float height) {
		super(x, y, width, height);
		deleted = false;
		boundingRect = new Rectangle(x, y, width, height); //we will use this to indicate collision between bricks and ball
	}

	public void delete() {
		deleted = true;	
	}
	
	public Rectangle getRect() {
		return boundingRect;
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	
	

}
