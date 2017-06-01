import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

final public class Main extends JFrame {

    private JPanel panel = null;
    private JButton btnPauseRun;
    private boolean isPaused = false;
	
	final int FRAMES_PER_SECOND = 56;
	final int SCREEN_HEIGHT = 1000;
	final int SCREEN_WIDTH = 1000;

	long current_time = 0;								//MILLISECONDS
	long next_refresh_time = 0;							//MILLISECONDS
	long last_refresh_time = 0;
	long minimum_delta_time = 1000 / FRAMES_PER_SECOND;	//MILLISECONDS
	long actual_delta_time = 0;							//MILLISECONDS
    
    ArrayList<Rectangle> barriers = null;
    ArrayList<Sprite> sprites = null;

    private Image img;
    
    public Main()
    {
        super("Fixed Timestep Game Loop Test");

        getContentPane().setBackground(Color.BLACK);
        Container cp = getContentPane();
        
        panel = new DrawPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().setLayout(null);
        
        btnPauseRun = new JButton("||");
        btnPauseRun.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		btnPauseRun_mouseClicked(arg0);
        	}
        });
        btnPauseRun.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnPauseRun.setBounds(10, 11, 48, 32);
        getContentPane().add(btnPauseRun);
        panel.setLayout(null);
        panel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setSize(SCREEN_WIDTH + 20, SCREEN_HEIGHT + 36);

	    sprites = new ArrayList<Sprite>();

//	    sprite =new Sprite(50, 50, 520, 20, -200, 0, 0, 100);	//TO DO: This causes a collision error after approx 20seconds of movement

	    for (int i = 0; i < 2; i++) {
        	Sprite sprite =new Sprite(50, 50, i *10 , 20, 200, i * 9, 0, 100);
        	sprites.add(sprite);
        }
	    	    
    	barriers = new ArrayList<Rectangle>();
    	barriers.add(new Rectangle(0,0,20,SCREEN_HEIGHT));
    	barriers.add(new Rectangle(SCREEN_WIDTH - 20, 0, 20, SCREEN_HEIGHT));
    	barriers.add(new Rectangle( 0, SCREEN_HEIGHT - 20, SCREEN_WIDTH, 20));
    	barriers.add(new Rectangle(200, 300, 200, 20));
    	barriers.add(new Rectangle(100, 600, 400, 20));
    	barriers.add(new Rectangle(150,200,300,10));
    }

	public static void main(String[] args)
    {
    	Main m = new Main();
    	m.setVisible(true);
    	
        Thread loop = new Thread()
        {
           public void run()
           {
              m.gameLoop();
           }
        };
        loop.start();
    	
    }
	
	class DrawPanel extends JPanel {
		 public void paintComponent(Graphics g)
	     {
	        //BS way of clearing out the old rectangle to save CPU.
	        g.setColor(getBackground());
	        
	        g.setColor(Color.GREEN);
	        for (Sprite sprite : sprites) {
//		        g.fillOval((int)sprite.getCurrentX(), (int)sprite.getCurrentY(), (int)sprite.getWidth(), (int)sprite.getHeight());	        	
//		        g.fillRect((int)sprite.getCurrentX(), (int)sprite.getCurrentY(), (int)sprite.getWidth(), (int)sprite.getHeight());	        	
		        g.drawImage(sprite.getImage(), (int)sprite.getCurrentX(), (int)sprite.getCurrentY(), (int)sprite.getWidth(), (int)sprite.getHeight(), null);
	        }

	        g.setColor(Color.BLUE);
	        for (Rectangle barrier : barriers) {
	            g.fillRect((int)barrier.getX(),(int) barrier.getY(), (int)barrier.getWidth(), (int)barrier.getHeight());       	
	        }
	        
	        g.setColor(Color.BLACK);
	     }
	}
    
    private void gameLoop() {
    	
		while (true) { // main game loop

			//adapted from http://www.java-gaming.org/index.php?topic=24220.0
			last_refresh_time = System.currentTimeMillis();
		    next_refresh_time = current_time + minimum_delta_time;

		    while (current_time < next_refresh_time)
            {
               Thread.yield();

               try {Thread.sleep(1);}
               catch(Exception e) {} 
            
               current_time = System.currentTimeMillis();
            }
		    
		    //UPDATE STATE
		    updateTime();
		    updateSprites();          

		    //REFRESH
		    this.repaint();

		}
    }
    
    private void updateTime() {

        current_time = System.currentTimeMillis();
        actual_delta_time = (isPaused ? 0 : current_time - last_refresh_time);
//	    System.out.println(String.format("%d, %d, %d", last_refresh_time, current_time, actual_delta_time));
	    last_refresh_time = current_time;
    }
    
	public void updateSprites() {
		
		for (Sprite sprite : sprites) {
			sprite.update(barriers, actual_delta_time);
		}

	}
	
	protected void btnPauseRun_mouseClicked(MouseEvent arg0) {
		if (isPaused) {
			isPaused = false;
			this.btnPauseRun.setText("||");
		}
		else {
			isPaused = true;
			this.btnPauseRun.setText(">");
		}
	}
}
