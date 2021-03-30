package grader;

//TODO finish documenting

/**
 * @author zhymir thompson
 *
 */
public class GradeFraction extends Grade implements GradingMethods {
	/**
	 * Grade Class Has name, grade value, and possible grade value
	 */
//	private String name;
//	private double gradeValue;
	private double possibleGrade;

	public GradeFraction(double aGradeValue) {
		super(aGradeValue);
//		this.setName("aName");
//		this.setGradeValue(aGradeValue);
		this.setPossibleGrade(100.0);
	}

	public GradeFraction(String aName, double aGradeValue) {
		super(aName, aGradeValue);
//		this.setName("aName");
//		this.setGradeValue(aGradeValue);
		this.setPossibleGrade(100.0);
	}

	public GradeFraction(double aGradeValue, double possible) {
		super(aGradeValue);
//		this.setName("aName");
//		this.setGradeValue(aGradeValue);
		this.setPossibleGrade(possible);
	}

	public GradeFraction(String aName, double aGradeValue, double possible) {
		super(aName, aGradeValue);
//		this.setName(aName);
//		this.setGradeValue(aGradeValue);
		this.setPossibleGrade(possible);
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public double getGradeValue() {
//		return gradeValue;
//	}
//
//	public void setGradeValue(double gradeValue) {
//		this.gradeValue = gradeValue;
//	}

	public double getPossibleGrade() {
		return possibleGrade;
	}

	public void setPossibleGrade(double possibleGrade) {
		this.possibleGrade = possibleGrade;
	}

	public double maxGrade() {
		/**
		 * Return best possible value for given grade object
		 */
		return this.getPossibleGrade();
	}

	@Override
	public String toString() {
		return super.toString() + " Possible Grade: " + this.getPossibleGrade();
//		return "Name: "+this.getName()+" Grade: "+this.getGradeValue();
	}

	@Override
	public String toString(boolean withName) {
		return super.toString(withName) + " Possible Grade: " + this.getPossibleGrade();
//		return (withName ? "Name: "+this.getName()+" " : "")+" Grade: "+this.getGradeValue();
	}

	@Override
	public String fileString() {
		// name: __, grade_value: __, possible_grade: __
		return super.fileString() + GraderConstants.STR_SEP + GraderConstants.G_POSS + GraderConstants.KV_SEP
				+ this.getPossibleGrade();
//		return GraderConstants.G_NAME + GraderConstants.KV_SEP + this.getName() + GraderConstants.STR_SEP
//				+ GraderConstants.G_VAL + GraderConstants.KV_SEP + this.getGradeValue() + GraderConstants.STR_SEP
//				+ GraderConstants.G_POSS + GraderConstants.KV_SEP + this.getPossibleGrade();
	}

	@Override
	public boolean equals(Grade aGrade) {
		/**
		 * Return boolean if grade objects are equal
		 * 
		 * @aGrade
		 */
		return super.equals(aGrade) &&
				this.getClass() == aGrade.getClass() &&
				this.getPossibleGrade() == ((GradeFraction) aGrade).getPossibleGrade();
//		return this.getName().equals(aGrade.getName()) &&
//				this.getGradeValue() == aGrade.getGradeValue() &&
//				this.getPossibleGrade() == aGrade.getPossibleGrade();
	}

	@Override
	public double calculateGrade() {
		return this.getGradeValue() / this.getPossibleGrade();
	}

//	@Override
//	public String printString() {
//		super.printString();
////		return this.fileString();
//	}

}