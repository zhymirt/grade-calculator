import grader.gradeBook;
import grader.gradeSection;
import grader.gradeSectionSum;
import grader.gradeSectionWeighted;
import grader.graderStaticFunctions;
import grader.grade;
// import java.io.*;

// import static grader.graderConstants.*;
// import static grader.graderStaticFunctions.*;

public class gradeMain {
    public static void main(String[] args) {
        grade tempGrade = new grade("aName", 80, 100);
        gradeSection tempSection = new gradeSection("section1");
        gradeSectionWeighted tempSection2 = new gradeSectionWeighted("section2");
        gradeSectionSum tempSection3 = new gradeSectionSum("section3");
        gradeBook tempBook = new gradeBook("book1");
        System.out.println(tempGrade.calculateGrade());
        tempSection.addGrade(tempGrade);
        tempSection.printGrades();
        tempBook.addGradeSection(tempSection);
        tempBook.addGradeSection(tempSection2);
        tempBook.addGradeSection(tempSection3);
        tempBook.printBook();
        tempBook.writeFile("test.txt");
        gradeBook tempBook2 = graderStaticFunctions.readFile("test.txt");
        tempBook2.printBook();
    }



}
// grade book
/* {
    'gradebook_name': "name",
    'grade_sections': {
        'name1': "section_name",

    }
} */
