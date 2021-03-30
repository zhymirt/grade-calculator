package grader;

import java.util.ArrayList;
import java.util.Iterator;

public class GradeSectionOld implements totalGradeMethods {

	private String name;
	private ArrayList<GradeFraction> gradeFractions;

	public GradeSectionOld(String name) {
		this.setName(name);
		this.gradeFractions = new ArrayList<>();
	}

	public GradeSectionOld(String name, int initialCapacity) {
		this.setName(name);
		this.gradeFractions = initialCapacity > GraderConstants.MIN_ARR_SIZE ? new ArrayList<>(initialCapacity)
				: new ArrayList<>();
	}

	public GradeSectionOld(String name, ArrayList<GradeFraction> gradeFractions) {
		this.setName(name);
		this.setGrades(gradeFractions);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<GradeFraction> getGrades() {
		return gradeFractions;
	}

	public void setGrades(ArrayList<GradeFraction> gradeFractions) {
		this.gradeFractions = gradeFractions;
	}

	public int getSize() {
		return this.gradeFractions.size();
	}

	public GradeFraction getGrade(int index) {
		return index > -1 && index < this.getSize() ? this.gradeFractions.get(index) : null;
	}

	public void addGrade(GradeFraction aGrade) {
		if (aGrade != null)
			this.gradeFractions.add(aGrade);
	}

	public void removeGrade(int index) {
		if (index > 0 && index < this.getSize())
			this.gradeFractions.remove(index);
	}

	public void removeGrade(GradeFraction aGrade) {
		if (aGrade != null)
			this.gradeFractions.remove(aGrade);
	}

	public void printGrades() {
		for (GradeFraction g : this.gradeFractions)
			System.out.println(g.toString());
	}

	public String toString() {
		String temp = "Name: " + this.getName() + " Grades: ";
		Iterator<GradeFraction> itr = this.gradeFractions.iterator();
		while (itr.hasNext()) {
			temp += itr.next().toString();
			temp += itr.hasNext() ? GraderConstants.LIST_DELIM : "";
		}
		/*
		 * int index = 0; int size = grades.size() - 1; for(int i = index; i < size + 1;
		 * ++i ) temp += g[i].toString()+","+( i < size ? " " : "");
		 */
		return temp;
	}

	public String fileString() {
		// name: __, weight: __, num_grades: __
		return GraderConstants.GS_NAME + GraderConstants.KV_SEP + this.getName() + GraderConstants.STR_SEP
				+ GraderConstants.GS_N_GRADES + GraderConstants.KV_SEP + this.getSize();
	}

	public String printString() {
		String temp = GraderConstants.LIST_PREFIX;
		Iterator<GradeFraction> itr = this.gradeFractions.iterator();
		while (itr.hasNext()) {
			temp += itr.next().printString();
			temp += itr.hasNext() ? GraderConstants.LIST_DELIM : GraderConstants.LIST_POSTFIX;
		}
		return temp;
	}

	public double calculateAverage() {
		if (this.getSize() == 0)
			return 0.0;
		double average = 0.0;
		for (GradeFraction g : gradeFractions)
			average += g.calculateGrade();
		return average / this.getSize();
	}

	public double calculateTotalGrade() {
		return this.calculateAverage();
	}

	public double calculateMaxFutureGrade(int numGrades) {
		if (this.getSize() == 0 && numGrades == 0)
			return 0.0;
		double average = 0.0;
		for (GradeFraction g : gradeFractions)
			average += g.calculateGrade();
		for (int i = 0; i < numGrades; ++i)
			average += 1;
		return average / (this.getSize() + numGrades);
	}
}