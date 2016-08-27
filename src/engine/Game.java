package engine;

import java.nio.charset.Charset;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.*;
import boardComponents.Board;
import enums.Signs;
import players.Player;

public class Game {
 
	private Board board;
	private Player playerOne;
	private Player playerTwo;
	private Terminal terminal;
	
    public void start() throws InterruptedException{
		this.terminal.enterPrivateMode();	
		writeOnTheConsole("Welcome to Tic-Tac-Toe.","Rules: The player who manages to mark a full row, col or diagonal with his sign wins",
				"Player One Sign: X", "Player Two Sign: O","Move the cursor with the arrow keys and press Enter to mark the cell","Press Enter when ready or Esc to quit.");
		
		Key key = this.terminal.readInput();
		key = waitForInput(key);
		
		while(!((key.getKind() == Key.Kind.Enter) || (key.getKind() == Key.Kind.Escape))){
			key = this.terminal.readInput();
			key = waitForInput(key);
			}
		if(key.getKind() == Key.Kind.Escape){
			this.terminal.clearScreen();
    		this.terminal.exitPrivateMode();
		}
		else{
			this.terminal.clearScreen();
			startPlaying();
		}
    }
	
	public Game(){
		this.board = new Board();
		this.terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
		this.playerOne = new Player();
		this.playerTwo = new Player();
	}
	
    private void startPlaying() throws InterruptedException{
		
		this.board.clear();
		String boardImage = this.board.draw();
		
		for(int row=0; row<=(this.board.ROWS * 3) + 1; row++){
		        	for(int col=0; col<=(this.board.COLS * 3) + 1; col++){
		        		if(((row % 3 == 0) && (col % 3 == 0)) && (row > 0 && col > 0)){
		        		int index = 0;
		        		this.terminal.moveCursor(col, row);
		        		this.terminal.putCharacter(boardImage.charAt(index));
		        		index++;
		        	}
		        }
		}
		
		Player currentPlayer = this.playerOne;
		
		int turn = 1;
		
		while(true){
			
			if(turn % 2 != 0){
				currentPlayer = this.playerOne;
				printOnSpecificRow((this.board.ROWS * 3) + 4, "Now Plays Player One : X");
			}
			else{
				currentPlayer = this.playerTwo;
				printOnSpecificRow((this.board.ROWS * 3) + 4, "Now Plays Player Two : O");
			}
			
			movePlayer(currentPlayer);
			
			  Signs winner = this.board.checkForWinner();
			
			 if(winner == Signs.X){
	        	   this.terminal.clearScreen();
	        	   writeOnTheConsole("Player one Wins!");
	        	   break;
	           }
	           else if (winner == Signs.O){
	        	   this.terminal.clearScreen();
	        	   writeOnTheConsole("Player two Wins!");
	        	   break;
	           }
			
			if(this.board.checkIfFull()){
			    this.terminal.clearScreen();
				writeOnTheConsole("GAME OVER", "Draw!");
				break;
			}
           
           turn++;
		}
		
		printOnSpecificRow(3, "Press Enter if you want to play again or Esc to quit.");
		
		Key key = this.terminal.readInput();
		key = waitForInput(key);
		
		while(!((key.getKind() == Key.Kind.Enter) || (key.getKind() == Key.Kind.Escape))){
			key = this.terminal.readInput();
			key = waitForInput(key);
			}
		if(key.getKind() == Key.Kind.Escape){
			this.terminal.clearScreen();
    		this.terminal.exitPrivateMode();
		}
		else{
			this.terminal.clearScreen();
			startPlaying();
		}
		
		
	}
	
	private void movePlayer(Player player) throws InterruptedException{
        int currentCol = 3;
		int currentRow = 3;
		
		this.terminal.moveCursor(currentCol, currentRow);
		
		while(true){
        	
			Thread.sleep(5);
        	Key key = terminal.readInput();
        	key = waitForInput(key);
        	
        	if(key.getKind() == Key.Kind.ArrowDown){
                  currentRow+=3;		
        	}
        	else if(key.getKind() == Key.Kind.ArrowUp){
        		currentRow-=3;
        	}
        	else if(key.getKind() == Key.Kind.ArrowLeft){
        		currentCol-=3;
        	}
        	else if(key.getKind() == Key.Kind.ArrowRight){
        		currentCol+=3;
        	}
        	else if(key.getKind() == Key.Kind.Enter){
        		try{
        			int boardRow = 0;
        			int boardCol = 0;
        			
        			if(currentRow > 3){
        			   boardRow = (currentRow / 3) -1;
        			}
        			
        			if(currentCol > 3){
        				boardCol = (currentCol / 3) - 1;
        			}
        			
        		player.setCell(this.board.getCell(boardRow, boardCol));
        	    this.terminal.putCharacter(this.board.getCell(boardRow, boardCol).getSignAsChar());
        	    break;
        	    }   catch(UnsupportedOperationException e){
        	    	continue;
        	    }
        	}
        	else if(key.getKind() == Key.Kind.Escape){
        		this.terminal.clearScreen();
        		this.terminal.exitPrivateMode();
        		break;
        	}
        	
        	if(currentRow < 3 || currentRow > 10 || currentCol < 3 || currentCol > 10){
        		currentRow = 3;
        		currentCol = 3;
        	}
        
        		this.terminal.moveCursor(currentCol, currentRow);
        	}
     }
	
	
	private Key waitForInput(Key key) throws InterruptedException{
		while(key == null){
    		Thread.sleep(5);
    		key = terminal.readInput();
    		}
		return key;
	}
	
	private void writeOnTheConsole(String... messages){
		for(int row=0; row<messages.length; row++){
			for(int col=0; col<messages[row].length(); col++){
				this.terminal.moveCursor(col, row);
				this.terminal.putCharacter(messages[row].charAt(col));
			}
		}
	}
	
	private void printOnSpecificRow(int row, String message){
		for(int col=0; col<message.length(); col++){
			this.terminal.moveCursor(col, row);
			this.terminal.putCharacter(message.charAt(col));
		}
	}
}
