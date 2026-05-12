# Divide & Conquer Algorithms

A powerful problem-solving paradigm that breaks problems into subproblems, solves them independently, and combines results.

---

## 🎯 Core Principle

**Divide & Conquer** solves problems by:

1. **DIVIDE:** Break the problem into smaller subproblems of the same type
2. **CONQUER:** Solve subproblems recursively or directly if small
3. **COMBINE:** Merge subproblem solutions into final answer

---

## 📐 Mathematical Framework

For recurrence relation: **T(n) = a·T(n/b) + f(n)**

### Master Theorem
Gives tight asymptotic bounds:

- If **f(n) = O(n^(log_b(a) - ε))** → **T(n) = Θ(n^log_b(a))**
- If **f(n) = Θ(n^log_b(a) · log k(n))** → **T(n) = Θ(n^log_b(a) · log^(k+1)(n))**
- If **f(n) = Ω(n^(log_b(a) + ε))** → **T(n) = Θ(f(n))**

---

## 🔎 1. Binary Search

### Problem
Find an element in a **sorted array** in minimum comparisons

### Approach
- Eliminate half of remaining elements with each comparison
- Compare middle element with target
- Recursively search appropriate half

### Implementation Variants
1. **Iterative:** Using while loop, O(1) space
2. **Recursive:** Cleaner code, O(log n) space for recursion stack

### Code Pattern
```java
// Recursive version
int binarySearch(int[] arr, int target, int left, int right) {
    if (left > right) return -1; // Not found
    
    int mid = (left + right) / 2;
    if (arr[mid] == target) return mid;
    else if (arr[mid] > target) 
        return binarySearch(arr, target, left, mid-1);
    else 
        return binarySearch(arr, target, mid+1, right);
}
```

### Complexity
- **Time:** O(log n) - halve search space each step
- **Space:** O(log n) recursive - O(1) iterative

### Precondition
✅ **Array MUST be sorted**

### Real-World Uses
- Database indexing
- Library searches
- Spell checking dictionaries

---

## ➕ 2. Fibonacci Sequence

### Problem
Calculate n-th Fibonacci number: F(n) = F(n-1) + F(n-2)

### Three Approaches Comparison

#### A. Naive Recursion (❌ Don't use for large n)
```java
int fib(int n) {
    if (n <= 1) return n;
    return fib(n-1) + fib(n-2);
}
```
- **Time:** O(2^n) - exponential!
- **Space:** O(n) recursion stack
- **Why Bad:** Recalculates same values constantly

#### B. Divide & Conquer (Memoization)
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
- **Time:** O(n) - each number calculated once
- **Space:** O(n) for memo

#### C. Divide & Conquer (Math Formula)
- Uses matrix exponentiation
- Time: O(log n)
- Space: O(log n)

### Lesson
Identifying **overlapping subproblems** leads to exponential speedup

---

## 🧮 3. Mathematical Problems

### Greatest Common Divisor (GCD)
```
GCD(a, b) = GCD(b, a mod b)
GCD(a, 0) = a
```
- **Time:** O(log min(a,b))
- Uses Euclidean algorithm

### Factorial
```
n! = n × (n-1)!
0! = 1
```
- **Time:** O(n)
- **Space:** O(n) for recursion

### Power (x^n)
#### Naive: O(n)
```java
int power(int x, int n) {
    if (n == 0) return 1;
    return x * power(x, n-1);
}
```

#### Divide & Conquer: O(log n)
```java
int power(int x, int n) {
    if (n == 0) return 1;
    int half = power(x, n/2);
    if (n % 2 == 0) return half * half;
    else return x * half * half;
}
```

---

## 📊 4. Statistical Algorithms

### Binary Search Applications

#### Find Median
- For sorted array: middle element(s)
- Time: O(1) or O(log n) depending on structure

#### Find Mode (Most Frequent Element)
Using divide & conquer:
```
Mode(arr) = 
    left_mode = Mode(left_half)
    right_mode = Mode(right_half)
    center_mode = Mode(center)
    return most frequent of three
```
- **Time:** O(n log n)

#### Find Majoritarian Element
Element appearing > n/2 times
- **Time:** O(n) with divide & conquer
- **Space:** O(log n)

#### Maximum Sum of Subarray
Kadane's algorithm variant:
```
MaxSum(arr, left, right) =
    mid = (left + right) / 2
    left_max = MaxSum(left, mid)
    right_max = MaxSum(mid, right)
    cross_max = max sum crossing midpoint
    return max of three
```
- **Time:** O(n log n)

---

## 🔄 5. Sorting Algorithms

### Mergesort - Classic Divide & Conquer
```
MergeSort(arr):
    if size == 1: return
    mid = size / 2
    MergeSort(left_half)
    MergeSort(right_half)
    Merge(left, right)
```

**Complexity:**
- **Time:** O(n log n) guaranteed
- **Space:** O(n) for merging

### Quicksort - Divide & Conquer with Partitioning
```
QuickSort(arr, left, right):
    if left < right:
        p = Partition(arr)
        QuickSort(left, p-1)
        QuickSort(p+1, right)
```

---

## ⏱️ Time Analysis Patterns

### Linear Search
T(n) = T(n-1) + O(1) → **O(n)**

### Binary Search
T(n) = T(n/2) + O(1) → **O(log n)**

### Mergesort
T(n) = 2·T(n/2) + O(n) → **O(n log n)**

### Quicksort (average)
T(n) = T(n/2) + T(n/2) + O(n) → **O(n log n)**

### Strassen's Matrix Multiply
T(n) = 7·T(n/2) + O(n²) → **O(n^2.81)**
(Better than naive O(n³))

---

## 🎯 Algorithm Decision Matrix

| Problem | Algorithm | Time | Space | Notes |
|---------|-----------|------|-------|-------|
| Sorted search | Binary Search | O(log n) | O(1) or O(log n) | Requires sorted input |
| Fibonacci | Memoization | O(n) | O(n) | Or matrix: O(log n) |
| GCD | Euclidean | O(log n) | O(log n) | Very efficient |
| Power | Exponentiation | O(log n) | O(log n) | Binary exponentiation |
| Sorting | Mergesort | O(n log n) | O(n) | Stable, guaranteed |
| Sorting | Quicksort | O(n log n)avg | O(log n) | Faster in practice |
| Median Find | QuickSelect | O(n)avg | O(1) | Linear average |
| Mode | Voting | O(n) | O(1) | With preprocessing |

---

## 💡 Key Insights

### When to Use Divide & Conquer
✅ Problem has optimal substructure  
✅ Subproblems are independent  
✅ Subproblems are smaller versions of original  
✅ Combining solutions is efficient

### Avoiding Pitfalls
❌ Don't use for problems without clear substructure  
❌ Watch for repeated subproblems (use memoization)  
❌ Be careful with recursion depth (stack overflow)  
❌ Ensure base cases are correct

### Optimization Techniques
1. **Memoization:** Cache subproblem results
2. **Tail Recursion:** Some languages optimize this
3. **Hybrid Approach:** Switch to iteration for small base cases
4. **Parallelization:** Process subproblems in parallel

---

## 🧪 Testing Considerations

### Edge Cases
- Array size = 1
- Empty array
- All same elements
- Reverse sorted
- Alternating patterns
- Very large values (overflow?)
- Very large arrays (stack overflow?)

### Verification
- Correctness: Does it solve the problem?
- Optimality: Is result truly optimal?
- Complexity: Does it match theoretical analysis?
- Stability: Preserves element order?

---

## 📚 Practice Problems

1. **Search:** Implement iterative binary search
2. **Fibonacci:** Compare naive vs memoized vs matrix methods
3. **Peak Element:** Find maximum in mountain array
4. **Inversion Count:** Count pairs (i,j) where i < j but arr[i] > arr[j]
5. **Merge Sorted Arrays:** Divide & conquer approach
6. **Closest Pair Points:** Find nearest pair in 2D plane
7. **Convex Hull:** Graham's scan algorithm

---

## 🔗 Related Topics

- **Dynamic Programming:** Similar breakdown, but different recurrence
- **Greedy Algorithms:** Alternative problem-solving approach
- **Recursion:** Foundation for divide & conquer
- **Sorting:** Classic application area

---

## 📖 Further Reading

- CLRS "Introduction to Algorithms" - Chapters 2, 4
- Sedgewick & Wayne "Algorithms" - Chapter 2.2
- [Khan Academy - Divide & Conquer](https://www.khanacademy.org/)
- [VisuAlgo - Algorithm Visualizations](https://visualgo.net/)
