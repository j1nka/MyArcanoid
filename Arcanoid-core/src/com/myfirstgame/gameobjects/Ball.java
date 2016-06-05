package com.myfirstgame.gameobjects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.myfirstgame.helpermethods.AssetLoader;
import com.myfirstgame.world.GameWorld;

public class Ball {

	//Some constants - maybe i should do a "Constant" class?
	
	public static final int BOTTOM_LANE = 160 * Gdx.graphics.getHeight()/Gdx.graphics.getWidth();
	public static final int TOP_LANE = 0;
	public static final int RIGHT_LANE = 160;
	public static final int LEFT_LANE = 0;
	
	private Vector2 velocity;
	private Circle boundingCircle;
	private boolean inTheAir;
	private Random rand;
	private Vector2 position;
	private float radius;
	
	public Ball(float x, float y, float radius) {
		position = new Vector2(x,y);
		this.radius = radius;
		velocity = new Vector2(0, 0);
		boundingCircle = new Circle(x,y, radius); // we will use this to indicate collisions with bricks or board
		inTheAir = false;
		rand = new Random();
	}
	
	public boolean collidesBrick(Brick brick) {
		return Intersector.overlaps(boundingCircle, brick.getRect());	
	}
	
	public void hitThe(CommonObject common) { 
		
		if (common instanceof Brick)
			AssetLoader.hitBrick.play();
		if (common instanceof Board)
			AssetLoader.hitBoard.play();
		
		//Quite simple logic - if ball hit the right half of the board/brick - it will
		//be redirected to the right. If it hits the left side - to the left. To make
		//some "normalization" i used difference between coordinate x (where ball hits the half of board)
		//to the width/2 and multiply this difference to velocity.y. So, in two words - if ball hits the end 
		//of the board - velocity.x = velocity.y, and if it hits the board before the end of the board,
		//velocity.x = (0-1)*velocity.y
		
		if (position.x < (common.getX() + common.getWidth()/2))
		{
			velocity.x = -velocity.y*(-2*position.x + 2*common.getX() + common.getWidth())/common.getWidth();
			velocity.y = - velocity.y;
			
		}
		else if (position.x > (common.getX() + common.getWidth()/2))
		{
			velocity.x = velocity.y*(2*position.x - 2*common.getX() - common.getWidth())/common.getWidth();
			velocity.y = - velocity.y;
		}
		else
		{
			velocity.x = 0;	
			velocity.y = - velocity.y;
		}
	}
	
	public boolean collidesBoard(Board board) {
		
		if (inTheAir) //it will helps us to avoid some collisions and mistakes
			return Intersector.overlaps(boundingCircle, board.getBoardRect());
		else
			return false;
	}
	
	public void onClick(float speedY) {
		
		velocity.y = -speedY;
		velocity.x = (rand.nextInt(2)%2 == 1 ? rand.nextInt((int)speedY) + 1 : rand.nextInt((int)speedY) - speedY); //this is random function 
		//that will decide for us in which direction by x axis should our ball flight :)
		inTheAir = true;
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	

	public float getRadius() {
		return radius;
	}
	
	public boolean isOutOfScope() {
		return position.y > BOTTOM_LANE;
	}
	
	public void resetBall(float x, float y) {
		position.x = x;
		position.y = y;
		velocity.x = 0;
		velocity.y = 0;
		boundingCircle.set(position, radius);
		inTheAir = false;
	}
	
	public boolean isInTheAir() {
		return inTheAir; 
	}
	
	public void stickToTheBoard(float x) {
		position.x = x; 
	}
	
	public void update() {
				
			if (position.x - radius < LEFT_LANE || position.x + radius > RIGHT_LANE) //if ball hits righ, left, top or bot - it's just reflects from it
				velocity.x = - velocity.x;
			if (position.y - radius < TOP_LANE)
				velocity.y = -velocity.y;
				
			position.add(velocity.cpy());
			boundingCircle.set(position, radius); //bounding circle should always be with our ball
		
	}
	
	public Circle getBoundingCircle() {
		return boundingCircle;
	}

}
