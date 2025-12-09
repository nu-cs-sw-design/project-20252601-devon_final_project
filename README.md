# Project: Java Linter

## Contributors
- **Devon Lai**

## Dependencies
- **Language:** Java 17  
  (Project runs on JVM 17+; test classes compiled with `--release 17`)
- **Build Tool:** Gradle 8.x (via the included Gradle Wrapper)
- **External Libraries:**
  - **ASM 9.7** — used for bytecode inspection (`asm` and `asm-tree` modules)
- **Other Requirements:**
  - A Java compiler (`javac`) to generate `.class` files  
  - A terminal capable of running Gradle commands

## Build Instructions

1. Ensure Java 17 or higher is installed.

2. Build the project by navigating to the project directory and running:

   gradle build  
   (or use the wrapper: ./gradlew build)

3. To run the linter, you must provide a directory containing compiled .class files.  
   If using the provided test inputs, compile them first:

   javac --release 17 -d test-classes test-inputs/*.java

4. Run the linter on the compiled classes:

   gradle run --args="test-classes"

You may replace "test-classes" with any folder containing .class files you want to analyze.
