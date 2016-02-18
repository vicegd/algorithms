# Algorithms examples
Some example algorithms for the Algorithmics course in the [School of Computer Science] (https://ingenieriainformatica.uniovi.es/) in the [University of Oviedo] (http://www.uniovi.es).

You can run the JUnit test cases to check whether everything is working properly. 

However, some test cases are skipped because some algorithms are not complete. Please, try to complete them and after that you can remove the **@Ignore** attribute in the corresponding test cases in order to execute all of them.

If you do not know how to complete any of the code snippets, you can see and download the code snippets in Gists (links are indicated below). 

## Topics

### Sorting
#### TOC
* Bubble (*Bubble.java*)
* Bubble with sentinel (*ImprovedBubble.java*)
* Direct insertion (*Insertion.java*)
* Direct selection (*Selection.java*)
* Quicksort (*Quicksort.java*)

### Divide and Conquer

#### TOC
* Factorial of a number (*Factorial.java*)
* Fibonacci series (*Fibonacci.java*)
* Sum of elements (*VectorSum.java*)
* Sequential search (*SequentialSearch.java*)
* Binary (dichotomous) search (*BinarySearch.java*)
* Quicksort (*Quicksort.java*)
* Mergesort (*Mergesort.java*)
* The majoritarian element (*MajoritarianElement.java*)
* Mode of a set of numbers (*Mode.java*)
* Median of a set of numbers (*Median.java*)
* Maximum sum of subsequences of elements (*MaxSum.java*)

#### Missing code snippets
* [fact2()](https://gist.github.com/vicegd/7553ecb737c0f888a870)
* [fib4()](https://gist.github.com/vicegd/6b5061139d53f620f52a)
* [sequentialSearch2()](https://gist.github.com/vicegd/f99718b268841865c2e8)
* [binarySearch2()](https://gist.github.com/vicegd/0220c92fa1525806ccbc)
* [majoritarian2()](https://gist.github.com/vicegd/723110ab76e7b5750f64)
* [mode2()](https://gist.github.com/vicegd/93eb127241b916e04110)
* [median1()](https://gist.github.com/vicegd/720801a57e72b364b97c)
* [maxsum2()](https://gist.github.com/vicegd/f24a1b8262707217f5f6)

### Parallel algorithms

#### TOC
* Obtaining the square of the values of an array (*RecursiveActionSquare.java*)
* Comparison of different thresholds and CPUs (*RecursiveActionComparison.java*)
* Sum of the elements of an array (*RecursiveTaskSum.java*)
* Fibonacci series (*FibonacciTask.java*)
* Processing files concurrently (*FileProcessingTask.java*)

### Greedy algorithms

#### TOC
* The problem of the change (*Change.java* - **OPTIMAL**)
* The problem of the change (*ChangeNotOptimal* - **NOT OPTIMAL**)
* The knapsack problem (*Knapsack.java* - **OPTIMAL**)
* The knapsack problem (0/1) (*Knapsack01.java* - **NOT OPTIMAL**)
* Maximize the number of files on a disk (*FilesDisc1.java* - **OPTIMAL**)
* Minimize the free space on a disk with files (*FilesDisc2.java* - **NOT OPTIMAL**)
* The diligent plumber (*Plumber.java* - **OPTIMAL**)
* Some diligent plumbers (*SomePlumbers.java* - **OPTIMAL**)
* The truck driver in a hurry (*TruckDriver.java* - **OPTIMAL**)
* Prim (*Prim.java* - **OPTIMAL**)
* The horse jumping problem (*ChessHorseSimpleHeuristic, ChessHorse.java* - **OPTIMAL SOMETIMES**)
* The problem of assigning tasks to agents (*AgentsTasks.java, AgentsTasksRandomValues, AgentsTasksDifferentSizesTimes* - **NOT OPTIMAL**)

#### Missing code snippets
* [calculate()](https://gist.github.com/vicegd/33fb652c42f6d659e294)
* [getTotalTimeOfWait()](https://gist.github.com/vicegd/a1cf2dea92c46e615682)
* [orderInWhichTasksAreHandledBESTWAY()](https://gist.github.com/vicegd/6a2536b070fd904eb24f)
* [assignTasksToPlumbersBESTWAY()](https://gist.github.com/vicegd/535378f474fa0c072e51)
* [minimumSpanningTree()](https://gist.github.com/vicegd/26a8aa81469bf904eab6)
* [jump()](https://gist.github.com/vicegd/a756666382190c037cba)
* [newMovement()](https://gist.github.com/vicegd/71f6e3ae15d79e644d86)
* [greedy2()](https://gist.github.com/vicegd/1ed82e1b03e487955c1a)
* [getCost2()](https://gist.github.com/vicegd/aba41de7c68ff2f90236)

### Dynamic programming

#### TOC
* Fibonacci series (*Fibonacci.java*)
* Combinations (*Combinations.java*)
* The knapsack problem (0/1) (*Knapsack01.java*)
* The problem of the change (*Change.java*)
* Cheaper travel on the river (*RiverTravel.java*)

#### Missing code snippets
* [fib2()](https://gist.github.com/vicegd/e42b1b4a86c3993ca9f1)
* [combinationsDivideAndConquer()](https://gist.github.com/vicegd/e64eae49928f982e43e4)
* [change()](https://gist.github.com/vicegd/81ce8a0f19a83cbedc7f)
* [riverTravel()](https://gist.github.com/vicegd/6904f0fc73561f6fcf79)

### Backtracking

#### TOC
* Permutations of elements (*Permutations.java, PermutationsTimes.java*)
* Subsets of a given sum (*SubsetsGivenSum.java*)
* The problem of the n queens (*ChessQueensOne.java, ChessQueensAll.java*)
* The horse jumping problem (*ChessHorseOne.java, ChessHorseAll.java*)
* The problem of assigning tasks to agents (*AgentsTaskTimes.java*)

#### Missing code snippets
* [backtracking() (queens)](https://gist.github.com/vicegd/b28e6c5b49878b93270b)
* [backtracking() (horse - one)](https://gist.github.com/vicegd/4f4475e7b66fbd4c9f2f )
* [backtracking() (horse - all)](https://gist.github.com/vicegd/ec8981ca9c674662c862)
* [findBestSolution()](https://gist.github.com/vicegd/5c7d325df9e2ee880094 )
* [calculateCost()](https://gist.github.com/vicegd/f8241289b9f11e4efa70)
* [assigned()](https://gist.github.com/vicegd/e7260dde753c95ec5525)
* [printBestSol()](https://gist.github.com/vicegd/99a2df03bbb0eeb32685)

### Branch and bound

#### TOC
* The problem of assigning tasks to agents  (*AgentsTasks.java*)
* The problem of the puzzle  (*EightPuzzle.java*)
* Optimal placement of rectangles (*RectanglesPlacement.java*)

## License

License [GNU General Public License] (https://en.wikipedia.org/wiki/GNU_General_Public_License)

Copyright (C) 2016 - [Vicente García Díaz] (http://www.vicegd.com)
