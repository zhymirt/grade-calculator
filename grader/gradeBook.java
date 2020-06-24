package grader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
// import java.util.Scanner;
import java.io.*;

public class gradeBook {

    private String name;
    private String gradeType;
    private HashMap<String, gradeSection> gradeSections;

    public gradeBook() {
        this.setName("name");
        this.setGradeType(graderConstants.UNWEIGHTAVG);
        this.gradeSections = new HashMap<>();
    }

    public gradeBook(int initial) {
        this.setName("name");
        this.setGradeType(graderConstants.UNWEIGHTAVG);
        this.gradeSections = initial > graderConstants.MIN_HASH_SIZE ?
         new HashMap<>(initial) : new HashMap<>();
    }

    public gradeBook(String aName) {
        this.setName(aName);
        this.setGradeType(graderConstants.UNWEIGHTAVG);
        this.gradeSections = new HashMap<>();
    }

    public gradeBook(String aName, String gradeType, int initialCapacity) {
        this.setName(aName);
        this.setGradeType(gradeType);
        this.gradeSections = new HashMap<>(initialCapacity);
    }

    public gradeBook(String aName, HashMap<String, gradeSection> aGradeSections) {
        this.setName(aName);
        this.setGradeType(graderConstants.UNWEIGHTAVG);
        this.gradeSections = new HashMap<String, gradeSection>(aGradeSections);
        // this.setGradeSections(aGradeSections);
    }

    public gradeBook(String aName, String aType, HashMap<String, gradeSection> aGradeSections) {
        this.setName(aName);
        this.setGradeType(aType);
        this.gradeSections = new HashMap<String, gradeSection>(aGradeSections);
        // this.setGradeSections(aGradeSections);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, gradeSection> getGradeSections() {
        return gradeSections;
    }

    public void setGradeSections(HashMap<String, gradeSection> gradeSections) {
        this.gradeSections = gradeSections;
    }

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }

    public gradeSection getGradeSection(String aName) {
        return this.gradeSections.get(aName);
    }

    public Boolean hasSection(String aName) {
        return this.gradeSections.containsKey(aName);
    }

    public void addGradeSection(gradeSection section) {
        this.gradeSections.putIfAbsent(section.getName(), section);
    }

    public void removeGradeSection(String name) {
        this.gradeSections.remove(name);
    }

    public void addGradeToSection(String name, grade aGrade) {
        if ( this.gradeSections.containsKey(name))
            this.gradeSections.get(name).addGrade(aGrade);
    }

    public void removeGradeFromSection(String name, int index) {
        if ( this.gradeSections.containsKey(name) )
            this.gradeSections.get(name).removeGrade(index);
    }

    public void removeGradeFromSection(String name, grade aGrade) {
        if ( this.gradeSections.containsKey(name) )
            this.gradeSections.get(name).removeGrade(aGrade);
    }

    public void printBook() {
        System.out.println("GradeBook: " + this.getName());
        for (Map.Entry<String, gradeSection> entry : gradeSections.entrySet())
            System.out.println(entry.getValue().toString());
    }

    public double calculateCurrentGrade() {
        double fullGrade = 0.0;
        for (Map.Entry<String, gradeSection> entry : gradeSections.entrySet())
            fullGrade += entry.getValue().calculateTotalGrade();
        return fullGrade;
    }

    public String printString() {
        String temp = this.fileString()+(this.getGradeSections().size() > 0 ? graderConstants.NEW_LINE : "");
        Iterator<Entry<String, gradeSection>> itr = this.gradeSections.entrySet().iterator();
        while( itr.hasNext() ) {
            gradeSection section = itr.next().getValue();
            temp += section.fileString() + (itr.hasNext() || section.getSize() > 1 ? graderConstants.NEW_LINE : "");
            Iterator<grade> gitr = section.getGrades().iterator();
            while( gitr.hasNext() )
                temp += gitr.next() + (gitr.hasNext() || itr.hasNext() ? graderConstants.NEW_LINE : "");
        }
        return temp;
    }

    public void writeFile(String filename) {
        if (filename == null || filename.isBlank())
            return;
        try {
            File newFile = new File(filename);
            if ( this.gradeSections.size() + 1 > graderConstants.MAX_LINES ) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
                writer.write(this.fileString()+graderConstants.NEW_LINE);
                for(Map.Entry<String, gradeSection> entry: gradeSections.entrySet()) {
                    writer.write(entry.getValue().fileString()+graderConstants.NEW_LINE);
                    for(grade g: entry.getValue().getGrades())
                        writer.write(g.fileString()+graderConstants.NEW_LINE);
                }
                writer.close();
            } else {
                FileWriter writer = new FileWriter(newFile);
                writer.write(this.printString());
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String fileString() {
        // GradeBook "name: __, grade_type: __, num_sections: __
        return graderConstants.GB_BOOK_NAME + graderConstants.KV_SEP + this.getName() + graderConstants.STR_SEP
                + graderConstants.GB_GRADE_TYPE + graderConstants.KV_SEP + this.getGradeType() + graderConstants.STR_SEP
                + graderConstants.GB_N_SECTIONS + graderConstants.KV_SEP + this.gradeSections.size();
    }

}