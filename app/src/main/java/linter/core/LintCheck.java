package linter.core;

import java.util.List;
import org.objectweb.asm.tree.ClassNode;

public interface LintCheck {
    String getId();
    String getDescription();
    List<LintIssue> analyze(ClassNode classNode);
}
