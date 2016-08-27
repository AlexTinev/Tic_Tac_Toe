package boardComponents;

import java.util.HashSet;
import enums.Signs;

public class Board {
       
	public final int ROWS = 3;
    public final int COLS = 3;
    private Cell[][] cells;
    
    public boolean checkIfFull(){
    	for(int i=0; i<ROWS; i++){
    		for(int j=0; j<COLS; j++){
    			if(this.cells[i][j].getSign() == Signs.E){
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public Signs checkForWinner(){
    	if(checkRows() != Signs.E){
    		return checkRows();
    	}
    	else if(checkCols() != Signs.E){
    		return checkCols();
    	}
    	else if(checkDiagonalLeftToRight() != Signs.E){
    		return checkDiagonalLeftToRight();
    	}
    	else if(checkDiagonalRightToLeft() != Signs.E){
    		return checkDiagonalRightToLeft();
    	}
    	else{
    		return Signs.E;
    	}
    }
    
    public String draw(){
    	
    	StringBuilder b = new StringBuilder();
    	
    	for(int i=0; i < ROWS; i++){
    		for(int j=0; j < COLS; j++){
    	        b.append(this.cells[i][j].getSign());
    		}
    		b.append("\n\r");
    	}
    	return b.toString();
    }
    
    public String clear(){
    	StringBuilder b = new StringBuilder();
    	
    	for(int i=0; i<ROWS; i++){
    		for(int j=0; j<COLS; j++){
    			this.cells[i][j].clear();
    		}
    		b.append("\n\r");
    	}
    	
    	return b.toString();
    }
    
    public Cell getCell(int row, int col){
    	if(row >= ROWS || row < 0){
    		throw new IndexOutOfBoundsException("Invalid row index");
    	}
    	else if(col >= COLS || col < 0){
    		throw new IndexOutOfBoundsException("Invalid col index");
    	}
    	
    	return this.cells[row][col];
    }
    
    public Board(){
    	this.cells = new Cell[ROWS][COLS]; 
    	initialize();
    }
    
    private Signs checkRows(){
    	Signs result = Signs.E;
    	
    	for(int i=0; i<ROWS; i++){
    		HashSet<Signs> cellSigns = new HashSet<Signs>();
    		for(int j=0; j<COLS; j++){
    			cellSigns.add(this.cells[i][j].getSign());
    		}
    		if(checkHashSet(cellSigns) != Signs.E){
        		result = checkHashSet(cellSigns);
        		break;
        	}
    	}
    	
    	return result;
    }
    
    private Signs checkCols(){
    	Signs result = Signs.E;
    	
    	for(int i=0; i<ROWS; i++){
    		HashSet<Signs> cellSigns = new HashSet<Signs>();
    		for(int j=0; j<COLS; j++){
    			cellSigns.add(this.cells[j][i].getSign());
    		}
    		if(checkHashSet(cellSigns) != Signs.E){
        		result = checkHashSet(cellSigns);
        		break;
        	}
    	}
    	
    	return result;
    }
    
    private Signs checkDiagonalLeftToRight(){
    	HashSet<Signs> cellSigns = new HashSet<Signs>();
    	
    	for(int i=0; i<ROWS; i++){
    		cellSigns.add(this.cells[i][i].getSign());
    	}
    	
    	return checkHashSet(cellSigns);
    }
    
    private Signs checkDiagonalRightToLeft(){
    	HashSet<Signs> cellSigns = new HashSet<Signs>();
    	
    	int boardRow = 0;
    	int boardCol = COLS - 1;
    	
    	while(boardCol > -1 && boardRow < ROWS){
    		cellSigns.add(this.cells[boardRow][boardCol].getSign());
    		boardRow++;
    		boardCol--;
    	}
    	
    	return checkHashSet(cellSigns);
    }
    
    private Signs checkHashSet(HashSet<Signs> cellSigns){
    	Signs result = Signs.E;
    	
    	if(cellSigns.size() == 1 && !cellSigns.contains(Signs.E)){
    		if(cellSigns.contains(Signs.X)){
    			result = Signs.X;
    		}
    		else{
    			result = Signs.O;
    		}
    	}
    	
    	return result;
    }
    
    private void initialize(){
    	for(int i = 0; i < ROWS; i++){
    		for(int j = 0; j < COLS; j++){
    			this.cells[i][j] = new Cell(i,j);
    		}
    	}
    }
    
    
    
}
