package linter.core;

public class LintIssue {
    private final String checkId;
    private final String className;
    private final String message;
    private final Severity severity;

    public LintIssue(String checkId, String className, String message, Severity severity) {
        this.checkId = checkId;
        this.className = className;
        this.message = message;
        this.severity = severity;
    }

    public String getCheckId() {
        return checkId;
    }

    public String getClassName() {
        return className;
    }

    public String getMessage() {
        return message;
    }

    public Severity getSeverity() {
        return severity;
    }

    @Override
    public String toString() {
        return "[" + severity + "] " + className + " (" + checkId + "): " + message;
    }
}
