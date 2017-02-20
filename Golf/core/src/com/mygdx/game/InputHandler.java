package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class InputHandler {
	
	public static void input(){
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
			Gdx.app.exit();
		
	}

}
