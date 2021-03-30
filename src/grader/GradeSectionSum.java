package grader;

import java.util.ArrayList;

public class GradeSectionSum extends GradeSection implements totalGradeMethods {

	// private String name;
//	private ArrayList<Grade> grades;

	public GradeSectionSum(String aName) {
		super(aName);
	}

	public GradeSectionSum(String aName, int initialSize) {
		super(aName, initialSize);
	}

	public GradeSectionSum(String aName, ArrayList<Grade> someGrades) {
		super(aName, someGrades);
	}

	@Override
	public double calculateTotalGrade() {
		
		double sum = 0.0;
		for (Grade aGrade : this.getGrades())
			sum += aGrade.getGradeValue(); // sum += aGrade.calculateGrade();
		return sum;
	}
}