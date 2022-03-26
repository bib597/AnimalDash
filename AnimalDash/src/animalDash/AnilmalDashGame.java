package animalDash;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import animalDash.AnilmalDashGame;
import animalDash.Renderer;

public class AnilmalDashGame {
  
	
	public class AnilmalDashGame {

		public static AnilmalDashGame rollingBall;
		public static final int WIDTH =800, HEIGHT = 800;
		public static int ticks, yMotion, score;
		public static boolean gameOver, started = true;
		public static Rectangle block;
		public static final int SideLength = 75;
		public static int currentx = 200;
		public static ArrayList<Rectangle> columns;
		public static Rectangle ball;
		public static int BlockCount = 1;
		public static Renderer renderer;
		public Random rand;

		public void AnilmalDashGame() {
			JFrame jframe = new JFrame();
			 
			renderer = new Renderer();
			rand = new Random();
			//Timer timer = new Timer(20, this);
			//hello
			
			jframe.add(renderer);
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jframe.setSize(WIDTH, HEIGHT);
			//jframe.addMouseListener(this);
			//jframe.addKeyListener(this);
			jframe.setResizable(false);
			jframe.setTitle("Roll and Jump");
			jframe.setVisible(true);
			
			ball = new Rectangle(2, 730, 30,30);
			//columns = new ArrayList<Rectangle>();
			//timer.start();
		}
		//paint the blocks that make up the different block columns
		public static void paintColumn(Graphics g) {
			int y = 685;
			for (int i = 0; i < BlockCount; i++) {
				g.setColor(Color.orange.darker());
				block = new Rectangle(currentx,y,SideLength,SideLength);
				g.fillRect(currentx,y,SideLength,SideLength);
				y = y - 75;	
			}
			BlockCount = BlockCount + 1;
			currentx = currentx + SideLength + 200;
		}
		public static void run() {
			
			ball.x = ball.x + 1;
			if (!ball.intersects(block)) {
			renderer.repaint();
		}
		}

		public static void repaint(Graphics g) {
			//Fills in color for the background
			g.setColor(Color.blue);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			paintColumn(g);
			paintColumn(g);
			
			
			
			//Fills in color for the bird
			g.setColor(Color.green);
			g.fillRect(AnilmalDAshGame.x, ball.y, ball.width, ball.height);
			

			/*//Fills in color for the ground
			g.setColor(Color.orange);
			g.fillRect(0, HEIGHT-120, WIDTH, 120);
			*/
			
			/*//Fills in color for grass
			g.setColor(Color.green);
			g.fillRect(0, HEIGHT-120, WIDTH, 20);
			//System.out.println("hello");
			*/
			}
			public static void main(String[] args) {
				rollingBall = new AnilmalDashGame();
				

			}

	

}
