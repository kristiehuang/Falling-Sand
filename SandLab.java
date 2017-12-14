import java.awt.*;
import java.util.*;

//Kristie Huang, B˚

public class SandLab
{
	public static void main(String[] args)
	{
		SandLab lab = new SandLab(120, 80);
		lab.run();
	}


	//add constants for particle types here
	public static final int EMPTY = 0;
	public static final int METAL = 1;
	public static final int SAND = 2;
	public static final int WATER = 3;
	public static final int ICE = 4;


	//do not add any more fields
	private int[][] grid;
	private SandDisplay display;

	public SandLab(int numRows, int numCols)
	{
		String[] names;
		names = new String[5];
		names[EMPTY] = "Empty";
		names[METAL] = "Metal";
		names[SAND] = "Sand";
		names[WATER] = "Water";
		names[ICE] = "Ice";
		display = new SandDisplay("Falling Sand", numRows, numCols, names);
		grid = new int[numRows][numCols];
	}

	//called when the user clicks on a location using the given tool
	private void locationClicked(int row, int col, int tool)
	{
		grid[row][col] = tool;
	}


	//copies each element of grid into the display
	public void updateDisplay()
	{
		//for element in grid; if tool = empty, set color black
		//if tool = metal, set color gray
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == EMPTY) {
					display.setColor(i, j, Color.BLACK);
				}
				else if (grid[i][j] == METAL){ //if tool is metal
					display.setColor(i, j, Color.gray);
				}
				else if (grid[i][j] == SAND){ //if tool is sand
					display.setColor(i, j, Color.yellow);
				}
				else if (grid[i][j] == WATER){ //if tool is water
					display.setColor(i, j, Color.cyan);
				}
				else if (grid[i][j] == ICE){ //if tool is ICE
					display.setColor(i, j, Color.white);
				}
			}
		}


	}

	//called repeatedly.
	//causes one random particle to maybe do something.
	public void step() {
		Random rand = new Random();
		int x = rand.nextInt(grid.length - 1);
		int y = rand.nextInt(grid[0].length);

		if (grid[x][y] == SAND) {
			if (grid[x+1][y] == EMPTY) {
				grid[x][y] = EMPTY;
				grid[x+1][y] = SAND;
			}
		}
		else if(grid[x][y] == WATER) {
			int num = rand.nextInt(3);
			System.out.println("" + num);
			if (grid[x][y] == WATER) {
				if ((( (y+1) < grid[0].length) && (y > 1)) && ((grid[x+1][y] == EMPTY) || (grid[x+1][y] == WATER)) && ((grid[x][y+1] == EMPTY)  || (grid[x][y+1] == WATER)) && ((grid[x][y-1] == EMPTY)  || (grid[x][y-1] == WATER)))
				{

					//AND out of bounds
					grid[x][y] = EMPTY;

					if ((num == 0) && (grid[x][y+1] == EMPTY)) {
						grid[x][y+1] = WATER; //right
					}
					else if ((num == 1) && (grid[x][y-1] == EMPTY)) {
						grid[x][y-1] = WATER; //left
					}
					else if ((num == 2) && (grid[x+1][y] == EMPTY)) {
						grid[x+1][y] = WATER; //down

					}
				}
			}
			//if num = 1, go left. if 2, go right. if 3, go down
			// If the location in that randomly chosen direction is empty, the water particle moves there. (Look for ways to minimize duplicate code in your step method.)
			//Test that the ICE behaves roughly like a liquid, taking the shape of a container.


		}
		else if(grid[x][y] == ICE) {
			int num = rand.nextInt(3);
			System.out.println("" + num);
			if (grid[x][y] == ICE) {
				//if ((grid[x+1][y] == EMPTY) && (grid[x][y+1] == EMPTY) && (grid[x][y-1] == EMPTY)) {
				if ((( (y+1) < grid[0].length) && (y > 1)) && ((grid[x+1][y] == EMPTY) || (grid[x+1][y] == WATER)) && ((grid[x][y+1] == EMPTY)  || (grid[x][y+1] == WATER)) && ((grid[x][y-1] == EMPTY)  || (grid[x][y-1] == WATER)))
				{

					//					grid.length (x)
					//					grid[0].length
					//AND out of bounds
					grid[x][y] = EMPTY;

					if ((num == 0) && (grid[x][y+1] == EMPTY)) {
						grid[x][y+1] = ICE; //right
					}
					else if ((num == 1) && (grid[x][y-1] == EMPTY)) {
						grid[x][y-1] = ICE; //left
					}
					else if ((num == 2) && (grid[x+1][y] == EMPTY)) {
						grid[x+1][y] = ICE; //down

					}
				}
			}
			//if num = 1, go left. if 2, go right. if 3, go down
			// If the location in that randomly chosen direction is empty, the ICE particle moves there. (Look for ways to minimize duplicate code in your step method.)
			//Test that the ICE behaves roughly like a liquid, taking the shape of a container.


		}


	}

	//do not modify
	public void run()
	{
		while (true)
		{
			for (int i = 0; i < display.getSpeed(); i++)
				step();
			updateDisplay();
			display.repaint();
			display.pause(1);  //wait for redrawing and for mouse
			int[] mouseLoc = display.getMouseLocation();
			if (mouseLoc != null)  //test if mouse clicked
				locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
		}
	}
}
