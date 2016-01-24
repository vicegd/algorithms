package topics.parallel;

import java.util.concurrent.RecursiveTask;

/**
 * To calculate the Fibonacci of a number n
 * @author viceg
 */
public class FibonacciTask extends RecursiveTask<Long> {
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD = 9;
	private FibonacciAlgorithm problem;
	private long result;
	 
	public FibonacciTask(FibonacciAlgorithm problem) {
		this.problem = problem;
	}

	@Override
	public Long compute() {
		if (problem.n < THRESHOLD) { 
			result = problem.solve();
		}
		else {
			FibonacciTask subTask1 = new 
					FibonacciTask(new FibonacciAlgorithm(problem.n-1));
			FibonacciTask subTask2 = new 
					FibonacciTask(new FibonacciAlgorithm(problem.n-2));
			subTask1.fork();
			result = subTask2.compute() + subTask1.join();
		}
		return result;
	}
}



