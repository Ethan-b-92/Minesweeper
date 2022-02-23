package mines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mines {

	PlaceInGame[][] board;
	int height, width, numMines;
	boolean showAll;
	public static Random rand = new Random();

	// Constructor that build the Mines
	public Mines(int height, int width, int numMines) {
		this.height = height;
		this.width = width;
		this.numMines = numMines;
		this.board = new PlaceInGame[height][width];
		this.showAll = false;
		int i;

		for (i = 0; i < this.height; i++)
			for (int j = 0; j < this.width; j++)
				board[i][j] = new PlaceInGame(i, j);
		i = 0;
		while (i < numMines) {
			int x = rand.nextInt(height), y = rand.nextInt(width);
				i++;
			if (board[x][y].checkmine())
				i--;
			else
				addMine(x, y);
		}
	}

	// Set new mines if he isn't exists return true
	public boolean addMine(int i, int j) {
		if (!board[i][j].checkmine())
		{
			board[i][j].mine = 1;
			return true;
		}
		return (false);
	}

	// Open the place around if there is no Mines
	public boolean open(int i, int j) {
		if (board[i][j].checkmine())
			return (false);

		if (board[i][j].checkopen())
			return (true);

		board[i][j].open = 1;
		if (board[i][j].HowManyMines() == 0)
			for (PlaceInGame p1 : board[i][j].p.neighbours())
				open(p1.p.x, p1.p.y);
		return (true);
	}

	// take off or put the flag
	public void toggleFlag(int x, int y) {
		if (board[x][y].checkflag())
			board[x][y].flag = 0;
		else
			board[x][y].flag = 1;
	}

	// check if all the place that openned are without mines
	public boolean isDone() {
		for (int i = 0; i < this.height; i++)
			for (int j = 0; j < this.width; j++)
				if ((board[i][j].checkmine() && board[i][j].checkopen())
						|| (!board[i][j].checkopen() && !board[i][j].checkmine()))
					return (false);
		return (true);
	}

	// Return a big string with F/X/ /.
	public String get(int i, int j) {
		String str = "";
		if (!board[i][j].checkopen() && showAll == false)
			if (board[i][j].checkflag())
				return (str += 'F');
			else
				return (str += '.');
		else {
			if (board[i][j].checkmine())
				return (str += 'X');
			else {
				if (board[i][j].HowManyMines() == 0)
					return (str += " ");
				else
					return (board[i][j].ToStringMines());
			}
		}
	}

	// the Printer function
	public String toString() {
		String string = "";
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				string += get(i, j);
			}
			string += '\n';
		}
		return (string);
	}

	// Show all places in the board
	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}

	// Private class - represent a place in the game board
	private class PlaceInGame {
		Place p;
		int mine, flag, open;

		// Constructor that put place in game
		public PlaceInGame(int i, int j) {
			p = new Place(i, j);
			flag = 0;
			mine = 0;
			open = 0;
		}

		// Check the mines
		public boolean checkmine() {
			if (mine != 1)
				return (false);
			return (true);
			
		}

		// Check the flag
		public boolean checkflag() {
			if (flag != 1)
				return (false);
			return (true);
		}

		// Check if there is no problem with the place 
		public boolean checkopen() {
			if (open != 1)
				return (false);
			return (true);
		}

		// Count the amount of mines
		public int HowManyMines() {
			int counter = 0;
			List<PlaceInGame> neighbourslist = this.p.neighbours();
			for (PlaceInGame p1 : neighbourslist)
				if (p1.checkmine())
					counter++;
			return (counter);
		}

		// Return the amount of mines in string
		public String ToStringMines() {
			String string = "";
			return (string += HowManyMines());
		}
	}

	// Private class - represent a place in board
	private class Place {
		int x, y;

		// The Constructor function
		public Place(int x, int y) {
			this.x = x;
			this.y = y;
		}

		// Returns the neighbors to their new place
		public List<PlaceInGame> neighbours() {

			List<PlaceInGame> ResV = new ArrayList<>();
			int x = this.x, y = this.y;
			if (x > 0) {
				if (y > 0) {
					ResV.add(board[x - 1][y - 1]);
					ResV.add(board[x - 1][y]);
					ResV.add(board[x][y - 1]);
				} else
					ResV.add(board[x - 1][y]);
			} else if (y > 0)
				ResV.add(board[x][y - 1]);
			if (x < height - 1) {
				if (y < width - 1) {
					ResV.add(board[x + 1][y + 1]);
					ResV.add(board[x + 1][y]);
					ResV.add(board[x][y + 1]);
				} else
					ResV.add(board[x + 1][y]);
			} else if (y < width - 1)
				ResV.add(board[x][y + 1]);
			if (x - 1 >= 0 && y + 1 <= width - 1)
				ResV.add(board[x - 1][y + 1]);
			if (x + 1 <= height - 1 && y - 1 >= 0)
				ResV.add(board[x + 1][y - 1]);
			return (ResV);
		}
	}
}
