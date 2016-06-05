package com.myfirstgame.helpermethods;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture textureForBg, textureForBgMenu;
	public static TextureRegion bg, bgMenu;
	
	public static Texture textureForGame;
	public static TextureRegion boardCol, ballCol;
	public static TextureRegion[] brickCol;
	
	public static Texture textureHeart;
	public static TextureRegion heartCol;
	
	public static Texture texturePause, texturePlay, textureArR, textureArL, textureButtons, textureMenu;
	public static TextureRegion pauseCol, playCol, arrowRight, arrowLeft, play, quit, playPressed, quitPressed, backToMenu;
	
	public static Sound hitBrick, hitBoard, isOut, isOver, isWin, mainTheme;
	
	public static BitmapFont gameFont;
	
	public static void load() {
		
		
		mainTheme = Gdx.audio.newSound(Gdx.files.internal("MainTheme.wav"));
		
		brickCol = new TextureRegion[8];
		
		textureForBg = new Texture(Gdx.files.internal("Space.png"));
		textureForBg.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	
		bg = new TextureRegion(textureForBg, 0, 0, 640, 480);
		bg.flip(false, true);
		
		textureHeart = new Texture(Gdx.files.internal("Heart.png"));
		heartCol = new TextureRegion(textureHeart, 30, 55, 700, 745);
		heartCol.flip(false, true);
		
		texturePause = new Texture(Gdx.files.internal("Pause.png"));
		pauseCol = new TextureRegion(texturePause, 0,0, 943, 943);
		pauseCol.flip(false, true);
		
		texturePlay = new Texture(Gdx.files.internal("Play.png"));
		playCol = new TextureRegion(texturePlay, 0,0, 943, 943);
		playCol.flip(false, true);
		
		textureForGame = new Texture(Gdx.files.internal("Arkanoid.png"));
		
		textureArR = new Texture(Gdx.files.internal("Arrow_Right.png"));
		arrowRight = new TextureRegion(textureArR, 0, 0, 510, 510);
		arrowRight.flip(false, true);
		
		textureArL = new Texture(Gdx.files.internal("Arrow_Left.png"));
		arrowLeft = new TextureRegion(textureArL, 0, 0, 510, 510);
		arrowLeft.flip(false, true);
		
		for (int i = 0; i < brickCol.length; i++)
		{
			brickCol[i] = new TextureRegion(textureForGame, i*66 , 0,  66, 35);
			brickCol[i].flip(false, true);
		}
	
		boardCol = new TextureRegion(textureForGame, 0, 35, 162, 25);
		boardCol.flip(false, true);
		
		ballCol = new TextureRegion(textureForGame, 440, 35, 25, 25);
		ballCol.flip(false, true);
		
		textureForBgMenu = new Texture(Gdx.files.internal("Space_Title.png"));
		bgMenu = new TextureRegion(textureForBgMenu, 0, 0, 960, 640);
		bgMenu.flip(false, true);
		
		textureButtons = new Texture(Gdx.files.internal("Buttons.png"));
		play = new TextureRegion(textureButtons, 0, 0, 415, 170);
		play.flip(false, true);
		quit = new TextureRegion(textureButtons, 0, 178, 415, 170);
		quit.flip(false, true);
		playPressed = new TextureRegion(textureButtons, 422, 0, 415, 170);
		playPressed.flip(false, true);
		quitPressed = new TextureRegion(textureButtons, 422, 178, 415, 170);
		quitPressed.flip(false,  true);
		
		textureMenu = new Texture(Gdx.files.internal("Menu.png"));
		backToMenu = new TextureRegion(textureMenu, 0, 0, 437, 443);
		backToMenu.flip(false, true);
		
		hitBoard = Gdx.audio.newSound(Gdx.files.internal("BoardHit.wav"));
		hitBrick = Gdx.audio.newSound(Gdx.files.internal("HitBrick.wav"));
		isOut = Gdx.audio.newSound(Gdx.files.internal("Out.mp3"));
		isOver = Gdx.audio.newSound(Gdx.files.internal("GameOver.wav"));
		isWin = Gdx.audio.newSound(Gdx.files.internal("Win.wav"));
		
		gameFont = new BitmapFont(Gdx.files.internal("ArcFont.fnt"), true);
		gameFont.getData().setScale(0.15f, 0.15f);
	

	}
	
	public static void dispose() {
		
		textureForBg.dispose();
		textureForGame.dispose();
		hitBoard.dispose();
		hitBrick.dispose();
		isOut.dispose();
		isOver.dispose();
		gameFont.dispose();
		textureHeart.dispose();
		textureArL.dispose();
		textureArR.dispose();
		texturePause.dispose();
		texturePlay.dispose();
		isWin.dispose();
		textureForBgMenu.dispose();
		textureButtons.dispose();
		textureMenu.dispose();
		mainTheme.dispose();
	}
}
