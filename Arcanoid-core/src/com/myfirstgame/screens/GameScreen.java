package com.myfirstgame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector;
import com.myfirstgame.helpermethods.AssetLoader;
import com.myfirstgame.helpermethods.InputHandler;
import com.myfirstgame.world.GameRenderer;
import com.myfirstgame.world.GameWorld;

public class GameScreen implements Screen {

	private GameRenderer myRender;
	private GameWorld myWorld;
	private int width;
	private int height;
	private Game game;
	
	public GameScreen(Game game) {
		
		
		width = 160; // just because it's easy to calculate all dimensions :) 
		height = width * Gdx.graphics.getHeight()/Gdx.graphics.getWidth(); // because of that we won't have dimension problems on different screens

		//And again - one for the objects, second for rendering them
		
		myWorld = new GameWorld(width, height, game); 
		myRender = new GameRenderer(myWorld, width, height);
		
		//This will help us to swap the screens
		
		this.game = game;
		
		//This will help us to handle screen touch

		Gdx.input.setInputProcessor(new InputHandler(myWorld)); 
	
	}
	
	@Override
	public void show() {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		
		//Update world (based on logic) and render it
		
		myRender.render();
		myWorld.update(delta);
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
