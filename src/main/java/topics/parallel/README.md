# Parallel Algorithms - Leveraging Multi-Core Processors

Master concurrent programming and divide-and-conquer parallelization using Java's Fork/Join Framework.

---

## 🎯 Core Concept

**Parallel algorithms** solve problems by dividing work among multiple processors/threads simultaneously.

### Sequential vs Parallel
```
Sequential (1 core):
Task: |===============================| (time: 30s)

Parallel (4 cores):
Task: |========|  |========|  |========|  |========| (time: 8s)
      (division + merge overhead ≈ 2s)
```

### Key Principle: Divide & Conquer
```
1. DIVIDE: Break problem into independent subproblems
2. CONQUER: Solve subproblems in parallel
3. COMBINE: Merge results into final answer
```

---

## 📊 Parallel Programming Models

### 1. **Shared Memory (Threads)**
- Threads share same memory
- Faster communication
- Synchronization complexity
- Race conditions, deadlocks

### 2. **Distributed Memory (Processes)**
- Separate memory spaces
- Message-based communication
- Harder to synchronize
- More fault-tolerant

### 3. **GPU Computing**
- Thousands of cores
- SIMD (Single Instruction Multiple Data)
- High memory bandwidth
- Specialized algorithms

### Java Focus: Threads via Fork/Join

---

## 🏗️ Java Fork/Join Framework

### ForkJoinPool
```java
// Default pool uses number of processors
ForkJoinPool commonPool = ForkJoinPool.commonPool();

// Custom pool
ForkJoinPool pool = new ForkJoinPool(8);  // 8 threads
```

### Two Main Task Types

#### 1. **RecursiveAction** - No Result
```java
class MyTask extends RecursiveAction {
    int[] array;
    int start, end;
    
    @Override
    protected void compute() {
        if (end - start <= THRESHOLD) {
            // Base case: process directly
            for (int i = start; i < end; i++) {
                doWork(array[i]);
            }
        } else {
            // Divide: create subtasks
            int mid = (start + end) / 2;
            MyTask left = new MyTask(array, start, mid);
            MyTask right = new MyTask(array, mid, end);
            
            // Conquer: execute in parallel
            left.fork();
            right.compute();
            left.join();
            
            // Combine: (if needed)
        }
    }
}
```

#### 2. **RecursiveTask<T>** - With Result
```java
class SumTask extends RecursiveTask<Long> {
    int[] array;
    int start, end;
    static final int THRESHOLD = 1000;
    
    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // Base case: sum directly
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // Divide
            int mid = (start + end) / 2;
            SumTask left = new SumTask(array, start, mid);
            SumTask right = new SumTask(array, mid, end);
            
            // Conquer: parallel execution
            left.fork();
            Long rightResult = right.compute();
            Long leftResult = left.join();
            
            // Combine: return sum of both halves
            return leftResult + rightResult;
        }
    }
}

// Usage
SumTask task = new SumTask(array, 0, array.length);
long result = ForkJoinPool.commonPool().invoke(task);
```

### Work-Stealing Scheduler
```
Thread pool maintains queues for each thread:

Thread 1: [Task1] [Task2] [Task3]    (busy)
Thread 2: [Task4]                     (busy)
Thread 3: [Task5] [Task6] [Task7] [Task8]  (becomes idle)

Thread 3 "steals" from Thread 1's queue:
Thread 3: [Task5] [Task6] [Task7] [Task8] [Task3]

Result: Load balanced across all threads!
```

---

## 1️⃣ Recursive Sum

### Problem
Calculate sum of all array elements in parallel

### Implementation
```java
class RecursiveSum extends RecursiveTask<Long> {
    private static final int THRESHOLD = 100;
    private int[] array;
    private int start, end;
    
    public RecursiveSum(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected Long compute() {
        // Base case: sum small portion sequentially
        if (end - start <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        }
        
        // Divide
        int mid = (start + end) / 2;
        RecursiveSum leftTask = new RecursiveSum(array, start, mid);
        RecursiveSum rightTask = new RecursiveSum(array, mid, end);
        
        // Conquer in parallel
        leftTask.fork();
        long rightResult = rightTask.compute();
        long leftResult = leftTask.join();
        
        // Combine
        return leftResult + rightResult;
    }
}
```

### Performance
```
Array size: 1,000,000
Sequential: 10ms
Parallel (4 threads): 3ms
Speedup: 3.3x (not quite 4x due to overhead)
```

### Threshold Selection
**Too small:** Overhead > benefit
```
Overhead: task creation, synchronization, context switching
```

**Too large:** Poor parallelism
```
Can't divide work evenly among threads
```

**Sweet spot:** Usually 100-10,000 elements depending on operation

---

## 2️⃣ Array Transformation (Square)

### Problem
Square all elements: array[i] = array[i]²

### Implementation
```java
class RecursiveActionSquare extends RecursiveAction {
    private static final int THRESHOLD = 100;
    private int[] array;
    private int start, end;
    
    public RecursiveActionSquare(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected void compute() {
        if (end - start <= THRESHOLD) {
            // Base case: transform sequentially
            for (int i = start; i < end; i++) {
                array[i] = array[i] * array[i];
            }
            return;
        }
        
        // Divide and conquer
        int mid = (start + end) / 2;
        RecursiveActionSquare left = new RecursiveActionSquare(array, start, mid);
        RecursiveActionSquare right = new RecursiveActionSquare(array, mid, end);
        
        left.fork();
        right.compute();
        left.join();
        // No combine needed (in-place modification)
    }
}
```

### Key Difference from RecursiveTask
- No return value (void)
- Modifies data in-place
- Two subtasks can execute completely independently

---

## 3️⃣ Fibonacci (Parallel)

### Problem
Calculate F(n) in parallel

### Implementation
```java
class FibonacciTask extends RecursiveTask<Long> {
    private int n;
    
    public FibonacciTask(int n) {
        this.n = n;
    }
    
    @Override
    protected Long compute() {
        if (n <= 1) {
            return (long) n;
        }
        
        // Only parallelize for larger n
        if (n <= 10) {
            // Sequential for small n (overhead not worth it)
            return fib(n);
        }
        
        // Divide: create two tasks
        FibonacciTask f1 = new FibonacciTask(n - 1);
        FibonacciTask f2 = new FibonacciTask(n - 2);
        
        // Conquer: execute in parallel
        f1.fork();
        long result2 = f2.compute();
        long result1 = f1.join();
        
        // Combine
        return result1 + result2;
    }
    
    private long fib(int n) {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }
}
```

### ⚠️ Important Note
Fibonacci is **NOT** a good parallel algorithm!
- Creates many overlapping subproblems
- Exponential work duplication
- Better: Use memoization (sequential) or DP

### Lesson
Not all divide-and-conquer parallelizes well!

---

## 📁 4. File Processing in Parallel

### Problem
Process large directory tree concurrently

### Implementation
```java
class FileProcessingTask extends RecursiveAction {
    private File file;
    private long fileSize = 0;
    
    public FileProcessingTask(File file) {
        this.file = file;
    }
    
    @Override
    protected void compute() {
        if (file.isFile()) {
            // Base case: process single file
            fileSize = processFile(file);
        } else if (file.isDirectory()) {
            // Divide: create tasks for subdirectories
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                List<FileProcessingTask> tasks = new ArrayList<>();
                
                for (File f : files) {
                    FileProcessingTask task = new FileProcessingTask(f);
                    task.fork();
                    tasks.add(task);
                }
                
                // Combine: wait for all and sum
                for (FileProcessingTask task : tasks) {
                    task.join();
                    fileSize += task.fileSize;
                }
            }
        }
    }
    
    private long processFile(File f) {
        // Example: count file size
        return f.length();
    }
    
    public long getFileSize() {
        return fileSize;
    }
}
```

### Real-World Use Cases
- Search across directories
- Image batch processing
- Log file analysis
- Backup operations

---

## ⚡ Optimization Techniques

### 1. **Threshold Tuning**
```java
// Too small: 10 elements
// Overhead: Task creation (100μs), fork/join (50μs)
// Speedup needed: 150μs / (work time)
// If work time < 150μs: Sequential is faster!

// Sweet spot: Usually 1000-5000 elements for simple ops
static final int THRESHOLD = 1000;
```

### 2. **Load Balancing**
Work-stealing handles this automatically
- Threads with empty queues steal from others
- No manual load balancing needed

### 3. **Minimize Synchronization**
```java
// Bad: Frequent shared memory access
task.fork();
someSharedVar++;  // Race condition!
task.join();

// Good: Work-steal handles synchronization internally
```

### 4. **Task Granularity**
Balance between:
- Too fine: Overhead dominates
- Too coarse: Load imbalance

### 5. **Avoid Task Creation Overhead**
```java
// If already at threshold, don't fork:
if (end - start <= THRESHOLD) {
    processSequentially();  // Inline, don't fork
}
```

---

## 📊 Parallel Scalability

### Speedup Formula
```
Speedup(p) = Sequential Time / Parallel Time with p processors

Ideal: S(p) = p (linear scaling)
Actual: S(p) < p (overhead, synchronization)

Efficiency: E(p) = S(p) / p  (should be 0.7-0.9 for good algorithms)
```

### Amdahl's Law
```
If f = fraction that must be sequential:
S(p) ≤ 1 / (f + (1-f)/p)

Example:
  f = 0.1 (10% sequential)
  p = 4 cores
  S(4) ≤ 1 / (0.1 + 0.9/4) = 3.08x

Lesson: Even small sequential portions limit speedup!
```

### Gustafson's Law
```
Better estimate for large problems:
S(p) = p - f(p - 1)

where f = sequential fraction
Shows speedup improves with larger problems
```

---

## 🔍 Comparison: Sequential vs Parallel

### When Parallel Wins
- ✅ Large datasets (n > 10,000)
- ✅ Compute-intensive (expensive operations)
- ✅ Multi-core available
- ✅ Low synchronization overhead
- ✅ Few bottlenecks

### When Sequential Wins
- ❌ Small datasets (n < 1,000)
- ❌ Simple operations (just addition)
- ❌ Single core only
- ❌ I/O bound (disk/network wait)
- ❌ High synchronization needs

---

## 🧪 Testing Parallel Code

### Correctness
```java
void testRecursiveSum() {
    int[] array = {1, 2, 3, 4, 5};
    RecursiveSum task = new RecursiveSum(array, 0, array.length);
    long result = ForkJoinPool.commonPool().invoke(task);
    
    // Verify against sequential
    long expected = Arrays.stream(array).sum();
    assertEquals(expected, result);
}
```

### Performance
```java
void testPerformance() {
    int[] array = new int[10_000_000];
    Arrays.fill(array, 1);
    
    // Sequential
    long start = System.nanoTime();
    long seqResult = sequentialSum(array);
    long seqTime = System.nanoTime() - start;
    
    // Parallel
    start = System.nanoTime();
    long parResult = parallelSum(array);
    long parTime = System.nanoTime() - start;
    
    assertEquals(seqResult, parResult);
    System.out.println("Speedup: " + (seqTime / (double) parTime));
}
```

### Thread Safety
```java
void testThreadSafety() {
    // Run same test multiple times
    for (int i = 0; i < 100; i++) {
        assertEquals(expected, parallelComputation());
    }
}
```

---

## ⚠️ Common Pitfalls

### 1. **Parallelizing Everything**
❌ Wrong: Parallelize small operations
```java
// Overhead > benefit!
for (int i = 0; i < 1000; i++) {
    FibonacciTask task = new FibonacciTask(5);  // Tiny computation
    task.fork();
}
```

### 2. **Shared Mutable State**
❌ Wrong: Multiple threads modify same variable
```java
class BadTask extends RecursiveAction {
    static int counter = 0;  // Shared!
    
    @Override
    protected void compute() {
        counter++;  // Race condition!
    }
}
```

### 3. **Blocking Operations**
❌ Wrong: Blocking inside compute()
```java
@Override
protected void compute() {
    Thread.sleep(1000);  // Blocks thread pool!
    // Other tasks can't run
}
```

### 4. **Bad Threshold**
❌ Too small: Overhead dominates
```java
static final int THRESHOLD = 1;  // Creates millions of tasks!
```

### 5. **Not Using Result**
```java
// Inefficient: Creates task but doesn't use result
new MyTask(...).fork();  // Task runs but result ignored?
```

---

## 🎓 Parallel Algorithms in This Module

### Implemented
- **`RecursiveTaskSum.java`** - Parallel array sum
- **`RecursiveActionSquare.java`** - Parallel array square
- **`FibonacciTask.java`** - Parallel Fibonacci (educational)
- **`FileProcessingTask.java`** - Parallel file traversal
- **`RecursiveActionComparison.java`** - Performance comparison

### Utilities
- **`FibonacciAlgorithm.java`** - Sequential version for comparison

---

## 📚 Best Practices

### 1. **Profile First**
```java
// Measure before and after parallelization
// Don't assume it will be faster!
```

### 2. **Start with Sequential**
```java
// Get correct sequential version
// Then parallelize carefully
```

### 3. **Tune Threshold**
```java
// Measure performance with different thresholds
// Find sweet spot for your hardware
```

### 4. **Check Speedup**
```java
// Actual speedup should be 0.7p to p for p processors
// Less indicates synchronization overhead
```

### 5. **Consider Alternatives**
```java
// For I/O: Use async, not parallel
// For conflicts: Use queues, not threads
// For small n: Sequential is often faster
```

---

## 🔗 Resources

### Java Documentation
- [ForkJoinPool](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ForkJoinPool.html)
- [RecursiveTask](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/RecursiveTask.html)
- [RecursiveAction](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/RecursiveAction.html)

### Further Reading
- **"Java Concurrency in Practice"** - Goetz et al.
- **"The Art of Multiprocessor Programming"** - Herlihy & Shavit
- CLRS - "Parallel Algorithms" chapters
- [Herb Sutter - Free Lunch is Over](http://www.gotw.ca/publications/concurrency-ddj.htm)

---

## 🎓 Learning Outcomes

After studying this module:

✅ Understand Fork/Join Framework  
✅ Identify parallelizable problems  
✅ Calculate optimal thresholds  
✅ Measure parallel performance  
✅ Avoid synchronization issues  
✅ Know when parallel helps vs hurts  
✅ Profile and optimize parallel code  
