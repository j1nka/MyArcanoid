package com.myfirstgame.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.myfirstgame.gameobjects.Ball;
import com.myfirstgame.gameobjects.Board;
import com.myfirstgame.gameobjects.Brick;
import com.myfirstgame.gameobjects.GameLife;
import com.myfirstgame.helpermethods.AssetLoader;

public class GameWorld {
	

	//Bunch of constants that will help us to build our game objects. PS Maybe it will be better t create some "Constant" class with all constant in there
	
	public static final float OFFSET_X = 5f;
	public static final float OFFSET_Y = 15f;
	public static final float GAP_X = 5f; 
	public static final float GAP_Y = 2.5f; 
	public static final float BRICK_WIDTH = (160 - 6*OFFSET_X)/5; 
	public static final float BRICK_HEIGHT = 5f;
	public static int BRICK_COLOR = 1;
	
	public enum GameState {READY, RUNNING, GAMEOVER, COMPLETED, PAUSE}; 
	
	
	private Board board;
	private Ball ball;
	private List<Brick> bricks;
	private List<GameLife> gameLifes;
	private int life;
	private int gameScore;
	private int numberOfBricks;
	private int width;
	private int height;
	private GameState gameState;
	private Random rand;
	private Game game;
	
	
	
	public GameWorld(int width, int height, Game game) {
		
		this.width = width;
		this.height = height;
		board = new Board(width/2 - BRICK_WIDTH/2, height - BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT, width/80); //New board is created
		//dimensions are based on "paper" work :)
		ball = new Ball(width/2, board.getY() - BRICK_HEIGHT/2, BRICK_HEIGHT/2); //New ball is created. Let's put it in the middle of board
		bricks = new ArrayList<Brick>(); // Pack of bricks
		gameLifes = new ArrayList<GameLife>(); 
		life = 5;
		gameScore = 0;
		numberOfBricks = 0;
		gameState = GameState.READY; //Now we ready to play!
		rand = new Random();
		generateBricks(bricks); // Method that will create some bricks...
		generateLifes(gameLifes, life); //...and some game life
		this.game = game;
	}
	
	//Some of GameState changing methods
	
	public void runGame() {
		gameState = GameState.RUNNING;
	}
	
	public void pauseGame() {
		gameState = GameState.PAUSE;
	}
	
	public boolean isGameRunning() {
		return gameState == GameState.RUNNING;
	}
	
	public boolean isGameReady() {
		return gameState == GameState.READY;
	}
	
	public boolean isGameOver() {
		return gameState == GameState.GAMEOVER;
	}
	
	public boolean isGameCompleted() {
		return gameState == GameState.COMPLETED;
	}
	
	public boolean isGamePaused() {
		return gameState == GameState.PAUSE;
	}
	
	//Restart the game - all data should be reset to it's initial state
	
	public void restart() {
		
		bricks.clear();
		gameLifes.clear();
		life = 5;
		gameScore = 0;
		numberOfBricks = 0;
		generateBricks(bricks);
		generateLifes(gameLifes, life);
		gameState = GameState.READY;
		
		board.restart(width/2 - (BRICK_WIDTH)/2);
		ball.resetBall(width/2, board.getY() - BRICK_HEIGHT/2);
		
	}
	
	//GameWorld update should be different for different game states. This method will help us to choose
	
	public void update(float delta) {
		
		switch(gameState) {
			case READY:
				updateReady();
				break;
			case RUNNING:
				updateRunning(delta);
				break;
			case PAUSE:
				updatePause();
			default:
					break;	
		}
	}
	
	public void updatePause() {
		//We need some rest now ) Don't do anything
	}
	
	//Ball should be always in the middle of the board before we set him to the air :)
	
	public void updateReady() {
		
		if(!ball.isInTheAir())
			ball.stickToTheBoard(board.getX() + board.getWidth()/2);
	}
	
	public void updateRunning(float delta) {
		
		//Again - stick ball to the board even while game is running. Eg - we lost one life and ball was reseted
		
		if(!ball.isInTheAir())
			ball.stickToTheBoard(board.getX() + board.getWidth()/2);
		
		//Ball hits the board. hitThe method will help to change the ball position based on some circumstances (just look at this method in ball class)
		
		if (ball.collidesBoard(board)) {
			ball.hitThe(board);
		}
		
		//Ball is lower than bottom line (y = width)
		
		if (ball.isOutOfScope())
		{
			AssetLoader.isOut.play(); // Play the "u lost one life" sound )
			ball.resetBall(board.getX() + board.getWidth()/2, board.getY() - ball.getRadius()); //Reset the ball position - bring him back
			// to the middle of the board
			life -= 1;
			gameLifes.get(gameLifes.size() - life - 1).wasteLife(); //One life was wasted. From the end of the array - because we want our "hearts" to
			//disappear from left to right. Dunno why. Just luv it :)
			if (life == 0)
			{
				AssetLoader.isOver.play();
				gameState = GameState.GAMEOVER;
			}
				
		}
			
		for (Brick brick : bricks) {
			
			if (!brick.isDeleted() && ball.collidesBrick(brick)) //Ball can hit only the bricks that weren't deleted
			{
				ball.hitThe(brick);
				brick.delete();
				numberOfBricks -= 1;
				gameScore += 1;
				if (numberOfBricks == 0)
				{
					AssetLoader.isWin.play();
					gameState = GameState.COMPLETED;
				}
					
			}
		}
		
		ball.update(); //update ball and board position
		board.update();
	}
	
	//brick generator method
	
	private void generateBricks(List<Brick> bricks) {
		
		int colorSet = AssetLoader.brickCol.length; //We got some textures for bricks - let's randomly choose one every time
		BRICK_COLOR = rand.nextInt(colorSet);
		
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				float x = (float)(OFFSET_X + BRICK_WIDTH*j + j*GAP_X); //All dimensions and offsets are based on "paper" work
				float y = (float)(OFFSET_Y + BRICK_HEIGHT*i + i*GAP_Y);
				
				bricks.add(new Brick(x,y, BRICK_WIDTH, BRICK_HEIGHT));
				numberOfBricks += 1;
			}
		}
		
		
	}
	
	//life generator method
	
	private void generateLifes(List<GameLife> gameLifes, int life) {
		
		for (int i = 0; i < life; i++)
		{
			float x = width - OFFSET_X - 5 - i*6; //All dimensions and offsets are based on "paper" work
			float y = OFFSET_Y/2 - 2.5f;
			gameLifes.add(new GameLife(x, y, 5, 5));
		}
			
	}
	
	//Some getters which will help our world's objects to communicate with other classes
	
	public Board getBoard() {
		return board;
	}
	
	public Ball getBall() {
		return ball;
	}
	
	public List<Brick> getBricks() {
		return bricks;
	}
	
	public int getScore() {
		return gameScore;
	}
	
	public List<GameLife> getLifes() {
		return gameLifes;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Game getCurrentGame() {
		return game;
	}
	
}
