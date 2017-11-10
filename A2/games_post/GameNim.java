import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class GameNim extends Game{

	char marks[] = {'O','X'}; // '0' for computer, and 'X' for human
	int WiningScore = 10;
	int LosingScore = -10;
	int NeutralScore = 0;

	public GameNim(){
		currentState = new StateNim();
	}

	public boolean isWinState(State state){
		StateNim tstate = (StateNim) state;
		//player who did the last move
		if(tstate.coinsleft == 1) return true;
		return false;
	}

	public boolean isStuckState(State state){
		if(isWinState(state))
			return false;
		StateNim tstate = (StateNim) state;

		//check is there any empty plot in range 1-13
		for(int i = 1; i < 14 ; i++){
			if(tstate.board[i] == ' ')
				return false;
		
		}
		return true;
	}

	public Set<State> getSuccessors(State state){

		if(isWinState(state) || isStuckState(state))
			return null;

		Set<State> successors = new HashSet<State>();
		StateNim tstate = (StateNim) state;

		StateNim successor_state;

		char mark = 'O';
		
		if(tstate.player == 1){
			mark = 'X';
			//System.out.println("00000");
		}
		int index = 0;
		int indices[] = new int[3];
		indices[0] = -1;
		for(int i = 1; i <= 13; i++){
			if(tstate.board[i] == ' '){

				indices[index++] = i;
				if(index == 3) break;

			}
		}
		
		for(int numCoins = 1;numCoins <= 3;numCoins++ )
        {
			successor_state = new StateNim(tstate);
        	for(int j = 1 ; j < numCoins ; j++){
            	
            	successor_state.board[indices[j]]='O';
				successor_state.coinsleft -= 1;
				successor_state.player = (state.player == 0 ? 1 : 0);
                
            }
			
                
                successors.add(successor_state);
            
            
            
        }
		return successors;

	}

	public double eval(State state){
		return 0;
	}
	public static void main(String[] args) throws Exception{

		Game game = new GameNim();
		Search search = new Search(game);
		int depth = 12;

		 //needed to get human's move
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while(true){

        	StateNim nextState = null;

        	switch(game.currentState.player){
        		case 1: // human turn
        			System.out.print("Enter your *valid* moves: > ");

        			String[] pos = in.readLine().split(" ");

        			nextState = new StateNim((StateNim)game.currentState);
        			nextState.player = 1;
        			for (int i = 0; i <pos.length; i++){
        				nextState.board[Integer.parseInt(pos[i])] = 'X';
        				//System.out.print(nextState.board[Integer.parseInt(pos[i])] + " ");
        				nextState.coinsleft -=1;
        				
        			}
        			System.out.println("Number of coins left: > "+nextState.coinsleft);
        			System.out.println("Human: \n" + nextState);
        			break;

        		case 0:
             		nextState = (StateNim) search.bestSuccessorState(depth);
        			System.out.println("Number of coins left: > "+nextState.coinsleft);
        			nextState.player = 0;
            	 	System.out.println("Computer: \n" + nextState);
            	 	break;
        	}
        	game.currentState = nextState;

        	game.currentState.player = (game.currentState.player==0 ? 1 : 0);
            
            //Who wins?
            if ( game.isWinState(game.currentState) ) {
            
            	if (game.currentState.player == 1) //i.e. last move was by the computer
            		System.out.println("Computer wins!");
            	else
            		System.out.println("You win!");
            	
            	break;
            }
            
            if ( game.isStuckState(game.currentState) ) { 
            	System.out.println("Cat's game!");
            	break;
            }

        }


	}
}