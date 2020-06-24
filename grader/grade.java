package grader;

public class grade implements gradingMethods {

    private String name;
    private double gradeValue;
    private double possibleGrade;

    public grade(double aGradeValue) {
        this.setName("aName");
        this.setGradeValue(aGradeValue);
        this.setPossibleGrade(100.0);
    }

    public grade(String aName, double aGradeValue) {
        this.setName("aName");
        this.setGradeValue(aGradeValue);
        this.setPossibleGrade(100.0);
    }

    public grade(double aGradeValue, double possible) {
        this.setName("aName");
        this.setGradeValue(aGradeValue);
        this.setPossibleGrade(possible);
    }

    public grade(String aName, double aGradeValue, double possible) {
        this.setName("aName");
        this.setGradeValue(aGradeValue);
        this.setPossibleGrade(possible);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(double gradeValue) {
        this.gradeValue = gradeValue;
    }

    public double getPossibleGrade() {
        return possibleGrade;
    }

    public void setPossibleGrade(double possibleGrade) {
        this.possibleGrade = possibleGrade;
    }

    public double maxGrade() {
        return this.getPossibleGrade();
    }

    public String toString() {
        return "Name: "+this.getName()+" Grade: "+this.getGradeValue();
    }

    public String toString(boolean withName) {
        return (withName ? "Name: "+this.getName()+" " : "")+" Grade: "+this.getGradeValue();
    }

    public String fileString() {
        // name: __, grade_value: __, possible_grade: __
        return graderConstants.G_NAME + graderConstants.KV_SEP + this.getName() + graderConstants.STR_SEP
        + graderConstants.G_VAL + graderConstants.KV_SEP + this.getGradeValue() + graderConstants.STR_SEP
        + graderConstants.G_POSS + graderConstants.KV_SEP + this.getPossibleGrade();
    }
    public boolean equals(grade aGrade) {
        return this.getName().equals(aGrade.getName()) && this.getGradeValue()==aGrade.getGradeValue();
    }

    public double calculateGrade() {
        return this.getGradeValue()/this.getPossibleGrade();
    }

    public String printString() {
        return this.toString();
    }

}