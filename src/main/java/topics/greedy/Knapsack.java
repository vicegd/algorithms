package topics.greedy;

/**
 * GREEDY ALGORITHM PROBLEM: THE KNAPSACK PROBLEM (BREAKABLE) 
 * it has an optimal solution
 * @author viceg
 */
public class Knapsack {
	 private int[] weights; //Weight of each of the objects
	 private float[] relations; //Values/weights
	 private float[] solution; //The solution, that is, the amount of each of the objects we take from the backpack
	
	 /**
	  * Constructor for Knapsack objects
	  * @param weights Weights that can be used
	  * @param values Values of the objects
	  * @param relations Relation value/weight of each object
	  * @param solution Array to save the amount of each object taken as a solution
	  */
	 public Knapsack(int[] weights, int[] values, float[] solution) {
		this.weights = weights;
		this.solution = solution;
		this.relations = new float[values.length];
		
		for (int i = 0; i < relations.length; i++) { //O(n)
			relations[i] = (float)values[i]/weights[i];
		}
	}
	 
	 /**
	  * This algorithm can have a complexity from O(n*logn) to O(n^2). 
	  * It depends on the heuristic method since the main loop 
	  * is repeated at most n times
	  * @param maxWeight Max weight that we can take from objects
	  */
	 public void findObjects(int maxWeight){
		 int i = 0;
		 float currentWeight = 0; //Actual weight of the backpack

		 do { //Builds and initializes arrays
			 i = bestObject(); //Heuristic for selection O(n) in this case. It could be O(logn)
			 if (currentWeight + weights[i] <= maxWeight) {
				 solution[i] = 1; //You take the whole object currentWeight += weights[i];
				 currentWeight += weights[i];
			 }
			 else {
				 solution[i] = (maxWeight - currentWeight)/weights[i]; //You take only a part of the object until we reach the limit
				 currentWeight = maxWeight;
			 } 
		 } while (currentWeight < maxWeight); //O(n)
	 }

	 /**
	  * This method has a complexity O(n) --> 
	  * We could try a binary search with a 
	  * complexity O(logn)
	  * @return The position of the best available object
	  */
	 private int bestObject(){
		 float max = Float.MIN_VALUE;
		 int position = Integer.MIN_VALUE;
		 for (int i = 0; i < relations.length; i++){ //O(n)
			if (relations[i] > max){
				max = relations[i];
				position = i;
			}
		 }
		 relations[position] = Float.MIN_VALUE; //This element is already used. We cannot used it again as the most valuable object
		 return position;
	 }

}
