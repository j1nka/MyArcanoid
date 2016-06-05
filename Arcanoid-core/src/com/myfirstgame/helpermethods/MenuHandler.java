package com.myfirstgame.helpermethods;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.myfirstgame.gameobjects.Buttons;
import com.myfirstgame.menu.MenuWorld;
import com.myfirstgame.screens.GameScreen;

public class MenuHandler implements InputProcessor {

	private Buttons play, quit;
	private MenuWorld menu;
	private Game game;
	
	public MenuHandler (MenuWorld menu) {
		this.menu = menu;
		play = menu.getPlay();
		quit = menu.getQuit();
		game = menu.getGame();
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
	
		//this will help us to scale units to pixels
		
		float byX = Gdx.graphics.getWidth()/menu.getWidth();
		float byY = Gdx.graphics.getHeight()/menu.getHeight();
		
		//play button coordinates
		
		int play_x = (int) play.getX();
		int play_y = (int) play.getY();
		int play_wid = (int) play.getWidth();
		int play_hei = (int) play.getHeight();
		
		//quit button coordinates
		
		int quit_x = (int) quit.getX();
		int quit_y = (int) quit.getY();
		int quit_wid = (int) quit.getWidth();
		int quit_hei = (int) quit.getHeight();
		
		//Tocuh the button and you will see a result :)
		
		if (screenX > (byX*play_x) && screenX < byX*(play_x + play_wid) 
				&& screenY > byY*(play_y) && screenY < byY*(play_y + play_wid))
			play.press(true);
		if (screenX > (byX*quit_x) && screenX < byX*(quit_x + quit_wid) 
				&& screenY > byY*(quit_y) && screenY < byY*(quit_y + quit_wid))
			quit.press(true);
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		// we took our finger off the screen - change the Screen or leave
		
		if (quit.isButtonPressed())
		{
			game.getScreen().dispose(); //we need to call this method manually 
			Gdx.app.exit();
		}
		
		if (play.isButtonPressed())
		{
			game.getScreen().dispose();
			game.setScreen(new GameScreen(game)); // swap screen to the Game
			play.press(false);
		}
			
		return false;
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
