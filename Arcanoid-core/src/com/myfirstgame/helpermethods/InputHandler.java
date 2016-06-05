package com.myfirstgame.helpermethods;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.myfirstgame.gameobjects.Ball;
import com.myfirstgame.gameobjects.Board;
import com.myfirstgame.screens.GameScreen;
import com.myfirstgame.screens.MenuScreen;
import com.myfirstgame.world.GameWorld;

public class InputHandler implements InputProcessor{
	

	private Ball gameBall;
	private Board gameBoard;
	private GameWorld world;
	private Game game;
	
	public static boolean leftPressed = false;
	public static boolean rightPressed = false;
	
	public InputHandler(GameWorld world) {
		this.world = world;
		this.gameBall = world.getBall();
		this.gameBoard = world.getBoard();	
		game = world.getCurrentGame();
	}


	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		//This will help us translate units to pixels so we won't have problems with different screens
		
		float byX = Gdx.graphics.getWidth()/world.getWidth();
		float byY = Gdx.graphics.getHeight()/world.getHeight();
		
		//Menu button coordinates
		
		float menuX_Left = byX * (world.getWidth()/2 - 30f);
		float menuX_Right = byX * (world.getWidth()/2 - 20f);
		float menuY_Bot = byY * (GameWorld.OFFSET_Y/2 - 5f);
		float menuY_Top = byY * (GameWorld.OFFSET_Y/2 + 5f);
		
		//If pressed - go back to Menu Screen
		
		if (screenX > menuX_Left && screenX < menuX_Right && 
				screenY > menuY_Bot && screenY < menuY_Top)
		{
			game.getScreen().dispose(); // And again we need to call this method manually
			game.setScreen(new MenuScreen(game));
		}
		
		//We are ready, nothing can stop us - press the button and play :)
		
		if (world.isGameReady())
		{
			world.runGame();
		}
		
		//Our ball get's speed only once - so, if it's not in the air - launch it!
		
		if (!gameBall.isInTheAir())
			
		{
			gameBall.onClick(world.getHeight()/90f);
		}
		
		//Pause button - press it and game will freeze. Press again and it will be alive again :)
		
		float coordX_Left = byX * (world.getWidth()/2 - 5f);
		float coordX_Right = byX * (world.getWidth()/2 + 5f);
		float coordY_Bot = byY * (GameWorld.OFFSET_Y/2 - 5f);
		float coordY_Top = byY * (GameWorld.OFFSET_Y/2 + 5f);
		
		if (screenX > coordX_Left && screenX < coordX_Right && 
				screenY > coordY_Bot && screenY < coordY_Top)
		{
			if (!world.isGamePaused() && world.isGameRunning())
				world.pauseGame();
			else
			{
				world.runGame();
			}
		}
		
		//This is our "input" buttons - Right and Left arrows that will move our board
		
		if (world.isGameRunning()) {
			
			if (!world.getBoard().atLeftSide() && screenX < byX * 20
					&& screenY > byY *(world.getHeight() - 20))
				leftPressed = true;
			if (!world.getBoard().atRightSide() && screenX > byX * (world.getWidth() - 20)
					&& screenY > byY *(world.getHeight() - 20))
				rightPressed = true;
			
		}
		
		//U win! Or u lose :( Anyway u need to restart the game
		
		if (world.isGameOver() || world.isGameCompleted())
			world.restart();
	
		return true;

	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		//Stop moving the board - button was released
		
		if (world.isGameRunning()) 
		{
			leftPressed = false;
			rightPressed = false;
		}
			
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
