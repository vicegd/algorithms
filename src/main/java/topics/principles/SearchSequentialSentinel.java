package topics.principles;

import java.util.ArrayList;
import java.util.List;

public class SearchSequentialSentinel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(3); list.add(1); list.add(10); list.add(5); list.add(-1);
		System.out.println(search(list, -1));
	}
	
	public static boolean search(List<Integer> list, int value){
		list.add(value); //we know now that the element is in the array
		int i = 0;
	    while (list.get(i) != value)
	    	i++;
	    if (i == list.size()-1) //element n+1
	    	return false;
	    else 
	    	return true;
	}

}

