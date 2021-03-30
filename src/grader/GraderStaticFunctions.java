package grader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class GraderStaticFunctions {
	private GraderStaticFunctions() {
	}

	// Checks is line in file contains string constants
	// related to gradeBook classes
	private static Boolean isGradeBookLine(String aString) {
		return aString.contains(GraderConstants.GB_BOOK_NAME) || aString.contains(GraderConstants.GB_GRADE_TYPE)
				|| aString.contains(GraderConstants.GB_N_SECTIONS);
	}

	// Checks if line in file contains string constants
	// related to gradeSection classes
	private static Boolean isSectionLine(String aString) {
		return aString.contains(GraderConstants.GS_NAME) || aString.contains(GraderConstants.GS_N_GRADES)
				|| aString.contains(GraderConstants.GS_W);
	}

	// Checks if line in file contains string constants
	// related to grade classes
	private static Boolean isGrade(String aString) {
		return aString.contains(GraderConstants.G_NAME) || aString.contains(GraderConstants.G_POSS)
				|| aString.contains(GraderConstants.G_VAL);
	}

	// Parses line for gradebook and returns gradebook object
	private static GradeBook parseGBLine(String fileLine) {
		String[] params = fileLine.split(GraderConstants.STR_SEP);
		GradeBook tempBook;
		String gradeBook_name = "";
		String grade_type = GraderConstants.WEIGHTAVG;
		int num_sections = -1;
		for (String param : params) {
			if (param.contains(GraderConstants.GB_BOOK_NAME)) {
				gradeBook_name = param
						.substring(GraderConstants.GB_BOOK_NAME.length() + GraderConstants.KV_SEP.length());
			} else if (param.contains(GraderConstants.GB_GRADE_TYPE)) {
				grade_type = param.substring(GraderConstants.GB_GRADE_TYPE.length() + GraderConstants.KV_SEP.length());
			} else if (param.contains(GraderConstants.GB_N_SECTIONS)) {
				num_sections = Integer.parseInt(
						param.substring(GraderConstants.GB_N_SECTIONS.length() + GraderConstants.KV_SEP.length()));
			} else {
				System.out.println("Unknown key");
				System.out.println(param);
				System.exit(2);
			}
		}
		tempBook = new GradeBook(gradeBook_name, grade_type, num_sections);
		return tempBook;
	}

	// Parses line for gradesection and returns gradesection
	private static GradeSection parseGSLine(String fileLine) {
		String[] sect_params = fileLine.split(GraderConstants.STR_SEP);
		String sectName = "";
		GradeSection tempSect;
		int num_grades = 0;
		double temp_weight = -1.0;
		for (String param : sect_params) {
			if (param.contains(GraderConstants.GS_NAME)) {
				sectName = param.substring(GraderConstants.GS_NAME.length() + GraderConstants.KV_SEP.length());
			} else if (param.contains(GraderConstants.GS_W)) {
				temp_weight = Double
						.parseDouble(param.substring(GraderConstants.GS_W.length() + GraderConstants.KV_SEP.length()));
			} else if (param.contains(GraderConstants.GS_N_GRADES)) {
				num_grades = Integer.parseInt(
						param.substring(GraderConstants.GS_N_GRADES.length() + GraderConstants.KV_SEP.length()));
			} else {
				System.out.println("Unknown key");
				System.out.println(param);
				System.exit(2);
			}
		}
		if (sectName.isBlank())
			System.exit(1);
		/*
		 * if( temp_weight > -1.0 ) { if( num_grades > -1 ) tempSect = new
		 * gradeSectionWeighted(sectName, temp_weight, num_grades); else tempSect = new
		 * gradeSectionWeighted(sectName, temp_weight); } else { if( num_grades > -1 )
		 * tempSect = new gradeSection(sectName, num_grades); else tempSect = new
		 * gradeSection(sectName); }
		 */
		if (num_grades > -1)
			tempSect = new GradeSectionWeighted(sectName, temp_weight, num_grades);
		else
			tempSect = new GradeSectionWeighted(sectName, temp_weight);
		return tempSect;
	}

	// Parses line for grade and returns grade
	private static GradeFraction parseGradeLine(String fileLine) {
		String grade_name = "";
		double temp_grade, temp_poss;
		temp_grade = temp_poss = -1.0;
		String[] grade_params = fileLine.split(GraderConstants.STR_SEP);
		for (String param : grade_params) {
			if (param.contains(GraderConstants.G_NAME)) {
				grade_name = param.substring(GraderConstants.G_NAME.length() + GraderConstants.KV_SEP.length());
			} else if (param.contains(GraderConstants.G_VAL)) {
				temp_grade = Double
						.parseDouble(param.substring(GraderConstants.G_VAL.length() + GraderConstants.KV_SEP.length()));
			} else if (param.contains(GraderConstants.G_POSS)) {
				temp_poss = Double.parseDouble(
						param.substring(GraderConstants.G_POSS.length() + GraderConstants.KV_SEP.length()));
			} else {
				System.out.println("Unknown key");
				System.out.println(param);
				System.exit(2);
			}
		}
		GradeFraction new_grade = new GradeFraction(grade_name, temp_grade, temp_poss);
		return new_grade;
	}

	// Adds gradesecion returned from parsed line
	private static String addParsedGradeSection(String fileLine, GradeBook aBook) {
		/*
		 * switch(aBook.getGradeType()) { case graderConstants.WEIGHTAVG:
		 * gradeSectionWeighted tempSection; break; case graderConstants.UNWEIGHTAVG:
		 * gradeSection tempSection; break; case graderConstants.SUM:
		 * System.out.println("Not implemented yet"); break; default:
		 * System.out.println("Unknown grade type"); System.exit(-1); }
		 */
		GradeSection temp = parseGSLine(fileLine);
		aBook.addGradeSection(temp);
		return temp.getName();
	}

	// Adds grade to given grade section
	private static void addParsedGrade(String fileLine, GradeSection section) {
		section.addGrade(parseGradeLine(fileLine));
	}

	// Checks if gradebook has section and adds grade if so
	public static void addParsedGrade(String fileLine, String sectionName, GradeBook tempBook) {
		if (tempBook.hasSection(sectionName))
			tempBook.getGradeSection(sectionName).addGrade(parseGradeLine(fileLine));
	}

	// Parses gradebook save file and returns gradebook object
	public static GradeBook readFile(String filename) {
		try {
			File newFile = new File(filename);
			BufferedReader reader = new BufferedReader(new FileReader(newFile));
			String line = reader.readLine();
			if (!isGradeBookLine(line)) {
				System.out.println("Cannot find gradebook keys");
				System.exit(1);
			}
			GradeBook tempBook = parseGBLine(line);
			if (tempBook == null) {
				System.out.println("GradeBook is null");
				System.exit(1);
			}
			line = reader.readLine();
			while (line != null) {
				if (line.contains(GraderConstants.GS_NAME)) {
					// gradeSection tempSect = parseGSLine(line);
					/*
					 * if(tempSect == null ) { System.out.println("Section is null");
					 * System.exit(1); } line = reader.readLine(); while( line != null &&
					 * isGrade(line)) { grade new_grade = parseGradeLine(line);
					 * tempSect.addGrade(new_grade); line = reader.readLine(); }
					 * tempBook.addGradeSection(tempSect);
					 */
					String sectName = addParsedGradeSection(line, tempBook);
					if (!tempBook.hasSection(sectName)) {
						System.out.println("Section is null");
						System.exit(1);
					}
					line = reader.readLine();
					while (line != null && isGrade(line)) {
						addParsedGrade(line, sectName, tempBook);
						line = reader.readLine();
					}
				} else {
					System.out.println("Unexpected line: " + "\'" + line + "\'");
					line = reader.readLine();
				}
			}
			reader.close();
			return tempBook;
		} catch (IOException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// Accepts user input to create new gradebook
	// Returns gradebook object
	public static GradeBook newGradeBook() {
		Scanner keyboard = new Scanner(System.in);
		String bookName, gradeType, answer;
		GradeBook newBook;
		System.out.println("Enter a name for your gradeBook");
		bookName = keyboard.nextLine();
		System.out.println("Enter the gradebook type ('weighted', 'unweighted', 'sum')");
		gradeType = keyboard.nextLine();
		switch (gradeType) {
		case "weighted":
			gradeType = GraderConstants.WEIGHTAVG;
			break;
		case "unweighted":
			gradeType = GraderConstants.UNWEIGHTAVG;
			break;
		case "sum":
			gradeType = GraderConstants.SUM;
			break;
		default:
			System.out.println("Unknown gradebook type");
		}
		newBook = new GradeBook(bookName, gradeType);
		System.out.println("Add Sections? (y/n)");
		answer = keyboard.nextLine();
		while (answer.equals("y")) {
			String sectName, sectWeight;
			GradeSection newSect;
			System.out.println("Enter a name for the section");
			sectName = keyboard.nextLine();
			if (gradeType.equals(GraderConstants.WEIGHTAVG)) {
				System.out.println("Enter a weight");
			}
			sectWeight = !gradeType.equals(GraderConstants.WEIGHTAVG) ? "" : keyboard.nextLine();
			if (sectWeight.isBlank() || Double.isNaN(Double.parseDouble(sectWeight)))
				newSect = new GradeSection(sectName);
			else
				newSect = new GradeSectionWeighted(sectName, Double.parseDouble(sectWeight));
			System.out.println("Add grades? (y/n)");
			answer = keyboard.nextLine();
			while (answer.equals("y")) {
				String gradeName, gradeValue, possGrade;
				GradeFraction tempGrade;
				System.out.println("Enter a name");
				gradeName = keyboard.nextLine();
				System.out.println("Enter a grade value");
				gradeValue = keyboard.nextLine();
				System.out.println("Enter possible grade value");
				possGrade = keyboard.nextLine();
				if (!gradeValue.isBlank()) {
					tempGrade = new GradeFraction(Double.parseDouble(gradeValue));
					if (!gradeName.isBlank())
						tempGrade.setName(gradeName);
					if (!possGrade.isBlank())
						tempGrade.setPossibleGrade(Double.parseDouble(possGrade));
				} else {
					System.out.println("Grade was null");
					break;
				}
				System.out.println("Add new grade? (y/n)");
				answer = keyboard.nextLine();
				newSect.addGrade(tempGrade);
			}
			newBook.addGradeSection(newSect);
			System.out.println("Add new Section? (y/n)");
			answer = keyboard.nextLine();
		}
		keyboard.close();
		return newBook;
	}

	public void editGradeBook(GradeBook aBook) {
		Scanner keyboard = new Scanner(System.in);
		String answer = "";
		while (true) {
			System.out.println("What would you like to do?");
			answer = keyboard.nextLine();
			switch (answer) {
			case "help":
				break;
			case "exit":
				keyboard.close();
				break;
			case "add section":
				break;
			case "edit section":
				break;
			case "remove section":
				break;
			case "add grade":
				break;
			case "edit grade":
				break;
			case "remove grade":
				break;
			default:
				System.out.println("Invalid request");
			}
		}
	}

}