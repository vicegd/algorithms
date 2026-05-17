# Contributing to Foundations of Algorithms

Thank you for your interest in contributing! This repository is an educational hub designed to help university students master classical, sequential, and parallel algorithms through clean, modern, and elegant Java code.

This document contains the complete guidelines, standards, and templates required to contribute effectively. We welcome everything from typo fixes to advanced parallel optimizations!

---

## 📋 Table of Contents

1. [Code of Conduct](#-code-of-conduct)
2. [Ways to Contribute](#-ways-to-contribute)
3. [Getting Started](#%EF%B8%8F-getting-started)
4. [Development Workflow](#-development-workflow)
5. [Coding Standards](#-coding-standards)
6. [Pull Request Process](#-pull-request-process)
7. [Testing Requirements](#-testing-requirements)
8. [Documentation Standards](#-documentation-standards)
9. [Commit Message Guidelines](#-commit-message-guidelines)
10. [Questions & Support](#-questions--support)
11. [Recognition](#-recognition)

---

## 🤝 Code of Conduct

### Our Commitment

We are committed to providing a **welcoming, safe, and inclusive environment** for all contributors, regardless of their experience level or background.

### Expected Behavior

- ✅ **Be Respectful:** Treat fellow contributors and students with professional courtesy.
- ✅ **Welcome Perspectives:** Encourage constructive discussions and diverse technical approaches.
- ✅ **Focus on Learning:** Remember that this repository is primarily an educational tool for university students.

### Unacceptable Behavior

- ❌ Harassment, derogatory comments, or personal insults.
- ❌ Plagiarism, copyright violations, or submitting code without proper attribution.

---

## 🗺️ Ways to Contribute

### 1. Report Issues 🐛

Found a bug, an integer overflow, or an edge-case failure? Please open an Issue.

**Issue Template:**

```text
Title: [Component] Clear, descriptive title of the anomaly

Description:
- What is the current unexpected behavior?
- What is the expected mathematical/algorithmic outcome?

Code Example:
[Provide a minimal code snippet or input data that reproduces the failure]

Environment Context:
- JDK Version: 21+
- OS: (Windows / macOS / Linux)
```

### 2. Improve Documentation 📚

Clear explanations are just as important as clean code. You can contribute by:

- Fixing typos in comments, console logs, or markdown files.
- Clarifying complex Javadoc descriptions.
- Adding Big-O complexity analysis blocks to undocumented methods.

### 3. Write or Enhance Tests 🧪

Help us make sure our algorithm implementations are completely bulletproof:

- Add unit tests covering tricky edge cases (e.g., empty arrays, single-element structures, negative values).
- Update legacy test classes to use modern JUnit 5 assertions.

### 4. Add New Algorithms 🆕

If you want to introduce a new algorithm to the repository:

- **Educational Approach:** Where applicable, provide both a naive/brute-force version (to illustrate the structural bottleneck to students) and the optimized version.
- **Process:** Open an Issue first to discuss the algorithm's scope with the maintainers before writing code.

---

## 🛠️ Getting Started

### Setup Your Development Environment

```bash
# 1. Fork the repository on GitHub to your personal account
# 2. Clone your personal fork locally
git clone https://github.com/YOUR_USERNAME/algorithms.git
cd algorithms

# 3. Add the original repository as the 'upstream' remote
git remote add upstream https://github.com/vicegd/algorithms.git

# 4. Create a clean, isolated branch for your changes
git checkout -b feature/your-feature-name

# 5. Build the project and run the baseline test suite
mvn clean test
```

---

## 🔄 Development Workflow

### Branch Naming Conventions

Keep branch names clean, descriptive, and prefixed by the type of change:

```text
feature/short-description      (e.g., feature/add-dijkstra-greedy)
fix/short-description          (e.g., fix/integer-overflow-factorial)
docs/short-description         (e.g., docs/refine-forkjoin-javadoc)
test/short-description         (e.g., test/add-hash-table-edge-cases)
refactor/short-description     (e.g., refactor/optimize-matrix-loops)
```

### Keeping Branches Synchronized

To maintain a clean, linear Git history and avoid merge conflicts, always rebase your active branch on top of the upstream master before pushing:

```bash
git fetch upstream
git rebase upstream/master
```

---

## 🎨 Coding Standards

### 1. Layout & Indentation

- **Indent Size:** Use 4 spaces per indentation level. Do not use raw tab characters.  
  (4 spaces ensure code is clearly visible on classroom projectors and presentation slides).
- **Line Width:** Maximum 120 characters per line to support modern displays.
- **Brace Style:** Use K&R style braces (opening brace remains on the same line as the declaration statement).

```java
public class ArrayUtility {
    public void process(int[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Dataset cannot be null.");
        }

        for (int i = 0; i < data.length; i++) {
            if (data[i] > 0) {
                executeCoreLogic(data[i]);
            }
        }
    }
}
```

### 2. Naming Typography

- **Classes & Interfaces:** PascalCase (e.g., `MaxPairWiseProduct`, `RecursiveTaskSum`).
- **Methods & Variables:** camelCase (e.g., `computeProduct()`, `currentIndex`).
- **Constants:** UPPER_CASE_WITH_UNDERSCORES (e.g., `SEQUENTIAL_THRESHOLD`).

### 3. Modern Java Features

- Use local type inference (`var`) when the concrete type is clearly obvious from the right-hand side of the assignment.
- Prefer modern Java features (such as `List.of()`, `Instant`, `Duration`, and Java Streams) over legacy alternatives.

### 4. Input Validation & Error Handling

Be defensive. Check for null values, empty structures, or boundary violations at the entry point of your algorithms, throwing descriptive runtime exceptions instead of failing silently.

```java
if (capacity < 0) {
    throw new IllegalArgumentException("Capacity metrics must be strictly non-negative.");
}
```

---

## 📥 Pull Request Process

### 1. Before Submitting

- Ensure all local tests pass via `mvn clean test`.
- Double-check that your branch has been rebased on the latest `upstream/master`.
- Verify that Javadocs compile cleanly via `mvn javadoc:javadoc`.

### 2. Pull Request Template

When opening a Pull Request, please fill out the following details:

```markdown
## 📝 Description
Provide a concise overview of the changes introduced and the problem they solve.

## ⚙️ Type of Change
- [ ] New Algorithm Implementation
- [ ] Bug / Vulnerability Fix
- [ ] Documentation / Javadoc Improvement
- [ ] Test Suite Enhancement
- [ ] Code Refactoring

## ✅ Checklist
- [ ] Code strictly follows the 4-space indentation standard.
- [ ] Javadoc multi-line blocks include proper HTML formatting tags.
- [ ] Code compiles completely without warnings.
- [ ] All unit tests pass successfully.
```

---

## 🧪 Testing Requirements

We use **JUnit 5 (Jupiter)** exclusively for all validation suites.

### Performance & Memory Guardrails

- **Massive Datasets:**  
  If your test initializes massive arrays (e.g., 10,000,000 elements) or structures like `ForkJoinPool`, handle them statically inside `@BeforeAll` and release them cleanly in `@AfterAll`.  
  Do not use `@BeforeEach` for heavy operations, as it slows down testing and wastes system memory.
- **Defensive Logging:**  
  Do not dump large loops or massive array listings into the logging stream.  
  Use `log.isTraceEnabled()` conditional guards or clamp string outputs to a small number of sample entries.
- **Preconditions vs Validation:**  
  Do not write unit tests that violan algorithmic preconditions.  
  Por ejemplo, no pruebes si una búsqueda binaria lanza una excepción al recibir un array desordenado; comprobar que está ordenado cuesta \(O(N)\), lo que rompe el objetivo de diseño \(O(\log N)\).

### Unit Test Structural Template

```java
package topics.parallel;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ForkJoinPool;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SearchEngineTest {
    private static ForkJoinPool threadPool;

    @BeforeAll
    static void setup() {
        threadPool = new ForkJoinPool();
    }

    @AfterAll
    static void teardown() {
        if (threadPool != null) {
            threadPool.shutdown();
        }
    }

    @Test
    void shouldLocateElementUsingValidBounds() {
        int[] sortedData = {-1, 0, 3, 10, 15};
        int targetElement = 10;

        assertEquals(3, mockSearchExecution(sortedData, targetElement),
            "The search engine failed to isolate the correct target index location.");
    }
}
```

---

## 📖 Documentation Standards

### Javadoc Structural Format

Every public method and class must feature a clean Javadoc block using standard HTML formatting tags so that the generated webpage documentation renders correctly.

| Purpose                 | HTML Tag                      |
|-------------------------|-------------------------------|
| Paragraph Breaks        | `<p>`                         |
| Structural Headings     | `<h2>`, `<h3>`                |
| Lists                   | `<ul>` and `<li>`             |
| Monospaced Code Formats | `<code>` or `{@code ...}`     |
| Strong Emphasis         | `<strong>`                    |

```java
/**
 * <h1>Recursive Factorial Computation</h1>
 * <p>
 * Evaluates the factorial of a given integer using a classic recursive strategy.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 *   <li><strong>Time Complexity:</strong> <code>O(N)</code> linear scale.</li>
 *   <li><strong>Space Complexity:</strong> <code>O(N)</code> memory stack frames.</li>
 * </ul>
 *
 * @param target The target integer boundary value to evaluate.
 * @return The computed mathematical factorial value.
 */
```

---

## 💬 Commit Message Guidelines

We enforce clean, professional Git logs following conventional styles to track changes transparently.

### Message Structural Format

```text
<type>(<scope>): <subject>

<body>
```

### Commit Types

- `feat`: A new algorithm implementation or framework addition.
- `fix`: A fix for a bug, arithmetic overflow, or logic flaw.
- `docs`: Documentation-only updates (README, Javadoc).
- `test`: Adding or refining test suites.
- `refactor`: Structural changes that neither fix bugs nor add new features.
- `perf`: Changes aimed at improving processing speeds or reducing memory footprint.

### Examples

```text
feat(parallel): add forkjoin recursive task for array sum
fix(factorial): resolve potential integer overflow using BigInteger
```