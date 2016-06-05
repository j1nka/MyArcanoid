package com.myfirstgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.myfirstgame.helpermethods.AssetLoader;
import com.myfirstgame.screens.GameScreen;
import com.myfirstgame.screens.MenuScreen;

public class AGmeArc extends Game {

	// One screen for menu, other for game
	
	public GameScreen game;
	public MenuScreen menu;
	
	@Override
	public void create() {
		
		//We need to load all assets before our Screens initialization
		
		AssetLoader.load();
		menu = new MenuScreen(this);
		setScreen(menu);
		
	}
	
	@Override
	public void dispose() {
		
		super.dispose();
		AssetLoader.dispose();
		
	}

}
