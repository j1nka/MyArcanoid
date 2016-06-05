package com.myfirstgame.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.myfirstgame.gameobjects.Buttons;
import com.myfirstgame.helpermethods.AssetLoader;

public class MenuRender {
	
	private MenuWorld world;
	private float width;
	private float height;
	private SpriteBatch batch;
	private OrthographicCamera myCam;
	private TextureRegion bgMenu, playBut, quitBut, playButPres, quitButPres;
	private Buttons play, quit;
	
	public MenuRender(MenuWorld world, float width, float height) {
		
		this. world = world;
		this. width = width;
		this.height = height;
		
		play = world.getPlay();
		quit = world.getQuit();
		
		// just to make our constructor less "heavy" - all menu assets initialization in one function
		
		initObjects();
		
		// we want to look "from a side", without 3d 
		
		myCam = new OrthographicCamera();
		myCam.setToOrtho(true, width, height); // width and height here are units, not pixels
		
		//Smth hat will draw our buttons and so one
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(myCam.combined);
	}
	
	public void render() {
		
		//Clear the screen
		
		Gdx.gl.glClearColor(0, 0, 0, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.disableBlending();
		batch.draw(bgMenu, 0, 0, width, height); //Draw the background 
		batch.enableBlending();
		
		//Draw buttons with different sprites if button was pressed or not
		
		if (!play.isButtonPressed())
			batch.draw(playBut, play.getX(), play.getY(), play.getWidth(), play.getHeight());
		else
			batch.draw(playButPres, play.getX(), play.getY(), play.getWidth(), play.getHeight());
		
		if (!quit.isButtonPressed())
			batch.draw(quitBut, quit.getX(), quit.getY(), quit.getWidth(), quit.getHeight());
		else
			batch.draw(quitButPres, quit.getX(), quit.getY(), quit.getWidth(), quit.getHeight());
		
		batch.end();
	}
	
	// Initialize assets of our MenuScreen
	
	private void initObjects() {
		
		bgMenu = AssetLoader.bgMenu;
		playBut = AssetLoader.play;
		playButPres = AssetLoader.playPressed;
		quitBut = AssetLoader.quit;
		quitButPres = AssetLoader.quitPressed;
		
	}

}
