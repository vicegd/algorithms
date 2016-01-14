package topics.principles;

public class SearchSequential {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] list = {3, 1, 10, 5, -1};
		System.out.println(search(list, -1));
	}
	
	public static boolean search(int[] list, int value){
		int i = 0;
		int n = list.length - 1;
	    while ((i <= n) && (list[i] != value))
	    	i++;
	    if (i == n+1)
	    	return false;
	    else 
	    	return true;
	}

}