package biz.letsweb.fulljar.domain;

/**
 *
 * @author Tomasz
 */
public enum ActivityEnum {

    LEARNING("Research required to be undertaken in order to deliver optimal solution."),
    CODING("Implementation of a design through entering code."),
    ANALYSIS("Analysis of a problem in order to build understanding of its structure."),
    DESIGN("Designing a solution."),
    TESTING("Testing implemented code."),
    ADMIN("Administrative tasks."),
    MEETING("Meeting.");
    private String desc;

    private ActivityEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
    
}