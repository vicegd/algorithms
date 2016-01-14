package topics.principles;

public class GetMaxFromList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] list = {3, 1, 10, 5, -1};
		System.out.println(sum(list));
	}
	
	public static int sum(int[] list){
		int value = 0;
	    for (int i = 0; i < list.length; i++){
	    	value += list[i];
	    }
	    return value;
	}

}
