# Backtracking - Systematic Exploration with Pruning

Master the art of exploring solution spaces efficiently by backing up when necessary.

---

## 🎯 Core Concept

**Backtracking** is a technique for solving problems by:

1. Building solution incrementally, one component at a time
2. Abandoning ("backtracking") when solution path is invalid
3. Exploring all valid complete solutions

### Key Difference from Other Approaches
- **Brute Force:** Explores all possibilities (slow!)
- **Backtracking:** Prunes invalid branches early (smart!)
- **DP:** Stores intermediate results (different structure)
- **Greedy:** Makes local optimal choices (no exploring alternatives)

---

## 📊 Backtracking vs Brute Force

### Example: Permutations of [1, 2, 3]

**Brute Force:** Generate all n! sequences, then check validity

**Backtracking:** Build valid sequences only
```
Start: []
  Try 1: [1]
    Try 2: [1, 2]
      Try 3: [1, 2, 3] ✓ VALID
    Try 3: [1, 3]
      Try 2: [1, 3, 2] ✓ VALID
  Try 2: [2]
    ...and so on
```

**Savings:** Avoid generating invalid sequences from the start

---

## 🏗️ Backtracking Template

### Basic Structure
```java
void backtrack(List<T> current, List<T> remaining) {
    // Base case: found complete solution
    if (current.size() == targetSize) {
        solutions.add(new ArrayList<>(current));
        return;
    }
    
    // Try each remaining option
    for (int i = 0; i < remaining.size(); i++) {
        T option = remaining.get(i);
        
        // Check if this choice is valid
        if (isValid(current, option)) {
            // Include option
            current.add(option);
            
            // Create new remaining list without this option
            List<T> newRemaining = new ArrayList<>(remaining);
            newRemaining.remove(i);
            
            // Explore with this choice
            backtrack(current, newRemaining);
            
            // Backtrack: remove the choice
            current.remove(current.size() - 1);
        }
    }
}
```

### More Efficient Version (Using Index)
```java
void backtrack(int index, List<Integer> current) {
    // Base case
    if (index == n) {
        if (isValidSolution(current)) {
            solutions.add(new ArrayList<>(current));
        }
        return;
    }
    
    // Pruning: check if current partial solution can lead to valid solution
    if (!canExtend(current)) {
        return;  // Prune this branch
    }
    
    for (int choice : getOptions(index)) {
        current.add(choice);
        backtrack(index + 1, current);
        current.remove(current.size() - 1);  // Backtrack
    }
}
```

---

## 1️⃣ N-Queens Problem

### Problem
Place N queens on N×N chessboard such that no two queens attack each other.

**Constraints:** No two queens in same row, column, or diagonal

### Solution Approach
- Place queens row by row
- For each row, try each column
- Prune if placement attacks existing queen

### Implementation
```java
List<List<Integer>> nQueens(int n) {
    List<List<Integer>> solutions = new ArrayList<>();
    backtrack(0, new int[n], solutions, n);
    return solutions;
}

void backtrack(int row, int[] cols, List<List<Integer>> solutions, int n) {
    if (row == n) {
        solutions.add(convertToList(cols));
        return;
    }
    
    for (int col = 0; col < n; col++) {
        if (isValid(row, col, cols)) {
            cols[row] = col;
            backtrack(row + 1, cols, solutions, n);
            // No need to reset; next iteration overwrites
        }
    }
}

boolean isValid(int row, int col, int[] cols) {
    for (int i = 0; i < row; i++) {
        // Same column
        if (cols[i] == col) return false;
        
        // Same diagonal: |row_diff| == |col_diff|
        if (Math.abs(row - i) == Math.abs(col - cols[i])) return false;
    }
    return true;
}
```

### Complexity
- **Time:** O(N!) in worst case (all valid solutions)
- **Space:** O(N) for recursion depth + solution space

### Key Insight
Pruning eliminates vast majority of branches:
- All permutations: 8! = 40,320
- Valid n-queens solutions: 92
- Speedup: ~400x!

---

## 🔄 2. Permutations

### Problem
Generate all permutations of n elements

### Simple Approach
```java
void permute(int[] nums, int start, List<List<Integer>> solutions) {
    if (start == nums.length - 1) {
        solutions.add(convertToList(nums));
        return;
    }
    
    for (int i = start; i < nums.length; i++) {
        // Swap
        swap(nums, start, i);
        
        // Recurse
        permute(nums, start + 1, solutions);
        
        // Backtrack: restore
        swap(nums, start, i);
    }
}
```

### Complexity
- **Time:** O(N!) - N! permutations
- **Space:** O(N) recursion depth

### Use Case
- Arrange N distinct items
- Find all orderings of sequence
- Brute-force testing of orders

---

## 📦 3. Subsets (Power Set)

### Problem
Generate all subsets of a set

**Example:** {1, 2, 3} → {}, {1}, {2}, {3}, {1,2}, {1,3}, {2,3}, {1,2,3}

### Backtracking Approach
```java
void subsets(int[] nums, int start, List<Integer> current, List<List<Integer>> solutions) {
    // Add current subset at each step (not just at end!)
    solutions.add(new ArrayList<>(current));
    
    for (int i = start; i < nums.length; i++) {
        // Include current element
        current.add(nums[i]);
        
        // Explore subsets with this element
        subsets(nums, i + 1, current, solutions);
        
        // Backtrack
        current.remove(current.size() - 1);
    }
}
```

### Complexity
- **Time:** O(N × 2^N) - N choices per 2^N subsets
- **Space:** O(2^N) for storing results

---

## 💰 4. Subset Sum

### Problem
Find all subsets that sum to target value

**Example:** 
```
Array: [3, 1, 2]
Target: 3
Result: [3], [1, 2]
```

### Implementation
```java
void subsetSum(int[] nums, int target, int start, 
               List<Integer> current, int currentSum, 
               List<List<Integer>> solutions) {
    // Check if current subset sums to target
    if (currentSum == target) {
        solutions.add(new ArrayList<>(current));
        return;  // Backtrack immediately
    }
    
    // Prune: if sum exceeded, stop
    if (currentSum > target) {
        return;
    }
    
    for (int i = start; i < nums.length; i++) {
        current.add(nums[i]);
        subsetSum(nums, target, i + 1, current, currentSum + nums[i], solutions);
        current.remove(current.size() - 1);
    }
}
```

### Key Optimization
Stop exploring when currentSum > target (pruning!)

### Complexity
- **Time:** O(2^N) worst case
- **Space:** O(N) recursion depth

---

## 🐴 5. Knight's Tour Problem

### Problem
Move chess knight to visit all squares exactly once

### Backtracking Approach
1. Place knight on board
2. Try all 8 possible knight moves
3. If move valid and square unvisited: move there
4. If stuck: backtrack and try different move

### Complexity
- Very expensive: O(8^N) worst case
- Heavy pruning reduces actual time significantly
- Heuristics: Warnsdorff's heuristic helps

---

## 🏰 6. Sudoku Solver

### Problem
Fill 9×9 grid with digits 1-9 respecting constraints

**Constraints:**
- Each row: digits 1-9 appear once
- Each column: digits 1-9 appear once  
- Each 3×3 box: digits 1-9 appear once

### Approach
```java
boolean solveSudoku(int[][] board) {
    // Find empty cell
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            if (board[i][j] == 0) {
                // Try digits 1-9
                for (int digit = 1; digit <= 9; digit++) {
                    if (isValid(board, i, j, digit)) {
                        board[i][j] = digit;
                        
                        if (solveSudoku(board)) {
                            return true;  // Found solution
                        }
                        
                        board[i][j] = 0;  // Backtrack
                    }
                }
                return false;  // No valid digit
            }
        }
    }
    return true;  // All cells filled
}

boolean isValid(int[][] board, int row, int col, int digit) {
    // Check row
    for (int j = 0; j < 9; j++) {
        if (board[row][j] == digit) return false;
    }
    
    // Check column
    for (int i = 0; i < 9; i++) {
        if (board[i][col] == digit) return false;
    }
    
    // Check 3x3 box
    int boxRow = row - row % 3;
    int boxCol = col - col % 3;
    for (int i = boxRow; i < boxRow + 3; i++) {
        for (int j = boxCol; j < boxCol + 3; j++) {
            if (board[i][j] == digit) return false;
        }
    }
    
    return true;
}
```

### Complexity
- Highly depends on initial state
- Well-constrained puzzles: very fast
- Worst case: exponential

---

## 🎨 Backtracking in This Module

### Implemented Problems
- **`Permutations.java`** - All permutations
- **`SubsetsGivenSum.java`** - Subsets summing to target
- **`ChessQueensOne.java`** - Find one N-queens solution
- **`ChessQueensAll.java`** - Find all N-queens solutions
- **`ChessHorseOne.java`** - Find one knight tour
- **`ChessHorseAll.java`** - Find all knight tours
- **`AgentsTasksTimes.java`** - Task assignment

---

## ✅ Decision: Backtracking vs Alternatives

| Problem Type | Use Backtracking? | Why |
|--------------|------------------|-----|
| All permutations | ✅ Yes | Natural fit |
| All subsets | ✅ Yes | Natural fit |
| N-Queens | ✅ Yes | Heavy pruning helps |
| Sudoku | ✅ Yes | Constraint satisfaction |
| Find one solution | ✅ Maybe | If constraints exist |
| Shortest path | ❌ No | Use BFS/Dijkstra |
| Optimal value | ❌ No | Use DP/Greedy |

---

## ⚡ Optimization Techniques

### 1. Constraint Checking (Pruning)
Check constraints BEFORE recursing, not after

### 2. Memoization
Cache partial results (though memory-intensive)

### 3. Ordering
Try most constrained options first (reduces branching)

### 4. Heuristics
- **N-Queens:** Placement strategies
- **Sudoku:** Assign cell with fewest options
- **Knight's Tour:** Warnsdorff's heuristic

### 5. Bitmask Optimization
Use bitmasks instead of Sets for faster membership checks

---

## 🧪 Testing Backtracking Solutions

### Verification
- Count: Is number of solutions correct?
- Validity: Does each solution meet constraints?
- Completeness: Did we find all solutions?

### Example
```java
void testNQueens() {
    List<List<String>> solutions = nQueens(4);
    assertEquals(2, solutions.size());  // 4-queens has 2 solutions
    
    for (List<String> solution : solutions) {
        assertTrue(isValidBoard(solution));
    }
}
```

---

## 📚 Time Complexity Summary

| Problem | Complexity | Notes |
|---------|-----------|-------|
| Permutations | O(N × N!) | Generate N! solutions |
| Power Set | O(N × 2^N) | Generate 2^N subsets |
| N-Queens | O(N!) worst | Heavy pruning in practice |
| Sudoku | Variable | Depends on puzzle |
| Subset Sum | O(2^N) worst | Pruning helps significantly |

---

## 💡 When Backtracking Shines

✅ **Constraint Satisfaction:** Puzzles, scheduling  
✅ **Combinatorial Problems:** Permutations, combinations  
✅ **Path Finding:** When all paths needed  
✅ **Heavy Pruning Possible:** Reduces exponential to practical  

❌ **Large N:** Exponential growth kills performance  
❌ **Continuous Spaces:** Only for discrete problems  
❌ **When One Solution Sufficient:** Branch-and-bound better  

---

## 🚀 Advanced Topics

- **Constraint Programming:** Systematic approach with constraint propagation
- **Exact Algorithms:** For NP-hard problems
- **Approximation Algorithms:** When exact is too slow

---

## 📖 Further Reading

- CLRS - "Introduction to Algorithms" - Chapter 22 (Graph-based)
- Sedgewick & Wayne - "Algorithms" - Chapter 6
- [VisuAlgo - Backtracking](https://visualgo.net/)
- LeetCode - Backtracking Tag (Medium-Hard)

---

## 🎓 Key Takeaways

1. **Backtracking = DFS with constraint checking**
2. **Pruning is critical** for performance
3. **Validate constraints early** to avoid wasted exploration
4. **Know base cases:** When to record solutions
5. **Restore state:** Essential for trying alternatives
6. **Test completeness:** Did we find all solutions?
