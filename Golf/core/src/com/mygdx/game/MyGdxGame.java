package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class MyGdxGame extends ApplicationAdapter {
	
	ShapeRenderer batch;
	WalkerForces[] walkers;
	Attractor[] ats;
	DragZone dz;
	int walkerCount = 3;
	int atCount = 3;
	
	@Override
	public void create () {
		batch = new ShapeRenderer();
		walkers = new WalkerForces[walkerCount];
		ats = new Attractor[atCount];
		dz = new DragZone(batch, Color.BLUE, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/2, 1);
		for(int i = 0; i < atCount; i++){
			ats[i] = new Attractor(batch);
		}

		
		
		for(int i = 0; i < walkerCount; i++){
			walkers[i] = new WalkerForces(batch);
		}
	}

	@Override
	public void render () {
		InputHandler.input();
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin(ShapeType.Line);
		Gdx.graphics.setTitle("" + Gdx.graphics.getFramesPerSecond());
//		for(int i = 0; i < atCount; i++)
//			ats[i].draw();
		dz.draw();
		for(int i = 0; i < walkerCount; i ++){
//			for(int j = 0; j < atCount; j++){
//					Vector2 force = ats[j].attract(walkers[i]);
//					walkers[i].addForce(force);
//			}
//			if(walkers[i].isInside(dz)){
//				walkers[i].drag(dz);
//			}
			walkers[i].addForce(calculateGravity(walkers[i]));
			walkers[i].draw();
			walkers[i].step();
			walkers[i].checkEdges();
			
			
		}

		batch.end();
	}
	
	private Vector2 calculateGravity(WalkerForces walker){
		return new Vector2(0, -0.1f * walker.mass);
	}
	
	private Vector2 calculateFriction(WalkerForces walker){

		Vector2 friction = walker.velocity.cpy();
		float c = 0.01f;
		float normal = 1.0f;
		float frictionMag = c*normal;
		
		friction.scl(-1);
		friction.nor();
		friction.scl(frictionMag);	
		return friction;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
