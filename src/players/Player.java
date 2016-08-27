package players;

import enums.Signs;
import boardComponents.Cell;

public class Player {
	
	private static int playerNumber = 0;
	private Signs playerSign;
	
	 public Signs getPlayerSign(){
	    	return this.playerSign;
	    }
	
	public void setCell(Cell cell){
	    	cell.getMarked(getPlayerSign());
	}
	
	public Player(){
		playerNumber++;
		setPlayerSign();
	}
	
	private void setPlayerSign(){
 	    if(playerNumber > 2){
			throw new IllegalStateException(" There can be only two players");
		}
		else if(playerNumber == 1){
			this.playerSign = Signs.X;
		}
		else{
			this.playerSign = Signs.O;
		}
	}
}
