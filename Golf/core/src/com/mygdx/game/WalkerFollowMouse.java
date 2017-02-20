package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class WalkerFollowMouse {

	private ShapeRenderer renderer;
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	private int radius;
	private float topSpeed;
	Vector2 mouse;
	Vector2 dir;
	private float red;
	private float green;
	private float blue;
	
	public WalkerFollowMouse(ShapeRenderer sr){
		position = new Vector2(MathUtils.random(0, Gdx.graphics.getWidth()), MathUtils.random(0, Gdx.graphics.getHeight()));
		velocity = new Vector2(0,0);
		acceleration = new Vector2(0,0);
		radius = MathUtils.random(5, 50);
		topSpeed = MathUtils.random(10,50);
		red = MathUtils.random(1.0f);
		green = MathUtils.random(1.0f);
		blue = MathUtils.random(1.0f);
		mouse = new Vector2();
		dir	= new Vector2();
		renderer = sr;
	}
	
	public void draw(){
		renderer.setAutoShapeType(true);
		renderer.set(ShapeType.Filled);
		renderer.setColor(red, green, blue, 1);
//		renderer.line(mouse, position);		
		renderer.circle(position.x, position.y, radius);
	}
	
	public void step(){
		mouse = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
		dir = subVect(mouse, position);
		dir.nor();
		dir.scl(0.5f);
		acceleration = dir;
		velocity.add(acceleration);
		velocity.limit(topSpeed);
		position.add(velocity);
	}
	
	private static Vector2 subVect(Vector2 v1, Vector2 v2){
		return new Vector2(v1.x - v2.x, v1.y - v2.y);
	}
	
	public void checkEdges(){
		if(position.x < 0)
			position.x = Gdx.graphics.getWidth();
		else if(position.x > Gdx.graphics.getWidth())
			position.x = 0;
		if(position.y < 0)
			position.y = Gdx.graphics.getHeight();
		else if(position.y > Gdx.graphics.getHeight())
			position.y = 0;
	}
}











