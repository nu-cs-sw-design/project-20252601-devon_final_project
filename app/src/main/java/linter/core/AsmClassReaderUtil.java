package linter.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

public final class AsmClassReaderUtil {

    private AsmClassReaderUtil() {
        // utility
    }

    public static ClassNode readClass(Path path) throws IOException {
        byte[] bytes = Files.readAllBytes(path);
        ClassReader reader = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);
        return classNode;
    }
}
