# Algorithms: An Educational Repository

A collection of **algorithm implementations** covering fundamental computer science concepts. This project is designed as an educational resource for students learning algorithmic problem-solving through practical code examples.

**Course:** Algorithmics | **Institution:** [School of Computer Science](https://ingenieriainformatica.uniovi.es/) | [University of Oviedo](http://www.uniovi.es)

---

## 🚀 Quick Start

### Prerequisites
- **Java 25+** (project compiles with Java 25)
- **Maven 3.6+** (tested with 3.9+, uses plugins requiring 3.6+)
- **JUnit 5.10+** (managed automatically by Maven)
- Git (optional)

### Installation, Setup & Testing

The project includes **unit tests** using **JUnit**.

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

# Run all tests with verbose output
mvn test -X

# Run tests for specific topic
mvn test -Dtest=sorting/*Test

# Run with coverage report (if configured)
mvn test jacoco:report
```

### Project Structure

```
algorithms/
├── src/
│   ├── main/java/topics/               # Algorithm implementations
│   │   ├── introduction/               # Getting started, recursion, search, data structures
│   │   │   └── examples/               # Java Collections examples (ArrayList, ArrayDeque, LinkedList, Stack, TreeSet…)
│   │   ├── sorting/                    # Sorting algorithms (Bubble, Mergesort, Quicksort, Heapsort, Radix, Shell…)
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
│   │   │   └── util/                   # Core B&B framework (BranchAndBound, Heap, Node…)
│   │   │       └── threads/            # Thread-safe B&B variants (BranchAndBoundThreads, HeapThreads, WorkerThread)
│   │   └── parallel/                   # Parallel algorithms (ForkJoin)
│   └── test/java/topics/               # Unit tests (JUnit 5)
├── .github/                            # GitHub configuration
│   ├── workflows/                      # CI/CD pipelines
│   │   └── javadoc.yml                 # Publish Javadoc to GitHub Pages
│   ├── ISSUE_TEMPLATE/                 # Issue templates (bug report, feature request, custom)
│   ├── java-upgrade/                   # Java upgrade hooks
│   └── dependabot.yml                  # Dependabot version updates (Maven)
├── .editorconfig                       # Editor formatting rules
├── .gitignore                          # Git ignore patterns
├── .vscode/                            # VS Code workspace settings
├── pom.xml                             # Maven configuration
├── README.md                           # This file
├── CONTRIBUTING.md                     # Contribution guidelines
├── CHANGELOG.md                        # Release history
├── CODE_OF_CONDUCT.md                  # Code of conduct
├── SECURITY.md                         # Security policy
└── LICENSE                             # MIT License
```

---

## 📚 Core Topics & Algorithms

### 1. **Foundations of Algorithms**
**Main implementations:**
- Array Summation
- Factorial Computation
- Foundational Arithmetic Engine
- Maximum Value Extraction
- MaxPairWiseProduct with 6 different implementations showing optimization progressions
- Random Dataset Generator
- Search (Algorithmic Structural Variations)

**📖 Learn more:** See [Foundations of Algorithms](https://learnalgorithms.dev/concepts/category/1-foundations-of-algorithms)

---

### 2. **Sorting Algorithms**
**Main implementations:**
- Bidirectional Bubble Sort (Cocktail Shaker Sort)
- Binary Insertion Sort
- Bubble Sort (Left-Bubbling)
- Bubble Sort (Optimized with Sentinel)
- Direct Insertion Sort
- Direct Selection Sort
- Heapsort
- Mergesort
- Quicksort (Median-of-Three)
- Radix Sort (LSD - Least Significant Digit)
- Shellsort

**📖 Learn more:** See [Sorting Algorithms](https://learnalgorithms.dev/concepts/foundamental-problem-domains/sorting-algorithms)

---

### 3. **Divide & Conquer**
**Main implementations:**
- Binary Search
- Factorial Calculation
- Fibonacci Sequence
- Greatest Common Divisor (GCD)
- Majoritarian Element
- Maximum Subarray Sum
- Median Calculation
- Mode Calculation
- Sequential (Linear) Search
- Vector Summation (Algorithmic Structural Variations)

**📖 Learn more:** See [Divide and Conquer](https://learnalgorithms.dev/concepts/algorithm-design-paradigms/divide-and-conquer)

---

### 4. **Greedy Algorithms**
**Main implementations:**
- 0/1 Knapsack
- Agent-Task Assignment
- Coin Change
- Disk Packing
- Fractional Knapsack
- Knight's Tour
- Knight's Tour (Warnsdorff's Heuristic)
- Multi-Plumber Scheduling
- Rapid Defense Assignment
- Single-Plumber Scheduling


**📖 Learn more:** See [Greedy Algorithms](https://learnalgorithms.dev/concepts/algorithm-design-paradigms/greedy-algorithms)

---

### 5. **Dynamic Programming**
**Main implementations:**
- 0/1 Knapsack
- Cheaper Travel on the River
- Coin Change
- Combinations (n choose k)
- Fibonacci Sequence

**📖 Learn more:** See [Dynamic Programming](https://learnalgorithms.dev/concepts/algorithm-design-paradigms/dynamic-programming)

---

### 6. **Backtracking**
**Main implementations:**
- Agent-Task Assignment
- Permutations Generation
- Subset Sum
- The Knight's Tour (All Solutions)
- The Knight's Tour (First Solution)
- The N-Queens (All Solutions)
- The N-Queens (First Solution)

**📖 Learn more:** See [Backtracking](https://learnalgorithms.dev/concepts/state-space-search-and-optimization/backtracking)

---

### 7. **Branch & Bound**
**Main implementations:**
- Task Assignment
- The 8-Puzzle
- Optimal Placement of Rectangles
- Optimal Placement of Rectangles (Concurrent Execution)

**📖 Learn more:** See [Branch and Bound](https://learnalgorithms.dev/concepts/state-space-search-and-optimization/branch-and-bound)

---

### 8. **Parallel Algorithms**
**Main implementations:**
- Naive Recursive Fibonacci
- Parallel Array Transformation (Fork/Join)
- Parallel Array Squaring (Fork/Join)
- Parallel Array Summation (Fork/Join)
- Parallel Fibonacci (Fork/Join)
- Parallel File Processing (Fork/Join)

**📖 Learn more:** See [Parallel Algorithms](https://learnalgorithms.dev/concepts/advanced-execution-models/parallel-algorithms)

---

## 📖 Additional Resources

- **API Documentation:** [vicegd.github.io/algorithms/javadoc](https://vicegd.github.io/algorithms/javadoc/index.html) — Javadoc for all packages and classes in this repository
- **Learn Algorithms:** [learnalgorithms.dev](https://learnalgorithms.dev) — In-depth explanations of the algorithms and related topics

---

## 📚 Documentation & Resources

To keep the repository organized and secure, our documentation is divided into the following files:

* 📖 **[Contributing Guide](CONTRIBUTING.md):** Coding standards and how to participate.
* 🤝 **[Code of Conduct](CODE_OF_CONDUCT.md):** Guidelines for a healthy and welcoming educational environment.
* 🛡️ **[Security Policy](SECURITY.md):** Possible algorithmic vulnerabilities and how to report them.
* ⏱️ **[Changelog](CHANGELOG.md):** Version history and the evolution of the codebase since 2015.

---

## 👨‍💼 Author

[Vicente García Díaz](http://www.vicentegarciadiaz.com)  
School of Computer Science    
University of Oviedo

---

## 📜 License

MIT License — Copyright (c) 2016 Vicente García Díaz  
See [LICENSE](LICENSE) file for details