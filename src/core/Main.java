package core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Main
{

	public static final int WIDTH = 600, HEIGHT = 350;
	private static Input input;
	private static Window window;
	
	private static final double fpsCap = 1.0/60.0;
	private static double time, unprocessedTime = 0;
	
	public static Paddle playerPaddle;
	public static Paddle computerPaddle;
	private static Ball ball;
	private static AI ai;
	
	public static void main(String[] args)
	{
		window = new Window(WIDTH, HEIGHT, "Pong");
		window.render();
		input = new Input(window);
		GLFW.glfwMakeContextCurrent(window.getWindowID());
		//Get Ready to render
		window.disableResize();
		init();
		
		
		time = getTime();
		double frameTime = 0;
		int frames = 0;

		while(window.isClosed() == false)
		{
			boolean canRender = false;
			double currentTime = getTime();
			double passed = currentTime - time;
			unprocessedTime += passed;
			frameTime += passed;
			time = currentTime;
			
			while(unprocessedTime >= fpsCap)
			{
				unprocessedTime -= fpsCap;
				canRender = true;
				window.update();
				//update code here
				update();
				if(frameTime >= 1.0)
				{
					frameTime = 0;
					System.out.println("FPS: " + frames);
					frames = 0;
				}
			}
			
			if(canRender)
			{
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
				//rendering code here
				render();
				window.swapBuffers();
				frames++;
			}

		}
		
		window.flush();
		GLFW.glfwTerminate();
		System.out.println("Disposing resources...");
		System.exit(0);
	}
	
	private static void render()
	{
		playerPaddle.render();
		computerPaddle.render();
		ball.render();
	}

	private static void update()
	{

		if(input.isKeyPressed(GLFW.GLFW_KEY_S) || input.isKeyPressed(GLFW.GLFW_KEY_DOWN))
		{
			playerPaddle.moveDown();
		}
		else if(input.isKeyPressed(GLFW.GLFW_KEY_W) || input.isKeyPressed(GLFW.GLFW_KEY_UP))
		{
			playerPaddle.moveUp();
		}
		else
		{
			playerPaddle.resetSpeed();
		}
		
		ball.update();
		ai.update();
	}

	private static void init()
	{
		GLFW.glfwMakeContextCurrent(window.getWindowID());
		//Get Ready to render
		GL.createCapabilities();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		System.out.println("OpenGL Version: " + GL11.glGetString(GL11.GL_VERSION));
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
				
		//game objects
		playerPaddle = new Paddle(0 , (HEIGHT - Paddle.height)/2);
		computerPaddle = new Paddle(WIDTH - Paddle.width, (HEIGHT - Paddle.height)/2);
		ball = new Ball((WIDTH - Ball.radius)/2, (HEIGHT - Ball.radius)/2);
		ai = new AI(computerPaddle, ball);
	}
	
	private static double getTime()
	{
		return (double) System.nanoTime() / (double) 1000000000;
	}

}
