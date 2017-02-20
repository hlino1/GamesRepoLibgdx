package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


public class Attractor {
	
	float mass;
	Vector2 position;
	ShapeRenderer sr;

	public Attractor(ShapeRenderer sr){
		mass = MathUtils.random(75, 250);
		position = new Vector2(MathUtils.random(250, Gdx.graphics.getWidth() - 250),
				MathUtils.random(250, Gdx.graphics.getHeight() - 250));
		this.sr = sr;
	}
	
	public Vector2 attract(WalkerForces walker){
		Vector2 force = Helper.subVect(position, walker.position);
		float distance = force.len();
		distance = MathUtils.clamp(distance, 5.0f, 20.0f);
		force.nor();
		float strenght = (Helper.G * mass *  walker.mass) / (distance * distance);	
		force.scl(strenght);
		
		return force;
		
		
	}
	
	public void draw(){
		sr.setAutoShapeType(true);
		sr.set(ShapeType.Line);
		sr.setColor(Color.DARK_GRAY);
		sr.circle(position.x, position.y, mass);
	}
	
	
}
