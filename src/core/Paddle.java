package core;

import org.lwjgl.opengl.GL11;

public class Paddle
{
	private float x, y;
	private float speed = 1f;
	private float speedCap = 10f;
	public static int width = 20, height = 80;
	
	public Paddle(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void render()
	{
		GL11.glRectf(x, y, x + width, y + height);
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public void setX(float value)
	{
		x = value;
	}
	
	public void setY(float value)
	{
		y = value;
	}

	public void moveUp()
	{
		speed += 0.5f;
		if(speed >= speedCap)
			speed = speedCap;
		if(y + height <= Main.HEIGHT)
			y += speed;
	}
	
	public void moveDown()
	{
		speed += 0.5f;
		if(speed >= speedCap)
			speed = speedCap;
		if(y >= 0)
			y -= speed;
	}
	
	public boolean checkBounds()
	{
		if(y >= 0 && (y + height) <= Main.HEIGHT)
			return true;
		return false;
	}
	
	public void resetSpeed()
	{
		speed = 1f;
	}
	
}
