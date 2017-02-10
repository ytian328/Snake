import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class SnakeGame {
    List<int[]> snake; 
    Map<Integer, Set<Integer>> map;
    int width, height;
    int[] food;
    Random rand;
    int score;

    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int canvasW, int canvasH) {

        snake = new ArrayList<int[]>();
        snake.add(new int[]{width / 2, height / 2});
        map = new HashMap<Integer, Set<Integer>>();
        add(snake.get(0));

        this.height = height;
        this.width = width;

        this.score = 0;
        
        this.rand = new Random();
        food = new int[2];
        genFood();
        
        
        StdDraw.setCanvasSize(canvasW, canvasH);
        StdDraw.setXscale(-5, width + 5);
		StdDraw.setYscale(-5, height + 10);
		
		StdDraw.setPenColor(StdDraw.BLUE);
		for(int i = -1; i <= width; i ++) {
			StdDraw.filledSquare(i, -1, 0.5);
			StdDraw.filledSquare(i, height, 0.5);
		}
		for(int i = -1; i <= height; i ++) {
			StdDraw.filledSquare(-1, i, 0.5);
			StdDraw.filledSquare(width, i, 0.5);
		}
		StdDraw.textLeft(0, height + 2, "Score:");
		StdDraw.textLeft(6, height + 2, "0");
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledSquare(width / 2, height / 2, 0.45);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledSquare(food[0], food[1], 0.43);
    }

    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] oldHead = snake.get(snake.size() - 1);
        int[] oldTail = snake.get(0);

        int[] head = new int[]{oldHead[0], oldHead[1]};

        //looking for possible next head position
        direction = translate(direction);
        if(direction.equals("U")) head[0] -= 1;
        else if(direction.equals("D")) head[0] += 1;
        else if(direction.equals("L")) head[1] -= 1;
        else if (direction.equals("R")) head[1] += 1;
        // if position is not valid, game over 
        if(head[0] < 0 || head[1] < 0 || head[0] > height - 1 || head[1] > width - 1 || onBody(head)) {
        	StdDraw.setPenColor(StdDraw.GREEN);
        	StdDraw.setFont(new Font("sans serif", Font.BOLD, 24) );
        	StdDraw.text(width / 2, height / 2, "Game Over!");
        	return -1;
        }

        if(head[0] != food[0] || head[1] != food[1]) { //no food
        	snake.add(head);
            add(head);
        	snake.remove(0);
            if(oldTail[0] != head[0] || oldTail[1] != head[1]) remove(oldTail);
        }
        else { //eat food
        	snake.add(head);
            add(head);
            score ++;
            
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledRectangle(16, height + 3, 10, 2);;
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.textLeft(6, height + 2, Integer.toString(score));
            //generate new food
            genFood();
            StdDraw.setPenColor(StdDraw.RED);
    		StdDraw.filledSquare(food[0], food[1], 0.43);
        }

        return score;
    }

    private void add(int[] pos) {
        if(!map.containsKey(pos[0])) map.put(pos[0], new HashSet<Integer>());
        map.get(pos[0]).add(pos[1]);
        
        StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledSquare(pos[0], pos[1], 0.45);
    }

    private void remove(int[] pos) {
        if(!map.containsKey(pos[0]) || !map.get(pos[0]).contains(pos[1])) return;
        map.get(pos[0]).remove(pos[1]);
        if(map.get(pos[0]).isEmpty()) map.remove(pos[0]);
        
        StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledSquare(pos[0], pos[1], 0.55);
    }

    private boolean onBody(int[] pos) {
        int[] tail = snake.get(0);
        if(pos[0] == tail[0] && pos[1] == tail[1]) return false;
        if(!map.containsKey(pos[0])) return false;
        if(!map.get(pos[0]).contains(pos[1])) return false;
        return true;
    }
    
    private void genFood() {
    	do {
    		food[0] = rand.nextInt(height);
    		food[1] = rand.nextInt(width);
    	}
    	while(onBody(food));
    	System.out.println("new food : " + food[0] + "," + food[1]);
    }
    
    private String translate(String direction) {
    	if(direction.equals("U")) return "R";
        else if(direction.equals("D")) return "L";
        else if(direction.equals("L")) return "U";
        else return "D";
    }
    

}