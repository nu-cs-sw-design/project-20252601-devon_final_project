package linter.checks;

import java.util.ArrayList;
import java.util.List;

import linter.core.LintCheck;
import linter.core.LintIssue;
import linter.core.Severity;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class PublicConstructorCheck implements LintCheck {

    @Override
    public String getId() {
        return "PUBLIC_CONSTRUCTOR";
    }

    @Override
    public String getDescription() {
        return "Public concrete classes should have at least one public constructor.";
    }

    @Override
    public List<LintIssue> analyze(ClassNode classNode) {
        List<LintIssue> issues = new ArrayList<>();

        int access = classNode.access;
        boolean isPublic = (access & Opcodes.ACC_PUBLIC) != 0;
        boolean isAbstract = (access & Opcodes.ACC_ABSTRACT) != 0;
        boolean isInterface = (access & Opcodes.ACC_INTERFACE) != 0;

        if (!isPublic || isAbstract || isInterface) {
            return issues;
        }

        boolean hasPublicCtor = false;

        for (Object mObj : classNode.methods) {
            MethodNode m = (MethodNode) mObj;
            if ("<init>".equals(m.name)) {
                if ((m.access & Opcodes.ACC_PUBLIC) != 0) {
                    hasPublicCtor = true;
                    break;
                }
            }
        }

        if (!hasPublicCtor) {
            String className = classNode.name.replace('/', '.');
            String msg = "Public non-abstract class has no public constructor.";
            issues.add(new LintIssue(getId(), className, msg, Severity.WARNING));
        }

        return issues;
    }
}
