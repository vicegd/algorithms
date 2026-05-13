# Algorithms: A Comprehensive Educational Repository

A comprehensive collection of **algorithm implementations** covering fundamental computer science concepts. This project is designed as an educational resource for students learning algorithmic problem-solving through practical code examples.

**Course:** Algorithmics | **Institution:** [School of Computer Science](https://ingenieriainformatica.uniovi.es/) | [University of Oviedo](http://www.uniovi.es)

---

## 🚀 Quick Start

### Prerequisites
- **Java 25** or higher
- **Maven 3.6+**
- Git (optional)

### Installation & Setup

```bash
# Clone the repository
git clone <repository-url>
cd algorithms

# Build the project
mvn clean compile

# Run all tests
mvn test

# Run a specific test class
mvn test -Dtest=YourTestClassName
```

### Project Structure

```
algorithms/
├── src/
│   ├── main/java/topics/          # Algorithm implementations
│   │   ├── sorting/               # Sorting algorithms
│   │   ├── divideconquer/         # Divide & Conquer strategies
│   │   ├── dynamic/               # Dynamic Programming
│   │   ├── greedy/                # Greedy Algorithms
│   │   ├── backtracking/          # Backtracking techniques
│   │   ├── branchandbound/        # Branch & Bound methods
│   │   ├── parallel/              # Parallel algorithms
│   │   └── principles/            # Data structure principles
│   └── test/java/topics/          # Unit tests (JUnit 5)
├── pom.xml                         # Maven configuration
└── README.md                       # This file
```

---

## 📚 Core Topics & Algorithms

### 0. **Getting Started - Introduction**
New to algorithms? Start here!

**What you'll learn:**
- What is an algorithm?
- How to measure efficiency (Big-O)
- Problem-solving strategies
- Correctness vs Efficiency trade-offs

**Key Example:** MaxPairWiseProduct problem with 6 different implementations showing optimization progression from O(n²) to O(n).

**📖 Learn more:** See [introduction/README.md](src/main/java/topics/introduction/README.md)

---

### 1. **Sorting Algorithms**
Learn fundamental sorting techniques with complexity analysis.

| Algorithm | File | Time | Space | Best Use Case |
|-----------|------|------|-------|---------------|
| Bubble Sort | `Bubble.java` | O(n²) | O(1) | Educational purposes |
| Improved Bubble | `ImprovedBubble.java` | O(n²) | O(1) | Small datasets |
| Direct Insertion | `DirectInsertion.java` | O(n²) | O(1) | Nearly sorted data |
| Direct Selection | `DirectSelection.java` | O(n²) | O(1) | Memory-constrained |
| Quicksort | `Quicksort.java` | O(n log n) avg | O(log n) | General purpose |
| Mergesort | `Mergesort.java` | O(n log n) | O(n) | Stable sorting needed |

**📖 Learn more:** See [sorting/README.md](src/main/java/topics/sorting/README.md)

---

### 2. **Divide & Conquer**
Master the divide-and-conquer problem-solving paradigm.

**Key Concepts:**
- Breaking problems into smaller subproblems
- Solving recursively
- Combining solutions

**Implementations:**
- **Searching:** Binary Search (O(log n))
- **Mathematical:** Fibonacci, Factorial, GCD
- **Array Operations:** Vector Sum, Mergesort, Quicksort
- **Statistical:** Median, Mode, Majoritarian Element, Max Sum

**📖 Learn more:** See [divideconquer/README.md](src/main/java/topics/divideconquer/README.md)

---

### 3. **Dynamic Programming**
Solve optimization problems by building solutions from subproblems.

**Key Concepts:**
- Overlapping subproblems
- Optimal substructure
- Memoization vs Tabulation

**Problems Solved:**
- Fibonacci Sequence
- 0/1 Knapsack Problem
- Coin Change Problem
- Combinations/Permutations
- River Travel Optimization

**📖 Learn more:** See [dynamic/README.md](src/main/java/topics/dynamic/README.md)

---

### 4. **Greedy Algorithms**
Learn when greedy choices lead to optimal solutions.

**Note:** Contains both **optimal** and **sub-optimal** implementations for learning.

**Classic Problems:**
- Coin Change (Optimal vs Non-Optimal)
- Knapsack Problem variants
- File Disk Optimization
- Agent Task Assignment
- Chess Horse Movement

**📖 Learn more:** See [greedy/README.md](src/main/java/topics/greedy/README.md)

---

### 5. **Backtracking**
Explore systematic search techniques with pruning.

**Applications:**
- N-Queens Problem
- Permutations
- Subsets with given sum
- Chess Horse paths
- Agent Task Assignment

**Complexity:** Exponential, with pruning optimizations

**📖 Learn more:** See [backtracking/README.md](src/main/java/topics/backtracking/README.md)

---

### 6. **Branch & Bound**
Solve optimization problems using intelligent search.

**Covered Problems:**
- Eight Puzzle (3x3 sliding puzzle)
- Rectangle Placement Optimization
- Agent Task Assignment

**📖 Learn more:** See [branchandbound/README.md](src/main/java/topics/branchandbound/README.md)

---

### 7. **Parallel Algorithms**
Master concurrent programming with Fork/Join Framework.

**Topics:**
- Recursive task decomposition
- Work-stealing algorithms
- Parallel array operations
- Concurrent file processing

**Framework:** Java ForkJoinPool (Java 7+)

**📖 Learn more:** See [parallel/README.md](src/main/java/topics/parallel/README.md)

---

### 8. **Principles & Fundamentals**
Master data structures, recursion, and basic search techniques.

**Core Topics:**
- Data structures: Arrays, LinkedLists, Stacks, Queues, Hash Tables, Trees
- Search algorithms: Linear search, Binary search
- Recursion fundamentals
- Problem-solving patterns (max/min, aggregation, search)

**Why start here:** All other algorithm categories depend on these foundations. Building a strong base here makes everything else easier.

**📖 Learn more:** See [principles/README.md](src/main/java/topics/principles/README.md)

---

## 🧪 Testing

The project includes **comprehensive unit tests** using **JUnit 5**.

```bash
# Run all tests with verbose output
mvn test -X

# Run tests for specific topic
mvn test -Dtest=sorting/*Test

# Run with coverage report (if configured)
mvn test jacoco:report
```

---

## 📖 Additional Resources

- **Complexity Analysis:** [Big-O Cheat Sheet](https://www.bigocheatsheet.com/)
- **Algorithm Visualizations:** [VisuAlgo](https://visualgo.net/)
- **Interactive Learning:** [LeetCode](https://leetcode.com/), [HackerRank](https://www.hackerrank.com/)
- **Books:** "Introduction to Algorithms" (CLRS), "Algorithms" (Sedgewick & Wayne)

---

## 👨‍💼 Author

[Vicente García Díaz](http://www.vicentegarciadiaz.com)  
School of Computer Science    
University of Oviedo

---

## 📜 License

MIT License — Copyright (c) 2016 Vicente García Díaz  
See [LICENSE](LICENSE) file for details

---

## 🤝 Contributing

This is an educational project. Suggestions for improvements are welcome.    
Full contribution guide: [CONTRIBUTING.md](CONTRIBUTING.md)

---

## 📝 Changelog

Complete release history: [CHANGELOG.md](CHANGELOG.md)
