# Contributing to Algorithms Repository

Thank you for your interest in contributing to this educational algorithms repository! This guide will help you understand how to participate effectively.

## Table of Contents

1. [Code of Conduct](#code-of-conduct)
2. [Ways to Contribute](#ways-to-contribute)
3. [Getting Started](#getting-started)
4. [Development Workflow](#development-workflow)
5. [Coding Standards](#coding-standards)
6. [Pull Request Process](#pull-request-process)
7. [Testing Requirements](#testing-requirements)
8. [Documentation Standards](#documentation-standards)
9. [Commit Message Guidelines](#commit-message-guidelines)
10. [Questions?](#questions)

---

## Code of Conduct

### Our Commitment

We are committed to providing a **welcoming and inclusive environment** for all contributors.

### Expected Behavior

- ✅ Be respectful and professional
- ✅ Welcome different perspectives and experiences
- ✅ Focus on what is best for the community
- ✅ Provide constructive feedback
- ✅ Acknowledge and learn from mistakes

### Unacceptable Behavior

- ❌ Harassment or discrimination
- ❌ Trolling or insulting comments
- ❌ Plagiarism or copyright violations
- ❌ Spam or promotional content
- ❌ Any form of abuse

### Reporting Issues

If you encounter unacceptable behavior, please contact the project maintainers at `vicegd@example.com`.

---

## Ways to Contribute

### 1. **Report Issues** 🐛

Found a bug? Have a suggestion? Please open an issue!

**Before reporting:**
- Check if issue already exists
- Provide minimal reproducible example
- Include Java version and OS
- Describe expected vs actual behavior

**Issue template:**
```
Title: Clear, descriptive title

Description:
- What were you trying to do?
- What happened instead?
- Expected behavior?

Code example:
[minimal code that reproduces the issue]

Environment:
- Java version: 23
- Maven version: 3.9
- OS: Windows/Mac/Linux
```

### 2. **Improve Documentation** 📚

Help make the learning experience better:
- Fix typos or unclear explanations
- Add examples or diagrams
- Improve code comments
- Create tutorials
- Translate documentation

**No code changes needed for documentation PRs!**

### 3. **Write Tests** ✅

Help ensure code quality:
- Add unit tests for untested algorithms
- Write integration tests
- Improve test coverage
- Test edge cases

**Current test coverage:** Check with `mvn test jacoco:report`

### 4. **Add New Algorithms** 🆕

Want to add a new algorithm?

**Requirements:**
- Fits into existing 7 topic categories OR creates new category
- Has educational value
- Includes multiple implementations (optimal + educational versions)
- Well-documented with Javadoc
- Has comprehensive unit tests
- Includes complexity analysis

**Process:**
1. Open issue proposing algorithm
2. Discuss with maintainers
3. Implement with test coverage
4. Submit PR with documentation

**Example:** "Add Dijkstra's Algorithm for Greedy" topic

### 5. **Code Reviews** 👀

Review PRs, suggest improvements, test changes locally. Even reviews from learners are valuable!

### 6. **Share Knowledge** 💡

- Write blog posts about the repository
- Create video tutorials
- Present at meetups
- Answer questions in issues
- Mentor other learners

---

## Getting Started

### **Prerequisites**

- Java 25 or higher
- Maven 3.6+
- Git
- Your favorite IDE (Eclipse, IntelliJ, VSCode, NetBeans)

### **Setup Development Environment**

```bash
# 1. Fork the repository on GitHub
# https://github.com/vicegd/algorithms/fork

# 2. Clone your fork locally
git clone https://github.com/YOUR_USERNAME/algorithms.git
cd algorithms

# 3. Add upstream remote (original repo)
git remote add upstream https://github.com/vicegd/algorithms.git

# 4. Create feature branch
git checkout -b feature/your-feature-name

# 5. Build project
mvn clean compile

# 6. Run tests
mvn test
```

### **IDE Setup**

**Eclipse:**
- Right-click project → Configure → Convert to Maven project
- Or: File → Import → Existing Maven Projects

**IntelliJ IDEA:**
- File → Open → select `pom.xml`
- Auto-configures Maven setup

**VSCode:**
- Install Extension Pack for Java
- Open folder, build runs automatically

---

## Development Workflow

### **Branch Naming Convention**

```
feature/short-description     (new algorithm or feature)
fix/short-description         (bug fix)
docs/short-description        (documentation improvement)
test/short-description        (test improvements)
refactor/short-description    (code refactoring)
chore/short-description       (maintenance tasks)
```

**Examples:**
```
feature/add-dijkstra-algorithm
fix/binary-search-infinite-loop
docs/improve-quicksort-javadoc
test/add-hash-table-edge-cases
refactor/simplify-knapsack-code
```

### **Keep Your Branch Updated**

```bash
# Fetch latest changes from upstream
git fetch upstream

# Rebase your branch on top of upstream
git rebase upstream/master
```

### **Local Testing Before Push**

```bash
# Run full test suite
mvn clean test

# Run specific test class
mvn test -Dtest=BinarySearchTest

# Run with coverage
mvn test jacoco:report
# Check target/site/jacoco/index.html
```

---

## Coding Standards

### **Java Style Guide**

This project follows the **[Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)**.
Key rules applied:

#### **1. Naming Conventions**

```java
// Classes: PascalCase
public class BinarySearch { }
public class Fibonacci { }

// Methods: camelCase
public int search(int[] array, int target) { }
public void displayResults() { }

// Constants: UPPER_CASE_WITH_UNDERSCORES
public static final int MAX_SIZE = 1000;
public static final String DEFAULT_NAME = "Algorithm";

// Variables: camelCase
int currentIndex;
double averageTime;
List<Integer> sortedNumbers;
```

#### **2. Code Formatting**

```java
// Braces: Opening on same line (K&R style)
if (condition) {
  doSomething();
} else {
  doOtherThing();
}

// Indentation: 2 spaces (no tabs) - Google Java Style
public void algorithm() {
  for (int i = 0; i < n; i++) {
    if (condition) {
      result += process(i);
    }
  }
}

// Line length: Max 100 characters
```

#### **3. Comments and Javadoc**

Every public class and method needs Javadoc.
No HTML tags inside Javadoc — use plain text, `-` bullet lists, and indented code examples:

```java
/**
 * Performs binary search on sorted array.
 *
 * Complexity:
 * - Time:  O(log n)
 * - Space: O(1)
 *
 * Example:
 *   int[] nums = {1, 3, 5, 7, 9};
 *   int index = search(nums, 5);  // Returns 2
 *
 * @param array the sorted array to search
 * @param target the value to find
 * @return index of target, or Integer.MIN_VALUE if not found
 * @throws IllegalArgumentException if array is null
 */
public int search(int[] array, int target) {
  // implementation
}
```

Inline comments (`//`) are avoided unless explaining a non-obvious *why*.
Comments that merely repeat what the code says are omitted.

#### **4. Error Handling**

```java
// Validate inputs explicitly
if (array == null) {
    throw new IllegalArgumentException("Array cannot be null");
}
if (array.length == 0) {
    throw new IllegalArgumentException("Array cannot be empty");
}
if (capacity < 0) {
    throw new IllegalArgumentException("Capacity must be non-negative");
}
```

### **Avoid These**

```java
// ❌ Single letter variables (except loops)
int a = calculate(b, c);

// ❌ Magic numbers without explanation
if (length > 2147483647) { }
// Better:
if (length > Integer.MAX_VALUE) { }

// ❌ Overly complex one-liners
return a>b?a<c?a:c:b<c?b:c;
// Better:
int max = Math.max(a, b);
int result = Math.min(max, c);
return result;

// ❌ Swallowing exceptions silently
try {
    risky();
} catch (Exception e) {
    // Fail silently
}
// Better:
try {
    risky();
} catch (Exception e) {
    logger.error("Operation failed", e);
    throw new RuntimeException("Unable to complete operation", e);
}
```

---

## Pull Request Process

### **1. Before Creating PR**

```bash
# Ensure all tests pass
mvn clean test

# Update documentation if needed
# Verify Javadoc compiles
mvn javadoc:javadoc

# Check code style
# (Use IDE's built-in formatter)
```

### **2. Create Pull Request**

Use this template:

```markdown
## Description
Brief description of changes and why

## Type of Change
- [ ] New algorithm
- [ ] Bug fix
- [ ] Documentation improvement
- [ ] Test addition
- [ ] Code refactoring

## Related Issue
Fixes #(issue number) or N/A

## Changes Made
- Change 1
- Change 2
- Change 3

## Testing Done
- [x] All tests pass
- [x] Added new tests
- [x] Tested edge cases

## Checklist
- [x] Code follows style guide
- [x] Javadoc added/updated
- [x] Tests added/updated
- [x] No breaking changes
- [x] Documentation updated
```

### **3. Review Process**

- At least one maintainer review required
- Address feedback constructively
- Update branch with changes
- Re-request review when updated

### **4. Merge Criteria**

✅ All tests passing  
✅ Code review approved  
✅ No conflicts with master  
✅ Proper documentation  
✅ Follows coding standards  

---

## Testing Requirements

### **Unit Test Standards**

```java
public class BinarySearchTest {
    
    private BinarySearch searcher;
    
    @BeforeEach
    void setUp() {
        searcher = new BinarySearch();
    }
    
    @Test
    void testFoundInBeginning() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(0, searcher.search(array, 1));
    }
    
    @Test
    void testFoundInMiddle() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(2, searcher.search(array, 5));
    }
    
    @Test
    void testNotFound() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(-1, searcher.search(array, 4));
    }
    
    @Test
    void testEmptyArray() {
        assertThrows(IllegalArgumentException.class, 
            () -> searcher.search(new int[0], 5));
    }
    
    @Test
    void testNullArray() {
        assertThrows(IllegalArgumentException.class, 
            () -> searcher.search(null, 5));
    }
    
    @Test
    void testUnsortedArray() {
        int[] array = {3, 1, 5, 7, 2};
        // Should throw or document behavior
        assertThrows(IllegalArgumentException.class, 
            () -> searcher.search(array, 5));
    }
}
```

### **Test Coverage Goals**

- New algorithms: ≥ 80% coverage
- Bug fixes: Tests demonstrating the bug
- All public methods covered
- Edge cases tested

---

## Documentation Standards

### **Algorithm README Format**

Each algorithm category has a README with:
- Overview of algorithm family
- Comparison matrix of implementations
- Complexity analysis
- When to use / when NOT to use
- Common mistakes
- Practice problems

### **Javadoc Requirements**

```java
/**
 * [One-line summary - appears in index]
 * 
 * [Detailed description of algorithm, often multi-paragraph]
 * 
 * <h3>Algorithm Steps</h3>
 * [Step-by-step description or pseudocode]
 * 
 * <h3>Complexity Analysis</h3>
 * [Time and space complexity with explanation]
 * 
 * <h3>When to Use</h3>
 * [Specific use cases and advantages]
 * 
 * <h3>Example</h3>
 * <pre>
 * [Concrete example with input and output]
 * </pre>
 * 
 * @param ... [All parameters]
 * @return ... [Return value]
 * @throws ... [Exceptions that can be thrown]
 */
```

---

## Commit Message Guidelines

Follow these for clear history:

### **Format**

```
<type>(<scope>): <subject>

<body>

<footer>
```

### **Type**

- `feat`: New algorithm or feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `test`: Test addition or improvement
- `refactor`: Code refactoring without feature change
- `chore`: Maintenance, build, dependencies
- `perf`: Performance improvement

### **Scope**

Module or category: `binary-search`, `quicksort`, `knapsack`, etc.

### **Subject**

- Imperative mood ("add" not "adds" or "added")
- Don't capitalize first letter
- No period at end
- Max 50 characters

### **Examples**

```
✓ feat(binary-search): add iterative implementation
✓ fix(quicksort): handle duplicate elements correctly
✓ docs(parallel): improve javadoc with examples
✓ test(knapsack): add edge case tests for 0/1 knapsack
✓ refactor(fibonacci): simplify iterative approach
✓ chore: update maven plugins to latest versions
✗ Added new function
✗ Fixed a bug
```

### **Body**

Explain:
- What changes were made
- Why this approach was chosen
- How this affects the system

### **Footer**

Reference issues:
```
Closes #123
Fixes #456
Related to #789
```

### **Full Example**

```
feat(merge-sort): add iterative implementation

Added iterative version of merge sort for comparison with
recursive version. This demonstrates trade-off between:
- Recursive: More intuitive but uses O(depth) space
- Iterative: Uses O(1) extra space but more complex

Closes #42
```

---

## Questions?

- 📧 Email: vicegd@example.com
- 💬 GitHub Issues: https://github.com/vicegd/algorithms/issues
- 💭 Discussions: https://github.com/vicegd/algorithms/discussions

---

## Recognition

Contributors will be:
- Listed in CONTRIBUTORS.md
- Acknowledged in CHANGELOG.md
- Featured in project documentation

Thank you for making this project better for learners everywhere! 🎓

---

**Last updated:** 2026-05-12  
**Maintained by:** Vicente García Díaz
