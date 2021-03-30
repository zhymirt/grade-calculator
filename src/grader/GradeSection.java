package grader;

import java.util.ArrayList;
import java.util.Iterator;

public class GradeSection implements totalGradeMethods {

	private String name;
//    private double weight;
	private ArrayList<Grade> grades;

	public GradeSection(String aName) {
		this.setName(aName);
		this.grades = new ArrayList<Grade>();
//        this.weight = 1.0;
	}

	public GradeSection(String aName, int initialSize) {
		this.setName(aName);
		this.grades = initialSize > GraderConstants.MIN_ARR_SIZE ? new ArrayList<Grade>(initialSize)
				: new ArrayList<Grade>();
//        this.weight = 1.0;
	}

	public GradeSection(String aName, ArrayList<Grade> aGrades) {
		this.setName(aName);
		this.setGrades(aGrades);
//        this.weight = 1.0;
	}

//	public GradeSection(String aName/* , double aWeight */) {
//        this.setName(aName);
//        this.grades = new ArrayList<Grade>();
////        this.setWeight(aWeight);
//    }

//	public GradeSection(String aName, /* double aWeight, */ int initialSize) {
//        this.setName(aName);
//        this.grades = new ArrayList<Grade>(initialSize);
////        this.setWeight(aWeight);
//    }
//    public GradeSection(String aName, ArrayList<Grade> aGrades, double aWeight) {
//        this.setName(aName);
//        this.setGrades(aGrades);
////        this.setWeight(aWeight);
//    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Grade> getGrades() {
		return grades;
	}

	public void setGrades(ArrayList<Grade> grades) {
		this.grades = grades;
	}
//
//    public double getWeight() {
//        return weight;
//    }
//
//    public void setWeight(double weight) {
//        this.weight = weight;
//    }

	public int getSize() {
		return this.grades.size();
	}

	public Grade getGrade(int index) {
		return index > -1 && index < this.getSize() ? this.grades.get(index) : null;
	}

	public void addGrade(Grade aGrade) {
		if (this.grades == null) {
			System.out.println("Grade array is null");
			return;
		}
		this.grades.add(aGrade);
	}

	public void removeGrade(int index) {
		this.grades.remove(index);
	}

	public void removeGrade(Grade aGrade) {
		this.grades.remove(aGrade);
	}

	public void printGrades() {
		for (Grade g : this.grades)
			System.out.println(g.toString());
	}

	@Override
	public String toString() {
		String temp = "Name: " + this.getName() + /* " Weight: "+this.getWeight()+ */" Grades: ";
		Iterator<Grade> itr = this.grades.iterator();
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
//        + GraderConstants.GS_W + GraderConstants.KV_SEP + this.getWeight() + GraderConstants.STR_SEP
				+ GraderConstants.GS_N_GRADES + GraderConstants.KV_SEP + this.getSize();
	}

	public String printString() {
		String temp = GraderConstants.LIST_PREFIX;
		Iterator<Grade> itr = this.grades.iterator();
		while (itr.hasNext()) {
			temp += itr.next().printString();
			temp += itr.hasNext() ? GraderConstants.LIST_DELIM : GraderConstants.LIST_POSTFIX;
		}
		return temp;
	}

//    public double calculateAverage() {
//    	double sum = 0.0;
//    	for (Grade g : this.getGrades())
//    		sum += g.calculateGrade();
//    	return this.getSize() > 0 ? sum / this.getSize() : 0.0;
////        return this.getSize() > 0 ? this.calculateSum() / this.getSize() : 0.0;
//    }

//    public double calculateSum() {
//        double sum = 0.0;
//        for ( Grade g : grades)
//            sum += g.getGradeValue(); // g.calculateGrade();
//        return sum;
//    }

//	public double calculateWeightedAverage() {
//    	return this.calculateAverage();
////        return this.calculateAverage() * this.getWeight();
//    }
	@Override
	public double calculateTotalGrade() {
		// TODO Auto-generated method stub
		double sum = 0.0;
		for (Grade g : this.getGrades())
			sum += g.calculateGrade();
		return this.getSize() > 0 ? sum / this.getSize() : 0.0;
	}
	
	public double calculateAverageForDesired(double desiredGrade, int numFutureGrades) {
		return ((this.getSize() + numFutureGrades) * desiredGrade) - (this.calculateTotalGrade() / this.getSize());
	}

}