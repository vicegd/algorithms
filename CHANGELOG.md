# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## Sections

- `Added`: New features or algorithms
- `Changed`: Changes to existing functionality
- `Deprecated`: Soon-to-be removed features
- `Removed`: Removed features or algorithms
- `Fixed`: Bug fixes
- `Security`: Security-related changes

---

## [Unreleased]

### Added

- Nothing yet for next release

### Changed

- Nothing yet for next release

### Fixed

- Nothing yet for next release

---

## [2.0.0] - 2026-05-12

### Added

- **Educational Enhancement Phase (Complete Redesign)**
  - Created comprehensive README system with 8 topic guides (2,500+ lines)
    - `introduction/README.md` - Getting started guide with MaxPairWiseProduct examples
    - `principles/README.md` - Data structures, search, recursion fundamentals
    - `sorting/README.md` - 6 sorting algorithms with comparison matrix
    - `divideconquer/README.md` - Master Theorem and classic D&C problems
    - `dynamic/README.md` - DP vs Greedy analysis with 1M+ speedup examples
    - `greedy/README.md` - Intentional anti-patterns showing when greedy fails
    - `backtracking/README.md` - Template code and optimization techniques
    - `branchandbound/README.md` - A* algorithm and heuristics for optimization
    - `parallel/README.md` - Fork/Join framework with Amdahl's Law
  
  - Enhanced Javadoc documentation for key algorithms:
    - `BinarySearch.java` - Master Theorem derivation with O(log n) analysis
    - `Bubble.java` - Detailed complexity analysis and educational comparison
    - `Fibonacci.java` - Naive O(2^n) vs O(n) comparison showing 1M+ speedup
    - `Knapsack01.java` - Problem statement and why greedy fails
    - Enhanced documentation with algorithm steps, examples, and use cases

  - Community & Documentation Files
    - `CONTRIBUTING.md` - Complete contribution guidelines (10 sections)
    - `CHANGELOG.md` - This file (release history)
    - `LICENSE` - Improved with project-specific sections and guidelines
    - `.gitignore` - Reorganized with comprehensive IDE support

- **Code Quality Improvements**
  - Updated Javadoc in 4+ key algorithm files with:
    - Algorithm description and pseudocode
    - Complexity analysis (time and space)
    - Example traces showing execution
    - When to use / when NOT to use
    - Comparison to alternatives
    - Real-world applications

### Changed

- **Repository Structure**
  - Removed IDE-specific files from version control:
    - Deleted `.project` (Eclipse config)
    - Deleted `.classpath` (Eclipse classpath)
    - Deleted `.settings/` (Eclipse preferences)
  - Project is now truly IDE-agnostic (Eclipse, IntelliJ, VSCode, NetBeans)

- **Documentation**
  - Reorganized main README.md with new structure:
    - Added Introduction (Getting Started) as Topic 0
    - Added Principles as Topic 8
    - Enhanced Learning Path with 3-level progression
    - Improved Quick Start section with Maven commands

- **Code Comments**
  - Enhanced class-level Javadoc across algorithm files
  - Added algorithm explanation sections
  - Added usage examples in Javadoc
  - Documented complexity analysis inline

- **Build Configuration**
  - Implicit (Java and JUnit versions updated in previous commit)

### Fixed

- **Documentation Accuracy**
  - Clarified when greedy algorithms fail (Coin Change, Knapsack)
  - Corrected Big-O notation in comments
  - Added edge case handling documentation

### Removed

- IDE-specific project files from git history (now in .gitignore only)

### Security

- No changes

---

## [1.0.0] - 2025-01-01

### Added

- **Initial Release**
  - 7 algorithmic paradigm topics: Sorting, Divide & Conquer, Dynamic Programming, Greedy, Backtracking, Branch & Bound, Parallel
  - ~149 Java algorithm implementations
  - ~48 unit test files
  - Basic README documentation
  - pom.xml with Maven 3.6+ and JUnit 5.10.2 dependencies

### Implementation Coverage

- **Sorting (6 algorithms)**
  - Bubble Sort, Improved Bubble, Direct Insertion, Direct Selection, Quicksort, Mergesort

- **Divide & Conquer (11 algorithms)**
  - Binary Search, Fibonacci, Factorial, GCD, Power, Median, Mode, Majoritarian Element, MaxSum, Mergesort, Quicksort

- **Dynamic Programming (5+ problems)**
  - Fibonacci, 0/1 Knapsack, Coin Change, Combinations, Permutations, River Travel

- **Greedy (10+ problems)**
  - Coin Change (non-optimal), Knapsack, Chess Horse, File Disk Optimization, Plumber Assignment

- **Backtracking (5+ problems)**
  - N-Queens, Permutations, Subsets, Subset Sum, Chess Horse Tour

- **Branch & Bound (3+ problems)**
  - Eight Puzzle, Rectangle Placement, Agent Task Assignment

- **Parallel Algorithms (4+ implementations)**
  - Recursive Sum, Array Transformation, Fibonacci (educational note), File Processing

- **Principles (5 core implementations)**
  - Factorial, Search, GetMaximum, GetAddition, Examples

### Documentation

- Basic README explaining project structure
- Javadoc on core methods
- SLF4J logging configuration

### Testing

- JUnit 5 unit tests across topics
- Test suite validates algorithm correctness

### License

- GNU GPLv2 - Educational use allowed and encouraged

---

## Version Numbering

- **Major (2.0)**: Complete overhaul, significant new features, breaking changes
- **Minor (x.1)**: New algorithms, new features, backward compatible
- **Patch (x.x.1)**: Bug fixes, small improvements, no new features

---

## How to Report Issues

Found a bug? Have a suggestion?

1. Check [GitHub Issues](https://github.com/vicegd/algorithms/issues) first
2. If not reported, create new issue with:
   - Clear title describing the problem
   - Steps to reproduce (if bug)
   - Expected vs actual behavior
   - Java version and OS
   - Code snippet if applicable

---

## Contribution History

### Version 2.0.0 Contributors

- **Vicente García Díaz** - Project maintainer and educational enhancement
  - Comprehensive README creation for all 8 topic areas
  - Enhanced Javadoc documentation for key algorithms
  - Community file creation (CONTRIBUTING.md, improved LICENSE)
  - Repository reorganization and cleanup

### Version 1.0.0 Contributors

- **Vicente García Díaz** - Original implementation
  - Core algorithm implementations
  - Initial unit test suite
  - Maven project configuration

---

## Future Roadmap

### Planned for v2.1.0
- [ ] Add Dijkstra's and Floyd-Warshall algorithms (Graph algorithms)
- [ ] Add String matching algorithms (KMP, Boyer-Moore)
- [ ] Enhanced test coverage reporting
- [ ] Performance benchmarking suite

### Planned for v3.0.0
- [ ] Graph algorithms category (10+ algorithms)
- [ ] String algorithms category
- [ ] Geometry algorithms
- [ ] Advanced data structures (Trie, Segment Tree, Fenwick Tree)
- [ ] Interactive visualization support
- [ ] Migration from GPLv2 to GPLv3 (planned)

### Under Consideration
- API improvements for algorithm interfaces
- Custom implementation templates for students
- Integration with online judge platforms
- Video tutorial links
- ASCII art visualizations in comments

---

## Deprecated Features

None in current version.

---

## Breaking Changes

None in current version.

---

## Migration Guides

### Migrating from v1.0.0 to v2.0.0

**No Breaking Changes!**

All v1.0.0 code remains functional. v2.0.0 adds:
- Better documentation (no code changes required)
- Enhanced Javadoc (pure documentation)
- New community guidelines (optional to follow)

**If you have a fork:**
```bash
# Update your local repo
git fetch upstream
git merge upstream/master

# No code refactoring needed
# Documentation improvements are backward compatible
```

---

## Dependency Changes

### Version 2.0.0
- Java: 17 → 23 (in v1.1.0)
- JUnit: 4.13.1 → 5.10.2 (in v1.1.0)
- Maven: 3.6+ (unchanged)
- SLF4J: 1.7.13 (unchanged)

### Version 1.0.0
- Java 17
- JUnit 4.13.1
- Maven 3.6+
- SLF4J 1.7.13

---

## Statistics

### Project Scope
- **Total Java Files**: ~149 (101 implementations + 48 tests)
- **Total Lines of Code**: ~20,000+ LOC
- **Total Documentation**: ~2,500+ lines (v2.0.0 addition)
- **Test Coverage**: Increasing with each release

### Release Timeline
- **v1.0.0**: January 2025 - Initial release
- **v1.1.0**: February 2025 - Java 17→23, JUnit 4→5 upgrade
- **v2.0.0**: May 2026 - Educational enhancement complete overhaul

---

## Verification

To verify the changelog accuracy, you can:

```bash
# Count algorithm implementations
find src/main/java/topics -name "*.java" ! -name "*Test*" | wc -l

# Count test files
find src/test/java/topics -name "*Test.java" | wc -l

# Count lines of documentation
find . -name "README.md" -exec wc -l {} \; | awk '{sum+=$1} END {print sum}'

# Check current version in pom.xml
grep -A 1 "<version>" pom.xml | head -1
```

---

## Contact & Support

- **Author**: Vicente García Díaz
- **Email**: vicegd@example.com
- **GitHub Issues**: https://github.com/vicegd/algorithms/issues
- **License**: GNU GPLv2

For more information, see [README.md](README.md) and [CONTRIBUTING.md](CONTRIBUTING.md).

---

**Last Updated**: 2026-05-12  
**Maintained by**: Vicente García Díaz  
**License**: GNU GPLv2
