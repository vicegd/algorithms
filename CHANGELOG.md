# Changelog

All notable changes to the repository will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

---

## [1.0.0] - 2026-05-17
### 🚀 Stable Release & Major Refactoring
This release establishes a fully modernized, thread-safe, and robust academic framework for algorithm engineering, consolidating all architectural improvements from 2015 to the present.

### 🆕 Added
* **Core Sorting Arsenal:** Established and refined foundational implementations for Bubble (Standard, Sentinel, Bidirectional), Insertion (Direct, Binary), Selection, Shellsort, Quicksort (Median-of-Three), Mergesort, Heapsort, and Radix Sort (LSD).
* **Parallel Foundations:** Integrated advanced parallel task processing examples using `ForkJoinPool` and `RecursiveTask`.
* **JUnit 5 Jupiter Integration:** Fully migrated and expanded the validation suite, replacing manual loops with advanced native assertions like `assertArrayEquals`.
* **Standardized Knowledge Infrastructure:** Added comprehensive `SECURITY.md`, `CONTRIBUTING.md`, and a student-focused `CODE_OF_CONDUCT.md`.
* **Java 21 LTS Upgrade:** Adopted modern language features including local variable type inference (`var`) and enhanced stream pipelines.
* **SLF4J Guard Clauses:** Wrapped all intensive execution traces with `log.isTraceEnabled()` checkpoints to prevent CPU bottlenecks when tracing is inactive.

### 🔄 Changed
* **Stateless Architecture:** Completely re-engineered `Mergesort`, `Heapsort`, and `Radix` to remove class-level state mutable fields (`private int[] elements`). All operations are now thread-safe and safe for concurrent execution.
* **Dynamic Gap Sequence:** Upgraded `Shellsort` to dynamically compute gap intervals based on array length (`N/2`), removing legacy hardcoded `{7, 3, 1}` constraints.
* **Nomenclature Refinement:** Standardized the use of `key` instead of `pivot` across all insertion-based algorithms (`DirectInsertion`, `BinaryInsertion`) to reinforce conceptual boundaries before students learn Quicksort.

### 🐛 Fixed
* **Mergesort Memory Allocation:** Eliminated the garbage collection bottleneck in the recursive `combine` step by utilizing a single, pre-allocated auxiliary space array.
* **Binary Search Integer Overflow:** Replaced the legacy midpoint calculation `(left + right) / 2` with `left + (right - left) / 2` to prevent negative arithmetic overflow on massive datasets.

---

## [0.0.1] - 2015-08-24
### 🌱 Project Inception
* Initial repository creation and first algorithmic commits for the university foundations course.