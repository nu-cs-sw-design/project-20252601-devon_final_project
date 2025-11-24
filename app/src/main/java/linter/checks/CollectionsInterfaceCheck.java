package linter.checks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import linter.core.LintCheck;
import linter.core.LintIssue;
import linter.core.Severity;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class CollectionsInterfaceCheck implements LintCheck {

    private final Set<String> concreteCollections = new HashSet<>();

    public CollectionsInterfaceCheck() {
        concreteCollections.add("java/util/ArrayList");
        concreteCollections.add("java/util/LinkedList");
        concreteCollections.add("java/util/HashMap");
        concreteCollections.add("java/util/HashSet");
    }

    @Override
    public String getId() {
        return "PROGRAM_TO_INTERFACE_COLLECTIONS";
    }

    @Override
    public String getDescription() {
        return "Prefer List/Map/Set interfaces over concrete collection classes.";
    }

    @Override
    public List<LintIssue> analyze(ClassNode classNode) {
        List<LintIssue> issues = new ArrayList<>();
        String className = classNode.name.replace('/', '.');

        // Fields
        for (Object fObj : classNode.fields) {
            FieldNode f = (FieldNode) fObj;
            String internalName = getObjectInternalName(f.desc);
            if (internalName != null && concreteCollections.contains(internalName)) {
                String msg = "Field '" + f.name + "' uses concrete collection type "
                        + internalToSimple(internalName) + "; consider using an interface type.";
                issues.add(new LintIssue(getId(), className, msg, Severity.WARNING));
            }
        }

        // Methods
        for (Object mObj : classNode.methods) {
            MethodNode m = (MethodNode) mObj;
            Type returnType = Type.getReturnType(m.desc);
            String retInternal = getObjectInternalName(returnType.getDescriptor());
            if (retInternal != null && concreteCollections.contains(retInternal)) {
                String msg = "Method '" + m.name + "' returns concrete collection type "
                        + internalToSimple(retInternal) + "; consider using an interface.";
                issues.add(new LintIssue(getId(), className, msg, Severity.WARNING));
            }

            Type[] argTypes = Type.getArgumentTypes(m.desc);
            for (int i = 0; i < argTypes.length; i++) {
                String argInternal = getObjectInternalName(argTypes[i].getDescriptor());
                if (argInternal != null && concreteCollections.contains(argInternal)) {
                    String msg = "Method '" + m.name + "', parameter " + (i + 1)
                            + " uses concrete collection type " + internalToSimple(argInternal)
                            + "; consider using an interface.";
                    issues.add(new LintIssue(getId(), className, msg, Severity.WARNING));
                }
            }
        }

        return issues;
    }

    private String getObjectInternalName(String desc) {
        Type t = Type.getType(desc);
        if (t.getSort() == Type.OBJECT) {
            return t.getInternalName();
        }
        return null;
    }

    private String internalToSimple(String internalName) {
        int idx = internalName.lastIndexOf('/');
        return idx >= 0 ? internalName.substring(idx + 1) : internalName;
    }
}
