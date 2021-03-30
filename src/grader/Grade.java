package grader;

public class Grade implements GradingMethods {

	private String name;
	private double gradeValue;

	public Grade(double aGrade) {
		this.setName("aName");
		this.setGradeValue(aGrade);
	}

	public Grade(String aName, double aGrade) {
		this.setName(aName);
		this.setGradeValue(aGrade);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGradeValue() {
		return this.gradeValue;
	}

	public void setGradeValue(double gradeValue) {
		this.gradeValue = gradeValue;
	}

	@Override
	public String toString() {
		return "Name: " + this.getName() + " Grade: " + this.getGradeValue();
	}

	public String toString(boolean withName) {
		return (withName ? "Name: " + this.getName() + " " : "") + " Grade: " + this.getGradeValue();
	}

	public String fileString() {
		// name: __, grade_value: __, possible_grade: __
		return GraderConstants.G_NAME + GraderConstants.KV_SEP + this.getName() + GraderConstants.STR_SEP
				+ GraderConstants.G_VAL + GraderConstants.KV_SEP + this.getGradeValue();
	}

	public boolean equals(Grade aGrade) {
		/**
		 * Return boolean if grade objects are equal
		 * 
		 * @aGrade
		 */
		return aGrade != null &&
				this.getName().equals(aGrade.getName()) &&
				this.getGradeValue() == aGrade.getGradeValue();
	}

	@Override
	public double calculateGrade() {
		return this.getGradeValue();
	}

	@Override
	public String printString() {
		return this.fileString();
	}

}
