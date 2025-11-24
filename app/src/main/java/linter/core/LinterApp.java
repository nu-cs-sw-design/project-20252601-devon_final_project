package linter.core;

import java.nio.file.Path;
import java.util.List;

import linter.checks.CollectionsInterfaceCheck;
import linter.checks.EqualsHashCodeCheck;
import linter.checks.PublicConstructorCheck;

public class LinterApp {

    public static void main(String[] args) throws Exception {
        System.out.println("test");

        if (args.length < 1) {
            System.err.println("Usage: java -jar linter.jar <path-to-classes>");
            System.exit(1);
        }

        Path root = Path.of(args[0]);

        List<LintCheck> checks = List.of(
            new EqualsHashCodeCheck(),
            new PublicConstructorCheck(),
            new CollectionsInterfaceCheck()
        );

        Linter linter = new Linter(checks);
        linter.analyzePath(root);

        for (LintIssue issue : linter.getIssues()) {
            System.out.println(issue);
        }
    }
}
