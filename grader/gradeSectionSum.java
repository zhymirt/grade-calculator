package grader;

import java.util.ArrayList;

public class gradeSectionSum extends gradeSection {

    // private String name;
    private ArrayList<grade> grades;

    public gradeSectionSum(String aName) {
        super(aName);
    }

    public gradeSectionSum(String aName, int initialSize) {
        super(aName, initialSize);
    }

    public gradeSectionSum(String aName, ArrayList<grade> someGrades) {
        super(aName, someGrades);
    }

    public double calculateTotalGrade() {
        double sum = 0.0;
        for( grade aGrade : this.grades)
            sum += aGrade.calculateGrade();
        return sum;
    }
}