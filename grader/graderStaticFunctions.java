package grader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
// import static grader.graderConstants.*;
public class graderStaticFunctions {
    private graderStaticFunctions(){}
    
    // Checks is line in file contains string constants
    // related to gradeBook classes
    public static Boolean isGradeBookLine(String aString) {
        return aString.contains(graderConstants.GB_BOOK_NAME) ||
        aString.contains(graderConstants.GB_GRADE_TYPE) ||
        aString.contains(graderConstants.GB_N_SECTIONS);
    }

    // Checks if line in file contains string constants
    // related to gradeSection classes
    public static Boolean isSectionLine(String aString) {
        return aString.contains(graderConstants.GS_NAME) ||
        aString.contains(graderConstants.GS_N_GRADES) ||
        aString.contains(graderConstants.GS_W);
    }

    // Checks if line in file contains string constants
    // related to grade classes
    public static Boolean isGrade(String aString) {
        return aString.contains(graderConstants.G_NAME) ||
        aString.contains(graderConstants.G_POSS) ||
        aString.contains(graderConstants.G_VAL);
    }

    public static gradeBook parseGBLine(String fileLine) {
        String[] params = fileLine.split(graderConstants.STR_SEP);
        gradeBook tempBook;
        String gradeBook_name = "";
        String grade_type = graderConstants.WEIGHTAVG;
        int num_sections = -1;
        for(String param: params) {
            if(param.contains(graderConstants.GB_BOOK_NAME)) {
                gradeBook_name = param.substring(graderConstants.GB_BOOK_NAME.length()+graderConstants.KV_SEP.length());
            } else if(param.contains(graderConstants.GB_GRADE_TYPE)) {
                grade_type = param.substring(graderConstants.GB_GRADE_TYPE.length()+graderConstants.KV_SEP.length());
            } else if(param.contains(graderConstants.GB_N_SECTIONS)) {
                num_sections = Integer.parseInt(param.substring(graderConstants.GB_N_SECTIONS.length()+graderConstants.KV_SEP.length()));
            } else {
                System.out.println("Unknown key");
                System.out.println(param);
                System.exit(2);
            }
        }
        tempBook = new gradeBook(gradeBook_name, grade_type, num_sections);
        return tempBook;
    }

    public static gradeSection parseGSLine(String fileLine) {
        String[] sect_params = fileLine.split(graderConstants.STR_SEP);
        String sectName = "";
        gradeSection tempSect;
        int num_grades = 0;
        double temp_weight = -1.0;
        for(String param: sect_params) {
            if(param.contains(graderConstants.GS_NAME)) {
                sectName = param.substring(graderConstants.GS_NAME.length()+graderConstants.KV_SEP.length());
            } else if(param.contains(graderConstants.GS_W)) {
                temp_weight = Double.parseDouble(param.substring(graderConstants.GS_W.length()+graderConstants.KV_SEP.length()));
            } else if(param.contains(graderConstants.GS_N_GRADES)) {
                num_grades = Integer.parseInt(param.substring(graderConstants.GS_N_GRADES.length()+graderConstants.KV_SEP.length()));
            } else {
                System.out.println("Unknown key");
                System.out.println(param);
                System.exit(2);
            }
        }
        if( sectName.isBlank() )
            System.exit(1);
        if( temp_weight > -1.0 ) {
            if( num_grades > -1 )
                tempSect = new gradeSectionWeighted(sectName, temp_weight, num_grades);
            else
                tempSect = new gradeSectionWeighted(sectName, temp_weight);
        } else {
            if( num_grades > -1 )
                tempSect = new gradeSection(sectName, num_grades);
            else
                tempSect = new gradeSection(sectName);
        }
        return tempSect;
    }

    public static grade parseGradeLine(String fileLine) {
        String grade_name = "";
        double temp_grade, temp_poss;
        temp_grade = temp_poss = -1.0;
        String[] grade_params = fileLine.split(graderConstants.STR_SEP);
        for(String param: grade_params) {
            if(param.contains(graderConstants.G_NAME)) {
                grade_name = param.substring(graderConstants.G_NAME.length()+graderConstants.KV_SEP.length());
            } else if(param.contains(graderConstants.G_VAL)) {
                temp_grade = Double.parseDouble(param.substring(graderConstants.G_VAL.length()+graderConstants.KV_SEP.length()));
            } else if(param.contains(graderConstants.G_POSS)) {
                temp_poss = Double.parseDouble(param.substring(graderConstants.G_POSS.length()+graderConstants.KV_SEP.length()));
            } else {
                System.out.println("Unknown key");
                System.out.println(param);
                System.exit(2);
            }
        }
        grade new_grade = new grade(grade_name, temp_grade, temp_poss);
        return new_grade;
    }

    public static String addParsedGradeSection(String fileLine, gradeBook aBook) {
        /* switch(aBook.getGradeType()) {
            case graderConstants.WEIGHTAVG:
                gradeSectionWeighted tempSection;
                break;
            case graderConstants.UNWEIGHTAVG:
                gradeSection tempSection;
                break;
            case graderConstants.SUM:
                System.out.println("Not implemented yet");
                break;
            default:
                System.out.println("Unknown grade type");
                System.exit(-1);
        } */
        gradeSection temp = parseGSLine(fileLine);
        aBook.addGradeSection(temp);
        return temp.getName();
    }

    public static void addParsedGrade(String fileLine, gradeSection section) {
        section.addGrade(parseGradeLine(fileLine));
    }

    public static void addParsedGrade(String fileLine, String sectionName, gradeBook tempBook) {
        if( !tempBook.hasSection(sectionName))
            return;
        tempBook.getGradeSection(sectionName).addGrade(parseGradeLine(fileLine));
    }

    public static gradeBook readFile(String filename) {
        try {
            File newFile = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(newFile));
            String line = reader.readLine();
            if( !isGradeBookLine(line)) {
                System.out.println("Cannot find gradebook keys");
                System.exit(1);
            }
            gradeBook tempBook = parseGBLine(line);
            if ( tempBook == null ) {
                System.out.println("GradeBook is null");
                System.exit(1);
            }
            line = reader.readLine();
            while ( line != null ) {
                if ( line.contains(graderConstants.GS_NAME) ) {
                    // gradeSection tempSect = parseGSLine(line);
                    /* if(tempSect == null ) {
                        System.out.println("Section is null");
                        System.exit(1);
                    }
                    line = reader.readLine();
                    while( line != null && isGrade(line)) {
                        grade new_grade = parseGradeLine(line);
                        tempSect.addGrade(new_grade);
                        line = reader.readLine();
                    }
                    tempBook.addGradeSection(tempSect); */
                    String sectName = addParsedGradeSection(line, tempBook);
                    if ( !tempBook.hasSection(sectName) ) {
                        System.out.println("Section is null");
                        System.exit(1);
                    }
                    line = reader.readLine();
                    while ( line != null && isGrade(line) ) {
                        addParsedGrade(line, sectName, tempBook);
                        line = reader.readLine();
                    }
                } else {
                    System.out.println("Unexpected line: " + "\'"+line+"\'");
                    line = reader.readLine();
                }
            }
            reader.close();
            return tempBook;
        } catch(IOException e) {
            System.out.println(e);
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }
}