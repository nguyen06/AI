public class StateNim extends State {
	public char board[] = new char[(4*4)+1];
	public int coinsleft;

	public StateNim(){
		
		coinsleft = 13;
		for(int i = 0; i <14; i++){
			board[i] = ' ';
		}
		player = 1;
	}

	public StateNim(StateNim state){

		for(int i = 0; i < 14 ; i++)
		{
			board[i] = state.board[i];
		}
		player = state.player;
		coinsleft = state.coinsleft;
	}
	 public String toString() {
    	
    	String ret = "";
    	
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<4; j++) {
    			ret += board[1 + i*4+j] + ", ";
    		}
    		ret += "\n";
    	}
    	return ret;
    }
}