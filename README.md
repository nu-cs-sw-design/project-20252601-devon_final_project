# Project: Java Linter

## Contributors
Devon Lai

## Dependencies
Language: Java 17 (project can run on JVM 17+; test classes compiled with --release 17)
Build Tool: Gradle 8.x (via Gradle Wrapper)
External Libraries:
ASM 9.7
Used for bytecode inspection (asm and asm-tree modules)
Other Requirements:
A Java compiler (javac) for generating test .class files
A terminal environment capable of running Gradle commands

## Build Instructions
To build the project, first ensure you have Java 17 or later installed. Navigate to the project directory in a terminal. Run “gradle build” (or “./gradlew build” if using the Gradle wrapper) to compile the source code and verify the project structure.
To run the linter, you must provide a directory containing compiled .class files. If you are using the provided test inputs, compile them first with “javac --release 17 -d test-classes test-inputs/*.java”. This will generate the .class files inside the test-classes directory.
Once you have compiled classes, run the linter using:
“gradle run --args="test-classes"”.
You can substitute “test-classes” with any directory that contains .class files you wish to analyze. The linter will execute all checks and print any warnings it finds.