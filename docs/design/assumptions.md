Project Assumptions
1. Input Will Be Compiled .class Files
The linter operates exclusively on JVM bytecode. It assumes all inputs have already been compiled with javac.
2. Bytecode Completeness
The .class files provided will not be corrupted and will follow standard JVM class file structure readable by ASM.
3. No Cross-Class Analysis for Milestone 1
Each check analyzes one class at a time without relying on information about other classes (e.g., no global dependency graph).
4. CLI Input Format
The CLI accepts a single path (directory or class file). No GUI or config system is assumed for this implementation.