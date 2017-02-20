package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class WalkerForces {

	private ShapeRenderer renderer;
	
	Vector2 position;
	Vector2 velocity;
	Vector2 acceleration;
	private float radius;
	float mass;
	private float topSpeed;
	private float red;
	private float green;
	private float blue;
	
	public WalkerForces(ShapeRenderer sr){
		position = new Vector2(MathUtils.random(25, Gdx.graphics.getWidth() - 25),
				MathUtils.random(25 , Gdx.graphics.getHeight() - 25));
		velocity = new Vector2(0,0);
		acceleration = new Vector2(0, 0);
		radius = MathUtils.random(5, 25);
		mass = radius;
		red = MathUtils.random(1.0f);
		green = MathUtils.random(1.0f);
		blue = MathUtils.random(1.0f);
		renderer = sr;
	}
	
	public void draw(){
		renderer.setAutoShapeType(true);
		renderer.set(ShapeType.Filled);
		renderer.setColor(red, green, blue, 1);
//		renderer.line(mouse, position);		
//		renderer.point(position.x, position.y, 0);
		renderer.circle(position.x, position.y, radius);
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
	
	public void step(){
		velocity.add(acceleration);
//		velocity.limit(topSpeed);
		position.add(velocity);
		acceleration.scl(0);
	}
	
	
	
	public void addForce(Vector2 force){
		Vector2 temp = force.cpy();
		temp.x = temp.x / mass;
		temp.y = temp.y / mass;
		acceleration.add(temp);
	}
	
	public void checkEdges(){
		if(position.x < 0){
			velocity.x *= -1;
			position.x = 0;
		}
		else if(position.x  > Gdx.graphics.getWidth()){
			position.x = Gdx.graphics.getWidth() - radius;
			velocity.x *= -1;
		}
		if(position.y < 0){
			position.y = 0;
			velocity.y *=-1;
		}
		else if(position.y + radius > Gdx.graphics.getHeight()){
			position.y = Gdx.graphics.getHeight() - radius;
			velocity.y *=-1;

		}
	}
	
	public boolean isInside(DragZone dz){
		return position.x > dz.x && position.x < dz.x + dz.w && 
				position.y > dz.y && position.y <dz.y + dz.h;
	}
	
	public void drag(DragZone dz){
		float speed = velocity.len();
		float dragMagnitude = dz.c * speed * speed;
		
		Vector2 drag = velocity.cpy();
		drag.scl(-1);
		drag.nor();
		drag.scl(dragMagnitude);
		this.addForce(drag);
	}
	
	public void externalForce(){
	}
}











