package com.myfirstgame.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.myfirstgame.AGmeArc;
import com.myfirstgame.gameobjects.Buttons;
import com.myfirstgame.helpermethods.AssetLoader;
import com.myfirstgame.screens.GameScreen;

public class MenuWorld {
	
	private float width;
	private float height;
	private Buttons play, quit;
	private Game game;
	private AssetManager assets;
	
	public MenuWorld(float width, float height, Game game) {
		
		this.width = width;
		this.height = height;
		
		//we will have quite simple menu - only with 2 buttons and background
		
		play = new Buttons(18f, height/2 + 15f, 55f, 20f);
		quit = new Buttons(width/2f + 7f, height/2 + 15f, 55f, 20f);
		
		//we need this to swap screens (further)
		
		this.game = game;
		
		//some music while we see MenuScreen :)
		
		AssetLoader.mainTheme.loop();
		
	}
	
	
	//some getters, so our MenuScreen can communicate with other classes
	
	public Game getGame() {
		return game;
	}
		
	public Buttons getPlay() {
		return play;
	}
	
	public Buttons getQuit() {
		return quit;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}

}
