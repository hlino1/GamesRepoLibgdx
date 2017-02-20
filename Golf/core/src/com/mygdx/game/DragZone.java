package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class DragZone {

	float x, y, w, h;
	float c;
	ShapeRenderer sr;
	Color color;
	
	public DragZone(ShapeRenderer sr, Color color, float x, float y, float w, float h, float c){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
		this.sr = sr;
		this.color = color;
	}
	
	public void draw(){
		sr.setAutoShapeType(true);
		sr.set(ShapeType.Filled);
		sr.setColor(color);
		sr.rect(x, y, w, h);
	}
	
	
	
}
