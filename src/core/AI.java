package core;

import java.util.Random;

public class AI
{
	private Paddle computerPaddle;
	private Ball ball;
	private Random random;
	public static int yOffset;
	
	public AI(Paddle paddle, Ball ball)
	{
		computerPaddle = paddle;
		this.ball = ball;
		random = new Random();
	}
	
	public void update()
	{
		int y = ball.getY();
		computerPaddle.setY(y - yOffset);
	}
	
	public void generateOffset()
	{
		yOffset =  random.nextInt(computerPaddle.height - ball.radius);
	}
}
