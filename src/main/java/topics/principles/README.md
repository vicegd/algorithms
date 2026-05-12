# Principles of Algorithms & Data Structures

## Foundations for Algorithm Design 🏗️

This section covers the **fundamental building blocks** and **principles** you need before diving into advanced algorithms. Master these concepts and the rest becomes much easier.

---

## Table of Contents

1. [What Are Principles?](#what-are-principles)
2. [Core Data Structures](#core-data-structures)
3. [Search Algorithms](#search-algorithms)
4. [Recursion](#recursion)
5. [Problem-Solving Patterns](#problem-solving-patterns)
6. [Code Quality Principles](#code-quality-principles)
7. [Learning Strategy](#learning-strategy)

---

## What Are Principles?

**Principles** are timeless, proven concepts that guide algorithm design. They're:
- ✅ Independent of language or hardware
- ✅ Reusable across many problems
- ✅ Foundation for advanced techniques
- ✅ Essential for understanding optimizations

```
Principle (Abstract) → Pattern (Reusable) → Algorithm (Specific Solution)
```

---

## Core Data Structures

### **1. Arrays (Lists)**

**Properties:**
- Sequential storage in memory
- Fixed size (in most languages)
- O(1) access by index
- O(n) insertion/deletion in middle

```java
Array: [10, 20, 30, 40, 50]
Index:  0   1   2   3   4

Access element at index 2: array[2] → 30 (instant!)
Insert 25 at index 2: [10, 20, 25, 30, 40, 50] (requires shift)
```

**When to use:**
- Need fast random access
- Know size in advance
- Memory is not critical

**When NOT to use:**
- Frequent insertions/deletions in middle
- Size changes frequently

### **2. Linked Lists**

**Properties:**
- Nodes connected by references/pointers
- Dynamic size
- O(n) access by index
- O(1) insertion/deletion once position found

```
LinkedList: [10] → [20] → [30] → [40] → [50] → null
             ↑     ↑      ↑      ↑      ↑
           Node  Node   Node   Node   Node

Insert 25 between 20 and 30:
[10] → [20] → [25] → [30] → [40] → [50] → null
             (just update pointers!)
```

**When to use:**
- Frequent insertions/deletions
- Don't need random access
- Size varies significantly

**When NOT to use:**
- Need fast random access
- Memory is limited (extra pointer overhead)

### **3. Stack (LIFO - Last In First Out)**

**Operations:**
- push(element) - add to top
- pop() - remove from top
- peek() - view top without removing
- isEmpty()

```
Push sequence: 1, 2, 3
Stack: [1, 2, 3] ← top
Pop order: 3, 2, 1
```

**Real-world examples:**
- Browser back button history
- Undo/redo in editors
- Function call stack
- Expression parsing

**Implementation:** Array or LinkedList

### **4. Queue (FIFO - First In First Out)**

**Operations:**
- enqueue(element) - add to back
- dequeue() - remove from front
- peek() - view front
- isEmpty()

```
Enqueue: 1, 2, 3
Queue: [1, 2, 3]
       ↑        ↑
      front    back
Dequeue order: 1, 2, 3
```

**Real-world examples:**
- Print job scheduling
- Customer service (first come, first served)
- Message processing
- Breadth-first search

**Implementation:** Array (circular) or LinkedList

### **5. Hash Table (Dictionary/Map)**

**Properties:**
- Key-value pairs
- O(1) average access
- O(n) worst case (collisions)
- Hash function determines placement

```
Hash Table with 10 slots:
Key: "name"   → Hash(key) = 3 → Stored at index 3
Key: "age"    → Hash(key) = 7 → Stored at index 7
Key: "email"  → Hash(key) = 3 → Collision! (linear probing)

Get "name":    Compute hash, look at index 3 → instant!
```

**When to use:**
- Need fast lookups by key
- Key-value relationships
- Caching

**When NOT to use:**
- Need ordering
- Keys are strings with poor hash function
- Memory is very limited

### **6. Tree Structures**

**Binary Tree:** Each node has ≤ 2 children

```
        1
       / \
      2   3
     / \
    4   5
```

**Properties:**
- Root: top node
- Leaf: node with no children
- Height: longest path to leaf
- Level/Depth: distance from root

**Special Trees:**
- **Binary Search Tree (BST)**: Left < Parent < Right (enables fast search)
- **Balanced Tree**: Height is O(log n)
- **Heap**: Partially ordered (fast min/max)

**When to use:**
- Hierarchical data
- Binary search (BST)
- Priority queues (heap)

---

## Search Algorithms

### **Linear Search (Sequential Search)**

**Algorithm:** Start at beginning, check each element

```
Search for 25 in [10, 20, 25, 30, 40]:
Check index 0: 10 ≠ 25
Check index 1: 20 ≠ 25
Check index 2: 25 = 25 ✓ Found!
```

**Complexity:**
- Time: O(n) always
- Space: O(1)

**Advantages:**
- Works on unsorted data
- Simple to implement
- Good for small arrays

**Disadvantages:**
- Slow for large arrays
- Checks unnecessary elements

**File:** `Search.java`

### **Binary Search**

**Prerequisite:** Data must be sorted!

**Algorithm:** Divide search space in half each iteration

```
Search for 25 in [10, 15, 20, 25, 30, 35, 40]:
                            ↑ middle
Check middle: 25 = 25 ✓ Found!

Search for 23 in [10, 15, 20, 25, 30, 35, 40]:
Compare 23 vs 25 (middle) → 23 < 25, search left half
Compare 23 vs 15 (middle of left) → 23 > 15, search right half
Compare 23 vs 20 (middle of right) → 23 > 20, search right half
Compare 23 vs 25 (next middle) → Not equal, search ended
Result: Not found
```

**Complexity:**
- Time: O(log n) even for 1 billion elements!
- Space: O(1) or O(log n) if recursive

**Advantages:**
- Extremely fast
- Works even on huge datasets
- Elegant algorithm

**Disadvantages:**
- Requires sorted data
- Cost of sorting might outweigh benefit

**Example:**
```
For 1,000,000 elements:
Linear: ~500,000 comparisons average
Binary: ~20 comparisons maximum
Speedup: 25,000x faster!
```

---

## Recursion

**Recursion:** A function calling itself with a simpler version of the problem

### **Structure of Recursive Functions**

```java
void recursiveFunction(input) {
    // Base case: stop recursion
    if (input satisfies base condition) {
        return result;
    }
    
    // Recursive case: break into smaller problem
    smaller_result = recursiveFunction(input - 1);
    
    // Combine results
    return combine(smaller_result);
}
```

### **Key Example: Factorial**

**Definition:**
```
0! = 1                          (base case)
n! = n × (n-1)!   for n > 0    (recursive case)

Example:
5! = 5 × 4!
   = 5 × (4 × 3!)
   = 5 × (4 × (3 × 2!))
   = 5 × (4 × (3 × (2 × 1!)))
   = 5 × (4 × (3 × (2 × 1)))
   = 120
```

**Recursive Code:**
```java
int factorial(int n) {
    if (n == 0) return 1;           // Base case
    return n * factorial(n - 1);    // Recursive case
}
```

**Call Stack Visualization:**
```
factorial(5)
  5 * factorial(4)
    4 * factorial(3)
      3 * factorial(2)
        2 * factorial(1)
          1 * factorial(0)
            return 1
          return 1 * 1 = 1
        return 2 * 1 = 2
      return 3 * 2 = 6
    return 4 * 6 = 24
  return 5 * 24 = 120
```

**File:** `Factorial.java`

### **Recursion vs Iteration**

| Aspect | Recursion | Iteration |
|--------|-----------|-----------|
| **Readability** | Often cleaner | More verbose |
| **Speed** | Slower (function overhead) | Faster |
| **Space** | O(depth) call stack | O(1) extra space |
| **Risk** | Stack overflow on deep calls | None |
| **Elegance** | Natural for some problems | Good for loops |

**Example Problem that Benefits from Recursion:**
- Tree traversal
- Backtracking (N-Queens)
- Divide & conquer
- Permutations/combinations

**Example Problem Better with Iteration:**
- Simple loops
- Performance-critical code
- Very deep recursion possible

---

## Problem-Solving Patterns

### **Pattern 1: Maximum/Minimum**

**Problem:** Find the largest element in an array

**Solution Strategy:**
```
1. Keep a variable: current_max = first element
2. Iterate through remaining elements
3. If element > current_max, update current_max
4. Return current_max
```

**File:** `GetMaximumFromList.java`

**Complexity:** O(n), O(1) space

**Variants:**
- Find minimum (same approach, change comparison)
- Find k largest elements
- Find median
- Find second largest (without sorting)

### **Pattern 2: Aggregation/Sum**

**Problem:** Sum all elements in an array

**Solution Strategy:**
```
1. Initialize sum = 0
2. For each element, add to sum
3. Return sum
```

**File:** `GetAdditionFromList.java`

**Complexity:** O(n), O(1) space

**Variants:**
- Product of elements
- Count of elements matching condition
- Average value

### **Pattern 3: Search**

**Problem:** Find if element exists in array

**Solution Strategy:**
```
For unsorted: Linear search O(n)
For sorted: Binary search O(log n)
```

**File:** `Search.java`

**Variants:**
- Find position (index) not just existence
- Find all occurrences
- Find closest value

---

## Code Quality Principles

### **1. Correctness First**

**Rule:** A fast wrong algorithm is worse than a slow correct one

```
✓ Test with examples
✓ Test edge cases (empty, single element, duplicates)
✓ Verify against known correct solution
✓ Use assertions
```

### **2. Clarity Over Cleverness**

**Bad (clever but confusing):**
```java
return a^b^((a^b)<<(1&~c));  // What does this do?
```

**Good (clear intent):**
```java
int max = a > b ? a : b;     // Obviously finds maximum
return max;
```

### **3. Fail Fast**

**Bad (silently fails):**
```java
int result = array[index];  // What if index is out of bounds?
```

**Good (explicit validation):**
```java
if (index < 0 || index >= array.length) {
    throw new IndexOutOfBoundsException("Invalid index: " + index);
}
int result = array[index];
```

### **4. Single Responsibility Principle**

**Bad (does too much):**
```java
void processData(int[] data) {
    // Read from file
    // Parse data
    // Validate
    // Sort
    // Calculate statistics
    // Write results
}
```

**Good (one responsibility each):**
```java
int[] readData() { /* read file */ }
void validateData(int[] data) { /* validate */ }
void sortData(int[] data) { /* sort */ }
Statistics calculateStats(int[] data) { /* stats */ }
```

---

## Learning Strategy

### **The Bottom-Up Approach**

This is why you start here with principles:

```
Level 4: Complex Algorithms (Graph, String, Geometry)
         ↑ Requires understanding of
Level 3: Advanced Techniques (DP, Greedy, Backtracking)
         ↑ Requires understanding of
Level 2: Fundamental Algorithms (Search, Sort, Tree)
         ↑ Requires understanding of
Level 1: Principles & Data Structures (Arrays, Lists, Recursion)
```

Without mastering Level 1, Level 2-4 seem like magic!

### **Recommended Study Order**

1. **Master this section first** (2-3 weeks)
   - Arrays and LinkedLists
   - Stacks and Queues
   - Linear and Binary Search
   - Recursion fundamentals

2. **Then move to Sorting** (1-2 weeks)
   - Apply search knowledge
   - Learn loop patterns
   - Understand Big-O in practice

3. **Then Divide & Conquer** (1-2 weeks)
   - Apply recursion knowledge
   - Binary Search + Mergesort
   - Master algorithm analysis

4. **Then specialized topics**
   - Dynamic Programming
   - Greedy algorithms
   - Backtracking
   - Advanced data structures

---

## Files in This Section

| File | Concept | Complexity | Notes |
|------|---------|-----------|-------|
| `Factorial.java` | Recursion | O(n) time, O(n) space | Classic example, shows call stack |
| `Search.java` | Linear Search | O(n) | Works on unsorted data |
| `GetMaximumFromList.java` | Finding max | O(n) | Single pass, optimal |
| `GetAdditionFromList.java` | Aggregation | O(n) | Running sum pattern |
| `examples/` | Real-world use cases | Various | See subfolder for applications |

---

## Key Principles Summary

✅ **Understand data structures deeply**
- Know when to use each (array vs linked list)
- Understand trade-offs
- Know complexity of operations

✅ **Search is fundamental**
- Linear search: simple, works always
- Binary search: faster, requires sorted data
- Know when each applies

✅ **Recursion is powerful**
- Natural for tree/hierarchy problems
- Requires base case and recursive case
- Watch out for stack overflow

✅ **Code quality matters**
- Correctness is foundation
- Clarity aids understanding
- Fail fast on errors

✅ **Build systematic thinking**
- Break problems into subproblems
- Identify patterns
- Reuse solutions

---

## Common Mistakes to Avoid

❌ **Jumping to complex algorithms without mastering basics**
- You'll understand nothing well

❌ **Confusing time and space complexity**
- O(n) time ≠ O(n) space
- Both matter!

❌ **Not testing edge cases**
- Empty array
- Single element
- Duplicates
- Negative numbers

❌ **Writing complex code to look smart**
- Clarity > Cleverness
- Future you will thank you

❌ **Not understanding why algorithm works**
- Just memorizing patterns leads to mistakes
- Understand the principle!

---

## Practice Exercises

Try solving these before checking the implementation:

1. **Find the missing number** in array 1 to n
   - Expected: O(n) time, O(1) space
   - Hint: Aggregation pattern + math

2. **Find duplicate** in array of 1 to n (where n is array size)
   - Expected: O(n) time, O(1) space
   - Hint: Can you modify search pattern?

3. **Reverse an array** in-place
   - Expected: O(n) time, O(1) space
   - Hint: Two pointers?

4. **Fibonacci with memoization**
   - Compare recursive vs iterative
   - Understand where time is spent

5. **Recursion depth test**
   - How deep can recursion go?
   - What's the maximum factorial Java can compute?

---

## Resources for Deeper Learning

- 📚 [Khan Academy - Algorithms](https://www.khanacademy.org/computing/computer-science/algorithms) - Free courses
- 🎓 [Visualization Tool](https://www.cs.usfca.edu/~galles/visualization/Algorithms.html) - See algorithms animate
- 📖 [Big-O Cheat Sheet](https://www.bigocheatsheet.com/) - Quick reference
- 🎥 [YouTube: Abdul Bari](https://www.youtube.com/@AbdulBari1) - Excellent explanations

---

## Next Steps

Once you've mastered these principles, you're ready for:

1. **[Introduction](../introduction/README.md)** - (if you haven't done this yet)
2. **[Sorting Algorithms](../sorting/README.md)** - Apply principles to classic algorithms
3. **[Divide & Conquer](../divideconquer/README.md)** - Advanced recursive techniques

---

**Author**: Vicente García Díaz  
**License**: GNU GPLv2  
**Educational Purpose**: Teaching fundamental algorithms and computational thinking

Happy learning! 🎓
