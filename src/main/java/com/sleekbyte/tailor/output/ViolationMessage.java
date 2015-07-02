package com.sleekbyte.tailor.output;

/**
 * Note: this class has a natural ordering that is inconsistent with equals.
 */
public class ViolationMessage implements Comparable<ViolationMessage> {

    private String filePath = "";
    private int lineNumber;
    private int columnNumber;
    private String classification = "";
    private String violationMessage = "";

    /**
     * Constructs a ViolationMessage with the specified message components.
     *
     * @param lineNumber       the logical line number in the source file
     * @param columnNumber     the logical column number in the source file
     * @param classification   the classification of the violation message
     * @param violationMessage the description of the violation message
     */
    public ViolationMessage(int lineNumber, int columnNumber, String classification, String violationMessage) {
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
        this.classification = classification;
        this.violationMessage = violationMessage;
    }

    /**
     * Constructs a ViolationMessage with the specified message components.
     *
     * @param filePath         the path of the source file
     * @param lineNumber       the logical line number in the source file
     * @param columnNumber     the logical column number in the source file
     * @param classification   the classification of the violation message
     * @param violationMessage the description of the violation message
     */
    public ViolationMessage(String filePath, int lineNumber, int columnNumber, String classification,
                            String violationMessage) {
        this.filePath = filePath;
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
        this.classification = classification;
        this.violationMessage = violationMessage;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public int compareTo(final ViolationMessage message) {
        int ret = this.lineNumber - message.lineNumber;
        if (ret == 0) {
            ret = this.columnNumber - message.columnNumber;
        }
        return ret;
    }

    @Override
    /**
     * Implementation required since equals() is overridden.
     */
    public int hashCode() {
        int result = filePath.hashCode();
        result = 31 * result + lineNumber;
        result = 31 * result + columnNumber;
        result = 31 * result + classification.hashCode();
        result = 31 * result + violationMessage.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ViolationMessage)) {
            return false;
        }

        ViolationMessage candidateObject = (ViolationMessage) obj;

        // Test primitives (int) for equality first, then objects (String)
        return (this.lineNumber == candidateObject.lineNumber)
            && (this.columnNumber == candidateObject.columnNumber)
            && (this.filePath.equals(candidateObject.filePath))
            && (this.classification.equals(candidateObject.classification))
            && (this.violationMessage.equals(candidateObject.violationMessage));
    }

    @Override
    public String toString() {
        // filePath, classification, violationMessage are optional in the output, but at least one must be present
        if (this.filePath.isEmpty() && this.classification.isEmpty() && this.violationMessage.isEmpty()) {
            return "";
        }

        if (this.columnNumber == 0) {
            return String.format("%s:%d: %s: %s", this.filePath, this.lineNumber, this.classification,
                this.violationMessage);
        }
        return String.format("%s:%d:%d: %s: %s", this.filePath, this.lineNumber, this.columnNumber, this.classification,
            this.violationMessage);
    }

}
