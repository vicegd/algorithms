package topics.principles;

public class SumElementsFromList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] list = {3, 1, 10, 5, -1};
		System.out.println(max(list));
	}
	
	public static int max(int[] list){
		int max = -1;
	    for (int i = 0; i < list.length; i++){
	    	if (list[i] > max)
	    		max = list[i];
	    }
	    return max;
	}

}
