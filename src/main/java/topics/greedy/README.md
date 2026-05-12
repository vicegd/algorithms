# Greedy Algorithms - Making Optimal Local Choices

Learn when greedy approaches work and when they fail. This module includes both optimal and non-optimal implementations to highlight important differences.

---

## 🎯 Greedy Algorithm Philosophy

A **greedy algorithm** always makes the locally optimal choice at each step, hoping to find a global optimum.

### Key Characteristics
✅ **Never reconsider:** Once a choice is made, it's final  
✅ **Local optimization:** Pick best immediate option  
✅ **No backtracking:** No revisiting previous decisions  
✅ **Simple & Fast:** Usually O(n log n) or better  

---

## ⚠️ Critical Insight: Greedy Doesn't Always Work!

### Example: Coin Change
**Problem:** Make amount 30 with coins [1, 5, 10, 25]

**Greedy Approach:**
1. Take largest coin ≤ remaining: Take 25 (remain: 5)
2. Take 5 (remain: 0)
3. Result: [25, 5] = **2 coins** ✅

**This works!** But not always...

**Counter-example:** Coins [1, 3, 4], Amount 6
- **Greedy:** 4 + 1 + 1 = 3 coins ❌
- **Optimal:** 3 + 3 = 2 coins ✓

### When to Use Greedy
✅ Has **greedy choice property:** Local optimum → global optimum  
✅ Has **optimal substructure:** Optimal solution from optimal subproblems  
✅ Problem allows it (not all problems do!)  

---

## 1️⃣ Classic: Coin Change

### Greedy Works For Real-World Currency
Real currency systems are designed so greedy works!

```java
int minCoinsGreedy(int[] coins, int amount) {
    // Coins already sorted in descending order
    int count = 0;
    
    for (int coin : coins) {
        count += amount / coin;  // Take as many as possible
        amount %= coin;           // Remaining amount
    }
    
    return amount == 0 ? count : -1;
}
```

### Why It Works For Real Coins
- **US Coins:** 1, 5, 10, 25
- **Euro Coins:** 1, 2, 5, 10, 20, 50, 100, 200
- **Canonical coin system:** Greedy always optimal

### Why It FAILS For Arbitrary Coins
```
Coins: [1, 3, 4]
Amount: 6
Greedy: 4 + 1 + 1 = 3 coins
Optimal: 3 + 3 = 2 coins
```

**Lesson:** Know when greedy works for your specific problem!

---

## 🎒 2. Fractional Knapsack (vs 0/1 Knapsack)

### Fractional Knapsack: Greedy Works ✅
**Problem:** Maximize value with capacity constraint, CAN take fractions

**Strategy:** Items by value-to-weight ratio
```
Ratio = value / weight
Take items in descending ratio order
For last item, take fraction to fill capacity
```

### Implementation
```java
double fractionalKnapsack(Item[] items, double capacity) {
    // Sort by value/weight ratio descending
    Arrays.sort(items, (a, b) -> 
        Double.compare(b.value/b.weight, a.value/a.weight)
    );
    
    double totalValue = 0;
    
    for (Item item : items) {
        if (capacity >= item.weight) {
            totalValue += item.value;
            capacity -= item.weight;
        } else {
            // Take fraction of last item
            totalValue += item.value * (capacity / item.weight);
            capacity = 0;
            break;
        }
    }
    
    return totalValue;
}
```

### Complexity
- **Time:** O(n log n) for sorting
- **Space:** O(1) excluding input

### Why Greedy Works
Taking items in ratio order provably maximizes value

### 0/1 Knapsack: Greedy FAILS ❌
**Problem:** Same as fractional, but can't take fractions

**Counter-example:**
```
Items: (weight, value)
  A: (5, 60)   → ratio 12
  B: (10, 100) → ratio 10
  C: (15, 90)  → ratio 6
Capacity: 15

Greedy by ratio: A + B won't fit (5+10=15) → only A(60) + B? No, won't fit
                Take A(5) → take B(10) → fit with C's weight remaining
                A + B = 160, but 5+10=15, so just A+B
Optimal: B + C = 190 or even just B = 100
```

**Lesson:** 0/1 Knapsack requires Dynamic Programming!

---

## 💰 3. Activity Selection Problem

### Problem
Select maximum non-overlapping activities from list with start/end times

**Strategy:** Always pick activity finishing earliest!

```java
int maxActivities(Activity[] activities) {
    // Sort by end time
    Arrays.sort(activities, (a, b) -> 
        Integer.compare(a.endTime, b.endTime)
    );
    
    int count = 1;
    int lastEnd = activities[0].endTime;
    
    for (int i = 1; i < activities.length; i++) {
        if (activities[i].startTime >= lastEnd) {
            count++;
            lastEnd = activities[i].endTime;
        }
    }
    
    return count;
}
```

### Why This Works
Finishing earliest leaves most room for other activities

### Complexity
- **Time:** O(n log n) sorting
- **Space:** O(1)

---

## 📁 4. Huffman Coding

### Problem
Build binary tree for optimal prefix-free encoding

**Strategy:** Repeatedly merge two smallest frequency nodes

```java
class HuffmanNode {
    char ch;
    int freq;
    HuffmanNode left, right;
}

HuffmanNode buildHuffmanTree(char[] chars, int[] freqs) {
    PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(
        (a, b) -> a.freq - b.freq
    );
    
    // Add leaf nodes
    for (int i = 0; i < chars.length; i++) {
        HuffmanNode node = new HuffmanNode();
        node.ch = chars[i];
        node.freq = freqs[i];
        pq.add(node);
    }
    
    // Build tree bottom-up
    while (pq.size() > 1) {
        HuffmanNode left = pq.poll();
        HuffmanNode right = pq.poll();
        
        HuffmanNode parent = new HuffmanNode();
        parent.freq = left.freq + right.freq;
        parent.left = left;
        parent.right = right;
        pq.add(parent);
    }
    
    return pq.poll();
}
```

### Why Greedy Works
Merging smallest frequencies creates optimal tree structure

---

## 🗺️ 5. Dijkstra's Shortest Path (Graph-based)

### Greedy Strategy
Always expand shortest known distance vertex next

```java
void dijkstra(Graph graph, int source) {
    int[] dist = new int[graph.size()];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[source] = 0;
    
    PriorityQueue<Integer> pq = new PriorityQueue<>(
        (a, b) -> Integer.compare(dist[a], dist[b])
    );
    pq.add(source);
    
    while (!pq.isEmpty()) {
        int u = pq.poll();
        
        for (Edge edge : graph.getEdges(u)) {
            int v = edge.to;
            if (dist[u] + edge.weight < dist[v]) {
                dist[v] = dist[u] + edge.weight;
                pq.add(v);
            }
        }
    }
}
```

### Complexity
- **Time:** O((V + E) log V) with priority queue
- **Space:** O(V)

### Why Greedy Works
Shortest known path is always correct (non-negative weights)

---

## 🚗 6. Job Sequencing with Deadlines

### Problem
Schedule jobs with deadlines to maximize profit

```
Job:      A  B  C  D
Profit:  100 50 30 20
Deadline: 4  2  3  1
```

**Greedy Strategy:** Sort by profit descending, fit in latest slots

```java
Job[] jobSequence(Job[] jobs) {
    // Sort by profit descending
    Arrays.sort(jobs, (a, b) -> b.profit - a.profit);
    
    Job[] schedule = new Job[jobs.length];
    boolean[] slots = new boolean[jobs.length];
    
    for (Job job : jobs) {
        // Find latest available slot before deadline
        for (int i = Math.min(job.deadline - 1, jobs.length - 1); i >= 0; i--) {
            if (!slots[i]) {
                schedule[i] = job;
                slots[i] = true;
                break;
            }
        }
    }
    
    return schedule;
}
```

---

## 📊 Greedy vs Other Paradigms

| Aspect | Greedy | DP | Divide & Conquer |
|--------|--------|----|-----------------| 
| **Optimal for** | Specific problems | Overlapping subproblems | Independent subproblems |
| **Speed** | Fastest (usually) | Medium | Medium |
| **Simplicity** | Simple | Complex | Medium |
| **Always optimal?** | ❌ Not always | ✅ Yes (if applicable) | ✅ Yes |
| **Backtrack?** | ❌ Never | ✅ Implicitly | ✅ No |

---

## ✅ Checklist: When to Use Greedy

Before using greedy, verify:

- [ ] Problem has **greedy choice property**
- [ ] **Optimal substructure** exists
- [ ] **No counterexamples** found
- [ ] Proven or tested on many cases
- [ ] Understand WHY it works, not just that it does

---

## 📚 Greedy Problems in This Module

### Optimal Implementations ✅
- `Change.java` - Coin change (with canonical coins)
- `Knapsack.java` - Fractional knapsack
- `ChessHorse.java` - Horse movement heuristic
- `Plumber.java` - Plumber routing problem
- `SomePlumbers.java` - Multiple plumbers

### Non-Optimal ⚠️ (For Comparison)
- `Knapsack01.java` - 0/1 Knapsack (can't use greedy)
- `Change.java` - Some non-canonical coin systems
- `FilesDisc2.java` - File disk problem variant

**Study both!** Understanding failures teaches more than successes.

---

## 🧪 Testing Greedy Solutions

### Verification Approach
1. Test on known optimal solutions
2. Compare against DP solution (if available)
3. Try to find counterexample
4. Test edge cases

### Example Test
```java
void testCoinChangeGreedy() {
    int[] coins = {1, 5, 10, 25};  // US coins
    assertEquals(2, minCoins(coins, 30));  // 25 + 5
    assertEquals(4, minCoins(coins, 40));  // 25 + 10 + 5
    
    // Counterexample - should fail with arbitrary coins
    int[] badCoins = {1, 3, 4};
    assertEquals(2, minCoins(badCoins, 6));  // Greedy gives 3, optimal is 2
}
```

---

## 💡 Famous Greedy Algorithms

### Graph Algorithms
- **Dijkstra:** Shortest path
- **Prim:** Minimum spanning tree
- **Kruskal:** Minimum spanning tree

### String/Sequence
- **Huffman Coding:** Data compression
- **Longest Increasing Subsequence:** With binary search
- **Activity Selection:** Interval scheduling

### Optimization
- **Greedy Knapsack:** Fractional version
- **Job Scheduling:** Deadline-based
- **Interval Partitioning:** Room allocation

---

## ⚠️ Greedy Algorithm Anti-Patterns

### Don't Use Greedy When:
❌ No greedy choice property  
❌ Optimal substructure missing  
❌ Counterexamples exist  
❌ Future decisions affect past ones  
❌ Problem needs to explore multiple paths  

### If Stuck:
→ Try DP instead  
→ Look for greedy choice property  
→ Prove why greedy works (or find counterexample)  
→ Compare against brute force for small inputs  

---

## 📖 Further Study

- CLRS "Introduction to Algorithms" - Chapter 16
- Sedgewick & Wayne "Algorithms" - Chapter 2.4
- GeeksforGeeks - Greedy Algorithm Problems
- LeetCode - Greedy Tag (Medium → Hard)

---

## 🎓 Learning Outcomes

After studying this module, you should:

✅ Understand when greedy works and when it fails  
✅ Recognize greedy choice property  
✅ Implement classic greedy algorithms  
✅ Prove correctness of greedy solutions  
✅ Find counterexamples for failing cases  
✅ Know when to use DP instead  
