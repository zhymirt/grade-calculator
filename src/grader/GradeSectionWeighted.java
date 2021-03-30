package grader;

import java.util.ArrayList;
import java.util.Iterator;

public class GradeSectionWeighted extends GradeSection implements totalGradeMethods {

	// private String name;
	private double weight;
//    private ArrayList<Grade> gradeFractions;

	public GradeSectionWeighted(String aName) {
		super(aName);
		// this.setName(aName);
//        this.gradeFractions = new ArrayList<Grade>();
		this.weight = 1.0;
	}

	public GradeSectionWeighted(String aName, int initialSize) {
		super(aName, initialSize);
		// this.setName(aName);
//        this.gradeFractions = initialSize > GraderConstants.MIN_ARR_SIZE ?
//         new ArrayList<Grade>(initialSize) : new ArrayList<Grade>();
		this.weight = 1.0;
	}

	public GradeSectionWeighted(String aName, ArrayList<Grade> aGrades) {
		super(aName, aGrades);
		// this.setName(aName);
		this.setGrades(aGrades);
		this.weight = 1.0;
	}

	public GradeSectionWeighted(String aName, double aWeight) {
		super(aName);
		// this.setName(aName);
//        this.gradeFractions = new ArrayList<Grade>();
		this.setWeight(aWeight);
	}

	public GradeSectionWeighted(String aName, double aWeight, int initialSize) {
		super(aName, initialSize);
		// this.setName(aName);
//        this.gradeFractions = new ArrayList<Grade>(initialSize);
		this.setWeight(aWeight);
	}

	public GradeSectionWeighted(String aName, ArrayList<Grade> aGrades, double aWeight) {
		super(aName, aGrades);
		// this.setName(aName);
		// this.setGrades(aGrades);
		this.setWeight(aWeight);
	}

	// public String getName() {
	// return name;
	// }

	// public void setName(String name) {
	// this.name = name;
	// }

//    public ArrayList<Grade> getGrades() {
//        return gradeFractions;
//    }
//
//    public void setGrades(ArrayList<Grade> gradeFractions) {
//        this.gradeFractions = gradeFractions;
//    }

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

//    public int getSize() {
//        return this.gradeFractions.size();
//    }
//
//    public Grade getGrade(int index) {
//        return  index > -1 && index < this.getSize() ? this.gradeFractions.get(index) : null;
//    }
//
//    public void addGrade(Grade aGrade) {
//        if(this.gradeFractions == null ) {
//            System.out.println("Grade array is null");
//            return;
//        }
//        this.gradeFractions.add(aGrade);
//    }
//
//    public void removeGrade(int index) {
//        this.gradeFractions.remove(index);
//    }
//
//    public void removeGrade(Grade aGrade) {
//        this.gradeFractions.remove(aGrade);
//    }
//
//    public void printGrades() {
//        for(Grade g: this.gradeFractions)
//            System.out.println(g.toString());
//    }

	@Override
	public String toString() {
		String temp = "Name: " + this.getName() + " Weight: " + this.getWeight() + " Grades: ";
		Iterator<Grade> itr = this.getGrades().iterator();
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

	@Override
	public String fileString() {
		// name: __, weight: __, num_grades: __
		return GraderConstants.GS_NAME + GraderConstants.KV_SEP + this.getName() + GraderConstants.STR_SEP
				+ GraderConstants.GS_W + GraderConstants.KV_SEP + this.getWeight() + GraderConstants.STR_SEP
				+ GraderConstants.GS_N_GRADES + GraderConstants.KV_SEP + this.getSize();
	}

	@Override
	public String printString() {
		String temp = GraderConstants.LIST_PREFIX;
		Iterator<Grade> itr = this.getGrades().iterator();
		while (itr.hasNext()) {
			temp += itr.next().printString();
			temp += itr.hasNext() ? GraderConstants.LIST_DELIM : GraderConstants.LIST_POSTFIX;
		}
		return temp;
	}

//    public double calculateAverage() {
//        if(this.getSize() == 0)
//            return 0.0;
//        double average = 0.0;
//        for(Grade g : gradeFractions)
//            average += g.calculateGrade();
//        return average/this.getSize();
//    }
//
//    public double calculateWeightedAverage() {
//        return this.calculateAverage()*this.getWeight();
//    }

	@Override
	public double calculateTotalGrade() {
		return super.calculateTotalGrade() * this.getWeight();
//    	if(this.getSize() == 0)
//    		return 0.0;
//    	double average = 0.0;
//    	for(Grade g : gradeFractions)
//    		average += g.calculateGrade();
//    	return this.getWeight() * average / this.getSize();
//    	return average/this.getSize();
//        return this.calculateAverage();
	}

}