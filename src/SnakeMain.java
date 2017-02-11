import java.util.concurrent.TimeUnit;

public class SnakeMain {
	final static int WIDTH = 50;
	final static int HEIGHT = 40;
	final static int CANVAS_W = WIDTH * 10;
	final static int CANVAS_H = HEIGHT * 10;

	public static void main(String[] args) throws InterruptedException {
		
		SnakeGame sg = new SnakeGame(WIDTH, HEIGHT, CANVAS_W, CANVAS_H);			
		SnakeControl myWindow = new SnakeControl("Snake Game");
        
		while(sg.move(myWindow.direction) != -1) {		
			TimeUnit.MILLISECONDS.sleep(300);
		}
		System.out.println("Game over!");
	}
}
