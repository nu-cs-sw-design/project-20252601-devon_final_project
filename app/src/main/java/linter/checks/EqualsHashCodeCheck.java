package linter.checks;

import java.util.ArrayList;
import java.util.List;

import linter.core.LintCheck;
import linter.core.LintIssue;
import linter.core.Severity;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class EqualsHashCodeCheck implements LintCheck {

    @Override
    public String getId() {
        return "EQUALS_HASHCODE";
    }

    @Override
    public String getDescription() {
        return "Classes should override equals and hashCode together.";
    }

    @Override
    public List<LintIssue> analyze(ClassNode classNode) {
        List<LintIssue> issues = new ArrayList<>();

        boolean overridesEquals = false;
        boolean overridesHashCode = false;

        for (Object mObj : classNode.methods) {
            MethodNode m = (MethodNode) mObj;
            if ("equals".equals(m.name) && "(Ljava/lang/Object;)Z".equals(m.desc)) {
                overridesEquals = true;
            } else if ("hashCode".equals(m.name) && "()I".equals(m.desc)) {
                overridesHashCode = true;
            }
        }

        if (overridesEquals ^ overridesHashCode) { // xor
            String className = classNode.name.replace('/', '.');
            String missing = overridesEquals ? "hashCode()" : "equals(Object)";
            String msg = "Overrides one of equals/hashCode but not both. Missing " + missing + ".";
            issues.add(new LintIssue(getId(), className, msg, Severity.WARNING));
        }

        return issues;
    }
}
