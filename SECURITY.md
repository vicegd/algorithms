# Security & Reliability Policy

This is an educational repository containing fundamental algorithm implementations. It is not a production library, and it does not handle network requests, authentication, or sensitive user data.

Therefore, we do not require complex or private disclosure processes.

### What we consider a "Security" or "Reliability" flaw:
In the context of algorithmic engineering, we care deeply about computational stability. We consider the following to be critical issues:
* **Arithmetic Overflows:** Operations exceeding 32-bit/64-bit bounds.
* **Resource Exhaustion:** Infinite loops, severe memory leaks, or recursive stack overflows.
* **Concurrency Deadlocks:** Thread freezing in parallel implementations.

### How to report an issue
**Just open a standard GitHub Issue!** We believe that discussing algorithmic flaws, edge-case failures, and memory limits openly is a fantastic learning opportunity for all. 

1. Go to the **Issues** tab.
2. Create a new issue describing the flaw.
3. Provide a minimal code snippet or the array of numbers that causes the algorithm to break.
4. If you have a fix in mind, feel free to open a Pull Request directly referencing the issue.

Thank you for helping us keep these foundations stable and mathematically sound!