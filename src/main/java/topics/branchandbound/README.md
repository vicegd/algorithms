# Branch & Bound - Intelligent Search for Optimization

Discover how to solve optimization problems systematically by exploring search spaces with intelligent pruning strategies.

---

## 🎯 Core Concept

**Branch & Bound** is a problem-solving algorithm for optimization that:

1. **Branch:** Explore solution space systematically (like a tree)
2. **Bound:** Calculate upper/lower bounds on possible solutions
3. **Prune:** Eliminate branches that can't possibly improve current best
4. **Iterate:** Until optimal solution found or all branches pruned

### Comparison with Other Techniques

| Technique | Explores | Optimal | Time |
|-----------|----------|---------|------|
| **Greedy** | Single path | ❌ Sometimes | O(n log n) |
| **DP** | Subproblems | ✅ Yes | O(n²) or better |
| **Backtracking** | All solutions | ✅ Yes | Exponential |
| **Branch & Bound** | Promising branches | ✅ Yes | Variable |

---

## 📊 Key Differences from Backtracking

### Backtracking
- Explores **all valid solutions**
- Returns **first valid or all solutions**
- Prunes invalid branches
- Example: N-Queens (find solutions)

### Branch & Bound
- Explores **promising branches only**
- Returns **optimal solution**
- Prunes suboptimal branches
- Example: Traveling Salesman (minimize distance)

---

## 🏗️ Branch & Bound Framework

### Basic Structure
```java
class Node {
    int cost;           // Known cost so far
    int bound;          // Upper bound on total cost
    // Partial solution state
}

BestSolution branchAndBound(InitialProblem problem) {
    PriorityQueue<Node> queue = new PriorityQueue<>(
        (a, b) -> Integer.compare(a.bound, b.bound)
    );
    
    BestSolution bestSolution = null;
    int bestCost = Integer.MAX_VALUE;
    
    Node root = createRootNode(problem);
    queue.add(root);
    
    while (!queue.isEmpty()) {
        Node current = queue.poll();
        
        // Pruning: If bound worse than best, skip
        if (current.bound >= bestCost) {
            continue;
        }
        
        // Leaf node (complete solution)
        if (isComplete(current)) {
            if (current.cost < bestCost) {
                bestSolution = current;
                bestCost = current.cost;
            }
            continue;
        }
        
        // Generate children (branch)
        for (Node child : generateChildren(current)) {
            // Calculate bound for child
            child.bound = calculateBound(child);
            
            // Only add if potentially better than current best
            if (child.bound < bestCost) {
                queue.add(child);
            }
        }
    }
    
    return bestSolution;
}
```

### Key Components

#### 1. **Cost Function**
Actual cost of partial solution so far
```
Example: Path distance accumulated
```

#### 2. **Bounding Function**
Estimate of best possible total cost from current node
```
Must be ≤ actual cost (lower bound)
Tighter bounds → more pruning → faster
```

#### 3. **Search Strategy**
How to order node exploration
- **Best-first:** Explore most promising first
- **Depth-first:** Memory efficient
- **Breadth-first:** Find solution eventually

#### 4. **Pruning Condition**
Skip branch if:
```
bound ≥ bestCostFound
```

---

## 1️⃣ Traveling Salesman Problem (TSP)

### Problem
Visit all cities exactly once with minimum total distance, returning to start.

**Example:**
```
Cities: A, B, C, D
Distances: (matrix)
Find: Shortest hamiltonian cycle
```

### Naive Approach
- Try all (n-1)! permutations
- Time: O(n!) - prohibitive for n > 12

### Branch & Bound Solution

#### 1. **Root Bound**
Lower bound = MST (Minimum Spanning Tree) cost + minimum edge from each node

```java
double calculateBound(Node node) {
    // Cost of edges already used
    double costSoFar = node.costSoFar;
    
    // Estimate for remaining path
    // Conservative: minimum edge from each unvisited city
    double estimate = 0;
    for (int city : unvisitedCities) {
        estimate += minOutgoingEdge(city);
    }
    
    return costSoFar + estimate;
}
```

#### 2. **Branching**
From current city, try going to each unvisited city

#### 3. **Pruning**
If bound ≥ best solution found, skip this branch

### Complexity
- **Best case:** O(n) with perfect bounding
- **Worst case:** O(n!) still possible
- **Typical:** Exponential but much better than naive

### Example: 4-city TSP
```
Without B&B: 3! = 6 permutations explored
With B&B: 2-3 permutations explored (strong pruning)
```

---

## 🎲 2. Eight Puzzle / 15 Puzzle

### Problem
```
1 2 3       1 2 3
4 5 6   →   4 5 6
7 8 _       7 8 9
```

Slide tiles to reach goal state, minimizing moves.

### Branch & Bound Approach

#### Search State
- Current tile configuration
- Number of moves so far

#### Bound Calculation
**A* algorithm** uses:
```
f(node) = g(node) + h(node)

where:
  g(node) = actual moves so far
  h(node) = estimated moves to goal (heuristic)
```

#### Heuristics for Puzzle
1. **Manhattan Distance** (preferred)
   ```
   Sum of distances each tile must move
   Admissible: never overestimates
   ```

2. **Hamming Distance**
   ```
   Count of misplaced tiles
   Less informative but faster to compute
   ```

#### Example
```
Current State:        Goal:
1 2 3                1 2 3
4 5 6                4 5 6
7 _ 8                7 8 9

Manhattan Distance:
- 8 at position (2,2), needs to be (2,1): distance 1
- 9 missing at position (2,2), goal at (2,2): distance 0
- Total: 1
```

### Algorithm Flow
```
1. Calculate f = g + h for initial state
2. Add to priority queue sorted by f
3. Pop state with smallest f
4. If goal, return solution
5. Generate children (move blank up/down/left/right)
6. Calculate f for each child
7. Prune states with f ≥ bestCost
8. Repeat from step 3
```

### Complexity
- **Space:** O(states) - need to store visited states
- **Time:** Depends heavily on heuristic quality

---

## 📦 3. Rectangle Placement Problem

### Problem
Place rectangles on a board maximizing area coverage (or minimizing waste).

**Constraints:**
- Each rectangle placed once
- No overlaps allowed
- Must fit within bounds

### Branch & Bound Solution

#### State Representation
- Which rectangles placed
- Where each is placed
- Remaining space

#### Bound Calculation
```
Current waste = Board area - Placed area
Remaining waste ≥ Current waste (no improvement possible)

If Current waste ≥ Best waste found:
  → Prune this branch
```

#### Branching Strategy
```
For each unplaced rectangle:
  Try placing in each valid position
    Create new state
    Calculate bound
    If promising, add to queue
```

### Optimization Techniques
1. **Position Heuristic:** Try corners first
2. **Rectangle Ordering:** Large rectangles first
3. **Constraint Propagation:** Shrink search space
4. **Symmetry Breaking:** Avoid exploring equivalent states

---

## 🎯 When to Use Branch & Bound

### ✅ Good For
- **Optimization problems:** Find minimum/maximum
- **Constrained:** Clear bounds exist
- **Medium-sized:** n ≤ 20-30 typically
- **Tight bounds:** When bounding function is effective

### ❌ Not Good For
- **Large n:** Exponential growth unstoppable
- **Loose bounds:** Pruning ineffective
- **No clear cost function:** Can't calculate bounds
- **Real-time:** Unpredictable time requirements

---

## ⚡ Optimization Techniques

### 1. **Tighter Bounding Functions**
Better bounds → more pruning → faster

**Trade-off:** Computation cost vs pruning benefit

### 2. **Best-First Search**
Explore most promising branches first
- Uses priority queue by bound value
- Often finds good solutions early
- Can prune more branches

### 3. **Depth-First with Backtracking**
Memory efficient for large problems
- Trades depth for space
- Still prunes using bounds

### 4. **Constraint Propagation**
Reduce search space before branching
```
Example:
  If rectangle R can't fit in remaining space:
    → Remove R from consideration
    → Reduces branching factor
```

### 5. **Variable Ordering**
Choose which variable to branch on first
- Often affects pruning effectiveness
- Problem-specific strategy

### 6. **Node Ordering**
Explore children in optimal order
- Try most promising first
- Better solutions found early

---

## 📊 Comparison: Optimization Algorithms

| Algorithm | B&B | DP | Greedy | Backtrack |
|-----------|-----|----|---------| ---------|
| **Optimal** | ✅ Yes | ✅ Yes | ❌ Maybe | ✅ Yes |
| **Exponential** | Sometimes | No | No | Always |
| **Memory** | O(bound) | O(n²) | O(1) | O(n) |
| **Best For** | Optimization | Overlapping | Fast/Heuristic | All solutions |

---

## 🧪 Implementation Considerations

### State Representation
```java
class SearchNode {
    double costSoFar;      // Actual cost accumulated
    double boundValue;     // Upper/lower bound
    SolutionState state;   // Partial solution
    SearchNode parent;     // For reconstructing path
}
```

### Queue Management
```java
// Best-first: Sort by bound
PriorityQueue<SearchNode> queue = 
    new PriorityQueue<>(Comparator.comparingDouble(n -> n.boundValue));

// Depth-first: Use Stack
Stack<SearchNode> stack = new Stack<>();

// Breadth-first: Use Queue
Queue<SearchNode> queue = new LinkedList<>();
```

### Visited State Tracking
```java
Set<State> visited = new HashSet<>();

if (visited.contains(node.state)) {
    continue;  // Skip repeated state
}
visited.add(node.state);
```

---

## 💡 Advanced Topics

### 1. **A* Algorithm**
Special case of B&B for pathfinding
```
Used in: GPS, game AI, robotics
Better heuristic → faster solution
```

### 2. **Integer Linear Programming**
B&B used in commercial solvers
```
Example: CPLEX uses B&B internally
```

### 3. **Cutting Planes**
Add constraints to tighten bounds
```
Advanced technique for combinatorial optimization
```

---

## 🔗 Branch & Bound in This Module

### Implemented Problems
- **`EightPuzzle.java`** - Sliding puzzle solver
- **`RectanglesPlacement.java`** - Rectangle packing
- **`RectanglesPlacementThreads.java`** - Parallel version
- **`AgentsTasks.java`** - Task assignment

### Utility Classes
- **`Node.java`** - Search tree node
- **`Heap.java`** - Priority queue for best-first search
- **`BranchAndBound.java`** - Generic B&B framework

---

## 🧪 Testing Branch & Bound

### Verification
```java
void testEightPuzzle() {
    State initial = createInitialState();
    State goal = createGoalState();
    
    Solution solution = solvePuzzle(initial, goal);
    
    // Verify it's actually a solution
    assertTrue(isSolvable(initial, goal));
    assertTrue(solution.isValid());
    
    // Verify optimality (if provable)
    int optimalMoves = 14;  // Known for this puzzle
    assertEquals(optimalMoves, solution.moveCount);
}
```

### Performance Metrics
- **Nodes explored:** Fewer is better (good pruning)
- **Time to solution:** Better with good heuristics
- **Optimality:** Verify known answers
- **Memory usage:** Track queue/visited size

---

## 📚 Practical Applications

### Real-World Uses
- **Circuit Design:** Component placement
- **Scheduling:** Job scheduling with constraints
- **Logistics:** Vehicle routing (TSP variant)
- **Game AI:** Optimal move selection
- **Robotics:** Path planning
- **Compiler Optimization:** Register allocation

---

## 📖 Further Reading

- CLRS - "Introduction to Algorithms" - Chapter 32 (NP-completeness)
- **Russell & Norvig** - "Artificial Intelligence" - A* search
- **Papadimitriou & Steiglitz** - "Combinatorial Optimization"
- [Wikipedia - Branch and Bound](https://en.wikipedia.org/wiki/Branch_and_bound)

---

## 🎓 Key Takeaways

1. **B&B = Systematic search + smart pruning**
2. **Bounds are critical** - better bounds = faster
3. **Search order matters** - best-first usually best
4. **Not always optimal time** - but better than naive
5. **Problem-dependent** - effectiveness varies
6. **Heuristics key** - good h(n) crucial for A*
