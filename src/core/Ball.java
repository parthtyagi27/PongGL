package core;

import java.util.Random;

import org.lwjgl.opengl.GL11;

public class Ball
{
	private int x, y;
	private float xSpeed = 4, ySpeed = 4, ballAngle;
	public static final int radius = 15; 
	private Random r;
	
	public Ball(int x, int y)
	{
		this.x = x;
		this.y = y;
		r = new Random();
		xSpeed = r.nextFloat() * r.nextInt(3 - 1) +1;
		ySpeed = r.nextFloat() * r.nextInt(3 - 1) +1;
		//ballAngle = (float) Math.acos((xSpeed/ySpeed));
		ballAngle = (float) Math.atan2(ySpeed, xSpeed);
		System.out.println("XSpeed = " + xSpeed + "  YSpeed = " + ySpeed + " angle = " + ballAngle);
	}
	
	public void render()
	{
		/*
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glScalef(radius, radius, 1);

		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex2f(0, 0);

		for(int i = 0; i <= smoothFactor; i++)
		{ 
		    double angle = Math.PI * 2 * i / smoothFactor;
		    GL11.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
		}
		GL11.glEnd();

		GL11.glPopMatrix();
		*/
		GL11.glRectf(x, y, x + radius, y + radius);
	}

	public void update()
	{
		int paddleX = Main.playerPaddle.getX();
		int paddleY = Main.playerPaddle.getY();
		
		if(x <paddleX + Paddle.width && (y < (paddleY + Paddle.height) && y >paddleY))
		{
			xSpeed = -xSpeed;
			ballAngle = (float) Math.atan2(y, x);
			AI.yOffset = r.nextInt(Main.computerPaddle.height - radius - 25);
		}
		
		// TODO Auto-generated method stub
		if(y + radius >= Main.HEIGHT)
		{
			ySpeed = -ySpeed;
			ballAngle = (float) Math.atan2(y, x);
			AI.yOffset = r.nextInt(Main.computerPaddle.height - radius - 25);
		}
		else if(y <= 0)
		{
			ySpeed = -ySpeed;
			ballAngle = (float) Math.atan2(y, x);
			AI.yOffset = r.nextInt(Main.computerPaddle.height - radius - 25);
		}
		else if(x + radius >= Main.WIDTH)
		{
			xSpeed = -xSpeed;
			ballAngle = (float) Math.atan2(y, x);
			AI.yOffset = r.nextInt(Main.computerPaddle.height - radius - 25);
			resetBall();
		}
		else if(x <= 0)
		{
			xSpeed = - xSpeed;
			ballAngle = (float) Math.atan2(y, x);
			AI.yOffset = r.nextInt(Main.computerPaddle.height - radius - 25);
			resetBall();
		}
		
		
		
		x += Math.cos(ballAngle) * xSpeed + xSpeed;
		y += Math.sin(ballAngle) * ySpeed + ySpeed;
	}
	
	private void resetBall()
	{
		x = (Main.WIDTH - radius) / 2;
		y = (Main.HEIGHT - radius)/ 2;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
}
