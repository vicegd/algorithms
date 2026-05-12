# Dynamic Programming - Optimization Through Subproblems

Master the art of solving complex optimization problems by breaking them into overlapping subproblems and storing results.

---

## 🎯 What is Dynamic Programming?

**Dynamic Programming (DP)** is an optimization technique for problems with:

1. **Optimal Substructure:** Optimal solution built from optimal subproblem solutions
2. **Overlapping Subproblems:** Same subproblems solved multiple times

**Key Difference from Divide & Conquer:**
- D&C: Independent subproblems (solve once)
- DP: Overlapping subproblems (solve & store)

---

## 📊 Two Approaches

### 1. Top-Down (Memoization)
```java
// Recursive with caching
Map<Integer, Integer> memo = new HashMap<>();

int solve(int n) {
    if (memo.containsKey(n)) return memo.get(n);
    
    // Base case
    if (n <= 1) return base;
    
    // Recursive case with memoization
    int result = solve(n-1) + solve(n-2);
    memo.put(n, result);
    return result;
}
```

**Pros:** Natural recursive thinking  
**Cons:** Stack overhead, harder to optimize

### 2. Bottom-Up (Tabulation)
```java
// Iterative with table building
int[] dp = new int[n+1];

dp[0] = base1;
dp[1] = base2;

for (int i = 2; i <= n; i++) {
    dp[i] = dp[i-1] + dp[i-2];
}
return dp[n];
```

**Pros:** No recursion overhead, can optimize space  
**Cons:** Must compute all states

---

## 1️⃣ Fibonacci Sequence

### Naive Recursion ❌
```java
int fib(int n) {
    if (n <= 1) return n;
    return fib(n-1) + fib(n-2);
}
```
- **Time:** O(2^n) - exponential!
- **Calls:** fib(5) makes 15 calls, fib(30) makes millions
- **Problem:** Recalculates fib(3) hundreds of times

### With Memoization ✅
```java
Map<Integer, Integer> memo = new HashMap<>();

int fib(int n) {
    if (memo.containsKey(n)) return memo.get(n);
    if (n <= 1) return n;
    
    int result = fib(n-1) + fib(n-2);
    memo.put(n, result);
    return result;
}
```
- **Time:** O(n) - each n calculated once
- **Space:** O(n) for memo + O(n) for recursion stack
- **Each n calculated:** Exactly once

### With Tabulation ✅
```java
int fib(int n) {
    if (n <= 1) return n;
    
    int[] dp = new int[n+1];
    dp[0] = 0;
    dp[1] = 1;
    
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
}
```
- **Time:** O(n)
- **Space:** O(n) for array
- **No recursion overhead**

### Space Optimization
```java
// Only need last two values
int fib(int n) {
    if (n <= 1) return n;
    int prev2 = 0, prev1 = 1;
    
    for (int i = 2; i <= n; i++) {
        int current = prev1 + prev2;
        prev2 = prev1;
        prev1 = current;
    }
    return prev1;
}
```
- **Time:** O(n)
- **Space:** O(1) ✨

### Performance Comparison
| n | Naive | Memoized | Tabulated | Optimized |
|---|-------|----------|-----------|-----------|
| 30 | 1,000,000 ops | 30 ops | 30 ops | 30 ops |
| 40 | Timeout | 40 ops | 40 ops | 40 ops |

---

## 2️⃣ 0/1 Knapsack Problem

### Problem Statement
Given:
- **n items** with weight w[i] and value v[i]
- **Knapsack capacity W**

Find: Maximum value subset fitting in capacity

Constraint: Can't take fraction of item (0/1)

### Why Greedy Fails
Items: (weight, value)
- A: (10, 60) → ratio 6
- B: (20, 100) → ratio 5
- C: (30, 120) → ratio 4
- Capacity: 50

**Greedy by ratio:** A + B = 160, but capacity exceeded!  
**Optimal:** B + C = 220 ✓

### Dynamic Programming Solution

#### Recurrence Relation
```
dp[i][w] = max value using first i items with capacity w

dp[i][w] = max(
    dp[i-1][w],              // Don't take item i
    v[i] + dp[i-1][w-w[i]]   // Take item i
)
```

#### Implementation
```java
int knapsack(int[] weights, int[] values, int capacity) {
    int n = weights.length;
    int[][] dp = new int[n+1][capacity+1];
    
    // Build table
    for (int i = 1; i <= n; i++) {
        for (int w = 0; w <= capacity; w++) {
            if (weights[i-1] <= w) {
                dp[i][w] = Math.max(
                    dp[i-1][w],
                    values[i-1] + dp[i-1][w-weights[i-1]]
                );
            } else {
                dp[i][w] = dp[i-1][w];
            }
        }
    }
    
    return dp[n][capacity];
}
```

### Complexity
- **Time:** O(n × W) where W = capacity
- **Space:** O(n × W) → can optimize to O(W)

### Reconstructing Solution
Track which items were selected:
```java
// From bottom-right, trace back
int w = capacity;
for (int i = n; i > 0; i--) {
    if (dp[i][w] != dp[i-1][w]) {
        selected[i-1] = true;  // Item included
        w -= weights[i-1];
    }
}
```

---

## 💰 3. Coin Change Problem

### Problem
Find minimum coins to make amount X

Example:
- Coins: [1, 5, 10, 25]
- Amount: 30
- Answer: [25, 5] = 2 coins

### Greedy Fails For Some Cases
Coins: [1, 3, 4], Amount: 6
- **Greedy:** 4 + 1 + 1 = 3 coins ❌
- **Optimal:** 3 + 3 = 2 coins ✓

### DP Solution
```
dp[i] = minimum coins to make amount i
dp[0] = 0
dp[i] = min(dp[i-coin] + 1) for all coins ≤ i
```

### Code
```java
int minCoins(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    dp[0] = 0;
    
    for (int i = 1; i <= amount; i++) {
        dp[i] = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= i && dp[i-coin] != Integer.MAX_VALUE) {
                dp[i] = Math.min(dp[i], dp[i-coin] + 1);
            }
        }
    }
    
    return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
}
```

### Complexity
- **Time:** O(amount × n) where n = number of coins
- **Space:** O(amount)

---

## 🧮 4. Combinations & Permutations

### Combinations: C(n,k) - Choose k from n
```
C(n,k) = C(n-1,k-1) + C(n-1,k)
C(n,0) = 1
C(n,n) = 1
```

### DP Implementation
```java
int combinations(int n, int k) {
    int[][] dp = new int[n+1][k+1];
    
    for (int i = 0; i <= n; i++) {
        dp[i][0] = 1;
        if (i <= k) dp[i][i] = 1;
    }
    
    for (int i = 2; i <= n; i++) {
        for (int j = 1; j < i && j <= k; j++) {
            dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
        }
    }
    
    return dp[n][k];
}
```

### Pascal's Triangle
```
    1
   1 1
  1 2 1
 1 3 3 1
1 4 6 4 1
```

Each value = C(n, k)

---

## 🚣 5. River Travel Optimization

### Problem
- Start at town 0, go to town n
- Canoe rental at each town
- Travel from i to j costs money
- Find minimum cost path

### Solution Approach
```
dp[i] = minimum cost to reach town i
dp[0] = 0
dp[i] = min(dp[j] + cost[j][i]) for all j < i
```

---

## 🎓 DP Problem-Solving Framework

### Step 1: Define State
What does `dp[...]` represent?
- Example: "Minimum cost to reach position i"

### Step 2: Find Recurrence
How does current state relate to previous states?
- dp[i] = f(dp[i-1], dp[i-2], ...)

### Step 3: Identify Base Cases
What are the simplest cases?
- dp[0], dp[1], etc.

### Step 4: Choose Implementation
- Top-Down (Memoization): Natural recursion
- Bottom-Up (Tabulation): More efficient

### Step 5: Optimize
- Can you reduce space complexity?
- Can you simplify the recurrence?

---

## 📊 Classic DP Problems Matrix

| Problem | State | Recurrence | Time | Space |
|---------|-------|-----------|------|-------|
| Fibonacci | dp[i] | dp[i]=dp[i-1]+dp[i-2] | O(n) | O(1) |
| Coin Change | dp[i] | dp[i]=min(dp[i-c]+1) | O(nk) | O(n) |
| Knapsack | dp[w] | dp[w]=max(...) | O(nW) | O(W) |
| LCS | dp[i][j] | dp[i][j]=... | O(nm) | O(nm) |
| Edit Distance | dp[i][j] | dp[i][j]=min(...) | O(nm) | O(nm) |

---

## ⚠️ Common Pitfalls

❌ **Overlapping subproblems?** Then DP helps  
❌ **No optimal substructure?** DP won't work  
❌ **Not memoizing?** Still solving repeatedly  
❌ **Wrong base case?** Cascading errors  
❌ **Array bounds?** Off-by-one errors  

---

## 🧪 Testing Strategy

- **Small cases:** Verify by hand
- **Base cases:** n=0, n=1, n=2
- **Boundary:** n at limits
- **Performance:** Compare with naive solution

---

## 📚 Learning Resources

- CLRS - "Introduction to Algorithms" Chapters 14-15
- Sedgewick & Wayne - "Algorithms" Chapter 1.3
- [DP Visualizations](https://visualgo.net/)
- LeetCode - DP Problems (Solve 20-30 to master)

---

## 💡 Pro Tips

1. **Start Simple:** Solve with recursion first, then memoize
2. **Draw Table:** Visualize dp table for bottom-up
3. **Trace Example:** Work through small example by hand
4. **Optimize Later:** Get correct solution first
5. **Space Optimization:** After working solution, then optimize
