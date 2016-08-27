package boardComponents;

import enums.Signs;

public class Cell {
      
	  private int row;
      private int col;
      private Signs sign;
      
      public void getMarked(Signs sign){
    	  if(getSign() != Signs.E){
    		throw new UnsupportedOperationException(" This cell is already set by the other player.");
    	  }
    	  else{
    	    this.sign = sign;
    	  }
      }
      
      public void clear(){
    	  this.sign = Signs.E;
      }
      
      public int getRow(){
   	   return this.row;
     }
     
     public int getCol(){
   	   return this.col;
     }
     
     public Signs getSign(){
   	   return this.sign;
     }
     
     public char getSignAsChar(){
   	  Signs temp = this.getSign();
   	  
   	  char result = '\u0000';
   	  
   	  if(temp == Signs.E){
   		  result = 'E';
   	  }
   	  else if(temp == Signs.X){
   		  result = 'X';
   	  }
   	  else if (temp == Signs.O){
   		  result = 'O';
   	  }
   	  
   	  return result;
     }
    
      public Cell(int row, int col){
    	   setRow(row);
    	   setCol(col);
    	   this.sign = Signs.E;
      }
      
      private void setRow(int row){
    	  if(row < 0){
    		 throw new IllegalArgumentException(" The cell row cannot be a negative number.");
    	  }
    	  else{
    		  this.row = row;
    	  }
      }
      
      private void setCol(int col){
    	  if(col < 0){
    		  throw new IllegalArgumentException(" The cell col cannot be a negativa number.");
    	  }
    	  else{
    		  this.col = col;
    	  }
      }
}
