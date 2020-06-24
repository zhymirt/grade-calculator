package grader;

import java.util.ArrayList;
import java.util.Iterator;

public class gradeSectionWeighted extends gradeSection {

    // private String name;
    private double weight;
    private ArrayList<grade> grades;

    public gradeSectionWeighted(String aName) {
        super(aName);
        // this.setName(aName);
        this.grades = new ArrayList<grade>();
        this.weight = 1.0;
    }
    public gradeSectionWeighted(String aName, int initialSize) {
        super(aName, initialSize);
        // this.setName(aName);
        this.grades = initialSize > graderConstants.MIN_ARR_SIZE ?
         new ArrayList<grade>(initialSize) : new ArrayList<grade>();
        this.weight = 1.0;
    }
    public gradeSectionWeighted(String aName, ArrayList<grade> aGrades) {
        super(aName, aGrades);
        // this.setName(aName);
        this.setGrades(aGrades);
        this.weight = 1.0;
    }
    public gradeSectionWeighted(String aName, double aWeight) {
        super(aName);
        // this.setName(aName);
        this.grades = new ArrayList<grade>();
        this.setWeight(aWeight);
    }
    public gradeSectionWeighted(String aName, double aWeight, int initialSize) {
        super(aName, initialSize);
        // this.setName(aName);
        this.grades = new ArrayList<grade>(initialSize);
        this.setWeight(aWeight);
    }
    public gradeSectionWeighted(String aName, ArrayList<grade> aGrades, double aWeight) {
        super(aName, aGrades);
        // this.setName(aName);
        // this.setGrades(aGrades);
        this.setWeight(aWeight);
    }

    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    public ArrayList<grade> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<grade> grades) {
        this.grades = grades;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSize() {
        return this.grades.size();
    }

    public grade getGrade(int index) {
        return  index > -1 && index < this.getSize() ? this.grades.get(index) : null;
    }

    public void addGrade(grade aGrade) {
        if(this.grades == null ) {
            System.out.println("Grade array is null");
            return;
        }
        this.grades.add(aGrade);
    }

    public void removeGrade(int index) {
        this.grades.remove(index);
    }

    public void removeGrade(grade aGrade) {
        this.grades.remove(aGrade);
    }

    public void printGrades() {
        for(grade g: this.grades)
            System.out.println(g.toString());
    }

    public String toString() {
        String temp = "Name: "+this.getName()+" Weight: "+this.getWeight()+" Grades: ";
        Iterator<grade> itr = this.grades.iterator();
        while( itr.hasNext() ) {
            temp += itr.next().toString();
            temp += itr.hasNext() ? graderConstants.LIST_DELIM : "";
        }
        /*
        int index = 0;
        int size = grades.size() - 1;
        for(int i = index; i < size + 1; ++i )
            temp += g[i].toString()+","+( i < size ? " " : "");
        */
        return temp;
    }

    public String fileString() {
        // name: __, weight: __, num_grades: __
        return graderConstants.GS_NAME + graderConstants.KV_SEP + this.getName() + graderConstants.STR_SEP
        + graderConstants.GS_W + graderConstants.KV_SEP + this.getWeight() + graderConstants.STR_SEP
        + graderConstants.GS_N_GRADES + graderConstants.KV_SEP + this.getSize();
    }

    public String printString() {
        String temp = graderConstants.LIST_PREFIX;
        Iterator<grade> itr = this.grades.iterator();
        while( itr.hasNext() ) {
            temp += itr.next().printString();
            temp += itr.hasNext() ? graderConstants.LIST_DELIM : graderConstants.LIST_POSTFIX;
        }
        return temp;
    }

    public double calculateAverage() {
        if(this.getSize() == 0)
            return 0.0;
        double average = 0.0;
        for(grade g : grades)
            average += g.calculateGrade();
        return average/this.getSize();
    }

    public double calculateWeightedAverage() {
        return this.calculateAverage()*this.getWeight();
    }

    public double calculateTotalGrade() {
        return this.calculateAverage();
    }

}