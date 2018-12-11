package core;

import java.util.Random;

import org.lwjgl.opengl.GL11;

public class Ball
{
	private float x, y;
	private float xSpeed = 1, ySpeed = 1, ballAngle;
	private float speed = 4;
	public static final int radius = 15; 
	private Random r;
	
	public Ball(int x, int y)
	{
		this.x = x;
		this.y = y;
		r = new Random();

		resetBall();
	}
	
	public void render()
	{
		GL11.glRectf(x, y, x + radius, y + radius);
	}

	public void update()
	{
		float playerPaddleY = Main.playerPaddle.getY();
		
		float computerPaddleY = Main.computerPaddle.getY();
		
		if(x < Paddle.width && y > playerPaddleY && y < playerPaddleY + Paddle.height)
			reflectBall();
		
		if(x + radius > Main.WIDTH - Paddle.width && y > computerPaddleY && y < playerPaddleY + Paddle.height)
			reflectBall();
		
		// TODO Auto-generated method stub
		if(y + radius > Main.HEIGHT)
		{
			ballAngle = calculateBallAngle();
			ySpeed = calculateYSpeed();
			speed = -speed;
			AI.yOffset = r.nextInt(Paddle.height - radius - 25);
		}
		else if(y < 0)
		{
			ballAngle = calculateBallAngle();
			ySpeed = calculateYSpeed();
			speed = -speed;
			AI.yOffset = r.nextInt(Paddle.height - radius - 25);
		}
		else if(x + radius > Main.WIDTH)
		{
			ballAngle = calculateBallAngle();
			xSpeed = calculateXSpeed();
			speed = -speed;
			AI.yOffset = r.nextInt(Paddle.height - radius - 25);
			//resetBall();
		}
		else if(x < 0)
		{
			ballAngle = calculateBallAngle();
			xSpeed = calculateXSpeed();
			speed = -speed;
			AI.yOffset = r.nextInt(Paddle.height - radius - 25);
			//resetBall();
		}
		
		
		
		x +=  xSpeed;
		y +=  ySpeed;
	}
	
	private void resetBall()
	{
		x = (Main.WIDTH - radius) / 2;
		y = (Main.HEIGHT - radius)/ 2;
		ballAngle =  (float) ((float) r.nextDouble() *  Math.PI);
		xSpeed = (float) (speed * Math.cos(ballAngle));
		ySpeed = (float) (speed * Math.sin(ballAngle));
		System.out.println("XSpeed = " + xSpeed + "  YSpeed = " + ySpeed + " angle = " + ballAngle);
	}
	
	private void reflectBall()
	{
		ballAngle = calculateBallAngle();
		xSpeed = calculateXSpeed();
		speed = -speed;
		AI.yOffset = r.nextInt(Paddle.height - radius - 25);
	}
	
	private float calculateBallAngle()
	{
		float angle = (float) Math.atan2(ySpeed, xSpeed);;
		System.out.println("Updated ball angle from: " + ballAngle + ", to : " + angle);
		return angle;
	}
	
	private float calculateXSpeed()
	{
		 return (float) (Math.cos(ballAngle) * speed);
	}
	
	private float calculateYSpeed()
	{
		return (float) (Math.sin(ballAngle) * speed);
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
}
