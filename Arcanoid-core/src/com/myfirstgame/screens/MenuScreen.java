package com.myfirstgame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.myfirstgame.AGmeArc;
import com.myfirstgame.helpermethods.AssetLoader;
import com.myfirstgame.helpermethods.MenuHandler;
import com.myfirstgame.menu.MenuRender;
import com.myfirstgame.menu.MenuWorld;

public class MenuScreen implements Screen {

	private float width;
	private float height;
	private MenuWorld menu;
	private MenuRender menuRender;
	private Game game;
	
	public MenuScreen(Game game) {
		
		width = 160; // just because it's easy to calculate all dimensions :) 
		height = width * Gdx.graphics.getHeight()/Gdx.graphics.getWidth(); // calculate height dynamically, based on width and height of the screen
		
		//One will contain objects of menu (like buttons, fonts, etc), other will render it
		
		menu = new MenuWorld(width, height, game);
		menuRender = new MenuRender(menu, width, height);
		
		this.game = game;
		
		//Wee need to check if some buttons are pressed etc
		
		Gdx.input.setInputProcessor(new MenuHandler(menu));
		
	}
	
	@Override
	public void show() {
		
		// TODO Auto-generated method stub
	}

		

	@Override
	public void render(float delta) {
		
		//this method will be called about 60 times in a sec (1/delta)
		
		menuRender.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		AssetLoader.mainTheme.stop();// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
