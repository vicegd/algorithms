# Algorithms: A Comprehensive Educational Repository

A comprehensive collection of **algorithm implementations** covering fundamental computer science concepts. This project is designed as an educational resource for students learning algorithmic problem-solving through practical code examples.

**Course:** Algorithmics | **Institution:** [School of Computer Science](https://ingenieriainformatica.uniovi.es/) | [University of Oviedo](http://www.uniovi.es)

---

## 🚀 Quick Start

### Prerequisites
- **Java 23** or higher
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

---

### 6. **Branch & Bound**
Solve optimization problems using intelligent search.

**Covered Problems:**
- Eight Puzzle (3x3 sliding puzzle)
- Rectangle Placement Optimization
- Agent Task Assignment

---

### 7. **Parallel Algorithms**
Master concurrent programming with Fork/Join Framework.

**Topics:**
- Recursive task decomposition
- Work-stealing algorithms
- Parallel array operations
- Concurrent file processing

**Framework:** Java ForkJoinPool (Java 7+)

---

## 💡 Learning Path (Recommended Order)

### Beginner
1. **Principles** - Data structures fundamentals
2. **Sorting** - Start with Bubble, progress to Quicksort
3. **Divide & Conquer** - Binary Search and Mergesort

### Intermediate
4. **Dynamic Programming** - Fibonacci → Knapsack
5. **Greedy** - Understand when greedy works vs fails
6. **Backtracking** - N-Queens, Permutations

### Advanced
7. **Branch & Bound** - Optimization techniques
8. **Parallel Algorithms** - Concurrent programming
9. **Complex Problems** - Combination of techniques

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

## 📊 Complexity Reference

### Time Complexity Classes
| Class | Example | Behavior |
|-------|---------|----------|
| O(1) | Array access | Constant |
| O(log n) | Binary Search | Logarithmic |
| O(n) | Linear Search | Linear |
| O(n log n) | Mergesort | Linearithmic |
| O(n²) | Bubble Sort | Quadratic |
| O(n³) | Matrix mult | Cubic |
| O(2ⁿ) | Backtracking | Exponential |
| O(n!) | Permutations | Factorial |

### Big-O Comparison
```
O(1) << O(log n) << O(n) << O(n log n) << O(n²) << O(n³) << O(2ⁿ) << O(n!)
```

---

## 🎓 Educational Features

✅ **Well-commented code** - Explains algorithm logic  
✅ **Multiple implementations** - Compare approaches  
✅ **Optimal vs Sub-optimal examples** - Learn from contrasts  
✅ **Unit tests** - Verify correctness  
✅ **Javadoc documentation** - Complete API reference  
✅ **Real-world problems** - Chess, puzzles, optimization  
✅ **Performance analysis** - Trace and timing code  
✅ **Visualization support** - Trace algorithm execution  

---

## 📖 Additional Resources

- **Complexity Analysis:** [Big-O Cheat Sheet](https://www.bigocheatsheet.com/)
- **Algorithm Visualizations:** [VisuAlgo](https://visualgo.net/)
- **Interactive Learning:** [LeetCode](https://leetcode.com/), [HackerRank](https://www.hackerrank.com/)
- **Books:** "Introduction to Algorithms" (CLRS), "Algorithms" (Sedgewick & Wayne)

---

## 👨‍💼 Author

**Vicente García Díaz**  
School of Computer Science  
University of Oviedo

[Personal Website](http://www.vicentegarciadiaz.com)

---

## 📜 License

GNU General Public License (GPL) v3.0  
See [LICENSE](LICENSE) file for details

Copyright © 2023-2026 Vicente García Díaz

---

## 🤝 Contributing

This is an educational project. Suggestions for improvements are welcome:

1. Open an issue describing the improvement
2. Fork the repository
3. Create a feature branch
4. Submit a pull request with clear descriptions

---

## 📝 Changelog

### Version 2.0 (Current)
- ✅ Updated to Java 23
- ✅ Upgraded to JUnit 5
- ✅ Enhanced documentation
- ✅ Improved code comments

### Version 1.0 (Legacy)
- Initial release with core algorithms
- JUnit 4 based tests
- Java 8 compatible
