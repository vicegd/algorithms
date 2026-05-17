# Security Policy

## Supported Versions

This project is an educational repository with classical algorithm implementations.
It is not intended to be deployed directly in production systems.

That said, we still accept and appreciate security-related reports against the
latest code in the default branch.

| Version          | Supported |
|------------------|-----------|
| `main` / `master`| ✅         |
| Tags / releases  | ❌         |

> Note: Older tags and branches are kept for historical/teaching purposes
> and are not actively maintained from a security standpoint.

## Reporting a Vulnerability

Because this repository contains algorithmic examples and teaching material,
the practical security risk is very limited: there are no network services,
no authentication flows, and no code that is meant to be deployed as‑is to
production.

However, if you believe you have found a security-relevant issue, such as:

- Unsafe example code that is likely to be copied into production systems.
- Vulnerabilities in build scripts, CI pipelines, or tooling.
- Use of deprecated or vulnerable dependencies in sample projects.

please report it as follows:

1. **Preferred channel: GitHub security features**

   - Open a private *security advisory* draft for this repository using
     GitHub’s “Report a vulnerability” feature, or
   - If advisories are not available, open a new issue and clearly label it
     as “Security” in the title and/or with an appropriate label.

2. **Include, if possible:**
   - A clear description of the issue and its impact.
   - Steps to reproduce (if applicable).
   - Any suggested mitigation or fix.

3. **Response expectations:**
   - We aim to acknowledge security-related reports within **7 days**.
   - If the issue is confirmed, we will:
     - Prepare and review a fix.
     - Update the repository and any relevant documentation.
     - Optionally credit you in the changelog or security notes, if you agree.

If the report is determined not to be a security vulnerability (for example,
a performance issue, a non-exploitable bug, or an intended teaching
simplification), we will treat it as a regular bug report or documentation
improvement.