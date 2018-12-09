package core;

import org.lwjgl.opengl.GL11;

public class Paddle
{
	private int x, y, speed = 2;
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
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int value)
	{
		x = value;
	}
	
	public void setY(int value)
	{
		y = value;
	}

	public void aIupdate()
	{
	}

	public void moveUp()
	{
		
		if(y + height <= Main.HEIGHT)
			y += speed;
	}
	
	public void moveDown()
	{
		if(y >= 0)
			y -= speed;
	}
	
	public boolean checkBounds()
	{
		if(y >= 0 && (y + height) <= Main.HEIGHT)
			return true;
		return false;
	}
	
	public void setSpeed()
	{
		speed = -speed;
	}
	
}
