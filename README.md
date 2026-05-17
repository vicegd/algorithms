# Algorithms: A Comprehensive Educational Repository

A comprehensive collection of **algorithm implementations** covering fundamental computer science concepts. This project is designed as an educational resource for students learning algorithmic problem-solving through practical code examples.

**Course:** Algorithmics | **Institution:** [School of Computer Science](https://ingenieriainformatica.uniovi.es/) | [University of Oviedo](http://www.uniovi.es)

---

## 🚀 Quick Start

### Prerequisites
- **Java 25+** (project compiles with Java 25)
- **Maven 3.6+** (tested with 3.9+, uses plugins requiring 3.6+)
- **JUnit 5.10.2** (managed automatically by Maven)
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
│   ├── main/java/topics/               # Algorithm implementations
│   │   ├── introduction/               # Getting started, recursion, search, data structures
│   │   │   └── examples/               # Java Collections examples (ArrayList, Stack, TreeSet…)
│   │   ├── sorting/                    # Sorting algorithms
│   │   │   ├── others/                 # Additional sorts (Heapsort, Radix, Shell, Bidirectional…)
│   │   │   └── utils/                  # Shared sorting utilities & interface
│   │   ├── divideconquer/              # Divide & Conquer strategies
│   │   │   └── utils/                  # Shared D&C utilities
│   │   ├── dynamic/                    # Dynamic Programming
│   │   ├── greedy/                     # Greedy Algorithms
│   │   │   └── agentsTasks/            # Agent-task assignment timing helpers
│   │   ├── backtracking/               # Backtracking techniques
│   │   │   └── times/                  # Timing / benchmark helpers
│   │   ├── branchandbound/             # Branch & Bound methods
│   │   │   ├── times/                  # Timing / benchmark helpers
│   │   │   └── util/                   # Core B&B framework (Heap, Node…)
│   │   │       └── threads/            # Thread-safe B&B variants
│   │   └── parallel/                   # Parallel algorithms (ForkJoin)
│   └── test/java/topics/               # Unit tests (JUnit 5)
├── .github/                            # GitHub configuration
│   └── workflows/                      # CI/CD pipelines
│       └── javadoc.yml                 # Publish Javadoc to GitHub Pages
├── .editorconfig                       # Editor formatting rules
├── .gitignore                          # Git ignore patterns
├── pom.xml                             # Maven configuration
├── README.md                           # This file
├── CONTRIBUTING.md                     # Contribution guidelines
├── CHANGELOG.md                        # Release history
└── LICENSE                             # MIT License
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
- Data structures: Arrays, LinkedLists, Stacks, Queues, Hash Tables, Trees
- Search algorithms: Linear search, Binary search
- Recursion fundamentals
- Problem-solving patterns (max/min, aggregation, search)

**Why start here:** All other algorithm categories depend on these foundations. Building a strong base here makes everything else easier.

**Key Example:** MaxPairWiseProduct problem with 6 different implementations showing optimization progression from O(n²) to O(n).

**📖 Learn more:** See [Foundations](https://learnalgorithms.dev/concepts/category/1-foundations)

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

**📖 Learn more:** See [Foundations of Sorting Algorithms](https://learnalgorithms.dev/concepts/foundamental-problem-domains/sorting)

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

**📖 Learn more:** See [Divide and Conquer](https://learnalgorithms.dev/concepts/algorithm-design-paradigms/divide-and-conquer)

---

### 3. **Greedy Algorithms**
Learn when greedy choices lead to optimal solutions.

**Note:** Contains both **optimal** and **sub-optimal** implementations for learning.

**Classic Problems:**
- Coin Change (Optimal vs Non-Optimal)
- Knapsack Problem variants
- File Disk Optimization
- Agent Task Assignment
- Chess Horse Movement

**📖 Learn more:** See [Greedy Algorithms](https://learnalgorithms.dev/concepts/algorithm-design-paradigms/greedy-algorithms)

---

### 4. **Dynamic Programming**
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

**📖 Learn more:** See [Dynamic Programming](https://learnalgorithms.dev/concepts/algorithm-design-paradigms/dynamic-programming)

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

**📖 Learn more:** See [Backtracking](https://learnalgorithms.dev/concepts/state-space-search-and-optimization/backtracking)

---

### 6. **Branch & Bound**
Solve optimization problems using intelligent search.

**Covered Problems:**
- Eight Puzzle (3x3 sliding puzzle)
- Rectangle Placement Optimization
- Agent Task Assignment

**📖 Learn more:** See [Branch and Bound](https://learnalgorithms.dev/concepts/state-space-search-and-optimization/branch-and-bound)

---

### 7. **Parallel Algorithms**
Master concurrent programming with Fork/Join Framework.

**Topics:**
- Recursive task decomposition
- Work-stealing algorithms
- Parallel array operations
- Concurrent file processing

**Framework:** Java ForkJoinPool

**📖 Learn more:** See [Parallel Algorithms](https://learnalgorithms.dev/concepts/advanced-execution-models/parallel-algorithms)

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

- **API Documentation:** [vicegd.github.io/algorithms](http://vicegd.github.io/algorithms) — Javadoc for all packages and classes in this repository
- **Learning Guide:** [learnalgorithms.dev](https://learnalgorithms.dev) — In-depth explanations of the algorithms and related topics


---

## 🤝 Contributing

This is an educational project. Suggestions for improvements are welcome.    
Full contribution guide: [CONTRIBUTING.md](CONTRIBUTING.md)

---

## 📝 Changelog

Complete release history: [CHANGELOG.md](CHANGELOG.md)

---

## 👨‍💼 Author

[Vicente García Díaz](http://www.vicentegarciadiaz.com)  
School of Computer Science    
University of Oviedo

---

## 📜 License

MIT License — Copyright (c) 2016 Vicente García Díaz  
See [LICENSE](LICENSE) file for details