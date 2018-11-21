package pr1;

public class Cell {
		private int value;
		private boolean merged; //to know if this cell has already merged this turn
		
		public Cell(){
			this.value = 0;
			this.merged = false;
		}
		
		public void setValue(int value){
			this.value = value;
		}
		
		public int getValue(){
			return this.value;
		}
		
		public boolean isEmpty(){
			if(value == 0)	return true;
			else return false;
		}
		
		public void setMerge(){
			this.merged = false;
		}
		
		public boolean doMerge(Cell neighbour){
			if(neighbour.getValue() == this.value && !this.merged){ 
				neighbour.setValue(2*this.value);
				neighbour.setMerge();
				this.value = 0;
				return true;
			}else return false;
		}
}
