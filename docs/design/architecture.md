Architecture Overview
This linter is structured around a simple pipeline that loads compiled Java .class files using ASM, converts them to ClassNode objects, and runs a configurable list of lint checks against each class. Each check inspects the bytecode-level structure of the class—fields, methods, descriptors, and access flags—to identify potential design issues.
The architecture follows the Strategy pattern, each lint rule implements a shared LintCheck interface, allowing the Linter engine to remain independent of the specific checks. The core logic is designed to allow for easy extension in future iterations.

Components
- LinterApp
    - Entry point of the CLI.
    - Responsible for parsing arguments, constructing the list of checks, instantiating the Linter, and printing results.
- Linter
    - Central orchestrator.
    - Performs directory traversal, loads .class files using ASM, and runs all registered checks. Collects and aggregates LintIssue results.
- LintCheck (Interface)
    - Represents a single lint rule. Each check inspects a ClassNode and returns zero or more LintIssue objects.
- LintIssue
    - Simple data structure representing a detected issue, including severity, class name, and a human-readable message.
- Concrete Checks
    - EqualsHashCodeCheck
    - PublicConstructorCheck
    - CollectionsInterfaceCheck

ASM Usage
ASM’s ClassReader and ClassNode are used to read class metadata without loading classes into the JVM. Only the asm-tree API is used, keeping parsing simple and allowing easy inspection of fields, methods, and descriptors.