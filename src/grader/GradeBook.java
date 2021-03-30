package grader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
// import java.util.Scanner;
import java.io.*;

public class GradeBook {

	private String name, gradeType;
//	private String gradeType;
	private HashMap<String, GradeSection> gradeSections;

	public GradeBook() {
		this.setName("name");
		this.setGradeType(GraderConstants.UNWEIGHTAVG);
		this.gradeSections = new HashMap<>();
	}

	public GradeBook(int initial) {
		this.setName("name");
		this.setGradeType(GraderConstants.UNWEIGHTAVG);
		this.gradeSections = initial > GraderConstants.MIN_HASH_SIZE ? new HashMap<>(initial) : new HashMap<>();
	}

	public GradeBook(String aName) {
		this.setName(aName);
		this.setGradeType(GraderConstants.UNWEIGHTAVG);
		this.gradeSections = new HashMap<>();
	}

	public GradeBook(String aName, String gradeType) {
		this.setName(aName);
		this.setGradeType(gradeType);
		this.gradeSections = new HashMap<>();
	}

	public GradeBook(String aName, String gradeType, int initialCapacity) {
		this.setName(aName);
		this.setGradeType(gradeType);
		this.gradeSections = new HashMap<>(initialCapacity);
	}

	public GradeBook(String aName, HashMap<String, GradeSection> aGradeSections) {
		this.setName(aName);
		this.setGradeType(GraderConstants.UNWEIGHTAVG);
		this.gradeSections = new HashMap<String, GradeSection>(aGradeSections);
		// this.setGradeSections(aGradeSections);
	}

	public GradeBook(String aName, String aType, HashMap<String, GradeSection> aGradeSections) {
		this.setName(aName);
		this.setGradeType(aType);
		this.gradeSections = new HashMap<String, GradeSection>(aGradeSections);
		// this.setGradeSections(aGradeSections);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, GradeSection> getGradeSections() {
		return gradeSections;
	}

	public void setGradeSections(HashMap<String, GradeSection> gradeSections) {
		this.gradeSections = gradeSections;
	}

	public String getGradeType() {
		return gradeType;
	}

	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}

	public GradeSection getGradeSection(String aName) {
		return this.gradeSections.get(aName);
	}

	public Boolean hasSection(String aName) {
		return this.gradeSections.containsKey(aName);
	}

	public void addGradeSection(GradeSection section) {
		this.gradeSections.putIfAbsent(section.getName(), section);
	}

	public void removeGradeSection(String name) {
		this.gradeSections.remove(name);
	}

	public void addGradeToSection(String name, Grade aGrade) {
		if (this.gradeSections.containsKey(name))
			this.gradeSections.get(name).addGrade(aGrade);
	}

	public void removeGradeFromSection(String name, int index) {
		if (this.gradeSections.containsKey(name))
			this.gradeSections.get(name).removeGrade(index);
	}

	public void removeGradeFromSection(String name, Grade aGrade) {
		if (this.gradeSections.containsKey(name))
			this.gradeSections.get(name).removeGrade(aGrade);
	}

	public void printBook() {
		System.out.println("GradeBook: " + this.getName());
		for (Map.Entry<String, GradeSection> entry : gradeSections.entrySet())
			System.out.println(entry.getValue().toString());
	}

	public double calculateCurrentGrade() {
		double fullGrade = 0.0;
//		switch (this.gradeType) {
//		case GraderConstants.WEIGHTAVG:
//			for (Map.Entry<String, GradeSection> entry : gradeSections.entrySet())
//				fullGrade += entry.getValue().calculateWeightedAverage();
//			break;
//		case GraderConstants.UNWEIGHTAVG:
//			for (Map.Entry<String, GradeSection> entry : gradeSections.entrySet())
//				fullGrade += entry.getValue().calculateAverage();
//			break;
//		case GraderConstants.SUM:
//			for (Map.Entry<String, GradeSection> entry : gradeSections.entrySet())
//				fullGrade += entry.getValue().calculateSum();
//			break;
//		default:
//			System.out.println("Invalid grade type");
//		}
		for (Map.Entry<String, GradeSection> entry : gradeSections.entrySet())
			fullGrade += entry.getValue().calculateTotalGrade();
		if (this.gradeType.equals(GraderConstants.WEIGHTAVG))
			return fullGrade;
		else if (this.gradeType.equals(GraderConstants.UNWEIGHTAVG))
			return fullGrade / this.getGradeSections().size();
		else if (this.gradeType.equals(GraderConstants.SUM))
			return fullGrade;
		return fullGrade;
	}

	public String printString() {
		String temp = this.fileString() + (this.getGradeSections().size() > 0 ? GraderConstants.NEW_LINE : "");
		Iterator<Entry<String, GradeSection>> itr = this.gradeSections.entrySet().iterator();
		while (itr.hasNext()) {
			GradeSection section = itr.next().getValue();
			temp += section.fileString() + (itr.hasNext() || section.getSize() > 0 ? GraderConstants.NEW_LINE : "");
			Iterator<Grade> gitr = section.getGrades().iterator();
			while (gitr.hasNext())
				temp += gitr.next().fileString() + (gitr.hasNext() || itr.hasNext() ? GraderConstants.NEW_LINE : "");
		}
		return temp;
	}

	public void writeFile(String filename) {
		if (filename == null || filename.isBlank())
			return;
		try {
			File newFile = new File(filename);
			if (this.gradeSections.size() + 1 > GraderConstants.MAX_LINES) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
				writer.write(this.fileString() + GraderConstants.NEW_LINE);
				for (Map.Entry<String, GradeSection> entry : gradeSections.entrySet()) {
					writer.write(entry.getValue().fileString() + GraderConstants.NEW_LINE);
					for (Grade g : entry.getValue().getGrades())
						writer.write(g.fileString() + GraderConstants.NEW_LINE);
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
		return GraderConstants.GB_BOOK_NAME + GraderConstants.KV_SEP + this.getName() + GraderConstants.STR_SEP
				+ GraderConstants.GB_GRADE_TYPE + GraderConstants.KV_SEP + this.getGradeType() + GraderConstants.STR_SEP
				+ GraderConstants.GB_N_SECTIONS + GraderConstants.KV_SEP + this.gradeSections.size();
	}

}