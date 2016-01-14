package topics.principles;

/**
 * To get the maximum of a list of numbers
 * @author viceg
 */
public class GetMaximumFromList {
	
	/**
	 * To get the maximum value of the numbers contained in an array
	 * @param list Array with numbers
	 * @return The maximum of all the numbers
	 */
	public int max(int[] list){
		int max = -1;
	    for (int i = 0; i < list.length; i++){
	    	if (list[i] > max)
	    		max = list[i];
	    }
	    return max;
	}

}
