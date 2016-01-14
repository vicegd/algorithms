package topics.principles;

/**
 * To get the addition of a list of numbers
 * @author viceg
 */
public class GetAdditionFromList {
	
	/**
	 * To sum up the total value of the numbers contained in an array
	 * @param list Array with numbers
	 * @return The sum of all the numbers
	 */
	public int sum(int[] list){
		int value = 0;
	    for (int i = 0; i < list.length; i++){
	    	value += list[i];
	    }
	    return value;
	}

}
