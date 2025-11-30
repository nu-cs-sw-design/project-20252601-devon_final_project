Functional Requirements
1. Class Loading
The system must read .class files from a user-specified directory or file.
The system must convert each .class file to an ASM ClassNode structure.

2. Lint Checks
The system must support at least three lint checks:
- Equals/HashCode Consistency Check
    - Detect when a class overrides only one of equals() or hashCode().
- Public Class Not Publicly Constructible Check
    - Detect when a public, concrete class has no public constructor.
- Program to Interface (Collections) Check
    - Detect usage of concrete collection types (e.g., ArrayList, HashMap) instead of interfaces (List, Map).
Each check must return a list of issues with severity and descriptive text.

3. CLI Behavior
Must accept one argument: a directory or class file path.
Must run all registered lint checks on all classes found.
Must print results in a readable format.

4. Output
Should print each issue in the format:
[SEVERITY] ClassName (CHECK_ID): message
Should print nothing if no issues are found.

Non-Functional Requirements
1. Extensibility
The architecture must allow additional lint checks to be added without modifying the Linter core logic.
2. Maintainability
The codebase must be simple, modular, and well-documented, especially around ASM usage.
3. Performance
The tool should handle traversal of a directory of .class files quickly using a single pass over each file.