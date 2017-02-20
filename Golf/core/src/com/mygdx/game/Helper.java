package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Helper {

	public static final float G = 0.04f;
	public static final Vector2 subVect(Vector2 v1, Vector2 v2){
		return new Vector2(v1.x - v2.x, v1.y - v2.y);
	}
}
