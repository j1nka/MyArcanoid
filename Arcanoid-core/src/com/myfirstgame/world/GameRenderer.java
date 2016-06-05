package com.myfirstgame.world;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.myfirstgame.gameobjects.Ball;
import com.myfirstgame.gameobjects.Board;
import com.myfirstgame.gameobjects.Brick;
import com.myfirstgame.gameobjects.GameLife;
import com.myfirstgame.helpermethods.AssetLoader;
import com.myfirstgame.helpermethods.InputHandler;

public class GameRenderer {
	
	private int width, height;
	private OrthographicCamera myCamera;
	private GameWorld gameWorld;
	private List<Brick> gameBricks;
	private Ball gameBall;
	private Board gameBoard;
	private List<GameLife> gameLifes;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batch;
	private GlyphLayout glyph;
	private TextureRegion bg, boardCol, ballCol, heartCol, pauseCol, playCol, arrL, arrR, backToMenu;
	private TextureRegion[] brickCol;
	
	public GameRenderer(GameWorld world, int width, int height) {
		
		gameWorld = world;
		
		//helper method to initialize some game objects and textures
		
		initGraphics();
		initGameObjects();
		
		this.width = width;
		this.height = height;

		glyph = new GlyphLayout(); //This will help us to move the text correctly 
		
		myCamera = new OrthographicCamera(); //2d view camera
		myCamera.setToOrtho(true, width, height); //set camera to our screen, width/height - units, not pixels
		
		batch = new SpriteBatch(); //This will draw our objects
		batch.setProjectionMatrix(myCamera.combined);
		
	}
	
	public void render() {
		
		//Delete all previous drawings
		
		Gdx.gl.glClearColor(0, 0, 0, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.disableBlending();
		batch.draw(bg, 0, 0, width, height); //Draw background
		batch.enableBlending();
		
		batch.draw(boardCol, gameBoard.getX(), gameBoard.getY(), gameBoard.getWidth(), gameBoard.getHeight()); //Draw board
		batch.draw(ballCol, gameBall.getX() - gameBall.getRadius(), gameBall.getY() - gameBall.getRadius(), //Draw ball
				gameBall.getRadius()*2, gameBall.getRadius()*2);
		
		batch.draw(arrL, 0, height - 10, 10, 10); //Draw "MoveLeft" arrow
		batch.draw(arrR, width - 10, height - 10, 10, 10); //Draw "MoveRight" arrow
		
		batch.draw(backToMenu, width/2 - 30f, GameWorld.OFFSET_Y/2 - 5f, 10, 10); //Draw "Menu" button
		
		AssetLoader.gameFont.draw(batch, "Score:" + gameWorld.getScore(), GameWorld.OFFSET_X, GameWorld.OFFSET_Y/2 - 2.5f); //Draw "High Score + score" 
		
		//Render our bricks. If brick is deleted - do not render it
		
		for (Brick brick : gameBricks)
		{
			float x = brick.getX();
			float y = brick.getY();
			if (!brick.isDeleted())
				batch.draw(brickCol[GameWorld.BRICK_COLOR], x, y, GameWorld.BRICK_WIDTH, GameWorld.BRICK_HEIGHT);
		}
		
		//Render game life. If life is wasted - do not render it
		
		for (GameLife life : gameLifes)
		{
			if (!life.isWasted())
				batch.draw(heartCol, life.getX(), life.getY(), life.getWidth(), life.getHeight());
		}
		
		//All is ready - press the button and play!
		
		if (gameWorld.isGameReady())
		{
			glyph.setText(AssetLoader.gameFont, "Touch the screen to start!"); //Now we got access to ".width" method!
			AssetLoader.gameFont.draw(batch, glyph, width/2 - glyph.width/2, height/2 + GameWorld.OFFSET_Y); //Draw the "Touch the screen!"
		}
			
		//Game time
		
		if (!gameWorld.isGamePaused())
		{
			batch.draw(pauseCol, width/2 - 5f, GameWorld.OFFSET_Y/2 - 5f, 10, 10); // Draw the pause icon. we can do this before btw
			
			if (gameWorld.isGameRunning() || gameWorld.isGameReady()) // we can move our board only if the game is ready, or the game is running
			{
				
				if (!gameBoard.atLeftSide() && InputHandler.leftPressed)  // move left and stop if we reached 0 by x
					gameBoard.moveBoard(-gameBoard.getBoardSpeed());
				if (!gameBoard.atRightSide() && InputHandler.rightPressed) //move right and stop if we reached width by x
					gameBoard.moveBoard(gameBoard.getBoardSpeed());
				
			
			}
		}
		
		else
		{
			batch.draw(playCol, width/2 - 5f, GameWorld.OFFSET_Y/2 - 5f, 10, 10); //Change Pause icon to Play icon
			glyph.setText(AssetLoader.gameFont, "Game Paused"); //Text time - just like we did with the "Press the button"
			AssetLoader.gameFont.draw(batch, glyph, width/2 - glyph.width/2, height/2 + GameWorld.OFFSET_Y);
		}
		
		if (gameWorld.isGameOver())
		{
			glyph.setText(AssetLoader.gameFont, "You lose!"); //And again
			AssetLoader.gameFont.draw(batch, glyph, width/2 - glyph.width/2, height/2 + GameWorld.OFFSET_Y);
		}
			
		if (gameWorld.isGameCompleted())
		{
			glyph.setText(AssetLoader.gameFont, "You win!"); //And again :)
			AssetLoader.gameFont.draw(batch, glyph, width/2 - glyph.width/2, height/2 + GameWorld.OFFSET_Y);
		}
			
		batch.end();
			
	}
	
	//Helper methods to initialize the graphics
	
	public void initGraphics() {
		
		bg = AssetLoader.bg;
		boardCol = AssetLoader.boardCol;
		ballCol = AssetLoader.ballCol;
		brickCol = AssetLoader.brickCol;
		heartCol = AssetLoader.heartCol;
		pauseCol = AssetLoader.pauseCol;
		playCol = AssetLoader.playCol;
		arrR = AssetLoader.arrowRight;
		arrL = AssetLoader.arrowLeft;
		backToMenu = AssetLoader.backToMenu;
		
	}
	
	//Helper methods to initialize the game objects
	
	public void initGameObjects() {
		
		gameBall = gameWorld.getBall();
		gameBricks = gameWorld.getBricks();
		gameBoard = gameWorld.getBoard();
		gameLifes = gameWorld.getLifes();
		
	}

}
