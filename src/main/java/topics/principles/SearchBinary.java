package topics.principles;


public class SearchBinary {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] list = {-1, 1, 3, 4, 15, 100};
		System.out.println(search(list, -1));
	}
	
	public static boolean search(int[] list, int value){
		int left = 0;
		int right = list.length-1;
		int k;
		
		do {
			k = (left + right) / 2;
			if (value > list[k])
				left = k+1;
			else 
				right = k-1;
		} while ((list[k] != value) && (left < right));
		
	    if (list[k] != value)
	    	return false;
	    else 
	    	return true;
	}

}