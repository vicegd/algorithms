package topics.greedy;

/**
 * GREEDY ALGORITHM PROBLEM: THE KNAPSACK PROBLEM (0/1). ELEMENTS CANNOT BE BROKEN
 * It has not an optimal solution in some cases
 * @author viceg
 */
public class Knapsack01 {
	 private int[] weights; //Weight of each of the objects
	 private float[] relations; //Values/weights
	 private float[] solution; //The solution, that is, the amount of each of the objects we take from the backpack
	
	 /**
	  * Constructor for Knapsack01 objects
	  * @param weights Weights that can be used
	  * @param values Values of the objects
	  * @param solution Array to save the amount of each object taken as a solution
	  */
	 public Knapsack01(int[] weights, int[] values, float[] solution) {
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
		 float currentWeight = 0; //actual weight of the backpack

		 do { //builds and initializes arrays
			 i = bestObject(); //heuristic for selection O(n) in this case. It could be O(logn)		 
			 //***************CHANGE COMPARED TO THE PREVIOUS VERSION******************
			 //We need to check if the elements have been finished => If so, exit the algorithm
			 if (i == -1) break; 
			//****************************************************************************
			 if (currentWeight + weights[i] <= maxWeight) {
				 solution[i] = 1; //you take the whole object currentWeight += weights[i];
				 currentWeight += weights[i];
			 }
			 //***************CHANGE COMPARED TO THE PREVIOUS VERSION******************
			 //Now, we cannot use partial objects...
			 // else {
			 //	 solution[i] = (maxWeight - currentWeight)/weights[i]; //you take only a part of the object until we reach the limit
			 //	 currentWeight = maxWeight;
			 // } 
			 //****************************************************************************
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
		 //***************CHANGE COMPARED TO THE PREVIOUS VERSION******************
		 /* Since we are working with entire objects, it is possible that we cannot 
		  * insert any of the objects and complete the 100% of the allowable weight. 
		  * With breakable objects, we always complete the 100% of the allowable weight */
		 if (position == Integer.MIN_VALUE) //When we have tried to use all the elements but we would need yet more
			 return -1; 
		 else {
			 relations[position] = Float.MIN_VALUE; //This element is already used. We cannot used it again
			 return position; 
		 }
		//****************************************************************************
	 }
	 
}
