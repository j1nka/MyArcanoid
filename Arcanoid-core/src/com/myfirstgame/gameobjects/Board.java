package com.myfirstgame.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.myfirstgame.helpermethods.InputHandler;

public class Board extends CommonObject {

	private Rectangle boardRect;
	private int boardSpeed;
	boolean moveMe;

	public Board(float x, float y, float width, float height, int speed) {
		super(x, y, width, height);
		boardRect = new Rectangle(x, y, width, height); //we will use this to indicate collision between board and ball
		boardSpeed = speed;
		moveMe = false;
	}
	
	public void moveBoard(int x) { 
			position.x += x;
	}
	
	public boolean atRightSide() {
		return (position.x + width) >= RIGHT_LANE;
	}
	
	public boolean atLeftSide() {
		return position.x <= LEFT_LANE; 
	}
	
	public Rectangle getBoardRect() {
		return boardRect;
	}
	
	public int getBoardSpeed() {
		return boardSpeed;
	}
	
	public void restart(float x) {
		position.x = x;
	}
	
	public void update() {
		
		boardRect.set(position.x, position.y, width, height);
		
	}
	
	


}
