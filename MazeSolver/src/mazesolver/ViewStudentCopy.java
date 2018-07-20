package mazesolver;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.concurrent.TimeUnit;

public class View extends JFrame {
    private int [][] maze = 
        { {1,1,1,1,1,1,1,1,1,1,1,1,1},
          {1,0,1,0,1,0,1,0,0,0,0,0,1},
          {1,0,1,0,0,0,1,0,1,1,1,0,1},
          {1,0,1,0,1,1,1,0,0,0,0,0,1},
          {1,0,1,0,0,0,0,0,1,1,1,0,1},
          {1,0,1,0,1,1,1,0,1,0,0,0,1},
          {1,0,1,0,1,0,0,0,1,1,1,0,1},
          {1,0,1,0,1,1,1,0,1,0,1,0,1},
          {1,0,0,0,0,0,0,0,0,0,1,9,1},
          {1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
    
    public final List<Integer> path = new ArrayList<Integer>();
    public int pathIndex;
    	public boolean searchPath(int x, int y) throws InterruptedException {
            if (this.maze[y][x] == 9) {
            	this.path.add(x);
            	this.path.add(y);
            	Graphics g = getGraphics();
                if (g != null) {
                	paint(g);
                	TimeUnit.MILLISECONDS.sleep(50);
                }
                else {
                	repaint();
                }
                return true;
            }
            
            if (this.maze[y][x] == 0) {
            	this.maze[y][x] = 2;
            	
            	Graphics g = getGraphics();
                if (g != null) {
                	paint(g);
                	TimeUnit.MILLISECONDS.sleep(50);
                }
                else {
                	repaint();
                }
            	
                int dx = -1;
                int dy = 0;
                if (searchPath(x + dx, y + dy)) {
                	this.path.add(x);
                	this.path.add(y);
                	Graphics g1 = getGraphics();
                    if (g1 != null) {
                    	paint(g1);
                    	TimeUnit.MILLISECONDS.sleep(50);
                    }
                    else {
                    	repaint();
                    }
                    return true;
                }

                dx = 1;
                dy = 0;
                if (searchPath(x + dx, y + dy)) {
                	this.path.add(x);
                	this.path.add(y);
                	Graphics g1 = getGraphics();
                    if (g1 != null) {
                    	paint(g1);
                    	TimeUnit.MILLISECONDS.sleep(50);
                    }
                    else {
                    	repaint();
                    }

                    return true;
                }

                dx = 0;
                dy = -1;
                if (searchPath(x + dx, y + dy)) {
                	this.path.add(x);
                	this.path.add(y);
                	Graphics g1 = getGraphics();
                    if (g1 != null) {
                    	paint(g1);
                    	TimeUnit.MILLISECONDS.sleep(50);
                    }
                    else {
                    	repaint();
                    }

                    return true;
                }

                dx = 0;
                dy = 1;
                if (searchPath(x + dx, y + dy)) {
                	this.path.add(x);
                	this.path.add(y);
                	Graphics g1 = getGraphics();
                    if (g1 != null) {
                    	paint(g1);
                    	TimeUnit.MILLISECONDS.sleep(50);
                    }
                    else {
                    	repaint();
                    }
                    
                    return true;
                }
            }
            if (this.maze[y][x] != 1) {
            	System.out.println("I've backtracked! " + y + " " + x);
            }
            return false;
        }
    
    public View() throws InterruptedException {
        setTitle("Simple Maze Solver");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pathIndex = 0;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.translate(50, 50);
        
        // draw the maze
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Color color;
                switch (maze[row][col]) {
                    case 1 : color = Color.BLACK; break;
                    case 2 : color = Color.DARK_GRAY; break;
                    case 9 : color = Color.RED; break;
                    default : color = Color.WHITE;
                }
                g.setColor(color);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);
            }
        }
        
        // draw the path list
        System.out.println("Im printing the path!");
        for (int p = 0; p < path.size(); p += 2) {
            int pathX = path.get(p);
            int pathY = path.get(p + 1);
            g.setColor(Color.GREEN);
            g.fillRect(pathX * 30, pathY * 30, 30, 30);
        }
        if (pathIndex != 0) {
        	// draw the ball on path
        	int pathX = path.get(pathIndex);
            int pathY = path.get(pathIndex + 1);
            g.setColor(Color.RED);
            g.fillOval(pathX * 30, pathY * 30, 30, 30);
        }
    }
    
    @Override
    protected void processKeyEvent(KeyEvent ke) {
        if (ke.getID() != KeyEvent.KEY_PRESSED) {
            return;
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            pathIndex -= 2;
            if (pathIndex < 0) {
                pathIndex = 0;
            }
        }
        else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            pathIndex += 2;
            if (pathIndex > path.size() - 2) {
                pathIndex = path.size() - 2;
            }
        }
        repaint(); 
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View view;
				try {
					view = new View();
					view.setVisible(true);
					view.searchPath(1, 1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
    
}

