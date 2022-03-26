package animalDash;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

import animalDash.AnilmalDashGame;
import animalDash.Renderer;

public class AnilmalDashGame implements ActionListener, MouseListener, KeyListener {
  
	//change made

		public static AnilmalDashGame Game;
		public static final int WIDTH =800, HEIGHT = 800;
		public static int ticks, yMotion, score;
		public static boolean gameOver, started = true;
		public static Rectangle block;
		public static Rectangle apple;
		public static final int SideLength = 75;
		public static int currentx = 200;
		public static ArrayList<Rectangle> features;
		public static ArrayList<Rectangle> apples;
		public static Rectangle animal;
		public static int BlockCount = 1;
		public static Renderer renderer;
		public static Random rand;

		public AnilmalDashGame() {
			JFrame jframe = new JFrame();
			 
			renderer = new Renderer();
			rand = new Random();
			Timer timer = new Timer(20, this);
			
			jframe.add(renderer);
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jframe.setSize(WIDTH, HEIGHT);
		    jframe.addMouseListener(this);
			jframe.addKeyListener(this);
			jframe.setResizable(true);
			jframe.setTitle("Animal Dash");
			jframe.setVisible(true);
			
			animal = new Rectangle(2, 648, 30,30);
			features= new ArrayList<Rectangle>();
			apples= new ArrayList<Rectangle>();
			timer.start();
		}
		public void jump() {
			
			animal.y = animal.y - 10;
			if (gameOver) {
		//	renderer.repaint();
		
				animal = new Rectangle(2, 648, 30,30);
				score = 0;
				features.clear();
				apples.clear();
				//renderer.repaint();
				gameOver = false;
				
			}
		if (!started) {
			started = true;
		}
		else if (!gameOver) { 
			if (yMotion > 0) {
				yMotion = 0;
			}
			yMotion -= 10;
		}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
//			
			int speed = 10;
			ticks++;
			run();
			
			
	if (started)
	{
		for (int i = 0; i < features.size(); i++) {
			Rectangle f = features.get(i);
			f.x -= speed;
		}
		for (int i = 0; i < apples.size(); i++) {
			Rectangle f = apples.get(i);
			f.x -= speed;
		}
        if (ticks % 2 == 0 && yMotion < 15) {
			yMotion += 2;
		}
        for (int i = 0; i < features.size(); i++) {
			Rectangle f = features.get(i);
			if (f.x + f.width < 0) {
				features.remove(f);

			}
		}
        for (int i = 0; i < apples.size(); i++) {
			Rectangle a = apples.get(i);
			if (a.x + a.width < 0) {
				apples.remove(a);
			}
		}
		animal.y = animal.y + yMotion ;
 
//	        
		boolean update = true;
	        for (Rectangle feature : features) {

	        	
	        	if (feature.intersects(animal)) {
        		    gameOver = true;
	        	//	score = score -1;
                    update = false;
	        	//	animal.x = feature.x - animal.width;
	        	}
	        }
	        update = true;
	        for (Rectangle app: apples) {
	        	if (app.intersects(animal) && update) {
	        		score++;
	        		update = false;
	        }
//	}
	        if (animal.y > HEIGHT || animal.y < 0) {
        	gameOver = true;
	        }
	
	
		
	        
	        if (animal.y + yMotion >= HEIGHT - 120) {
	        	animal.y = HEIGHT - 120 - animal.height;	        
	       // renderer.repaint();
}
	        }
	}

}

//		//
		//paint the blocks that make up the different block columns
		
				public static void paintPond(Graphics g) {
					int y = 625;
				//	for (int i = 0; i < BlockCount; i++) {
						g.setColor(Color.cyan.brighter());
						block = new Rectangle(currentx,y,SideLength,SideLength);
						features.add(block);
						g.fillRect(currentx,y,SideLength,SideLength);
						//y = y - 45;	
				//	}
						BlockCount = BlockCount + 1;
						currentx = currentx + 200;
					
				}
				public static void paintFence(Graphics g) {
					int y = 555;
					for (int i = 0; i < BlockCount; i++) {
						g.setColor(Color.black.brighter());
						block = new Rectangle(currentx,y,SideLength,SideLength);
						features.add(block);
						g.fillRect(currentx,y,SideLength+50,SideLength+50);
						//y = y - 45;	
					}
					BlockCount = BlockCount + 1;
					currentx = currentx + 200;
				}
				public static void paintApple(Graphics g) {
					int y = 315;
				//	for (int i = 0; i < BlockCount; i++) {
						g.setColor(Color.red.brighter());
						block = new Rectangle(currentx,y,SideLength,SideLength);
						features.add(block);
						g.fillRect(currentx,y,SideLength-30,SideLength-30);
						//y = y - 45;	
					//}
					BlockCount = BlockCount + 1;
					currentx = currentx + SideLength + 200;
				}
		public static void run() {
			
			addFeature(true);
			addApple(true);
			animal.x = animal.x + 1;
		//	addFeature(true);
			//animal.y = animal.y + 427;
			renderer.repaint();
			if (animal.y + yMotion <= HEIGHT - 120) {
				//addFeature(true);
	        //	animal.y = HEIGHT - 120 - animal.height;	
				//animal.y = animal.y + 1;
	           renderer.repaint();
			}
//			if (!animal.intersects(block)) {
//			   renderer.repaint();
//	}
		}

		

		public static void repaint(Graphics g) {
			//Fills in color for the background
			//
			g.setColor(Color.blue);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
		//	paintApple(g);
		//	paintPond(g);
		//	paintPond(g);
		//	paintFence(g);
//			paintApple(g);
			
			
			//Fills in color for the bird
			g.setColor(Color.yellow);
			g.fillRect(animal.x, animal.y, animal.width, animal.height);
			

			for (Rectangle column : features) {
				paintColumn(g, column);
			}
			for (Rectangle column : apples) {
				paintApples(g, column);
			}
			g.setColor(Color.orange);
			g.fillRect(0, HEIGHT-120, WIDTH, 120);
//			/*//Fills in color for the ground
//			g.setColor(Color.orange);
//			g.fillRect(0, HEIGHT-120, WIDTH, 120);
//			*/
			
			//Fills in color for grass
			g.setColor(Color.green.darker().darker());
			g.fillRect(0, HEIGHT-120, WIDTH, 20);
			//System.out.println("hello");
			
			
		if (!gameOver) {
			g.setColor(Color.white);
		    g.setFont(new Font("Arial",1,100));
	    	g.drawString(String.valueOf(score), WIDTH / 2 -25,100 );
	    	
	    }
	    	if (gameOver) {
	    		g.setColor(Color.white);
			    g.setFont(new Font("Arial",1,100));
		    	g.drawString("Game Over", WIDTH / 50 + 70 ,100 );
	    	}
}
		
		public static void addFeature(boolean start) {
			int space = 300;
			int width = 100;
			int x = rand.nextInt(3);
			int height;
			if (x ==1 ) {
				height = 515;
			}
			else if (x == 2) { 
				height = 625;
			}
			else {
				height = 700;
			}
			
			if (start)
			{
				features.add(new Rectangle(currentx,height,SideLength,SideLength));
			
			}
			else {
				
			}
			BlockCount = BlockCount + 1;
			currentx = currentx + SideLength + 200;
			
		}
		public static void paintColumn(Graphics g, Rectangle column) {
			g.setColor(Color.green.darker());
			g.fillRect(column.x, column.y, column.width, column.height);
		}
		public static void addApple(boolean start) {
			int space = 300;
			int width = 100;
			int x = rand.nextInt(3);
			int height;
			if (x ==1 ) {
				height = 215;
			}
			else if (x == 2) { 
				height = 325;
			}
			else {
				height = 100;
			}
			
			if (start)
			{
				apples.add(new Rectangle(currentx,height,SideLength,SideLength));
			
			}
			else {
				
			}
			BlockCount = BlockCount + 1;
			currentx = currentx + SideLength + 200;
			
		}
		public static void paintApples(Graphics g, Rectangle column) {
			g.setColor(Color.red.darker());
			g.fillRect(column.x, column.y, column.width-30, column.height-30);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			jump();
		}
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				jump();
			}
		}
			public static void main(String[] args) {
				Game = new AnilmalDashGame();
				
							}
			

		

}
