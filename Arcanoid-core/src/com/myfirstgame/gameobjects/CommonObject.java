package com.myfirstgame.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class CommonObject {
		
	//And again some constants )
	
	public static final int RIGHT_LANE = 160; 
	public static final int LEFT_LANE = 0;
	
	protected Vector2 position;
	protected float width;
	protected float height;
	
	//In this game we got lots of rectangular objects (bricks, buttons, board) - so i made a SuperClass for them
	
	public CommonObject(float x, float y, float width, float height) {
		position = new Vector2(x, y);
		this.width = width;
		this.height = height;
	}

	public float getX() {	
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	

}
