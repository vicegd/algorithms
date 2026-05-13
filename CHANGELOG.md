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

## [1.8.0] - 2026-03-28

### Added

- Chapter 1 practical exercises added
- Additional `combinations` variants
- New 2026 parallel algorithm implementations
- New `Pair` data structure

### Fixed

- First example corrections for 2026 academic year

---

## [1.7.0] - 2024-02-05

### Fixed

- Fixed resource leak in parallel algorithms: added `pool.close()` call
- `ChessHorse` path-finding implementation improvements

---

## [1.6.0] - 2023-01-24

### Added

- `Node` data structure
- Updates for 2023 academic year (Introduction and Combinations sections)

### Changed

- Java version migration across the project
- Parallel algorithms updated for new semester
- Backtracking refinements

---

## [1.5.0] - 2022-02-08

### Added

- `ChessHorse` improvements and new test cases
- Lab 1.1 practical exercises

### Changed

- Full update for 2022 academic year
- Fixed logger properties (`log4j.properties`)
- Removed external library dependency
- Cleaned up empty code snippets

### Removed

- PRIM algorithm (out of scope for the course)
- Truck-driver problem

---

## [1.4.0] - 2020-01-20

### Changed

- **Java 8 migration** — project updated to Java 8 compatibility
- Preparation and updates for the 2020 academic year
- Added new Divide & Conquer implementations
- Fixed `.classpath` and `.gitignore` configuration

### Fixed

- Updated JUnit dependency (Oct 2020)
- Various small fixes

---

## [1.3.0] - 2019-02-01

### Fixed

- Bug fixes across multiple algorithm topics
- Removed deprecation warnings throughout the codebase
- README.md maintenance (Apr 2019)

---

## [1.2.0] - 2018-02-12

### Changed

- Branch & Bound: removed out-of-scope backpack and task variants; cleaned up problem set
- Switched from `hashCode()` to `UUID` for node identity in Branch & Bound (Apr 2016)

---

## [1.1.0] - 2017-01-12

### Added

- `MaxPairWiseProduct` problem with multiple implementations to Introduction topic
- GCF (Greatest Common Factor) added to Divide & Conquer
- Code snippets in README for `GCF.java`

### Changed

- Complete review of all major algorithm categories:
  - Divide & Conquer: full review and Javadoc improvements
  - Parallel Computing: Fork/Join review and corrections
  - Backtracking: review and correctness fixes
- Project renamed from `BasicAlgorithms` to `algorithmsCourse` (Oct 2016)

### Fixed

- Fibonacci implementation edge case corrected
- Classpath configuration fixed
- Logger level adjusted

---

## [1.0.0] - 2016-01-11

### Added

- **Project Creation** — initial repository for the Algorithmics course at the University of Oviedo
  - Maven project configured with JUnit and Log4J
  - **Principles** — core data-structure examples: `Factorial`, `Search`, `GetMaximum`, `GetAddition`
  - **Sorting** — `BubbleSort`, `DirectInsertion`, `DirectSelection`; rotation logging
  - **Divide & Conquer** — `BinarySearch`, `Fibonacci`, `Factorial`, `GCD`; Javadoc coverage
  - **Greedy Algorithms** — coin change and foundational greedy problems
  - **Dynamic Programming** — `Fibonacci`, `Knapsack`, combinatorics
  - **Backtracking** — N-Queens, Permutations, Subset Sum
  - **Branch & Bound** — multi-threaded B&B implementation
  - **Parallel Algorithms** — first Fork/Join pool implementations
  - Unit tests for all topic areas
  - SLF4J + Log4J logging infrastructure
  - GNU GPLv2 license — educational use encouraged

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

### Version 2.0.0 (2026)
- Java: → 25 (`maven.compiler.source/target=25`)
- JUnit: 5.10.2 (unchanged)
- Maven Compiler Plugin: 3.13.0
- Maven Surefire Plugin: 3.2.5
- SLF4J: 1.7.13 (unchanged)

### Version 1.0.0 (2016)
- Java 8
- JUnit 4 (vintage)
- Maven 3.6+
- SLF4J + Log4J 1.7.13

---

## Statistics

### Project Scope
- **Total Java Files**: ~149 (101 implementations + 48 tests)
- **Total Lines of Code**: ~20,000+ LOC
- **Total Documentation**: ~2,500+ lines (v2.0.0 addition)
- **Test Coverage**: Increasing with each release

### Release Timeline
- **v1.0.0**: January 2016 — Project creation
- **v1.1.0**: January 2017 — Reviews, MaxPairWise, project rename
- **v1.2.0**: February 2018 — Branch & Bound cleanup
- **v1.3.0**: February 2019 — Bug fixes and deprecation removal
- **v1.4.0**: January 2020 — Java 8 migration
- **v1.5.0**: February 2022 — 2022 academic year update
- **v1.6.0**: January 2023 — Java version migration, Node, parallel update
- **v1.7.0**: February 2024 — Parallel pool fix, ChessHorse
- **v1.8.0**: March 2026 — 2026 academic year additions
- **v2.0.0**: May 2026 — Java 25 upgrade, complete documentation overhaul

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
