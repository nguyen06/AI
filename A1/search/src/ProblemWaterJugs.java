
import java.util.HashSet;
import java.util.Set;

public class ProblemWaterJugs extends Problem{

	static final int jug12 = 0;
	static final int jug8 = 1;
	static final int jug3 = 2;

	boolean goal_test(Object state){
		StateWaterJugs water_state = (StateWaterJugs) state;

		if(water_state.jugArray[jug12] == 1 || water_state.jugArray[jug8] == 1 || water_state.jugArray[jug3] == 1){
			return true;
		}else return false;
	}

	Set<Object> getSuccessors(Object state){

		Set<Object> set = new HashSet<Object>();

		StateWaterJugs water_state = (StateWaterJugs) state;

		//let create without any constraint, the remove the illegal ones
		StateWaterJugs successor_state;
		

		//fill the large jug when it is empty requires pouring 12 gallons into it
		successor_state = new StateWaterJugs(water_state);
		//check if the 12gallons jug is emtpy
		if(successor_state.jugArray[0] == 0){
			successor_state.jugArray[0] +=12;
			set.add(successor_state);
		}

		//fill the large jug when it is empty requires pouring 8 gallons into it
		successor_state = new StateWaterJugs(water_state);
		//check if the 8gallons jug is emtpy
		if(successor_state.jugArray[1] == 0){
			successor_state.jugArray[1] +=8;
			set.add(successor_state);
		}

		//fill the large jug when it is empty requires pouring 3 gallons into it
		successor_state = new StateWaterJugs(water_state);
		//check if the 3gallons jug is emtpy
		if(successor_state.jugArray[2] == 0){
			successor_state.jugArray[2] +=3;
			set.add(successor_state);
		}

		//empty 12 gallons jug
		successor_state = new StateWaterJugs(water_state);
		//check if the 12gallons jug is emtpy or not
		if(successor_state.jugArray[0] > 0){
			successor_state.jugArray[0] =0;
			set.add(successor_state);
		}

		//empty 8 gallons jug
		successor_state = new StateWaterJugs(water_state);
		//check if the 8gallons jug is emtpy or not
		if(successor_state.jugArray[1] > 0){
			successor_state.jugArray[1] =0;
			set.add(successor_state);
		}
		//empty 3 gallons jug
		successor_state = new StateWaterJugs(water_state);
		//check if the 3gallons jug is emtpy or not
		if(successor_state.jugArray[2] > 0){
			successor_state.jugArray[2] =0;
			set.add(successor_state);
		}

		successor_state = new StateWaterJugs(water_state);
		//pour 12gallon jug into 8gallon jug
		//check if we can do that
		if(successor_state.jugArray[1] < 8 && successor_state.jugArray[0] >0 ){
			//get the available volume from 8gallon jug
			int available_volume = 8 - successor_state.jugArray[1];
			//check if the 12gallon has less than available volume in 8gallon jug, if no, 
			//we can four all volume from 12gallon jug into 8gallon jug
			if(available_volume >= successor_state.jugArray[0]){
				successor_state.jugArray[1] += successor_state.jugArray[0];
				successor_state.jugArray[0] = 0;
			}else{
				//else we can pour the allowed volume from 12gallon jug to 8gallon jug
				successor_state.jugArray[1] += available_volume;
				successor_state.jugArray[0] -= available_volume;
			}
			set.add(successor_state);
		}

		successor_state = new StateWaterJugs(water_state);
		//pour 12gallon jug into 3gallon jug
		//check if we can do that
		if(successor_state.jugArray[2] < 3 && successor_state.jugArray[0] >0 ){
			//get the available volume from 3gallon jug
			int available_volume = 3 - successor_state.jugArray[2];
			//check if the 12gallon has less than available volume in 3gallon jug, if yes, 
			//we can four all volume from 12gallon jug into 3gallon jug
			if(available_volume >= successor_state.jugArray[0]){
				successor_state.jugArray[2] += successor_state.jugArray[0];
				successor_state.jugArray[0] = 0;
			}else{
				//else we can pour the allowed volume from 12gallon jug to 3gallon jug
				successor_state.jugArray[2] += available_volume;
				successor_state.jugArray[0] -= available_volume;
			}
			set.add(successor_state);
		}

		successor_state = new StateWaterJugs(water_state);
		//pour 8gallon jug into 12gallon jug
		//check if we can do that
		if(successor_state.jugArray[0] < 12 && successor_state.jugArray[1] >0 ){
			//get the available volume from 12gallon jug
			int available_volume = 12 - successor_state.jugArray[0];
			//check if the 8gallon has less than available volume in 12gallon jug, if yes, 
			//we can four all volume from 8gallon jug into 12gallon jug
			if(available_volume >= successor_state.jugArray[1]){
				successor_state.jugArray[0] += successor_state.jugArray[1];
				successor_state.jugArray[1] = 0;
			}else{
				//else we can pour the allowed volume from 8gallon jug to 12gallon jug
				successor_state.jugArray[0] += available_volume;
				successor_state.jugArray[1] -= available_volume;
			}
			set.add(successor_state);
		}

		successor_state = new StateWaterJugs(water_state);
		//pour 8gallon jug into 3gallon jug
		//check if we can do that
		if(successor_state.jugArray[2] < 3 && successor_state.jugArray[1] >0 ){
			//get the available volume from 3gallon jug
			int available_volume = 3 - successor_state.jugArray[2];
			//check if the 8gallon has less than available volume in 3gallon jug, if yes, 
			//we can four all volume from 8gallon jug into 3gallon jug
			if(available_volume >= successor_state.jugArray[1]){
				successor_state.jugArray[2] += successor_state.jugArray[1];
				successor_state.jugArray[1] = 0;
			}else{
				//else we can pour the allowed volume from 8gallon jug to 12gallon jug
				successor_state.jugArray[2] += available_volume;
				successor_state.jugArray[1] -= available_volume;
			}
			set.add(successor_state);
		}

		successor_state = new StateWaterJugs(water_state);
		//pour 3gallon jug into 12gallon jug
		//check if we can do that
		if(successor_state.jugArray[0] < 12 && successor_state.jugArray[2] >0 ){
			//get the available volume from 12gallon jug
			int available_volume = 12 - successor_state.jugArray[0];
			//check if the 3gallon has less than available volume in 12gallon jug, if yes, 
			//we can four all volume from 3gallon jug into 12gallon jug
			if(available_volume >= successor_state.jugArray[2]){
				successor_state.jugArray[0] += successor_state.jugArray[2];
				successor_state.jugArray[2] = 0;
			}else{
				//else we can pour the allowed volume from 3gallon jug to 12gallon jug
				successor_state.jugArray[0] += available_volume;
				successor_state.jugArray[2] -= available_volume;
			}
			set.add(successor_state);

		}
		successor_state = new StateWaterJugs(water_state);
		//pour 3gallon jug into 8gallon jug
		//check if we can do that
		if(successor_state.jugArray[1] < 8 && successor_state.jugArray[2] >0 ){
			//get the available volume from 12gallon jug
			int available_volume = 8 - successor_state.jugArray[1];
			//check if the 3gallon has less than available volume in 8gallon jug, if yes, 
			//we can four all volume from 3gallon jug into 8gallon jug
			if(available_volume >= successor_state.jugArray[2]){
				successor_state.jugArray[1] += successor_state.jugArray[2];
				successor_state.jugArray[2] = 0;
			}else{
				//else we can pour the allowed volume from 3gallon jug to 8gallon jug
				successor_state.jugArray[1] += available_volume;
				successor_state.jugArray[2] -= available_volume;
			}
			set.add(successor_state);
		}



		return set;

	}



	double step_cost(Object fromJug, Object toJug){

		int cost = 0;
		StateWaterJugs From = (StateWaterJugs)fromJug;
		StateWaterJugs To = (StateWaterJugs)toJug;

		for(int i =0 ; i < 3 ; i++){
			cost = Math.max(cost, Math.abs(To.jugArray[i] - From.jugArray[i]));
		}

		return cost;

	}

	public double h(Object state) { return 0; }



	public static void main(String[] args) throws Exception {
		ProblemWaterJugs problem = new ProblemWaterJugs();
		int[] jugArray = {0,0,0};
		problem.initialState = new StateWaterJugs(jugArray); 
		
		Search search  = new Search(problem);

		System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());

		System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());

        System.out.println("DepthFirstTreeSearch:\t" + search.DepthFirstTreeSearch());

        System.out.println("DepthFirstGraphSearch:\t" + search.DepthFirstGraphSearch());

        System.out.println("UniformCostTreeSearch:\t" + search.UniformCostTreeSearch());

        System.out.println("UniformCostGraphSearch:\t" + search.UniformCostGraphSearch());
		//System.out.println("AstarGraphSearch:\t" + search.AstarGraphSearch());
		System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());

        System.out.println("GreedyBestFirstGraphSearch:\t" + search.GreedyBestFirstGraphSearch());

        System.out.println("AstarTreeSearch:\t" + search.AstarTreeSearch());

        System.out.println("AstarGraphSearch:\t" + search.AstarGraphSearch());

        System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());

        System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());


	}

}