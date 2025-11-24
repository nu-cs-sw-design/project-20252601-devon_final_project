package linter.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.tree.ClassNode;

public class Linter {

    private final List<LintCheck> checks;
    private final List<LintIssue> issues = new ArrayList<>();

    public Linter(List<LintCheck> checks) {
        this.checks = checks;
    }

    public void analyzePath(Path root) throws IOException {
        Files.walk(root)
             .filter(p -> p.toString().endsWith(".class"))
             .forEach(p -> {
                 try {
                     ClassNode cn = AsmClassReaderUtil.readClass(p);
                     analyzeClassNode(cn);
                 } catch (IOException e) {
                     System.err.println("Failed to read class file: " + p + " (" + e.getMessage() + ")");
                 }
             });
    }

    public void analyzeClassNode(ClassNode classNode) {
        for (LintCheck check : checks) {
            List<LintIssue> result = check.analyze(classNode);
            issues.addAll(result);
        }
    }

    public List<LintIssue> getIssues() {
        return new ArrayList<>(issues);
    }
}
