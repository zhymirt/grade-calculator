package main;

import grader.GradeBook;
import grader.GradeFraction;
import grader.GradeSection;
import grader.GradeSectionSum;
import grader.GradeSectionWeighted;
import grader.GraderStaticFunctions;

import java.util.Scanner;

//import grader.GradeFraction;
// import java.io.*;

// import static grader.graderConstants.*;
// import static grader.graderStaticFunctions.*;

public class gradeMain {
	public static void main(String[] args) {
		GradeFraction tempGrade = new GradeFraction("aName", 80, 100);
		GradeSection tempSection = new GradeSection("section1");
		GradeSectionWeighted tempSection2 = new GradeSectionWeighted("section2");
		GradeSectionSum tempSection3 = new GradeSectionSum("section3");
		GradeBook tempBook = new GradeBook("book1");
		System.out.println(tempGrade.calculateGrade());
		tempSection.addGrade(tempGrade);
		tempSection.printGrades();
		tempBook.addGradeSection(tempSection);
		tempBook.addGradeSection(tempSection2);
		tempBook.addGradeSection(tempSection3);
		tempBook.printBook();
		tempBook.writeFile("test.txt");
		GradeBook tempBook2 = GraderStaticFunctions.readFile("test.txt");
		tempBook2.printBook();
		GradeBook tempBook3 = GraderStaticFunctions.newGradeBook();
		tempBook3.printBook();
		Scanner keyboard = new Scanner(System.in);
		GradeBook mainBook = null;
		while (true) {
			System.out.println("Enter a command");
			String command = keyboard.nextLine();
			switch (command) {
			case "New Grade":
				mainBook = GraderStaticFunctions.newGradeBook();
				break;
			case "Load":
				System.out.println("Enter the filename");
				mainBook = GraderStaticFunctions.readFile(keyboard.nextLine());
				break;
			case "Save":
				System.out.println("Enter the filename to save");
				if (mainBook != null)
					mainBook.writeFile(keyboard.nextLine());
				break;
			case "Exit":
				keyboard.close();
				System.exit(0);
				break;
			default:
				System.out.println("Invalid command");
			}
		}
		// keyboard.close();
	}

}
// grade book
/*
 * { 'gradebook_name': "name", 'grade_sections': { 'name1': "section_name",
 * 
 * } }
 */
