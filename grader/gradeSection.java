package grader;

import java.util.ArrayList;
import java.util.Iterator;

public class gradeSection implements totalGradeMethods {

    private String name;
    private ArrayList<grade> grades;

    public gradeSection(String name) {
        this.setName(name);
        this.grades = new ArrayList<>();
    }

    public gradeSection(String name, int initialCapacity) {
        this.setName(name);
        this.grades =  initialCapacity > graderConstants.MIN_ARR_SIZE ?
         new ArrayList<>(initialCapacity) : new ArrayList<>();
    }
    public gradeSection(String name, ArrayList<grade> grades) {
        this.setName(name);
        this.setGrades(grades);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<grade> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<grade> grades) {
        this.grades = grades;
    }

    public int getSize() {
        return this.grades.size();
    }

    public grade getGrade(int index) {
        return  index > -1 && index < this.getSize() ? this.grades.get(index) : null;
    }

    public void addGrade(grade aGrade) {
        if(aGrade != null)
            this.grades.add(aGrade);
    }

    public void removeGrade(int index) {
        if( index > 0 && index < this.getSize() )
            this.grades.remove(index);
    }

    public void removeGrade(grade aGrade) {
        if( aGrade != null )
            this.grades.remove(aGrade);
    }

    public void printGrades() {
        for(grade g: this.grades)
            System.out.println(g.toString());
    }

    public String toString() {
        String temp = "Name: "+this.getName()+" Grades: ";
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

    public double calculateTotalGrade() {
        return this.calculateAverage();
    }
    
    public double calculateMaxFutureGrade(int numGrades) {
        if(this.getSize() == 0 && numGrades == 0)
            return 0.0;
        double average = 0.0;
        for(grade g : grades)
            average += g.calculateGrade();
        for( int i = 0 ; i < numGrades ; ++i )
            average += 1;
        return average/(this.getSize()+numGrades);
    }
}